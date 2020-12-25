public class Compare{
    private double initA;
    private double initB;
    private double counter;
    public Compare(double num1, double num2){
        initA=num1;
        initB=num2;
        counter=initA>initB ? initA:initB;
    }
    public double compareNum(double currentNum){
        counter=counter>currentNum ? counter:currentNum;
        return counter;
    }
}