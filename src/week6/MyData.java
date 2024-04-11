package week6;

public class MyData {
    int value;
    String name;

    public MyData(int v) {
        value = v;
    }

    @Override
    public String toString() {
        return "" + value + "";
    }


    @Override
    public boolean equals(Object that) {
        return (this.value == ((MyData)that).value);//타입 캐스팅?
    }


}
