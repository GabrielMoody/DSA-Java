package org.gabriel.DSA.Algorithm.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

  /**
   * Merge Sort
   * Time Complexity: O(n log n)
   */
  public int[] mergeSort(int[] arr) {
    if(arr.length > 1) {
      int mid = arr.length / 2;

      int[] L = Arrays.copyOfRange(arr, 0, mid); 
      int[] R = Arrays.copyOfRange(arr, mid, arr.length);

      mergeSort(L);
      mergeSort(R);

      int i = 0;
      int j = 0;
      int k = 0;

      while(i < L.length && j < R.length) {
        if(L[i] < R[j]) {
          arr[k] = L[i];
          i++;
        } else {
          arr[k] = R[j];
          j++;
        }
        k++;
      }

      while(i < L.length) {
        arr[k] = L[i];
        i++;
        k++;
      }

      while(j < R.length) {
        arr[k] = R[j];
        j++;
        k++;
      }
    }

    return arr;
  }


  /**
   * Quick Sort
   * Time Complexity: O(n log n)
   * Worst Case: O(n^2)
   */
  public int[] quickSort(int[] arr, int low, int high) {
    if(low < high) {
      int pivot = partition(arr, low, high);

      quickSort(arr, low, pivot - 1);
      quickSort(arr, pivot + 1, high);
    }

    return arr;
  }

  private int partition(int[] arr, int low, int high) {
    int pivot = high;
    int second = low - 1;

    for(int i = low; i < high; i++) {
      if(arr[i] <= arr[pivot]) {
        second++;
        int temp = arr[second];
        arr[second] = arr[i];
        arr[i] = temp;
      }
    }

    int temp = arr[pivot];
    arr[pivot] = arr[second + 1];
    arr[second + 1] = temp;
    
    return second + 1;
  }

  /**
   * Counting Sort
   * Time Complexity: O(n + max) -> max is the largest element in the array
   */
  public int[] countingSort(int[] arr) {
    int max = 0;
    int[] output = new int[arr.length + 1];

    for(int i : arr) {
      if(i > max) {
        max = i;
      }
    }

    int[] counting = new int[max + 1];

    for(int i : arr) {
      counting[i]++;
    }

    for(int i = 1; i <= max; i++) {
      counting[i] += counting[i - 1];
    }

    for(int i = arr.length - 1; i >= 0; i--) {
      output[counting[arr[i]] - 1] = arr[i];
      counting[arr[i]]--;
    }

    return Arrays.copyOf(output, arr.length);
  }

  /**
   * Radix Sort
   * Time Complexity: O(d * (n + k))
   * d = number of digits of the largest number
   * n = total elements to sort
   * k = base number (i.e. base 10 number)
   */
  public int[] radixSort(int[] arr) {
    int max = 0;
    for(int i : arr) {
      if(i > max) {
        max = i;
      }
    }

    int digit = String.valueOf(Math.abs(max)).length();
    int place = 1;

    for(int i = 0; i < digit; i++) {
      arr = countingSort(arr, place);
      place *= 10;
    }

    return arr;
  }

  private int[] countingSort(int[] arr, int place) {
    int[] counting = new int[10];
    int[] output = new int[arr.length + 1];

    for(int i : arr) {
      int idx = (i / place) % 10;
      counting[idx]++;
    }

    for(int i = 1; i < arr.length; i++) {
      counting[i] += counting[i - 1];
    }

    for(int i = arr.length - 1; i >= 0; i--) {
      int idx = (arr[i] / place) % 10;
      output[counting[idx] - 1] = arr[i];
      counting[idx]--;
    }

    return Arrays.copyOf(output, arr.length);
  }

  /**
   * Bucket Sort
   * Time Complexity: O(n) Average Case
   */
  public double[] bucketSort(double[] arr) {
    ArrayList<ArrayList<Double>> bucket = new ArrayList<>();
    double[] output = new double[arr.length + 1];

    for(double a : arr) {
      bucket.add(new ArrayList<>());
    }

    for(double i : arr) {
      int idx = (int) i * 10;
      bucket.get(idx).add(i);
    }

    for(int i = 0; i < bucket.size(); i++) {
      Collections.sort(bucket.get(i));
    }

    int idx = 0;

    for(int i = 0; i < bucket.size(); i++) {
      for(int j = 0; j < bucket.get(i).size(); j++) {
        output[idx] = bucket.get(i).get(j);
        idx++;
      }
    }

    return Arrays.copyOf(output, arr.length);
  }

  /**
   * Heap Sort
   * Time Complexity: O(n log n)
   */
  public int[] heapSort(int[] arr) {
    for(int j = arr.length / 2 - 1; j >= 0; j--) {
      heapify(arr, arr.length, j);
    }

    for(int i = arr.length - 1; i >= 0; i--) {
      int temp = arr[i];
      arr[i] = arr[0];
      arr[0] = temp;

      heapify(arr, i, 0);
    }

    return arr;
  }

  private int[] heapify(int[] arr, int size, int i) {
    int largest = i;
    int left_child = 2 * i + 1;
    int right_child = 2 * i + 2;

    if(left_child < size && arr[left_child] > arr[largest]) {
      largest = left_child;
    }

    if(right_child < size && arr[right_child] > arr[largest]) {
      largest = right_child;
    }

    if(largest != i) {
      int temp = arr[i];
      arr[i] = arr[largest];
      arr[largest] = temp;
      heapify(arr, size, largest);
    }

    return arr;
  }

  /**
   * Shell Sort
   * Time Complexity: O(n log n) Average case
   */
  public int[] shellSort(int[] arr) {
    int n = arr.length;
    int interval = n / 2;

    while(interval > 0) {
      for(int i = interval; i < n; i++) {
        int temp = arr[i];
        int j = i;

        while(j >= interval && arr[j - interval] > temp) {
          arr[j] = arr[j - interval];
          j -= interval;
        }

        arr[j] = temp;
      }
      interval /= 2;  
    }
    
    return arr;
  }

  public static void main(String[] args) {
    Sorting sort = new Sorting();

    int[] arr = {37, 12, 45, 3, 28, 19, 41, 7, 33, 22};

    double[] arrBucket = {0.42, 0.32, 0.33, 0.52, 0.37, 0.47, 0.51};

    // int[] sorted = sort.bubbleSort(arr);
    // int[] sorted = sort.selectionSort(arr);
    // int[] sorted = sort.insertionSort(arr);
    // int[] sorted = sort.mergeSort(arr);
    // int[] sorted = sort.quickSort(arr, 0, arr.length - 1);
    // int[] sorted = sort.countingSort(arr);
    // int[] sorted = sort.radixSort(arr);
    // double[] sorted = sort.bucketSort(arrBucket);
    // int[] sorted = sort.heapSort(arr);
    int[] sorted = sort.shellSort(arr);

    for(int i : sorted) { 
      System.out.printf("%d ", i);
    }
  }
}
