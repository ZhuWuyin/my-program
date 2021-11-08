#include <stdio.h>

// Definition for a binary tree node.
struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

// Solution
struct TreeNode* searchBST(struct TreeNode* root, int val){
    if (root==NULL || val==root->val){
        return root;
    }
    return (val<root->val) ? searchBST(root->left, val):searchBST(root->right, val);
}