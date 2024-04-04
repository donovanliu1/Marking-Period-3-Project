import java.util.ArrayList;
import java.util.Arrays;


public class Requirement {
    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> integerArrayList1 = new ArrayList<>();
        System.out.println(integerArrayList1);
        System.out.println(integerArrayList1.add(1));
        integerArrayList1.add(0, 1);
        System.out.println(integerArrayList1.get(0));
        System.out.println(integerArrayList1.set(1, 0));
        System.out.println(integerArrayList1.remove(1));
        for (int i = 0; i < integerArrayList.size(); i++) {
            if (integerArrayList.get(i) == 2) {
                integerArrayList.remove(i);
                i--;
            }
        }
        int[] arr = random(5);
        selectionSort(arr);
        arr = random(5);
        insertionSort(arr);
        int[][] twoD = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        for (int i = 0; i < twoD[0].length; i++) {
            for (int j = 0; j < twoD.length; j++) {
                System.out.print(twoD[j][i]);
            }
            System.out.println();
        }
        Object object = new Object(); // this counts?
        Requirement2 requirement2 = new Requirement2();
        Requirement2 requirement3 = new Requirement2();
        System.out.println(requirement3.equals(requirement2));
        ArrayList<Requirement> temp = new ArrayList<>();
        temp.add(new Requirement2());
        temp.add(new Requirement2());

    }
    public static void print(int[] arr)
    {
        String str = "[";
        for(int i = 0; i < arr.length - 1; i++)
            str += arr[i] + ", ";
        str += arr[arr.length - 1] + "]";
        System.out.println(str);
    }
    public static int[] random(int length)
    {
        int[] ints = new int[length];
        for(int i = 0; i < length; i++)
        {
            ints[i] = (int)(Math.random()*100);
        }
        return ints;
    }
    public static int findMinIndex(int index, int[] arr)
    {
        int minUpdate = 0;
        int min = arr[index];
        for(int i = index; i < arr.length; i++)
        {
            if(arr[i] < min)
            {
                min = arr[i];
                index = i;
                minUpdate++;
            }
        }
        System.out.println("Minimum Updates: " + minUpdate);
        return index;
    }
    public static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void selectionSort(int[] arr)
    {
        int swaps = 0;
        for(int i = 0; i < arr.length; i++)
        {
            int minIndex = findMinIndex(i, arr);
            if(minIndex != i)
            {
                swap(arr, i, minIndex);
                swaps++;
            }
            print(arr);
        }
        System.out.println("Swaps: " + swaps);
    }
    public static void insertionSort(int[] arr)
    {
        int swaps = 0;
        for(int i = 1; i < arr.length; i++)
        {
            int temp = arr[i];
            int index = i;
            while(index > 0 && temp < arr[index - 1])
            {
                swap(arr, index, index - 1);
                swaps++;
                index--;
            }
            print(arr);
        }
        System.out.println("Swaps: " + swaps);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}