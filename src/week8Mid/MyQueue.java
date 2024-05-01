package week8Mid;


public class MyQueue {  //  Multi-level Queue ?!
    class Node{
        int level, id;
        Node next;

        Node(int l, int i){
            level=l;
            id=i;
            next=null;
        }

        public String toString() {
            return ""+id+"["+level+"]";
        }
    }

    Node front, rear;
    MyQueue(){
        front=null;
        rear=null;
    }

    public void enqueue(int id) {
// Q 2-1) HERE !
        Node newNode = new Node(999, id);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            Node current = rear;
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) {
                rear = newNode;
            }
        }
    }

    public void enqueue(int lev, int id) {
// Q 2-2) HERE !
        Node newNode = new Node(lev, id);
        if (front == null) {
            front = newNode;
            rear = newNode;
            return;
        }
        Node current = front;
        Node previous = null;
        // front.level
        while (current != null && current.level <= lev) {
            previous = current;
            current = current.next;
        }
        if (previous == null) {
            newNode.next = front;
            front = newNode;
        } else {
            previous.next = newNode;
            newNode.next = current;
            if (current == null) {
                rear = newNode;
            }
        }
    }

    public int dequeue() {
// Q 2-3) HERE !
        if (front == null) {
            return -9999;
        } else {
            int retV = front.id;
            front = front.next;
            return retV;
        }

    }

    public int dequeue(int lev) {
// Q 2-4) HERE !
        if (front == null) {
            return -9999;
        } else {
            Node current = front;
            Node prev = null;

            while (current != null && current.level != lev) {
                prev = current;
                current = current.next;
            }
            if (current == null) {
                return -9999;
            }
            if (prev == null) {
                return dequeue();
            } else {
                prev.next = current.next;
                if (prev.next == null) {
                    rear = prev;
                }
                return current.id;
            }
        }







    }

    public void showQ() {
        System.out.print("\n>>> Queue Status  : -");
        Node temp = front;
        while (temp!=null) {
            System.out.print(" > "+temp.toString());
            temp=temp.next;
        }
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue();

        int[][] data = {{3,101},{2,102},{1,103},{2,104},{1,105},{3,106},
                {1,107},{2,108},{1,109},{3,110},{2,111},{1,112}
        };

        q.showQ();
        for (int i=0;i<6;i++) {
            q.enqueue(data[i][0], data[i][1]);
            System.out.print("\nAfter enqueue "+ data[i][0]+","+data[i][1]);
            q.showQ();
        }

        q.enqueue(113);
        q.enqueue(114);
        System.out.print("\nAfter enqueue(113), enqueue(114)" );
        q.showQ();

        System.out.print("\n\n >>> dequeued id (overall levels) = "+q.dequeue()); // returns id
        q.showQ();
        System.out.print("\n >>> dequeued id (level 3) = "+q.dequeue(3));
        q.showQ();
        System.out.print("\n >>> dequeued id (level 3) = "+q.dequeue(3));
        q.showQ();
        System.out.print("\n >>> dequeued id (level 2) = "+q.dequeue(2));
        q.showQ();
        System.out.print("\n >>> dequeued id (level 1) = "+q.dequeue(1));
        q.showQ();
        System.out.print("\n >>> dequeued id (non-level<=999) = "+q.dequeue(999));

    }
}

