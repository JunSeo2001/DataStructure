package week5;

public class MyArrayList1 {

    int maxSize = 20;
    int[] array = new int[20];
    int nOfItems = 0;

    public void add(int data) {   //last add
        if (nOfItems >= maxSize) {
            grow();
        }
        array[nOfItems++] = data;
    }

    private void grow() {
        int[] newArray = new int[maxSize * 2];
        for (int i = 0; i < nOfItems; i++) {
            newArray[i] = array[i];
            array = newArray;
            maxSize *=2;
        }

    }


    public void add(int index, int data) {
        if (nOfItems >= maxSize) {                     //array 메모리 체크
            System.out.println("Memory Full!!");
            return;
        }
        if (index<0 || index >= nOfItems) {           //array 범위 체크
            System.out.println("Invalid Index!!");
            return;
        }
        for (int i = nOfItems-1; i >= index; i--) {
            array[i+1] = array[i];
            array[index] = data;
            nOfItems++;
        }

    }

    public int removeIndex(int index) {  //return value
        return 0;
    }

    public int removeData(int data) {   //return index
        return 0;
    }

    public int get(int index) {
        return 0;
    }

    public void set(int index, int data) {

    }

    public int indexOF(int data) { // return index, if not found, return -1
        return 0;
    }

    public int size() {
        return nOfItems;
    }

    public void showArray() {

    }




    public static void main(String[] args) {
        int[] data = {113, 336, 74, 71, 86, 176, 313, 80, 225, 342,
                170, 292, 275, 266, 79, 16, 109, 175, 245, 156,
                50, 61, 277, 167, 81, 24, 76, 186, 78, 101,
                301, 62, 152, 219, 294};

        MyArrayList1 al = new MyArrayList1();

        for (int i = 0; i < 10; i++) {
            al.add(data[i]);
        }
        al.showArray();
        al.add(5, 100);
        al.showArray();

        System.out.println(al.indexOF(100));

    }
}
