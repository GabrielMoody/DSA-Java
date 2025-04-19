/**
 * @author Gabriel Waworundeng
 */

package org.gabriel.DSA.DataStructure.Tree;

public class AVL {
  public class Node {
    int value;
    Node left;
    Node right;
    int height;

    public Node(int value) {
      this.value = value;
      this.left = null;
      this.right = null;
      this.height = 0;
    }
  }

  public int getHeight(Node n) {
    if(n == null) return 0;
    return n.height;
  }

  public int getBalanceFactor(Node root) {
    if(root == null) return 0;
    return getHeight(root.left) - getHeight(root.right);
  }

  private Node LL(Node x) {
    Node y = x.right;
    Node T2 = y.left;

    y.left = x;
    x.right = T2;

    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

    return y;
  }

  private Node RR(Node y) {
    Node x = y.left;
    Node T2 = x.right;

    x.right = y;
    y.left = T2;

    y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
    x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

    return x;
  }

  private Node maxValue(Node root) {
    while(root.right != null) {
      root = root.right;
    }

    return root;
  }

  public Node insert(Node root, int value) {
    if(root == null) return new Node(value);

    if(value < root.value) {
      root.left = insert(root.left, value);
    } else if(value > root.value) {
      root.right = insert(root.right, value);
    } else {
      return root;
    } 

    root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));

    int factor = getBalanceFactor(root);

    if(factor > 1) {
      if(value < root.left.value) {
        return RR(root);
      } else if(value > root.left.value) {
        root.left = LL(root.left);
        return RR(root);
      }
    } else if (factor < -1) {
      if(value > root.right.value) {
        return LL(root);
      } else if (value < root.right.value) {
        root.right = RR(root.right);
        return LL(root);
      }
    }

    return root;
  }

  public Node remove(Node root, int value) {
    if(root == null) return null;

    if(value < root.value) {
      root.left = remove(root.left, value);
    } else if (value > root.value) {
      root.right = remove(root.right, value);
    } else {
      if((root.left == null) || (root.right == null)) {
        Node temp = null;
        if(temp == root.right)
          temp = root.right;
        else 
          temp = root.left;
        
        if(temp == null) {
          temp = root;
          root = null;
        } else {
          root = temp;
        }
      } else {
        // Inorder predecessor
        Node temp = maxValue(root.left);
        root.value = temp.value;
        root.left = remove(root.left, value);
      }
    }

    if(root == null) return root;

    root.height = 1 + Math.max(getHeight(root.left), getHeight(root.right));
    int factor = getBalanceFactor(root);

    if(factor > 1) {
      if(value < root.left.value) {
        return RR(root);
      } else if(value > root.left.value) {
        root.left = LL(root.left);
        return RR(root);
      }
    } else if (factor < -1) {
      if(value > root.right.value) {
        return LL(root);
      } else if (value < root.right.value) {
        root.right = RR(root.right);
        return LL(root);
      }
    }

    return root;
  }

  public void inOrder(Node root) {
    if(root != null) {
      inOrder(root.left);
      System.out.printf("%s -> ", root.value);
      inOrder(root.right);
    }
  }

  public static void main(String[] args) {
    Node root = null;
    AVL tree = new AVL();

    root = tree.insert(root, 16);
    root = tree.insert(root, 13);
    root = tree.insert(root, 14);
    root = tree.insert(root, 10);
    root = tree.insert(root, 20);
    root = tree.insert(root, 17);
    root = tree.insert(root, 2);
    root = tree.insert(root, 1);

    // root = tree.remove(root, 15);

    System.out.println(root.left.value);
    System.out.println(tree.getBalanceFactor(root.left));
    // tree.inOrder(root);
    // tree.printTree(root);
  }
}