package LastPractice;

import java.util.ArrayDeque;
import java.util.Deque;

public class CountingBST {

    class Node {
        char key;
        int count;
        Node left, right, parent;

        Node(char d) {
            key = d;
            count = 1;
            left = null;
            right = null;
            parent = null;
        }

        @Override
        public String toString() {
            return "" + key + "(" + count + ")";
        }
    }

    Node root;

    public CountingBST() {
        root = null;
    }

    public void insert(char d) {
        if (d == ' ') {
            return; // 빈칸은 삽입하지 않음
        }
        if (root == null) {
            root = new Node(d);
        } else {
            insert(root, d);
        }
    }

    private void insert(Node node, char d) {
        if (d < node.key) {
            if (node.left == null) {
                node.left = new Node(d);
                node.left.parent = node;
            } else {
                insert(node.left, d);
            }
        } else if (d > node.key) {
            if (node.right == null) {
                node.right = new Node(d);
                node.right.parent = node;
            } else {
                insert(node.right, d);
            }
        } else {
            node.count++; // 동일한 키가 삽입된 경우 빈도수 증가
        }
    }

    public void delete(char d) {
        Node node = searchNode(root, d);
        if (node != null) {
            if (node.count > 1) {
                node.count--; // 빈도수 감소
            } else {
                root = deleteNode(root, d);
            }
        }
    }

    private Node searchNode(Node node, char d) {
        if (node == null || node.key == d) {
            return node;
        }
        if (d < node.key) {
            return searchNode(node.left, d);
        } else {
            return searchNode(node.right, d);
        }
    }

    private Node deleteNode(Node root, char key) {
        if (root == null) return null;

        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.key = minValue(root.right);
            root.right = deleteNode(root.right, root.key);
        }
        return root;
    }

    private char minValue(Node root) {
        char minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    public void showTree() {
        System.out.println();
        showTree(root);
        System.out.println();
    }

    private void showTree(Node node) {
        if (node != null) {
            showTree(node.left);
            System.out.print(node + " ");
            showTree(node.right);
        }
    }

    public void showLevelOrder() {
        if (root == null) {
            return;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int nodeCount = queue.size();
            System.out.print("Level " + level + " : ");
            while (nodeCount > 0) {
                Node node = queue.poll();
                System.out.print(node + " ");
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
                nodeCount--;
            }
            System.out.println();
            level++;
        }
    }

    public static void main(String[] args) {
        String input = "hi, this is a question list for final evaluation. do your best. good luck!";

        System.out.println("\n==[Q2]=================");

        CountingBST q2 = new CountingBST();
        for (int i = 0; i < input.length(); i++) {
            q2.insert(input.charAt(i));
        }
        System.out.println("\n [Show Duplication]");
        q2.showTree();
        System.out.println("\n\n [Show Level-Order]");
        q2.showLevelOrder();
        System.out.println();
    }
}
