import java.math.BigInteger;

public class PrimeNum{
    static boolean primeNum(BigInteger num){
        for (BigInteger i=new BigInteger("2"); i.compareTo(num)==-1; i=i.add(BigInteger.valueOf(1l))){
            if (num.mod(i).compareTo(BigInteger.valueOf(0l))==0){
                return false;
            }
        }
        return true;
    }
}
