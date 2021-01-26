public class List1{

    public int length=0;
    public int[] intIndexArray={};
    public int[] StringIndexArray={};
    public int[] doubleIndexArray={};
    public Integer[] intArray={};
    public String[] StringArray={};
    public Double[] doubleArray={};

    /**
     * @param index //The index of the element you want
     * @return This method will return the element you want
     */
    public Object getElement(int index){
        for (int i=0; i<StringIndexArray.length; i++){
            if (StringIndexArray[i]==index){
                return (Object) StringArray[i];
            }
        }
        for (int i=0; i<intIndexArray.length; i++){
            if (intIndexArray[i]==index){
                return (Object) intArray[i];
            }
        }
        for (int i=0; i<doubleIndexArray.length; i++){
            if (doubleIndexArray[i]==index){
                return (Object) doubleArray[i];
            }
        }
        return (Object) new Integer(-1);
    }
    
    /**
     * This method will add the element array in the list, and change the index based on the length of element array
     * @param startIndex //The index you want to start to append element
     * @param element //The things you want to append
     */
    public void listAppend(int startIndex, int... element){
        int[] index={};
        for (int i: element){
            index=Stack.append(index, startIndex);
            startIndex++;
            length++;
        }
        intIndexArray=Stack.append(intIndexArray, index);
        intArray=Stack.append(intArray, element);
    }

    /**
     * This method will add the element array in the list, and change the index based on the length of element array
     * @param startIndex //The index you want to start to append element
     * @param element //The things you want to append
     */
    public void listAppend(int startIndex, String... element){
        int[] index={};
        for (String i: element){
            index=Stack.append(index, startIndex);
            startIndex++;
            length++;
        }
        StringIndexArray=Stack.append(StringIndexArray, index);
        StringArray=Stack.append(StringArray, element);
    }

    /**
     * This method will add the element array in the list, and change the index based on the length of element array
     * @param startIndex //The index you want to start to append element
     * @param element //The things you want to append
     */
    public void listAppend(int startIndex, double... element){
        int[] index={};
        for (double i: element){
            index=Stack.append(index, startIndex);
            startIndex++;
            length++;
        }
        doubleIndexArray=Stack.append(doubleIndexArray, index);
        doubleArray=Stack.append(doubleArray, element);
    }

    /**
     * This method will remove the element based on the indexes offered without sort the index
     * @param index //This is an array which contain the index of element you want remove
     */
    public void listRemove(int... index){
        for (int i: index){
            for (int j=0; j<StringIndexArray.length; j++){
                if (StringIndexArray[j]==i){
                    StringArray=Stack.pop(StringArray, j)[0];
                    StringIndexArray=Stack.pop(StringIndexArray, j)[0];
                    length--;
                }
            }
            for (int j=0; j<intIndexArray.length; j++){
                if (intIndexArray[j]==i){
                    intArray=Stack.pop(intArray, j)[0];
                    intIndexArray=Stack.pop(intIndexArray, j)[0];
                    length--;
                }
            }
            for (int j=0; j<doubleIndexArray.length; j++){
                if (doubleIndexArray[j]==i){
                    doubleArray=Stack.pop(doubleArray, j)[0];
                    doubleIndexArray=Stack.pop(doubleIndexArray, j)[0];
                    length--;
                }
            }
        }
    }

    public void add(int addition, int... index){
        for (int i: index){
            for (int j=0; j<intIndexArray.length; j++){
                if (intIndexArray[j]==i){
                    intArray[j]+=addition;
                }
            }
        }
    }

    public void add(double addition, int... index){
        for (int i: index){
            for (int j=0; j<doubleIndexArray.length; j++){
                if (doubleIndexArray[j]==i){
                    doubleArray[j]+=addition;
                }
            }
        }
    }

    /**
     * Get the max index of the List
     * If there was nothing in list will return -1
     */
    public int getMaxIndex(){
        int max=-1;
        for (int i: intIndexArray){
            max=max<i ? i:max;
        }
        for (int i: doubleIndexArray){
            max=max<i ? i:max;
        }
        for (int i: StringIndexArray){
            max=max<i ? i:max;
        }
        return max;
    }

    /**
     * Get the min index of list
     * If there was nothing in list will return -1
     */
    public int getMinIndex(){
        int min=getMaxIndex();
        int counter=0;
        for (int i: intIndexArray){
            min=min>i ? i:min;
            counter++;
        }
        for (int i: doubleIndexArray){
            min=min>i ? i:min;
            counter++;
        }
        for (int i: StringIndexArray){
            min=min>i ? i:min;
            counter++;
        }
        if (counter!=0){
            return min;
        }
        return -1;
    }

    /**
     * This method will sort the index when you use "listRemove" method
     */
    public void sortIndex(){
        int[] allIndex=new int[length];
        int i=0;
        for (int j=0; j<intIndexArray.length; j++){
            allIndex[i]=intIndexArray[j];
            i++;
        }
        for (int j=0; j<StringIndexArray.length; j++){
            allIndex[i]=StringIndexArray[j];
            i++;
        }
        for (int j=0; j<doubleIndexArray.length; j++){
            allIndex[i]=doubleIndexArray[j];
            i++;
        }
        for (int k=0; k<length; k++){
            for (int l=0; l<intIndexArray.length; l++){
                if (allIndex[k]==intIndexArray[l]){
                    intIndexArray[l]=k;
                }
            }
            for (int l=0; l<StringIndexArray.length; l++){
                if (allIndex[k]==StringIndexArray[l]){
                    StringIndexArray[l]=k;
                }
            }
            for (int l=0; l<doubleIndexArray.length; l++){
                if (allIndex[k]==doubleIndexArray[l]){
                    doubleIndexArray[l]=k;
                }
            }
        }
    }

    public String toString(){
        String a="[";
        if (getElement(0).getClass()==new String().getClass()){
            a+="\""+getElement(0)+"\"";
        }
        else {
            a+=getElement(0);
        }
        for (int i=1; i<=getMaxIndex(); i++){
            if (getElement(i).getClass()==new String().getClass()){
                a+=",\""+getElement(i)+"\"";
            }
            else {
                a+=","+getElement(i);
            }
        }
        a+="]";
        return a;
    }

}