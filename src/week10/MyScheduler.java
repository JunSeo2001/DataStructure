package week10;

public class MyScheduler<T extends Comparable<T>> {

    class Node {
        T aPlan;
        Node next, prev;

        Node(T p) {
            aPlan = p;
            next = null;
            prev = null;
        }

        public String toString() {
            return aPlan.toString();
        }
    }

    Node head, tail;

    public MyScheduler() {
        head = null;
        tail = null;
    }



    public void register(T p) {
        if (head == null) {
            head = new Node(p);
            tail = head;
        }
        else {
            if (p.compareTo(head.aPlan)<0) {
                Node newNode = new Node(p);
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            else {
                Node temp;
                temp = head.next;
                while(temp != null && p.compareTo(temp.aPlan)>0) {
                    temp = temp.next;
                }
                Node newNode = new Node(p);
                if (temp != null) {
                    newNode.next = temp;
                    newNode.prev = temp.prev;
                    temp.prev.next = newNode;
                    temp.prev = newNode;
                } else {
                    tail.next = newNode;
                    newNode.prev = tail;
                    tail = newNode;
                }
            }

        }

    }
    public void done(T p) {
        if (head == null) {
            System.out.println("스케줄이 없습니다.");
            return;
        }
        Node temp = head;
        while (temp != null && !temp.aPlan.equals(p)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("일치하는 스케줄이 없습니다.");
            return;
        }

        // 일치하는 노드가 head인 경우
        if (temp == head) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        } else if (temp == tail) {
            // 일치하는 노드가 tail인 경우
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            }
        } else {
            // 일치하는 노드가 중간에 위치하는 경우
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
        temp.prev = null;
        temp.next = null;
    }

//   public void showSchedule() {
//	   Node temp = head;
//	   while(temp != null) {
//		   System.out.println(temp.toString());
//		   temp = temp.next;
//	   }
//	   System.out.println(nOfTasks());
//   }

    public void showSchedule() {
        System.out.println();
        showSchedule(head);
    }

    private void showSchedule(Node p) {
        if(p!=null) {
            System.out.println(p.toString());
            showSchedule(p.next);
        }
    }

    public int nOfTasks() {
        return nOfTasks(head);
    }

    private int nOfTasks(Node p) {
        if(p==null)
            return 0;
        else return 1+nOfTasks(p.next);
    }

    public static void main(String[] args) {

        MyScheduler<Plan> ms = new MyScheduler<>();

        ms.showSchedule();
        ms.register(new Plan(10, "Seminar"));
        ms.register(new Plan(19, "Party"));
        ms.register(new Plan(7, "Swimming"));
        ms.showSchedule();
        ms.register(new Plan(9, "Tea Meeting"));
        ms.register(new Plan(13, "Lunch"));
        ms.done(new Plan(7, "Swimming"));
        ms.showSchedule();
        ms.done(new Plan(9, "Tea Meeting"));
        ms.register(new Plan(17, "Tennis"));
        ms.showSchedule();
        ms.done(new Plan(9, "Seminar"));
        ms.showSchedule();
        ms.done(new Plan(10, "Seminar"));
        ms.showSchedule();

        String s, t;
        s="aa"; t="bb";
        if(s.compareTo(t)>0);
    }

}