package week13;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class HeapInList {

    LinkedList<Character> heap = new LinkedList<>();

    class Node {
        char data;
        Node last, left, right, parent;

        public Node(char d) {
            data = d;
            left = null;
            right = null;
            parent = null;

        }

        public Node(char d, Node l, Node r, Node p) {
            data = d;
            left = l;
            right = r;
            parent = p;
        }

        public String toString() {
            return " "+data;
        }

    }

    Node root;
    Node last;

    public HeapInList() {
        root = null;
        last = null;
        heap.add(null);
    }
    public void insert(char c) {
        Node parentOfNext = null;

        if (root == null) {
            root = new Node(c, null, null, parentOfNext);
            last = root;
        } else if (root == last) {
            parentOfNext = root;
            root.left = new Node(c, null, null, parentOfNext);
            last = root.left;
        } else if (last == last.parent.left) {
            parentOfNext = last.parent;
            last.parent.right = new Node(c, null, null, parentOfNext);
            last = last.parent.right;
        } else { // last == last.parent.right
            parentOfNext = last;

            while (parentOfNext != null && parentOfNext.parent != null && parentOfNext == parentOfNext.parent.right) {
                parentOfNext = parentOfNext.parent;
            }

            if (parentOfNext != null && parentOfNext.parent != null) {
                parentOfNext = parentOfNext.parent.right;
            } else if (parentOfNext != null && parentOfNext.parent == null) {
                parentOfNext = root;
                while (parentOfNext.left != null) {
                    parentOfNext = parentOfNext.left;
                }
            }

            while (parentOfNext != null && parentOfNext.left != null) {
                parentOfNext = parentOfNext.left;
            }

            if (parentOfNext != null) {
                parentOfNext.left = new Node(c, null, null, parentOfNext);
                last = parentOfNext.left;
            }
        }

        fixUpward(last);
    }




    private void fixUpward(Node node) {
        if (node.parent == null) {
            return;
        }
        if (node.data > node.parent.data) {
            swap(node, node.parent);
            fixUpward(node.parent);
        }
    }


    private Character delete() {
        if (root == null) {
            return null;
        }
        char retVal = root.data;
        if (root == last) {
            root = null;
            last = null;
        } else {
            root.data = last.data;
            deleteLast();
            fixDownword(root);
        }
        return retVal;
    }

    private void deleteLast() {
        if (last == last.parent.right) {
            last.parent.right = null;
            last = last.parent.left;
        } else { // current last is the left child
            last.parent.left = null;
            Node newLast = last.parent;

            while (newLast.parent != null && newLast == newLast.parent.left) {
                newLast = newLast.parent;
            }

            if (newLast.parent != null) {
                newLast = newLast.parent.left;
            }

            while (newLast.right != null) {
                newLast = newLast.right;
            }

            last = newLast;
        }
    }

    private void fixDownword(Node node) {
        Node bigger = node.left;
        if (bigger == null) {
            return;
        } else if (node.right != null && node.left.data < node.right.data) {
            bigger = node.right;
        }

        if (node.data < bigger.data) {
            swap(node, bigger);
            fixDownword(bigger);
        }
    }


    private void swap(Node node1, Node node2) {
        char temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }


    private void showHeap() {
        Deque<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node p = q.removeFirst();
            System.out.print(p.toString());
            if (p.left != null) {
                q.add(p.left);
            }
            if (p.right != null) {
                q.add(p.right);
            }
        }
    }

    public static void main(String[] args) {
        HeapInList heap = new HeapInList();
        for (int i = 0; i < 26; i++) {
            heap.insert((char) ('A' + i));
            heap.showHeap();
            System.out.println();
        }

        for (int i = 0; i < 25; i++) {
            System.out.print(heap.delete());
            heap.showHeap();
            System.out.println();
        }
    }


}
