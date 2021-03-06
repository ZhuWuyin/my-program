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
            if (a.indexOf("^")!=a.positiveLength+1){
                index=a.indexOf("^");
                BigDecimal val1=BigDecimal.valueOf(Double.valueOf((String)a.getElement(index-1)));
                int val2=Integer.valueOf((String)a.getElement(index+1));
                result=val1.pow(val2);
            }
            else if(a.indexOf("*")!=a.positiveLength+1){
                index=a.indexOf("*");
                BigDecimal[] interm=toBigDecimal(a, index);
                BigDecimal val1=interm[0];
                BigDecimal val2=interm[1];
                result=val1.multiply(val2);
            }
            else if(a.indexOf("/")!=a.positiveLength+1){
                index=a.indexOf("/");
                BigDecimal[] interm=toBigDecimal(a, index);
                BigDecimal val1=interm[0];
                BigDecimal val2=interm[1];
                result=val1.divide(val2);
            }
            else if(a.indexOf("+")!=a.positiveLength+1){
                index=a.indexOf("+");
                BigDecimal[] interm=toBigDecimal(a, index);
                BigDecimal val1=interm[0];
                BigDecimal val2=interm[1];
                result=val1.add(val2);
            }
            else if(a.indexOf("-")!=a.positiveLength+1){
                index=a.indexOf("-");
                BigDecimal[] interm=toBigDecimal(a, index);
                BigDecimal val1=interm[0];
                BigDecimal val2=interm[1];
                result=val1.subtract(val2);
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
            System.out.println(func);
            boolean determ=true;
            while (determ){
                int index=func.indexOf(")");
                if (index!=func.positiveLength+1){
                    boolean breakOp=true;
                    Object result;
                    List interm=new List();
                    int i;
                    for (i=index-1; i>=0 && breakOp; i--){
                        Object intermFor=func.getElement(i);
                        if (!intermFor.equals("(")){
                            interm.append(intermFor);
                        }
                        else {
                            breakOp=false;
                        }
                    }
                    result=calculate(interm);
                    func.instead(result, index);
                    for (int j=index-1; j>i; j--){
                        func.pop(j);
                    }
                    System.out.println(func);
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