//import List;
import java.math.BigDecimal;
import java.util.Scanner;
import java.util.regex.Pattern;

public class calculator {
    public static List processTo(String input){
        List result=new List();
        String append="";
        String interm="";
        for (int i=0; i<input.length(); i++){
            interm=input.substring(i, i+1);
            if (!Pattern.matches("[1234567890.]", interm)){
                if (append.length()!=0){
                    result.append(append);
                }
                append="";
                result.append(interm);
                continue;
            }
            append+=interm;
        }
        if (append.length()!=0){
            result.append(append);
        }
        return result;
    }

    public static BigDecimal[] toBigDecimal(List a, int index){
        BigDecimal[] result={new BigDecimal((String)a.getElement(index-1)),
                            new BigDecimal((String)a.getElement(index+1))};
        return result;
    }

    public static Object calculate(List a){
        boolean determ=true;
        BigDecimal result=new BigDecimal(0L);

        while (determ){
            int index=0;
            int index1=0;
            int index2=0;
            if (a.indexOf("^")!=a.positiveLength+1){
                index=a.indexOf("^");
                BigDecimal val1=BigDecimal.valueOf(Double.valueOf((String)a.getElement(index-1)));
                int val2=Integer.valueOf((String)a.getElement(index+1));
                if (val2<0){
                    BigDecimal interm=val1.pow(-val2);
                    System.out.println(interm.toString()+" "+val2);
                    result=new BigDecimal("1").divide(interm, interm.toString().length()+val2+1, BigDecimal.ROUND_HALF_UP);
                }
                else {
                    result=val1.pow(val2);
                }
            }
            else if(a.indexOf("*")!=a.positiveLength+1 || a.indexOf("/")!=a.positiveLength+1){
                index1=a.indexOf("*");
                index2=a.indexOf("/");
                if (index1<index2){
                    index=index1;
                    BigDecimal[] interm=toBigDecimal(a, index);
                    BigDecimal val1=interm[0];
                    BigDecimal val2=interm[1];
                    result=val1.multiply(val2);
                }
                else {
                    index=index2;
                    BigDecimal[] interm=toBigDecimal(a, index);
                    BigDecimal val1=interm[0];
                    BigDecimal val2=interm[1];
                    result=val1.divide(val2, 10, BigDecimal.ROUND_HALF_UP);
                }
            }
            else if(a.indexOf("+")!=a.positiveLength+1 || a.indexOf("-")!=a.positiveLength+1){
                index1=a.indexOf("+");
                index2=a.indexOf("-");
                if (index1<index2){
                    index=index1;
                    BigDecimal[] interm=toBigDecimal(a, index);
                    BigDecimal val1=interm[0];
                    BigDecimal val2=interm[1];
                    result=val1.add(val2);
                }
                else {
                    index=index2;
                    BigDecimal[] interm=toBigDecimal(a, index);
                    BigDecimal val1=interm[0];
                    BigDecimal val2=interm[1];
                    result=val1.subtract(val2);
                }
            }
            else {
                determ=false;
            }
            if (determ){
                a.instead(result.toString(), index);
                a.pop(index+1);
                a.pop(index-1);
            }
        }
        return (Object)a.getElement(0);
    }

    public static void main(String[] args) {
        boolean judge=true;
        while (judge){
            Scanner input=new Scanner(System.in);
            String a=input.nextLine();
            if (a.equals("break")){
                judge=false;
                continue;
            }
            List func=processTo(a);
            boolean determ=true;
            while (determ){
                int index1=func.reversIndexOf("(");
                if (index1!=func.positiveLength+1){
                    Object result;
                    List interm=new List();
                    int index2=func.indexOf(index1, ")");
                    for (int i=index1+1; i<index2; i++){
                        interm.append(func.getElement(i));
                    }
                    result=calculate(interm);
                    func.instead(result, index2);
                    for (int i=index2-1; i>=index1; i--){
                        func.pop(i);
                    }
                }
                else {
                    determ=false;
                    System.out.println(calculate(func));
                }
            }
        }
        System.out.println("-Finished");
    }
}
