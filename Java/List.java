public class List {

    public int length=0;
    public int[] intIndexArray={};
    public int[] StringIndexArray={};
    public int[] doubleIndexArray={};
    public int[] intArray={};
    public String[] StringArray={};
    public double[] doubleArray={};

    /**
     * @param index //The index of the element you want
     * @return This method will return the element you want with String type
     */
    public String getElement(int index){
        for (int i=0; i<StringIndexArray.length; i++){
            if (StringIndexArray[i]==index){
                return StringArray[i];
            }
        }
        for (int i=0; i<intIndexArray.length; i++){
            if (intIndexArray[i]==index){
                return intArray[i]+"";
            }
        }
        for (int i=0; i<doubleIndexArray.length; i++){
            if (doubleIndexArray[i]==index){
                return doubleArray[i]+"";
            }
        }
        return "-1";
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
     * This method will remove the element based on the indexes offered
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
     */
    public int getMaxIndex(){
        int max=0;
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

}