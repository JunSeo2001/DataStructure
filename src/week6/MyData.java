package week6;

import java.util.Queue;
import java.util.Stack;

public class MyData {
    int value;
    Object o;
    String name;

    public MyData(int v) {
        value = v;
    }

    public MyData(String n) {
        name = n;
    }

    @Override
    public String toString() {
        return "" + value + " ";
    }


    @Override
    public boolean equals(Object that) {
        return (this.value == ((MyData)that).value);//타입 캐스팅?
    }


    Stack<Integer> s;
    Queue<Integer> q;

}
