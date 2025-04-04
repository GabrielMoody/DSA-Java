/**
 * @author Gabriel Waworundeng
 */

package org.gabriel.DSA.DataStructure;

public class S_LinkedList<T> {

  private class Node<T> {
    T value;
    Node<T> next;

    public Node(T value) {
      this.value = value;
      this.next = null;
    }
  }

  private Node<T> head;

  public T insert_last(T value) {
    Node<T> curr = head;
    Node<T> newNode = new Node<>(value);

    if(curr == null) {
      head = newNode;
      return head.value;
    } else {
      while(curr.next != null) {
        curr = curr.next;
      }
      curr.next = newNode;
    }

    return curr.value;
  }

  public void insert_first(T value) {
    Node<T> temp = head;
    Node<T> newNode = new Node<>(value);

    if(head == null) {
      head = newNode;
      return;
    }

    newNode.next = temp;
    head = newNode;
  }

  public void insert_at(T value, int pos) {
    if(pos == 1) {
      insert_first(value);
      return;
    }
    
    Node<T> newNode = new Node<>(value);
    Node<T> curr = head;

    for(int i = 1; i < pos - 1; i++) {
      if(curr == null) return;

      curr = curr.next;
    }

    Node<T> temp = curr.next;
    curr.next = newNode;
    newNode.next = temp;
  }

  public void delete_first() {
    if(head == null) return;

    head = head.next;
  }

  public void delete_last() {
    Node<T> curr = head;

    if(head == null) return;

    if(head.next == null) {
      head = null;
      return;
    }

    while(curr.next.next != null) {
      curr = curr.next;
    }

    curr.next = null;
  }

  public void delete_at(int pos) {
    if(pos == 1) {
      delete_first();
      return;
    }

    Node<T> prev = head;

    for(int i = 1; i < pos - 1; i++) {    
      prev = prev.next;
    }

    Node<T> curr = prev.next;
    Node<T> next = curr.next;

    curr = null;
    prev.next = next;
  }
    
  public void reversed() {
    // Do nothing if Linked List is empty or only have one value
    if(head == null || head.next == null) return;

    Node<T> prev = head;
    Node<T> curr = prev.next;
    Node<T> next = curr.next;

    prev.next = null;

    while(next != null) {
      curr.next = prev;
      prev = curr;
      curr = next;
      next = curr.next;
    }

    head = curr;
    head.next = prev;
  }

  public void print() {
    Node<T> curr = head;
    while(curr != null) {
      System.out.printf("%s -> ", curr.value);
      curr = curr.next;
    }
  }
  
  public static void main(String[] args) {
      S_LinkedList<Integer> ll = new S_LinkedList<>();

      ll.insert_last(10);
      ll.insert_last(20);
      ll.insert_last(25);
      ll.insert_last(30);
      ll.insert_at(15, 5);
      ll.delete_at(2);
      System.out.println("Linked list : ");
      ll.print();
      ll.reversed();
      System.out.println("\nReversed Linked list : ");
      ll.print();
  }
}