public class Sort{
    public static void main(String[] args) {
        int[] nums={2,3,3,1,-7,4,10};
        int max=nums[0];
        for (int i: nums){
            max=i>max ? i:max;
        }
        int min=nums[0];
        for (int i: nums){
            min=i<min ? i:min;
        }
        if (min<0 && max>0){
            int[] index=new int[max-min+1];
            int counter=0;
            for (int i=min; i<=max; i++){
                index[counter]=i;
                counter++;
            }
            List sort=new List(-min, max+1);
            sort.instead(0, index);
            Integer interm;
            for (int i: nums){
                interm=(Integer)sort.getElement(i)+1;
                sort.instead(interm, i);
            }
            for (int i=-sort.negativeLength; i<sort.positiveLength; i++){
                int getter=(int)sort.getElement(i);
                for (int j=0; j<getter; j++){
                    System.out.println(i);
                }
            }
        }
    }
}