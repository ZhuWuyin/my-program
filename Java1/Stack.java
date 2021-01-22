package Java1;

public class Stack {
    /**
     * This method would not change the original array
     * @param arrayOrigin //The array what you want add something
     * @param varAdd //The things you want add
     * @return Original array + additional things
     */
    public static int[] append(int[] arrayOrigin, int... varAdd){
        int originLength=arrayOrigin.length;
        int addLength=varAdd.length;
        int[] interm=new int[addLength+originLength];
        if(originLength!=0){
            for(int i=0; i<originLength; i++){
                interm[i]=arrayOrigin[i];
            }
        }
        for(int i=originLength; i<interm.length; i++){
            interm[i]=varAdd[i-originLength];
        }
        return interm;
    }

    /**
     * This method would not change the original array
     * @param arrayOrigin //The array what you want add something
     * @param varAdd //The things you want add
     * @return Original array + additional things
     */
    public static String[] append(String[] arrayOrigin, String... varAdd){
        int originLength=arrayOrigin.length;
        int addLength=varAdd.length;
        String[] interm=new String[addLength+originLength];
        if(originLength!=0){
            for(int i=0; i<originLength; i++){
                interm[i]=arrayOrigin[i];
            }
        }
        for(int i=originLength; i<interm.length; i++){
            interm[i]=varAdd[i-originLength];
        }
        return interm;
    }

    /**
     * This method would not change the original array
     * @param arrayOrigin //The array what you want add something
     * @param varAdd //The things you want add
     * @return Original array + additional things
     */
    public static double[] append(double[] arrayOrigin, double... varAdd){
        int originLength=arrayOrigin.length;
        int addLength=varAdd.length;
        double[] interm=new double[addLength+originLength];
        if(originLength!=0){
            for(int i=0; i<originLength; i++){
                interm[i]=arrayOrigin[i];
            }
        }
        for(int i=originLength; i<interm.length; i++){
            interm[i]=varAdd[i-originLength];
        }
        return interm;
    }

    /**
     * This method would not change the original array
     * @param arrayOrigin //The array what you want add something
     * @param varAdd //The things you want add
     * @return Original array + additional things
     */
    public static Double[] append(Double[] arrayOrigin, double[] varAdd) {
        int originLength=arrayOrigin.length;
        int addLength=varAdd.length;
        Double[] interm = new Double[addLength + originLength];
        if(originLength!=0){
            for(int i=0; i<originLength; i++){
                interm[i]=arrayOrigin[i];
            }
        }
        for(int i=originLength; i<interm.length; i++){
            interm[i]=varAdd[i-originLength];
        }
        return interm;
	}

    /**
     * This method would not change the original array
     * @param arrayOrigin //The array what you want add something
     * @param varAdd //The things you want add
     * @return Original array + additional things
     */
	public static Integer[] append(Integer[] arrayOrigin, int[] varAdd) {
        int originLength=arrayOrigin.length;
        int addLength=varAdd.length;
        Integer[] interm = new Integer[addLength + originLength];
        if(originLength!=0){
            for(int i=0; i<originLength; i++){
                interm[i]=arrayOrigin[i];
            }
        }
        for(int i=originLength; i<interm.length; i++){
            interm[i]=varAdd[i-originLength];
        }
        return interm;
	}

    /**
     * This method would not change the original array
     * @param arrayOrigin //The array which you want pop somethings
     * @param index //The indexes that you want to pop int the original array
     * @return This method will return a 2D array which contain the processed array and what removed
     */
    public static int[][] pop(int[] arrayOrigin, int... index){
        int intermSize= arrayOrigin.length - index.length<0 ? 0:arrayOrigin.length - index.length;
        int[] interm=new int[intermSize];
        int[] popArray=new int[index.length];
        int popCounter=0;
        int intermCounter=0;
        if (interm.length!=0){
            for (int i=0; i<arrayOrigin.length; i++){
                int counter=0;
                for (int j: index){
                    if (i==j){
                        popArray[popCounter]=arrayOrigin[i];
                        popCounter++;
                        counter=1;
                        break;
                    }
                }
                if (counter==0){
                    interm[intermCounter]=arrayOrigin[i];
                    intermCounter++;
                }
            }
        }
        int[][] result={interm, popArray};
        return result;
    }

