
/*
* ADAM OBRYAN
* adamobryan.com       
* github.com/aob412
*
*
**/



import java.util.*;

public class AOB {

    /**
     * Helper method for printing out arrays.
     * @param int[] arr Array of integers to print
     */
    public static void printArray(int[] arr) {
	System.out.print("[ ");
	for (int j=0; j < (arr.length - 1); j++) {
	    System.out.print(arr[j] + ", ");
	}
	if (arr.length > 0) {
	    System.out.print(arr[arr.length - 1]);
	}
	System.out.println(" ]");
	    
    }

    /**
     * Swap two elements in an array
     * @param int[] arr - the array
     * @param int index1 - the index of first element to swap
     * @param int index2 - the index of the second element to swap
     */

    public static void swap(int[] arr, int index1, int index2) {
	if (index1 == index2) {
	    // Do nothing!
	} else {
	    int tmp = arr[index1];
	    arr[index1] = arr[index2];
	    arr[index2] = tmp;
	}
    }

    /**
     * Sort an array in ascending order using the Selection Sort algorithm
     * @param int[] arr - the array
     */
    
    public static void selectionSort(int[] arr) {
	int switches = 0;
	if (arr.length < 2) {
	    return;
	}
	
	int minIndex = 0;
	int minVal = 0;

	for (int j = 0; j < (arr.length-1); j++) {

	    //printArray(arr);
	    minIndex = j;
	    minVal = arr[j];
	    for(int k = j + 1; k < arr.length; k++) {

		if (arr[k] < minVal) {

		    minVal = arr[k];
		    minIndex = k;
		}
	    }
	    swap(arr, j, minIndex);
            switches++;
            
	}
        System.out.print("Selection Sort: ");printArray(arr);
System.out.println("Swaps: " + switches);
    }
    
     public static void bubbleSort(int[] arr) {
	int switches = 0;
	if (arr.length < 2) {
	    return;
	}
	int n = arr.length;
        int temp = 0;
	for(int i=0; i < n; i++){
                        for(int j=1; j < (n-i); j++){
                               
                                if(arr[j-1] > arr[j]){
                                        //swap the elements!
                                        temp = arr[j-1];
                                        arr[j-1] = arr[j];
                                        arr[j] = temp;
                                }
	    
            switches++;
            
	}}
        System.out.print("Bubble Sort: ");printArray(arr);
System.out.println("Swaps: " + switches);
    }

    public static void main(String[] args) {
	int[][] a1 = { {8, 9, 5, 6, 4, 3},
		       {9, 0, 14, 13, 10, 8, 2, 1, 17, 18, 19, 201, 220, 235, 2},
		       {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200 },
		       {22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 1},
		       {20, 18, 13, 12, 11, 9, 6, 5, 4, 3, 2, 1, -87, -900, -9, -909, -911, -80, -44, -32, -1000} };
	int[][] a2 =deepCopy(a1);
        for (int j=0; j < (a1.length - 1); j++) {
            selectionSort(a1[j]);
        }
        for (int j=0; j < (a2.length - 1); j++) {
            bubbleSort(a2[j]);
        }
        //printArray(a2);

    }
    //deep copy
    public static int[][] deepCopy(int[][] original) {
    if (original == null) {
        return null;
    }

    final int[][] result = new int[original.length][];
    for (int i = 0; i < original.length; i++) {
        result[i] = Arrays.copyOf(original[i], original[i].length);
    }
    return result;
}

    
}
