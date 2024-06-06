package week14FinExam;

import java.util.ArrayDeque;
import java.util.Deque;

public class HeapInList {

    class Node{
        char key;
        Node left, right, parent;
        public Node(char val) {
            key=val;
            left=null;
            right=null;
            parent=null;
        }
        public Node(char val, Node l, Node r, Node p) {
            key=val;
            left=l;
            right=r;
            parent=p;
        }
        public String toString() {
            return " "+key;
        }
    }

    Node heapRoot;
    Node last;

    public HeapInList() {
        heapRoot=null;
        last=null;
    }

    public void insert(char c) {
        Node parentOfNext=null;

        if (heapRoot==null) { // root = last = null
            heapRoot=new Node(c, null, null, parentOfNext);
            last=heapRoot;
        }
        else if (heapRoot==last) { //root = last != null
            parentOfNext=heapRoot;
            heapRoot.left=new Node(c, null, null, parentOfNext);
            last=heapRoot.left;
        }
        else if (last==last.parent.left) {
            parentOfNext=last.parent;
            parentOfNext.right=new Node(c, null, null, parentOfNext);
            last=parentOfNext.right;
        }
        else { // last is the right child
            parentOfNext=last;
            while (parentOfNext.parent!=null && parentOfNext==parentOfNext.parent.right)
                parentOfNext=parentOfNext.parent;

            if (parentOfNext.parent!=null ) // ie. left child
                parentOfNext=parentOfNext.parent.right;

            while (parentOfNext.left!=null)
                parentOfNext=parentOfNext.left;

            parentOfNext.left=new Node(c, null, null, parentOfNext);
            last=parentOfNext.left;
        }
        fixUpwardIter(last);
    }

    private void fixUpward(Node node) {
        if (node ==null || node.parent==null)
            return;
        if (node.key > node.parent.key) {
            swap(node, node.parent);
            fixUpward(node.parent);
        }
    }

    private void fixUpwardIter(Node node) {
        // 2-1) add your code...
        while (node != null && node.parent != null && node.key > node.parent.key) {
            swap(node, node.parent);
            node = node.parent;
        }
    }

    private void swap(Node a, Node b) {
        char temp = a.key;
        a.key=b.key;
        b.key=temp;
    }

    public void showHeap() {
        Node node = heapRoot;
        Deque<Node> q = new ArrayDeque<Node>();
        q.add(node);
        while(!q.isEmpty()) {
            Node p = q.removeFirst();
            System.out.print(p.toString());
            if (p.left!=null) q.add(p.left);
            if (p.right!=null) q.add(p.right);
        }
    }
    public Character delete() {
        if (heapRoot==null)
            return null;

        char retVal = heapRoot.key;

        if (heapRoot==last) {
            heapRoot=null;
            last=null;
            return retVal;
        }
        else {
            heapRoot.key = last.key;
            deleteLast();
            fixDownwardIter(heapRoot);
        }
        return retVal;
    }

    private void fixDownward(Node node) {
        Node bigger=node.left;
        if (node ==null || bigger == null)
            return;
        if (node.right!=null && node.left.key<node.right.key)
            bigger = node.right;

        if (node.key < bigger.key) {
            swap(node, bigger);
            fixDownward(bigger);
        }
    }
    private void fixDownwardIter(Node node) {
        // 2-2) add your code...
        while (node != null) {
            Node bigger = node.left;
            if (bigger == null)
                break;
            if (node.right != null && node.left.key < node.right.key)
                bigger = node.right;
            if (node.key >= bigger.key)
                break;
            swap(node, bigger);
            node = bigger;
        }

    }

    private void deleteLast() {  // determine new last !
        if (last==last.parent.left) {
            last.parent.left = null;

            Node prev=last.parent;
            while (prev.parent!=null && prev==prev.parent.left)
                prev=prev.parent;

            if (prev.parent!=null ) // ie. right child
                prev=prev.parent.left;

            while (prev.right!=null)
                prev=prev.right;

            last = prev;
        }
        else {  // ie. node==node.parent.right
            last.parent.right = null;
            last = last.parent.left;
        }
    }

    public static void main(String[] args) {
        HeapInList heap = new HeapInList();

        for (int i=0;i<26;i++) {
            heap.insert((char)('A'+i));
            System.out.println();

            heap.showHeap();
        }

        System.out.println("\n\n<< Tree Created >>");
        heap.showHeap();

        System.out.println("\n\n<< Sorted List >>\n");

        for (int i=0;i<26;i++) {
            heap.showHeap();
            System.out.println();
            heap.delete();
        }

    }
}
