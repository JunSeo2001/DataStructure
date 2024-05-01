package week9;

public class MyLinkedList2 {
    class Node {
        int data;
        Node prev;
        Node next;

        public Node(int d) {
            data = d;
            prev = null;
            next = null;
        }

        public String toString() {
            return "" + data;
        }
    }

    Node tail;
    Node head;

    public MyLinkedList2() {
        tail = null;
        head = null;
    }

    public void add(int data) {
        addFirst(data);   //add first as default!
    }

    public void addFirst(int data) {
        if (head == null) {
            head = new Node(data);
            tail = head;
        } else {
            Node newNode = new Node(data);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void addLast(int data) {
        if (tail == null) {
            tail = new Node(data);
            head = tail;
        } else {
            Node newNode = new Node(data);
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }



    public void add(int index, int data) {
        if (!validIndex(index)){
            System.out.println("Invalid Index!");
            return;
        }
        if (index == 0) {
            addFirst(data);
        } else if (index == nOfNodes()) {
            addLast(data);
        } else {
            Node newNode = new Node(data);
            Node p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            //p의 뒤에 넣는다.
            newNode.next = p.next;
            p.next.prev = newNode;
            p.next = newNode;
            newNode.prev = p;
        }
    }

    private boolean validIndex(int index) {
        if(index < 0 || index >= nOfNodes()) {
            return false;
        } else {
            return true;
        }
    }

    private int nOfNodes() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public int removeFirst() {
        if (head == null) {
            return -9999;     // ie. null!
        } else {
            int retV = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            } else {
                head.prev = null;
            }
            return retV;
        }
    }

    public int removeLast() {
        if (tail == null) {
            return -9999;     // ie. null!
        } else {
            int retV = tail.data;
            tail = tail.prev;
            if (tail == null) {
                head = null;
            } else {
                tail.next = null;
            }
            return retV;
        }
    }


    public int removeIndex(int index) {  //return value
        //addIndex 참고해서 만들어봐라
        if (!validIndex(index)) {
            System.out.println("Invalid Index!");
            return -9999;
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == nOfNodes()) {
            return removeLast();
        }else{
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            int retV = temp.data;
            temp.next.prev = temp.prev;
            temp.prev.next = temp.next;
            return retV;
        }

    }

    public int removeData(int d) {
        if (head == null) {
            return -9999;
        } else {
            if (head.data == d) {
                return removeFirst();
            } else if (tail.data == d) {
                return removeLast();
            } else {
                Node temp = head;
                while (temp != null && temp.data != d) {
                    temp = temp.next;
                }
                if (temp == null) {
                    return -9999;
                } else {
                    int retV = temp.data;
                    temp.next.prev = temp.prev;
                    temp.prev.next = temp.next;
                    return retV;
                }
            }
        }
    }

    public int get(int index) {
        if (!validIndex(index)) {
            System.out.println("Invalid Index!!");
            return -9999;
        } else {
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            return p.data;
        }
    }

    public boolean set(int index, int data) {
        if (!validIndex(index)) {
            System.out.println("Invalid Index!!");
            return false;
        } else {
            Node p = head;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
            p.data = data;
            return true;
        }
    }

    public int indexOF(int data) {    // return index, if not found return -1
        Node p = head;
        for (int i = 0; i < nOfNodes(); i++) {
            if (p.data == data) {
                return i;
            }
            p=p.next;
        }
        return -1;
    }

    public int size() {
        return nOfNodes();
    }

    public void showList() {
        System.out.println();
        Node p = head;
        while (p != null) {
            System.out.println("->" + p.data);
            p=p.next;
        }
    }




    public static void main(String[] args) {
        int[] data = {113, 336, 74, 71, 86, 176, 313, 80, 225, 342,
                170, 292, 275, 266, 79, 16, 109, 175, 245, 156,
                50, 61, 277, 167, 81, 24, 76, 186, 78, 101,
                301, 62, 152, 219, 294};

        MyLinkedList2 al = new MyLinkedList2();

        System.out.println(">>> Test : add(data)");
        for (int i = 0; i < 10; i++) {
            al.add(data[i]);
        }
        al.showList();


        System.out.println(">>>Test : add(index, data)");
        al.add(5, 100);
        al.add(5, 200);
        al.add(5, 300);
        al.add(al.size(), 400);
        al.showList();


        System.out.println(">>>Test : indexOF(data)");
        System.out.println(100 + ":"+al.indexOF(100));
        System.out.println(200 + ":"+al.indexOF(200));
        System.out.println(300 + ":"+al.indexOF(300));

        System.out.println(">>>Test : set(index, data),get(index)");
        System.out.println("Before :"+al.get(3));
        al.set(3, 400);
        System.out.println("After :"+al.get(3));

        System.out.println(">>>Test : removeIndex(index)");
        System.out.println("Before :");
        al.showList();
        System.out.println("removeIndex :" + al.removeIndex(3));
        System.out.println("After :");
        al.showList();

        System.out.println(">>>Test : removeData(data)");
        System.out.println("Before :");
        al.showList();
        System.out.println("removeData :" + al.removeData(100));
        System.out.println("After :");
        al.showList();
    }
}
