package LastPractice;

import java.util.ArrayList;

class Task {
    String task;
    int priority;
    long insertionTime;

    public Task(String task, int priority, long insertionTime) {
        this.task = task;
        this.priority = priority;
        this.insertionTime = insertionTime;
    }

    @Override
    public String toString() {
        return task + "(" + priority + ")";
    }
}

public class HeapScheduler {
    ArrayList<Task> heap;
    private long timeCounter;

    public HeapScheduler() {
        heap = new ArrayList<>();
        heap.add(null); // 0번 인덱스를 비워둡니다.
        timeCounter = 0;
    }

    public void insert(String task, int priority) {
        timeCounter++;
        Task newTask = new Task(task, priority, timeCounter);
        heap.add(newTask);
        fixUpward(heap.size() - 1);
    }

    private void fixUpward(int index) {
        int pIndex = index / 2;
        if (pIndex > 0) {
            Task current = heap.get(index);
            Task parent = heap.get(pIndex);
            if (current.priority > parent.priority ||
                    (current.priority == parent.priority && current.insertionTime < parent.insertionTime)) {
                swap(index, pIndex);
                fixUpward(pIndex);
            }
        }
    }

    private void swap(int i, int j) {
        Task temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public Task delete() {
        if (heap.size() <= 1) {
            return null;
        }
        Task retVal = heap.get(1);
        if (heap.size() == 2) {
            heap.remove(1);
        } else {
            heap.set(1, heap.remove(heap.size() - 1));
            fixDownward(1);
        }
        return retVal;
    }

    private void fixDownward(int index) {
        int largerChild = 2 * index; // 현재 노드의 왼쪽 자식 노드의 인덱스
        if (largerChild >= heap.size()) {
            return; // 왼쪽 자식 노드가 없는 경우 종료
        }
        // 오른쪽 자식 노드가 존재하고, 오른쪽 자식 노드가 더 우선순위가 높은 경우
        if (largerChild + 1 < heap.size() &&
                (heap.get(largerChild).priority < heap.get(largerChild + 1).priority ||
                        (heap.get(largerChild).priority == heap.get(largerChild + 1).priority &&
                                heap.get(largerChild).insertionTime > heap.get(largerChild + 1).insertionTime))) {
            largerChild++; // 오른쪽 자식 노드로 변경
        }
        // 현재 노드와 더 큰 우선순위를 가지는 자식 노드와 비교
        if (heap.get(index).priority < heap.get(largerChild).priority ||
                (heap.get(index).priority == heap.get(largerChild).priority &&
                        heap.get(index).insertionTime > heap.get(largerChild).insertionTime)) {
            swap(index, largerChild); // 교환
            fixDownward(largerChild); // 재귀 호출
        }
    }


    public boolean isEmpty() {
        return heap.size() <= 1;
    }

    public void showHeap() {
        System.out.println(heap);
    }

    public static void main(String[] args) {
        System.out.println("\n==[Q3]=================");

        HeapScheduler q3 = new HeapScheduler();
        q3.insert("cooking", 3);
        q3.insert("date", 5);
        q3.insert("tennis", 2);
        q3.insert("swimming", 2);
        q3.insert("study", 4);
        q3.insert("sleeping", 1);
        q3.insert("game", 3);
        q3.showHeap();

        System.out.println("\n[To-do List]");
        int priority = 1;
        while (!q3.isEmpty()) {
            System.out.println(priority + " : " + q3.delete());
            priority++;
        }
    }
}
