package me.lceckie.juggling.box.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Leckie
 * @version HalfManTree.java, v0.1 2019/8/2 9:10
 */
public class HalfManTree {

  private Node root;

  public HalfManTree(Map<String, Integer> keys) {
    if (keys == null || keys.isEmpty()) {
      throw new IllegalArgumentException("关键字不能为空");
    }
    List<Node> nodes = new ArrayList<>();
    keys.forEach((k, v) -> nodes.add(new Node(k, v)));
    while (nodes.size() >= 2) {
      int idx1 = 0, idx2 = 0;
      if (nodes.get(1).weight < nodes.get(0).weight) {
        idx1 = 1;
      } else {
        idx2 = 1;
      }
      for (int i = 2; i < nodes.size(); i++) {
        if (nodes.get(i).weight < nodes.get(idx2).weight) {
          idx2 = i;
          if (nodes.get(idx2).weight < nodes.get(idx1).weight) {
            int tmp = idx1;
            idx1 = idx2;
            idx2 = tmp;
          }
        }
      }

      Node node1 = nodes.get(idx1);
      Node node2 = nodes.get(idx2);
      Node node = new Node(null, node1.weight + node2.weight);
      node.left = node1;
      node.right = node2;
      nodes.remove(node1);
      nodes.remove(node2);
      nodes.add(node);
    }
    root = nodes.get(0);
  }

  public void print(String prefix, Node node) {
    if (node.left == null) {
      System.out.println("k=" + node.key + ", code=" + prefix);
      return;
    }
    print(prefix + 0, node.left);
    print(prefix + 1, node.right);
  }

  static class Node {

    String key;
    int weight;
    Node left, right;

    Node(String key, int weight) {
      this.key = key;
      this.weight = weight;
    }
  }

  public static void main(String[] args) {
    Map<String, Integer> keys = new HashMap<String, Integer>() {{
      put("A", 100);
      put("B", 50);
      put("C", 20);
      put("D", 80);
      put("E", 1000);
      put("F", 59);
      put("G", 10);
      put("H", 510);
    }};
    HalfManTree halfManTree = new HalfManTree(keys);
    halfManTree.print("", halfManTree.root);
  }

}
