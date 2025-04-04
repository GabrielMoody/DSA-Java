package org.gabriel.DSA.Algorithm.Sorting;

/**
 * @author gabriel
 */

public class Sorting {
  /**
     * Bubble Sort
     * Time Complexity : O(n^2)
    */
  public int[] bubbleSort(int[] arr) {
    for(int i = 0; i < arr.length; i++) {
      // Set the swaped value to true if there is a swapped element
      boolean swapped = false;

      for(int j = 0; j < arr.length - 1 - i; j++) {
        if(arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          swapped = true;
        }

      }

      // If the swapped is false, meaning there is no swapping process
      // And the array is considered sorted
      if(!swapped) return arr;
    }

    return arr;
  }

  /**
   * Selection Sort 
   * Time complexity : O(n^2)
   */
  public int[] selectionSort(int[] arr) {
    for(int i = 0; i < arr.length - 1; i++) {
      // Save the index of lowest element
      int low = i;

      for(int j = 0 + i + 1; j < arr.length; j++) {
        if(arr[j] < arr[low]) {
          low = j;
        }
      }

      int temp = arr[i];
      arr[i] = arr[low];
      arr[low] = temp;
    }

    return arr;
  }

  /**
   * Insertion Sort
   * Time Complexity: O(n^2)
   */
  public int[] insertionSort(int[] arr) {

    for(int i = 1; i < arr.length; i++){
      for(int j = i; j > 0 && arr[j] < arr [j-1]; j--) {
        if(arr[j] < arr[j-1]) {
          int temp = arr[j-1];
          arr[j-1] = arr[j];
          arr[j] = temp;
        }
      }
    }

    return arr;
  }


  public static void main(String[] args) {
    Sorting sort = new Sorting();

    int[] arr = {20, 2, 3, 7, 21, 83, 15};

    // int[] sorted = sort.bubbleSort(arr);
    // int[] sorted = sort.selectionSort(arr);
    int[] sorted = sort.insertionSort(arr);

    for(int i : sorted) { 
      System.out.printf("%d ", i);
    }
  }
}
