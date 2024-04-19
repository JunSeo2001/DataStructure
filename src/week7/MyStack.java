package week7;

import week5.MyArrayList1;

public class MyStack extends MyArrayList1 {

    int top;

    public MyStack(int n) {
        super(n);
        top = 0;
    }

    //push, pop, showStack
    public void push(int data) {
        add(data);
        top++;
    }

    public int pop() {
        int poppedItem = get(top);
        removeIndex(top);
        top--;
        return poppedItem;
    }


    public void showStack() {
        showArray();
    }



    public static void main(String[] arg) {
        MyStack s = new MyStack(2);

        s.push(10);
        s.push(20);
        s.push(30);
        s.push(40);

        s.showStack();

        System.out.println(s.pop());
        System.out.println(s.pop());

        s.showStack();
    }
}