    /**
     * This method would not change the original array
     * @param arrayOrigin //The array which you want pop somethings
     * @param index //The indexes that you want to pop int the original array
     * @return This method will return a 2D array which contain the processed array and what removed
     */
    public static String[][] pop(String[] arrayOrigin, int... index){
        int intermSize= arrayOrigin.length - index.length<0 ? 0:arrayOrigin.length - index.length;
        String[] interm=new String[intermSize];
        String[] popArray=new String[index.length];
        int popCounter=0;
        int intermCounter=0;
        if (interm.length!=0){
            for (int i=0; i<arrayOrigin.length; i++){
                int counter=0;
                for (int j: index){
                    if (i==j){
                        popArray[popCounter]=arrayOrigin[i];
                        popCounter++;
                        counter=1;
                        break;
                    }
                }
                if (counter==0){
                    interm[intermCounter]=arrayOrigin[i];
                    intermCounter++;
                }
            }
        }
        String[][] result={interm, popArray};
        return result;
    }

    /**
     * This method would not change the original array
     * @param arrayOrigin //The array which you want pop somethings
     * @param index //The indexes that you want to pop int the original array
     * @return This method will return a 2D array which contain the processed array and what removed
     */
    public static double[][] pop(double[] arrayOrigin, int... index){
        int intermSize= arrayOrigin.length - index.length<0 ? 0:arrayOrigin.length - index.length;
        double[] interm=new double[intermSize];
        double[] popArray=new double[index.length];
        int popCounter=0;
        int intermCounter=0;
        if (interm.length!=0){
            for (int i=0; i<arrayOrigin.length; i++){
                int counter=0;
                for (int j: index){
                    if (i==j){
                        popArray[popCounter]=arrayOrigin[i];
                        popCounter++;
                        counter=1;
                        break;
                    }
                }
                if (counter==0){
                    interm[intermCounter]=arrayOrigin[i];
                    intermCounter++;
                }
            }
        }
        double[][] result={interm, popArray};
        return result;
    }

    /**
     * This method would not change the original array
     * @param arrayOrigin //The array which you want pop somethings
     * @param index //The indexes that you want to pop int the original array
     * @return This method will return a 2D array which contain the processed array and what removed
     */
    public static Integer[][] pop(Integer[] arrayOrigin, int... index) {
        int intermSize= arrayOrigin.length - index.length<0 ? 0:arrayOrigin.length - index.length;
        Integer[] interm = new Integer[intermSize];
        Integer[] popArray = new Integer[index.length];
        int popCounter=0;
        int intermCounter=0;
        if (interm.length!=0){
            for (int i=0; i<arrayOrigin.length; i++){
                int counter=0;
                for (int j: index){
                    if (i==j){
                        popArray[popCounter]=arrayOrigin[i];
                        popCounter++;
                        counter=1;
                        break;
                    }
                }
                if (counter==0){
                    interm[intermCounter]=arrayOrigin[i];
                    intermCounter++;
                }
            }
        }
        Integer[][] result = { interm, popArray };
        return result;
	}

    /**
     * This method would not change the original array
     * @param arrayOrigin //The array which you want pop somethings
     * @param index //The indexes that you want to pop int the original array
     * @return This method will return a 2D array which contain the processed array and what removed
     */
	public static Double[][] pop(Double[] arrayOrigin, int... index) {
        int intermSize= arrayOrigin.length - index.length<0 ? 0:arrayOrigin.length - index.length;
        Double[] interm = new Double[intermSize];
        Double[] popArray=new Double[index.length];
        int popCounter=0;
        int intermCounter=0;
        if (interm.length!=0){
            for (int i=0; i<arrayOrigin.length; i++){
                int counter=0;
                for (int j: index){
                    if (i==j){
                        popArray[popCounter]=arrayOrigin[i];
                        popCounter++;
                        counter=1;
                        break;
                    }
                }
                if (counter==0){
                    interm[intermCounter]=arrayOrigin[i];
                    intermCounter++;
                }
            }
        }
        Double[][] result={interm, popArray};
        return result;
	}

}