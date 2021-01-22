package Java1;

public class Sort{
    public static void main(String[] args) {
        int[] b={1,2,5,3,2,653,4};
        List a=new List();
        for (int i: b){
            if ((int)a.getElement(i)==-1){
                a.listAppend(i, 1);
            }
            else {
                a.add(1, i);
            }
        }
        for (int i=0; i<=a.getMaxIndex(); i++){
            if ((int)a.getElement(i)!=-1){
                for (int j=0; j<(int)a.getElement(i); j++){
                    System.out.println(i);
                }
            }
        }
    }
}