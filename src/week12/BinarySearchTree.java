package week12;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTree {

    class Node {
        int key;
        Node left, right, parent;

        Node(int d){
            key = d;
            left=null;
            right=null;
            parent=null;
        }

        public String toString() {
            return ""+key+" ";
        }
    }

    Node node;

    public BinarySearchTree() {
        node =null;
    }

//    public void insert(int d) {
//        if (root==null) {
//            root = new Node(d);
//        }
//        else {
//            insert(root, d, null);
//        }
//    }
//
//    private void insert(Node node, int d, Node parent) {
//        if (node==null) { // insert here !
//            Node newNode = new Node(d);
//            if (d <parent.key) {
//                parent.left = newNode;
//                newNode.parent = parent;
//            }
//            else { //d >parent.key
//                parent.right = newNode;
//                newNode.parent = parent;
//            }
//        }
//        else if (d <node.key)
//            insert(node.left, d, node);
//        else // d > node.key
//            insert(node.right, d, node);
//    }

    public void insert(int d) {
        if (node ==null) {
            node = new Node(d);
        }
        else {
            if (d < node.key) {
                if (node.left == null) {
                    node.left = new Node(d);
                    node.left.parent = node;

                } else {
                    insert(node.left, d);
                }
            }else {
                if (node.right == null) {
                    node.right = new Node(d);
                    node.right.parent = node;
                }else{
                    insert(node.right, d);
                }
            }

        }
    }

    private void insert(Node node, int d) {
        if (d < node.key) {
            if (node.left == null) {
                node.left = new Node(d);
                node.left.parent = this.node;

            } else {
                insert(node.left, d);
            }
        }else {
            if (node.right == null) {
                node.right = new Node(d);
                node.right.parent = node;
            }else{
                insert(node.right, d);
            }
        }
    }

    public void showTree() {

        System.out.println();
        showTree(node);
        System.out.println();

    }

    private void showTree(Node p) {
//        if (p!=null) {
//            showTree(p.left);
//            System.out.print(p.key);
//            showTree(p.right);
//        }
        levelOrder(node);
    }

    private void levelOrder(Node root) {

        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node p = queue.removeFirst();
            System.out.print(p.toString());
            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);
            }

        }

    }

    //전위
    private void preorder(Node node) {
        if (node != null) {
            System.out.print(node.toString());
            preorder(node.left);
            preorder(node.right);
        }
    }
    //중위
    private void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.toString());
            inorder(node.right);
        }
    }

    //후위
    private void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.toString());
        }
    }


    public boolean search(int d) {
        return search(node, d);
    }

    private boolean search(Node node, int d) {
        if (node==null)
            return false;
        if (node.key==d)
            return true;
        if (d<node.key)
            return search(node.left, d);
        else
            return search(node.right, d);
    }

    private Node searchNode(Node node, int d) {
        if (node==null)
            return null;
        if (node.key==d)
            return node;

        if (d<node.key)
            return searchNode(node.left, d);
        else
            return searchNode(node.right, d);
//
//        while (node != null && node.key != d) {
//            if (d < node.key) {
//                node = node.left;
//            }
//            if (d > node.key) {
//                node = node.right;
//            }
//            return node;
//
//        } iteration
    }

    private void rotateLeft(Node root) {
        if (root == this.node) {
            this.node = root.right;  // this.node = root
            root.right.parent = null;
            root.right.left = root;
            root.parent = root.right;
            root.right = null;
        } else {
            if (root == root.parent.left) {
                root.parent.left = root.right;
                root.right.parent = root.parent;
                root.right.left = root;
                root.parent = root.right;
                root.right = null;
            } else {
                root.parent.right = root.right;
                root.right.parent = root.parent;
                root.right.left = root;
                root.parent = root.right;
                root.right = null;
            }
        }
    }

    private void rotateRight(Node pp) {
        if (pp == this.node) {
            this.node = pp.left;  // this.node = root
            pp.left.parent = null;
            pp.left.right = pp;
            pp.parent = pp.left;
            pp.left = null;
        } else {
            if (pp == pp.parent.left) {
                pp.parent.left = pp.left;
                pp.left.parent = pp.parent;
                pp.left.right = pp;
                pp.parent = pp.left;
                pp.left = null;
            } else {
                pp.parent.right = pp.left;
                pp.left.parent = pp.parent;
                pp.left.right = pp;
                pp.parent = pp.left;
                pp.left = null;
            }
        }
    }


    public void delete(int d) {
        Node node = searchNode(this.node, d);
        if (node != null) {   //  <----  ADDED !
            if (this.node == node)
                this.node = deleteNode(this.node);
            else {
                if (node.key<node.parent.key)
                    node.parent.left = deleteNode(node);
                else
                    node.parent.right = deleteNode(node);
            }
        }
    }

    private Node deleteNode(Node node) {
        // case 1 : no child
        if (node.left==null && node.right==null)
            return null;

            // case 2 : 1 child
        else if (node.left==null && node.right!=null) {
            node.right.parent = node.parent;
            return node.right;
        }
        else if (node.left!=null && node.right==null) {
            node.left.parent = node.parent;
            return node.left;
        }

        // case 3 : 2 children
        else {
            Node s = node.right;
            while (s.left!=null)
                s=s.left;
            // now, s = successor !
            node.key = s.key;
            // now, let's delete successor !
            if (s==node.right) {
                node.right = s.right;
                if ( s.right!=null)
                    s.right.parent = node;
            }
            else {
                s.parent.left = s.right;
                if ( s.right!=null)
                    s.right.parent = s.parent;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        int [] keys = {4,7,5,1,0,3,9,2,6,8};


        BinarySearchTree t = new BinarySearchTree();

        for (int i=0; i<keys.length;i++) {
            t.insert(keys[i]);
            t.showTree();
        }

        System.out.println(t.node.key);

        Node node = t.searchNode(t.node, 7); // 7 노드를 찾습니다.
        if (node != null) {
            System.out.println("\nBefore rotate left:");
            t.showTree();

            // 7 노드를 왼쪽으로 회전합니다.
            t.rotateLeft(node);

            System.out.println("\nAfter rotate left:");
            t.showTree();
        }

        node = t.searchNode(t.node, 4); // 4 노드를 찾습니다.
        if (node != null) {
            System.out.println("\nBefore rotate right:");
            t.showTree();

            // 4 노드를 오른쪽으로 회전합니다.
            t.rotateRight(node);

            System.out.println("\nAfter rotate right:");
            t.showTree();
        }

        System.out.println(t.search(3));
        System.out.println(t.search(11));

        t.delete(3);
        System.out.println("\nAfter delete 3 (case2 : only left child)");
        t.showTree();
        t.delete(4);
        System.out.println("\nAfter delete 4 (case3) ");
        t.showTree();
        t.delete(2);
        System.out.println("\nAfter delete 2 (case1 : right end) :");
        t.showTree();
        t.delete(10);
        System.out.println("\nAfter delete 10 (Not Found) :");
        t.showTree();

    }

}
