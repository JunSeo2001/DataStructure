package week11;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleTree {

    class Node {
        char data;
        Node left;
        Node right;

        Node(char c) {
            data = c;
            left = null;
            right = null;
        }

        public String toString() {
            return "" + data;
        }
    }

    Node root;
    char [] array;

    SimpleTree() {
        root = null;
        array = new char[10];
    }

    public Node makeTree(char c) {
        root = new Node(c);
        return root;
    }

    public Node makeTree(SimpleTree leftSubTree, char c, SimpleTree rightSubTree) {
        root = new Node(c);
        root.left = leftSubTree.root;
        root.right = rightSubTree.root;
        return root;
    }

    public void showTree() {
        showTree(root);
    }
    public void showTree(Node p) {
        if(p != null){
            showTree(p.left);
            showTree(p.right);
            System.out.println(p.toString());
        }
    }

    private void toArray() {
        toArray(root,1);
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(" " + array[i]);
        }
    }

    private void toArray(Node p, int i) {
        if (p != null) {
            array[i] = p.data;
            toArray(p.left, 2 * i);
            toArray(p.right, 2*i+1);
        }
    }

    public int getNodeCount() {
        return getNodeCount(root);
    }

    private int getNodeCount(Node p) {
        if (p == null) {
            return 0;
        } else {
            return 1 + getNodeCount(p.left) + getNodeCount(p.right);
        }
    }

    private int getHeight() {
        return getHeight(root);
    }

    private int getHeight(Node p) {
        if (p == null) {
            return 0;
        } else {
            return 1 + Math.max(getHeight(p.left), getHeight(p.right));
        }
    }


    private void levelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(current.data + " ");

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        System.out.println();
    }



    public static void main(String[] args) {
        //a*b-c/d를 트리로 만들려고 하는 중
        SimpleTree t1 = new SimpleTree();
        t1.makeTree('a');
        SimpleTree t2= new SimpleTree();
        t2.makeTree('b');
        SimpleTree t3= new SimpleTree();
        t3.makeTree('c');
        SimpleTree t4= new SimpleTree();
        t4.makeTree('d');

        SimpleTree t5= new SimpleTree();
        t5.makeTree(t1, '*', t2);
        SimpleTree t6= new SimpleTree();
        t6.makeTree(t3, '/', t4);

        SimpleTree t7 = new SimpleTree();
        t7.makeTree(t5, '-', t6);

        t7.showTree();

        t7.toArray();

        System.out.println("Node Count = " +t7.getNodeCount());
        System.out.println("Height = " +t7.getHeight());

        t7.levelOrder();
    }




}
