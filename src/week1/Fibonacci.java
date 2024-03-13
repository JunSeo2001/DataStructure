package week1;

public class Fibonacci {
    static int count=0;

    public int fiboRecursion(int n) {
        if(n<=2)
            return 1;
        else
            return fiboRecursion(n-1)+fiboRecursion(n-2); // = fibo(n)
    }

    public int fiboIter(int n){
        int [] val = new int[n+1];
        val[0] = 0; val[1] = 1;

        for(int i= 2; i <= n; i++){    // int i = n; i>=2; i--
            val[i] = val[i-1]+val[i-2];
        }

        return val[n];
    }

    public static void main(String[] arg){
        Fibonacci f = new Fibonacci();

        System.out.println("Iter-Answer ="+ f.fiboIter(30));
        System.out.println("Rec-Answer ="+ f.fiboRecursion(30));
    }
}
