package week12;

import java.util.LinkedList;

public class HeapInLikedList {

    LinkedList<Character> heap = new LinkedList<>();

    class Node{
        Node last, left, right, parent;

    }

    public HeapInLikedList() {
        heap.add(null);

    }

    private char delete() {
        return 0;
    }


    private void insert(char c) {

    }



    private void showHeap() {

    }

    public static void main(String[] args) {
        HeapInLikedList heap = new HeapInLikedList();
        for (int i = 0; i < 26; i++) {
            heap.insert((char) ('A' + i));
            heap.showHeap();
        }

        for (int i = 0; i < 26; i++) {
            System.out.print(heap.delete());
//            heap.showHeap();
        }
    }


}
