import java.math.BigInteger;
import java.util.Scanner;

public class PrimeNum{
    static boolean primeNum(BigInteger num){
        for (BigInteger i=new BigInteger("2"); i.compareTo(num)==-1; i=i.add(BigInteger.valueOf(1l))){
            if (num.mod(i).compareTo(BigInteger.valueOf(0l))==0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        Scanner num=new Scanner(System.in);
        BigInteger i=new BigInteger(num.next());
        num.close();
        if (primeNum(i)){
            System.out.println("it is");
        }
        else {
            System.out.println("it isn't");
        }
    }
}