/* Name: Subah Mehrotra
   Course: CSC 226
*/

import java.util.Arrays;


public class QuickSelect {

    // Calls the helper method to get the kth smallest number
    public static int QuickSelect(int[] A, int k){
      int ele = Helper(A, 0, A.length-1, k);
      return ele;
    }

    // Returns the kth smallest number
    public static int Helper(int[] A, int left, int right, int k) {
        if (k > 0 && k <= right - left + 1) {
            int n = right - left + 1, i;
            int median[] = new int[(n + 6) / 7]; // Number of groups
            int MM;
            for (i = 0; i < n / 7; i++) {
                Arrays.sort(A,left + i * 7,(left + i * 7) + 7);
                median[i] = A[((left + i * 7) + 7)/2];
            }
            if (i * 7 < n) {
                Arrays.sort(A,left + i * 7,(left + i * 7) + (n%7));
                median[i] = A[(left + i * 7 + n%7)/2];
                i++;
            }
            if(i == 1){
            MM = median[i-1];
            }
            else{
              MM = Helper(median, 0, i - 1, i / 2); // Find median of medians
            }
            int pivot = partition(A, left, right, MM); // Find pivot
            if (pivot - left == k - 1) {
                return A[pivot];
            }
            if (pivot - left > k - 1) {
                return Helper(A, left, pivot - 1, k);
            }
            return Helper(A, pivot + 1, right, k - pivot + left - 1);
        }
        return -1; // Returning the default value for an invalid k input
    }

    // Helps to find the pivot
    public static int partition(int[] A, int left, int right, int val) {
        int i;
        for (i = left; i < right; i++) {
            if (A[i] == val) {
                break;
            }
        }
        swap(A, i, right);
        int pivotEle = A[right];
        int index = left;
        for (i = left; i <= right; i++) {
            if (A[i] < pivotEle) { // If current element is smaller than pivot element
                swap(A, index, i); // Swap that element
                index++;
            }
        }
        swap(A, right, index);
        return index;
    }

    // Helps to swap elements in an array
    public static void swap(int[] A, int first, int second){
      int temp = A[first];
      A[first] = A[second];
      A[second] = temp;
    }

    public static void main(String[] args){
        int[] A = {50, 54, 49, 49, 48, 49, 56, 52, 51, 52, 50, 59, 65, 75, 85, 85};
        //int[] A = {10, 35, 12, 243};
        //System.out.println("The median is " + QuickSelect(A, (A.length+1)/2));
        System.out.println(QuickSelect(A, 0));
        System.out.println(QuickSelect(A, 1));
        System.out.println(QuickSelect(A, 14));
        System.out.println(QuickSelect(A, 7));
        System.out.println(QuickSelect(A, 16));
        System.out.println(QuickSelect(A, 19));

    }
}
