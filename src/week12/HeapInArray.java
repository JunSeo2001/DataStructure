package week12;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class HeapInArray {

    ArrayList<Character> heap = new ArrayList<>();

    public HeapInArray() {
        heap.add(null);
    }

    private void insert(char ch) {
        int lastIndex = heap.size();
        heap.add(lastIndex, ch);
        fixUpward(lastIndex);
    }

    private void fixUpward(int index) {
        int pIndex = index/2;
        if (pIndex > 0) {
            if (heap.get(index) > heap.get(pIndex)) {
                swap(index, pIndex);
                fixUpward(pIndex);
            }
        }
    }

    private void swap(int i, int j) {
        char temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }


    private void showHeap() {
        System.out.println(heap);
    }

    private Character delete() {
        if (heap.size() <= 1) {
            return null;
        }
        char retVal = heap.get(1);
        if (heap.size() == 2) {
            heap.remove(1); // 1 == index 1
        } else {
            heap.set(1, heap.removeLast());
            fixDownward(1);
        }
        return retVal;
    }

    private void fixDownward(int i) {
        int bigger = 2 * i;
        if (bigger >= heap.size()) {
            return;
        }
        if ((bigger + 1) < heap.size() && (heap.get(bigger)) < (heap.get(bigger + 1))) {
            bigger++;
        }
        if (heap.get(i) < heap.get(bigger)) {
            swap(i, bigger);
            fixDownward(bigger);
        }

    }


    public static void main(String[] args) {
        HeapInArray heap = new HeapInArray();
        for (int i = 0; i < 26; i++) {
            heap.insert((char) ('A' + i));
            heap.showHeap();
        }

        for (int i = 0; i < 26; i++) {
            System.out.print(heap.delete());
            heap.showHeap();
        }
        int i = 1 /2;
        System.out.println(i);
    }



}
