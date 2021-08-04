pragma solidity ^0.6.0;

contract FirstChain{
    
    struct LogisticNode {
        uint[] nodeGoods;          // ID of Goods a node possess
    }
    
    struct Good {
        string goodName;            // Good Name
        uint goodPrice;              // Good price
        bool isBought;               // Whether the good is isBought
        string releaseTime;            // Good release time
        address payable owner;               // Goods' owner
        address[] transferProcess;   // Addresses the good is passed by
    }
    
    uint currentNodeAddress=0;
    uint currentGoodID=0;
    mapping(address => LogisticNode) allLogisticNodes;    // All the nodes
    mapping(uint => Good) goods;                  // All the goods
    mapping(address => string) private nodesPassword;   // Password of the node
    
    address[] defaultArray;
    
    function stringComparison(string memory str1, string memory str2) public pure returns(bool) {
        bytes memory a = bytes(str1);
        bytes memory b = bytes(str2);
        if (a.length == b.length){
            for (uint i=0; i<a.length; i++){
                if (a[i] != b[i]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    event RegisterNode(address nodeAddress, bool isSuccess, string message); 
    
    function registerNode(string memory _nodePassword) public returns(address) {
        currentNodeAddress++;
        address payable _nodeAddress = address(currentNodeAddress);
        uint[] memory defaultArray;
        LogisticNode memory newNode = LogisticNode(defaultArray);
        nodesPassword[_nodeAddress] = _nodePassword;
        allLogisticNodes[_nodeAddress] = newNode;

        emit RegisterNode(_nodeAddress, true, "Register Success!");
        return _nodeAddress;
    }
    
    // Node publish goods
    event NodePublishGood(address _nodeAddress, bool isSuccess, string message);
    // @Param: _nodeAddress, goodID, goodname, good price, releaseTime
    function nodePublishGood(address payable _nodeAddress, string memory _nodePassword, string memory _goodName, uint _goodPrice, string memory _releaseTime) public {
        require(stringComparison(_nodePassword, nodesPassword[_nodeAddress]));
        currentGoodID++;
        defaultArray = [_nodeAddress];
        goods[currentGoodID] = Good(_goodName, _goodPrice, false, _releaseTime, _nodeAddress, defaultArray);
        allLogisticNodes[_nodeAddress].nodeGoods.push(currentGoodID);
        emit NodePublishGood(_nodeAddress, true, "Publish Success!");
    }
    
    // Node transfer goods
    event NodeTransferGood(bool isSuccess, string message);
    function nodeTransferGood(address payable _buyer, uint _goodID) public payable {
        if (bytes(nodesPassword[_buyer]).length != 0 && msg.value == goods[_goodID].goodPrice){
            goods[_goodID].owner.transfer(msg.value);
            goods[_goodID].transferProcess.push(_buyer);
            goods[_goodID].owner = _buyer;
            emit NodeTransferGood(true, "Transferation successed");
        }
    }
    
    function getNodeGoods(address payable owner) public view returns(uint[] memory){
        return allLogisticNodes[owner].nodeGoods;
    }
    
    function getOwner(uint _goodID) public view returns(address) {
        return goods[_goodID].owner;
    }
    
    function getPrice(uint _goodID) public view returns(uint){
        return goods[_goodID].goodPrice;
    }
    
    function getGoodTransferPath(uint _goodID) public view returns(address[] memory) {
        return goods[_goodID].transferProcess;
    }
}