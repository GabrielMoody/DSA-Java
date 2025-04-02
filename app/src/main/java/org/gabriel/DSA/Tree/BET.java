package org.gabriel.DSA.tree;

import org.gabriel.DSA.Stack;

// Binary Expression Tree
public class BET {
  private class Node<T> {
    T value;
    Node<T> left, right;

    public Node(T value) {
      this.value = value;
    }
  }

  private Node<String> root;

  private boolean isOperator(String op) {
    return switch (op) {
      case "+", "-", "/", "*", "^" -> true;
      default -> false;
    };
  }

  public void postfixToBET(String notation) {
    String[] token = notation.split(" ");
    Stack<Node<String>> s = new Stack<>();

    for(String i : token) {
      Node<String> node = new Node<>(i);

      if(!isOperator(i)) {        
        s.push(node);
      } else {
        node.right = s.pop();
        node.left = s.pop();
        s.push(node);
      }
    }

    root = s.pop();
  }

  public void inOrder(Node root) {
    if(root != null) {
      inOrder(root.left);
      System.out.print(root.value);
      inOrder(root.right);
    }
  }

  public void postOrder(Node root) {
    if(root != null) {
      postOrder(root.left);
      postOrder(root.right);
      System.out.print(root.value);
    }
  }

  public void preOrder(Node root) {
    if(root != null) {
      System.out.print(root.value);
      preOrder(root.left);
      preOrder(root.right);
    }
  }

  public static void main(String[] args) {
    String postfix = "a b + c *";
    BET bet = new BET();
    bet.postfixToBET(postfix);

    // bet.inOrder(bet.root);
    bet.preOrder(bet.root);
    // bet.postOrder(bet.root);
  }
}