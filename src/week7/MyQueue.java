package week7;

public class MyQueue extends MyLinkedList1{

    Node front, rear;

    public MyQueue() {
        super();
        front = head;
        rear = null;
    }

    private void setRear() {
        //find Last node p
        //rear = p
    }

    public void enqueue(int data) {
        addLast(data);
        front = head;
    }

    public void addLast(int data) {
        Node p = head;
        if (p == null) {
            addFirst(data);
        } else if (p.next == null) {
            p.next = new Node(data);
            nOfNodes++;
        } else {
            while (p.next != null) {
                p=p.next;
            }
            p.next = new Node(data);
            nOfNodes++;
        }
    }

    public int dequeue() {
        return removeFirst();
    }

    public void showQ() {
        showList();
    }

    public static void main(String[] arg) {
        MyQueue q = new MyQueue();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);

        q.showQ();

        System.out.println(q.dequeue());

        q.showQ();

    }
}
