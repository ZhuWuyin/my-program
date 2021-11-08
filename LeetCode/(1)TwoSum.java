class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] interm=new int[nums.length];
        int counter=0;
        for (int i=0; i<nums.length; i++){
            for (int j=i+1; j<nums.length; j++){
                if ((target-nums[i])==nums[j]){
                    interm[counter]=i;
                    interm[counter+1]=j;
                    counter+=2;
                }
            }
        }
        int[] result=new int[counter];
        for (int i=0; i<counter; i++){
            result[i]=interm[i];
        }
        return result;
    }
}