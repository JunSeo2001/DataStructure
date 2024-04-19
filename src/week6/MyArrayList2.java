package week6;

public class MyArrayList2<T> {
    int maxSize;
    T[] array;
    int nOfItems;

    public MyArrayList2(int n) {
        maxSize = n;
        array = (T[]) new Object[maxSize]; //타입 컨버젼
        nOfItems = 0;
    }

    public void add(T data) {   //last add
        if (nOfItems >= maxSize) {
            grow();
        }
        array[nOfItems++] = data;
    }

    public void add(int index, T data) {
        if (nOfItems >= maxSize) {
            grow();
        }
        if (!validIndex(index)) {           //array 범위 체크
            System.out.println("Invalid Index!!");
            return;
        }
        for (int i = nOfItems-1; i >= index; i--) {
//            int temp = array[i+1];
            array[i+1] = array[i];
        }
        array[index] = data;
        nOfItems++;
    }
    private void grow() {
        T[] newArray = (T[])new Object[maxSize * 2];
        for (int i = 0; i < nOfItems; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        maxSize *=2;

        System.out.println("Memory Double-up!");
    }

    private boolean validIndex(int index) {
        if(index < 0 || index >= nOfItems) {
            return false;
        } else {
            return true;
        }
    }


    public T remove(int index) {  //return value
        if (validIndex(index)) {
            T temp = array[index];
            for (int i = index; i < nOfItems - 1; i++) {
                array[i] = array[i + 1];
            }
            nOfItems --;
            return temp;
        }else{
            System.out.println("Invalid Index!!");
            return null;
        }
    }

    public int remove(T data) {
        int index = indexOF(data);
        remove(index);
        return index;
    }

    public T get(int index) {
        if (validIndex(index)) {
            return array[index];
        } else {
            System.out.println("Invalid Index!!");
            return null;
        }
    }

    public boolean set(int index, T data) {
        if (validIndex(index)) {
            array[index] = data;
            return true;
        } else {
            System.out.println("Invalid Index!!");
            return false;
        }
    }

    public int indexOF(T data) {
        for (int i = 0; i < nOfItems; i++) {
            if (array[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return nOfItems;
    }

    public void showArray() {
        int n = nOfItems;
        int nRow = 1 + (int) n / 10;
        for (int i = 0; i < nRow; i++) {
            for (int j = i * 10; j < Math.min(n, (i + 1) * 10); j++) {
                System.out.print(array[j] + " ");
            }
            System.out.println();
        }
    }




    public static void main(String[] args) {
        int[] orgData = {113, 336, 74, 71, 86, 176, 313, 80, 225, 342,
                170, 292, 275, 266, 79, 16, 109, 175, 245, 156,
                50, 61, 277, 167, 81, 24, 76, 186, 78, 101,
                301, 62, 152, 219, 294};

        String[] orgData2 = {
                "나", "너", "우리", "나이"
        };

        MyData[] data = new MyData[orgData.length];
        for (int i = 0; i < data.length; i++) {
            data[i] = new MyData(orgData[i]);
        }

        MyData[] data2 = new MyData[orgData2.length];
        for (int i = 0; i < data2.length; i++) {
            data2[i] = new MyData(orgData2[i]);
        }

        MyArrayList2<MyData> al= new MyArrayList2<>(2);

        System.out.println(">>> Test : add(data), grow()");
        for (int i = 0; i < 10; i++) {
            al.add(data[i]);
        }
        al.showArray();


        System.out.println(">>>Test : add(index, data)");
        al.add(5, new MyData(100));
        al.add(5,  new MyData(200));
        al.add(5,  new MyData(300));
        al.add(al.size(),  new MyData(400));
        al.showArray();


        System.out.println(">>>Test : indexOF(data)");
        System.out.println(100 + ":"+al.indexOF(new MyData(100)));
        System.out.println(200 + ":"+al.indexOF(new MyData(200)));
        System.out.println(300 + ":"+al.indexOF(new MyData(300)));

        System.out.println(">>>Test : set(index, data),get(index)");
        System.out.println("Before :"+al.get(3));
        al.set(3, new MyData(400));
        System.out.println("After :"+al.get(3));

        System.out.println(">>>Test : removeIndex(index)");
        System.out.println("Before :");
        al.showArray();
        System.out.println("removeIndex :" + al.remove(3));
        System.out.println("After :");
        al.showArray();

        System.out.println(">>>Test : removeData(data)");
        System.out.println("Before :");
        al.showArray();
        System.out.println("removeData :" + al.remove(new MyData(100)));
        System.out.println("After :");
        al.showArray();
    }
}



