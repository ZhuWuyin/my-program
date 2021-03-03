import java.util.*;

public class Sort{

    public static ArrayList<Integer> selectSort(ArrayList<Integer> aList){
        for (int i=0; i<aList.size(); i++){
            Integer first=aList.get(i);
            int index=i;
            for (int j=i; j<aList.size(); j++){
                if (first>aList.get(j)){
                    first=aList.get(j);
                    index=j;
                }
            }
            aList.remove(index);
            aList.add(i, first);
        }
        return aList;
    }

    public static ArrayList<Integer> insertSort(ArrayList<Integer> a){
        int index=0;
        while (index<a.size()-1){
            int intermIndex=index+1;
            int finalIndex=0;
            boolean determ=false;
            if (a.get(index)>a.get(intermIndex)){
                int i=index;
                while (i>=0 && !determ){
                    if (a.get(intermIndex)>=a.get(i)){
                        determ=true;
                        finalIndex=i+1;
                    }
                    else if (i==0){
                        finalIndex=0;
                        determ=true;
                    }
                    i--;
                }
                if (determ){
                    Integer intermElement=a.get(intermIndex);
                    a.remove(intermIndex);
                    a.add(finalIndex, intermElement);
                }
            }
            index++;
        }
        return a;
    }

    public static ArrayList<ArrayList> convert(ArrayList<Integer> a){
        ArrayList<ArrayList> list=new ArrayList<ArrayList>();
        for (Integer i: a){
            ArrayList<Integer> interm=new ArrayList<Integer>();
            interm.add(i);
            list.add(interm);
        }
        return list;
    }

    public static ArrayList<Integer> mergeSort(ArrayList<ArrayList> a){
        while (a.size()>1){
            for (int i=2; i<=a.size(); i+=2){
                int index1=i-1;
                int index2=i-2;
                a.get(index2).addAll(a.get(index1));
                a.set(index2, selectSort(a.get(index2)));
                a.remove(index1);
                System.out.println(a);
            }
        }
        return a.get(0);
    }

    public static Integer linearSearch(ArrayList<Integer> List,Integer Object){
        for (int i=0; i<List.size(); i++){
            if (List.get(i)==Object){
                return Integer.valueOf(i);
            }
        }
        return -1;
    }

}