public class Sort{
    public static void main(String[] args) {
        int[] b={1,2,5,3,2,653,4};
        List a=new List();
        for (int i: b){
            if (a.getElement(i).equals("-1")){
                a.listAppend(i, 1);
            }
            else {
                a.add(1, i);
            }
        }
        for (int i=0; i<=a.getMaxIndex(); i++){
            if (!a.getElement(i).equals("-1")){
                for (int j=0; j<Integer.valueOf(a.getElement(i)); j++){
                    System.out.println(i);
                }
            }
        }
    }
}