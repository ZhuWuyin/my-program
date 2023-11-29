import java.util.ArrayList;
import java.util.Scanner;

public class fastinversioncount {

    public static int accumulator=0;

    public static void countInversion(ArrayList<Integer> arr, int start, int end){
        int size=end-start;

        if (size<=1){
            return;
        }

        if (size==2){
            int a=arr.get(start);
            int b=arr.get(start+1);

            if (a>b){
                accumulator++;
                arr.remove(start+1);
                arr.add(start, b);
            }

            return;
        }

        int half=(end-start)/2+start;
        countInversion(arr, start, half);
        countInversion(arr, half, end);

        int indexL=start;
        int indexR=half;

        while (indexL<half && indexR<end) {
            int elementL=arr.get(indexL);
            int elementR=arr.get(indexR);

            if (elementL>elementR){
                arr.remove(indexR);
                arr.add(indexL, elementR);
                half++;
                indexR++;
                indexL++;
                if (elementL!=elementR){
                    accumulator+=half-indexL;
                }
            }
            else if (elementL<=elementR){
                indexL++;
            }
        }
    }
}
