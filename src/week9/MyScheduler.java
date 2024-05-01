package week9;

public class MyScheduler {

    class Node {
        int time;
        String task;
        Node next;

        Node(int tm, String tk) {
            time = tm;
            task = tk;
            next = null;
        }

        public String toString() {
            return "" + time + ":" + task;
        }
    }

    Node head;

    public MyScheduler() {
        head = null;
    }



    public void register(int tm, String tk) {
        if (head == null) {
            head = new Node(tm, tk);
        } else {
            if (tm < head.time) {
//                Node newNode = new Node(tm, tk);
//                newNode.next = head;
//                head = newNode;
                Node temp = head;
                head = new Node(tm, tk);
                head.next = temp;
            } else{
                Node temp1,temp2;
                temp1 = head;
                temp2 = temp1.next;
                while (temp2 !=null && tm > temp2.time) {
                    temp1 = temp2;
                    temp2 = temp2.next;
                }
                Node newNode = new Node(tm, tk);
                newNode.next = temp2;
                temp1.next = newNode;
            }
        }
    }
    public void done(int tm, String tk) {
        if (head != null) {
            if (head.time == tm && head.task == tk) {
                head = head.next;
            } else {
                Node temp1, temp2;
                temp1 = head;
                temp2 = temp1.next;
                while (temp2 != null && (tm != temp2.time || tk != temp2.task)){
                    temp1 = temp2;
                    temp2 = temp1.next;
                }
                if (temp2 != null) {
                    temp1.next = temp2.next;
                }
            }
        }

    }

    public void showSchedule() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
        System.out.println("Count ="+nOfTasks());
    }

    public int nOfTasks() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    public static void main(String[] args) {

        MyScheduler ms = new MyScheduler();

        ms.showSchedule();
        ms.register(10, "Seminar");
        ms.register(19, "Party");
        ms.register(7, "Swimming");
        ms.showSchedule();
        ms.register(9, "Tea Meeting");
        ms.register(13, "Lunch");
        ms.done(7, "Swimming");
        ms.showSchedule();
        ms.done(9, "Tea Meeting");
        ms.register(17, "Tennis");
        ms.showSchedule();
        ms.done(9, "Seminar");
        ms.showSchedule();
        ms.done(10, "Seminar");
        ms.showSchedule();


    }

}
