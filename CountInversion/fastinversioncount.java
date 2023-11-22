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

    public static int strToNum(String str){
        int flag=1;

        if (str.charAt(0)=='-'){
            str=str.substring(1, str.length());
            flag=-1;
        }

        int result=0;
        for (int i=0; i<str.length(); i++){
            result+=Math.pow(10, str.length()-i-1)*(str.charAt(i)-'0');
        }
        return result*flag;
    }

    public static boolean isDigit(char c){
        return c<='9' && c>='0';
    }

    public static ArrayList<Integer> strToArr(String str){
        String element="";
        boolean flag=false;
        ArrayList<Integer> result=new ArrayList<>();
        
        for (int i=0; i<str.length(); i++){
            char curr=str.charAt(i);
            char next=i+1<str.length() ? str.charAt(i+1):0;
            if ((curr=='-' && isDigit(next) || isDigit(curr)) && !flag){
                flag=true;
            }
            else if (!isDigit(curr) && flag){
                flag=false;
                result.add(strToNum(element));
                element="";
            }
            if (flag){
                element+=str.substring(i, i+1);
            }
        }

        if (element.length()>0){
            result.add(strToNum(element));
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String inputStr=input.nextLine();
        accumulator=0;
        ArrayList<Integer> arr=strToArr(inputStr);
        countInversion(arr, 0, arr.size());
        System.out.println(accumulator);
        input.close();
    }
}
