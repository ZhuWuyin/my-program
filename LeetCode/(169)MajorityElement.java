class Solution {
    public int majorityElement(int[] nums) {
        int counter=0;
        int num=nums[0];
        for (int i: nums){
            if (counter==0){
                num=i;
            }
            counter+= (num==i) ? 1:-1;
        }
        return num;
    }
}
