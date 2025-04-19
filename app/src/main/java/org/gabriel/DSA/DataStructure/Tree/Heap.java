package org.gabriel.DSA.DataStructure.Tree;

import java.util.ArrayList;

public class Heap{

  private final ArrayList<Integer> heap = new ArrayList<>();
  private boolean max = false;

  public Heap(int[] arr) {
    for(int i : arr) {
      heap.add(i);
    }
  }

  private void maxHeapify(int i) {
    if(heap.isEmpty()) return;

    int largest = i;

    int left_child = 2 * i + 1;
    int right_child = 2 * i + 2;

    if(left_child < heap.size() && heap.get(left_child) > heap.get(largest)) {
      largest = left_child;
    }

    if(right_child < heap.size() && heap.get(right_child) > heap.get(largest)) {
      largest = right_child;
    }

    if(largest != i) {
      int temp = heap.get(i);
      heap.set(i, heap.get(largest));
      heap.set(largest, temp);
      maxHeapify(largest);
    }
  }

  private void minHeapify(int i) {
    if(heap.isEmpty()) return;

    int smallest = i;

    int left_child = 2 * i + 1;
    int right_child = 2 * i + 2;

    if(left_child < heap.size() && heap.get(left_child) < heap.get(smallest)) {
      smallest = left_child;
    }

    if(right_child < heap.size() && heap.get(right_child) < heap.get(smallest)) {
      smallest = right_child;
    }

    if(smallest != i) {
      int temp = heap.get(i);
      heap.set(i, heap.get(smallest));
      heap.set(smallest, temp);
      minHeapify(smallest);
    }
  }

  public void maxHeap() {
    for(int i = heap.size() / 2 - 1; i >= 0; i--) {
      max = true;
      maxHeapify(i);
    }
  }

  public void minHeap() {
    for(int i = heap.size() / 2 - 1; i >= 0; i--) {
      max = false;
      minHeapify(i);
    }
  }

  public void insert(int e) {
    if(heap.isEmpty()) {
      heap.add(e);
    } else {
      heap.add(e);
      for(int i = heap.size() / 2 - 1; i >= 0; i--) {
        if(max) maxHeapify(i);
        else minHeapify(i);
      }
    }
  }

  public void delete(int e) {
    int idx = heap.indexOf(e);

    heap.set(idx, heap.getLast());
    heap.removeLast();

    for(int i = heap.size() / 2 - 1; i >= 0; i--) {
      if(max) maxHeapify(i);
      else minHeapify(i);
    }
  }

  public void print() {
    for(int i : heap) {
      System.out.printf("%d -> ", i);
    }
  }

  public static void main(String[] args) {
    int[] arr = {3, 9, 2, 1, 4, 5};

    Heap heap = new Heap(arr);

    heap.minHeap();
    heap.insert(7);
    heap.delete(3);
    heap.print();
  }
}