/**
* @author Gabriel Waworundeng
*/

package org.gabriel.DSA.DataStructure;

import java.util.ArrayList;

public class Stack<T>{
  private int top = -1; 
  private final ArrayList<T> stack = new ArrayList<>();

  public void push(T value) {
    this.stack.addLast(value);
    this.top++;
  }

  public T pop() {
    T value = stack.get(top);
    this.stack.remove(this.top);
    this.top--;
    return value;
  }

  public T peek() {
    return this.stack.get(top);
  }

  public boolean isEmpty() {
    return this.top < 0;
  }

  public void print() {
    for(int i = stack.size() - 1; i >= 0; i--) {
      System.out.printf("%s ", stack.get(i));
    }
  }

  public static void main(String[] args) {
      Stack<String> s = new Stack<>();

      s.push("10");
      s.push("+");
      s.push("20");
      s.push("=");
      System.out.println(s.pop());
      System.out.println(s.pop());
      // s.print(); 
  }
}