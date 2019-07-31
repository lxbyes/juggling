package me.lceckie.juggling.box.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Leckie
 * @version AVLTree.java, v0.1 2019/7/29 11:35
 */
public class AVLTree {

  public Node root;

  List<Node> nodes = new ArrayList<>();

  public AVLTree(int[] values) {
    if (values == null || values.length == 0) {
      System.out.println("没有节点");
      return;
    }
    root = new Node(values[0]);
    nodes.add(root);
    for (int i = 1; i < values.length; i++) {
      insertNode(root, values[i]);
    }
  }

  static class Node {

    int depth;
    Node parent;
    int value;
    Node lChild, rChild;

    Node(int value) {
      this.depth = 0;
      this.value = value;
    }
  }

  private int getDepth(Node node) {
    if (node == null) {
      return 0;
    }
    return node.depth;
  }

  private int getBalance(Node node) {
    if (node == null) {
      return 0;
    }
    return getDepth(node.lChild) - getDepth(node.rChild);
  }

  private void updateDepth(Node node) {
    if (node == null) {
      return;
    } else {
      int lDepth = getDepth(node.lChild);
      int rDepth = getDepth(node.rChild);
      int depth = Math.max(lDepth, rDepth) + 1;
      if (node.depth != depth) {
        node.depth = depth;
        // 当前节点depth变了，父节点相应也可能变化
        updateDepth(node.parent);
      }
    }
  }

  private void rRotate(Node node) {
    Node parent = node.parent;
    Node newRoot = node.lChild;

    node.lChild = newRoot.rChild;
    if (newRoot.rChild != null) {
      newRoot.rChild.parent = node;
    }
    updateDepth(node);

    newRoot.rChild = node;
    node.parent = newRoot;
    updateDepth(newRoot);

    if (parent != null) {
      if (parent.lChild == node) {
        parent.lChild = newRoot;
      } else {
        parent.rChild = newRoot;
      }
    } else {
      root = newRoot;
    }
    newRoot.parent = parent;
  }

  private void lRotate(Node node) {
    Node parent = node.parent;
    Node newRoot = node.rChild;

    node.rChild = newRoot.lChild;
    if (newRoot.lChild != null) {
      newRoot.lChild.parent = node;
    }
    updateDepth(node);

    newRoot.lChild = node;
    node.parent = newRoot;
    updateDepth(newRoot);

    if (parent != null) {
      if (parent.lChild == node) {
        parent.lChild = newRoot;
      } else {
        parent.rChild = newRoot;
      }
    } else {
      root = newRoot;
    }
    newRoot.parent = parent;
  }

  private void lrRotate(Node node) {
    lRotate(node.lChild);
    rRotate(node);
  }

  private void rlRotate(Node node) {
    rRotate(node.rChild);
    lRotate(node);
  }

  private void insertNode(Node node, int value) {
    if (value < node.value) {
      if (node.lChild == null) {
        Node n = new Node(value);
        n.parent = node;
        node.lChild = n;
        updateDepth(n);
        adjust(node, value);
        nodes.add(n);
      } else {
        insertNode(node.lChild, value);
      }
    } else {
      if (node.rChild == null) {
        Node n = new Node(value);
        n.parent = node;
        node.rChild = n;
        updateDepth(n);
        adjust(node, value);
        nodes.add(n);
      } else {
        insertNode(node.rChild, value);
      }
    }
  }

  public void deleteNode(int value) {
    
  }

  private void adjust(Node node, int value) {
    if (getBalance(node) > 1) {
      if (node.lChild.value > value) {
        rRotate(node);
      } else {
        lrRotate(node);
      }
    } else if (getBalance(node) < -1) {
      if (node.rChild.value < value) {
        lRotate(node);
      } else {
        rlRotate(node);
      }
    }
    if (node.parent != null) {
      adjust(node.parent, value);
    }
  }

  private static void midOrderPrint(Node node) {
    if (node.lChild != null) {
      midOrderPrint(node.lChild);
    }
    System.out.printf("%d\t", node.value);
    if (node.rChild != null) {
      midOrderPrint(node.rChild);
    }
  }

  private static void listTree(Node root) {
    Queue<Node> queue = new ArrayBlockingQueue(15);
    queue.add(root);
    while (queue.size() > 0) {
      Node node = queue.poll();
      System.out.printf("%d\t", node.value);
      if (node.lChild != null) {
        queue.add(node.lChild);
      }
      if (node.rChild != null) {
        queue.add(node.rChild);
      }
    }
  }

  public static void main(String[] args) {
    int[] values = {3, 19, 890, 54, 665, 654, 21, 10, 1, 0, 9, 200, 22, 32, 12, 100, 102, 302, 678, 666, 590, 904};
    AVLTree avlTree = new AVLTree(values);
    midOrderPrint(avlTree.root);
    System.out.println();
    System.out.println("------------------");
    listTree(avlTree.root);
    System.out.println();
    System.out.println("------------------");
    for (Node node : avlTree.nodes) {
      System.out.printf("value=%d, depth=%d, lchild=%s, rchild=%s\n", node.value, node.depth,
          node.lChild != null ? node.lChild.value : null, node.rChild != null ? node.rChild.value : null);
    }
  }
}
