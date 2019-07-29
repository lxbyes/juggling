package me.lceckie.juggling.box.tree;

/**
 * @author Leckie
 * @version BinarySearchTree.java, v0.1 2019/7/29 10:02
 */
public class BinarySearchTree {

  public static void main(String[] args) {
    int[] keys = {3, 19, 21, 10, 1, 0, 9, 200, 22, 32, 12};
    Node root = binarySearchTree(keys);
    midOrderPrint(root);
    Node node = searchBST(root, 200);
    System.out.println(node != null ? "FOUND" : "NOT_FOUND");
  }

  public static class Node {

    int key;
    Node lChild, rChild;

    Node(int key) {
      this.key = key;
    }
  }

  public static Node binarySearchTree(int[] keys) {
    if (keys == null || keys.length == 0) {
      return null;
    }
    Node root = new Node(keys[0]);
    for (int i = 1; i < keys.length; i++) {
      insertNode(root, keys[i]);
    }
    return root;
  }

  private static void insertNode(Node node, int key) {
    if (key < node.key) {
      if (node.lChild == null) {
        node.lChild = new Node(key);
      } else {
        insertNode(node.lChild, key);
      }
    } else {
      if (node.rChild == null) {
        node.rChild = new Node(key);
      } else {
        insertNode(node.rChild, key);
      }
    }
  }

  private static void midOrderPrint(Node node) {
    if (node.lChild != null) {
      midOrderPrint(node.lChild);
    }
    System.out.printf("%d\t", node.key);
    if (node.rChild != null) {
      midOrderPrint(node.rChild);
    }
  }

  private static Node searchBST(Node root, int k) {
    if (root == null) {
      return null;
    }
    if (root.key == k) {
      return root;
    }
    if (k < root.key) {
      return searchBST(root.lChild, k);
    } else {
      return searchBST(root.rChild, k);
    }
  }
}
