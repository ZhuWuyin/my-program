/*
* The index of this class such ...-3, -2, -1, 0, 1, 2, 3...
* and distributed by positive part(0, 1, 2, 3...) and negative part(...-3, -2, -1)
*/

public class List {

    public Object[] positiveList={};
    public Object[] negativeList={};
    public int positiveLength=0;
    public int negativeLength=0;
    public int length=0;

    public List(){}

    public List(int poLen){
        length=poLen;
        positiveLength=poLen;
        positiveList=new Object[positiveLength];
    }

    public List(Object... elements){
        positiveLength=elements.length;
        length=positiveLength;
        positiveList=elements;
    }

    public List(int negLen, int posLen){
        negativeLength=negLen;
        positiveLength=posLen;
        length=negLen+posLen;
        negativeList=new Object[negLen];
        positiveList=new Object[posLen];
    }

    public void append(Object... elements){
        int len=positiveLength+elements.length;
        Object[] result=new Object[len];
        for (int i=0; i<positiveLength; i++){
            result[i]=positiveList[i];
        }
        for (int i=positiveLength; i<len; i++){
            result[i]=elements[i-positiveLength];
        }
        positiveLength+=elements.length;
        length+=elements.length;
        positiveList=result;
    }

    public void negativeAppend(Object... elements){
        int len=negativeLength+elements.length;
        Object[] result=new Object[len];
        for (int i=0; i<negativeLength; i++){
            result[i]=negativeList[i];
        }
        for (int i=negativeLength; i<len; i++){
            result[i]=elements[i-negativeLength];
        }
        negativeLength+=elements.length;
        length+=elements.length;
        negativeList=result;
    }

    public Object[][] instead(Object element, int... index){
        int negLen=0;
        int posLen=0;
        for (int i: index){
            if (i<0){
                negLen++;
            }
            else {
                posLen++;
            }
        }
        Object[] negativeInstead=new Object[negLen];
        Object[] positiveInstead=new Object[posLen];
        int negCounter=0;
        int posCounter=0;
        for (int i: index){
            if (i<0){
                negativeInstead[negCounter]=negativeList[~(i-0b1)-1];
                negativeList[~(i-0b1)-1]=element;
                negCounter++;
            }
            else {
                positiveInstead[posCounter]=positiveList[i];
                positiveList[i]=element;
                posCounter++;
            }
        }
        Object[][] result={negativeList, positiveList};
        return result;
    }

    public Object getElement(int index){
        if (index<0){
            Object interm=negativeList[~(index-0b1)-1];
            return new String().getClass()==interm.getClass() ? "\""+interm+"\"": interm;
        }
        else {
            Object interm=positiveList[index];
            return new String().getClass()==interm.getClass() ? "\""+interm+"\"": interm;
        }
    }

    public String toString(){
        String result="{[";
        if (negativeLength>0){
            int index=~negativeLength+0b1;
            result+=getElement(index);
            for (int i=index+1; i<0; i++){
                result+=","+getElement(i);
            }
        }
        result+="],[";
        if (positiveLength>0){
            result+=getElement(0);
            for (int i=1; i<positiveLength; i++){
                result+=","+getElement(i);
            }
        }
        result+="]}";
        return result;
    }

}