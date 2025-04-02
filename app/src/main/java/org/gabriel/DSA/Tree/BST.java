package org.gabriel.DSA.Tree;

// Binary Serach Tree
public class BST {
  private class Node<E>{
    E value;
    Node<E> left, right;

    public Node(E value) {
      this.value = value;
      this.left = null;
      this.right = null;
    }
  }

  public Node<Integer> root;

  public BST(int value) {
    root = new Node<>(value);
  }

  public Node<Integer> insert(int value, Node<Integer> root) {  
    if(root == null) {
      return new Node<>(value);
    }

    if(value < root.value) {
      root.left = insert(value, root.left);
    } else {
      root.right = insert(value, root.right);
    }

    return root;
  } 

  public Node<Integer> find(int value, Node<Integer> root) {
    if(value == root.value) return root;

    if(value < root.value) {
      root.left = find(value, root.left);
    } else {
      root.right = find(value, root.right);
    }

    return root;
  }

  public Node<Integer> maxValue(Node<Integer> root) {
    while(root.right != null) {
      root = root.right;
    }
    
    return root;
  }

  public Node<Integer> delete(int value, Node<Integer> root) {
    if(root == null) {
      return root;
    }

    if(value < root.value) {
      root.left = delete(value, root.left);
    } else if (value > root.value) {
      root.right = delete(value, root.right);
    } else {
      if (root.left == null) {
        Node<Integer> temp = root.right;
        root.right = null;
        root = temp;
      } else if (root.right == null) {
        Node<Integer> temp = root.left;
        root.left = null;
        root = temp;
      } else {
        // Inorder predecessor
        Node<Integer> max = maxValue(root.left);
        delete(max.value, root.left);
        root.value = max.value;
      }
    }

    return root;
  }

  public void inOrder(Node<Integer> root) {
    if(root != null) {
      inOrder(root.left);
      System.out.printf("%s ", root.value);
      inOrder(root.right);
    }
  }

  public static void main(String[] args) {
    BST tree = new BST(20);

    tree.insert(40, tree.root);
    tree.insert(15, tree.root); 
    tree.insert(17, tree.root);
    tree.insert(19, tree.root);
    tree.insert(10, tree.root);
    tree.insert(12, tree.root);
    tree.insert(41, tree.root);
    tree.inOrder(tree.root);

    tree.delete(20, tree.root);

    System.out.println();
    
    tree.inOrder(tree.root);
    System.out.println(tree.root.value);
  }
}