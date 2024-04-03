package week1;

public class Euclidean {
    public static int fibo1(int n){
        if(n<0) return -1;
        if(n<=1) return n;

        int f1 = 0;
        int f2 = 1;
        int fn = 0;
        for (int i=2; i<=n; i++){
            fn = f1+f2;
            f1=f2;
            f2=fn;
        }
        return fn;
    }
    public static int GCD(int a, int b){
        if (a < b) {
            int temp =a;
            a = b;
            b = temp;
        }
        int r=a%b;
        if(r==0)
            return b;
        else
            return GCD(b,r);

    }
    public static void main(String[] args) {

        System.out.println(GCD(428, 38));


    }
}