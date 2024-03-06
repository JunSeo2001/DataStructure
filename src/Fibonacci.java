public class Fibonacci {
    static int count=0;
    public static void main(String[] arg){
        int n = 30;

        int result = 0, r1=1, r2 =1;

        for(int i= 3; i <= n; i++){
            count++;
            result=r1+r2;
            r1=r2;
            r2=result;
        }
        System.out.println("Answer ="+ result+" "+count);

        count=0;
        result = fibo(n);
        System.out.println("Answer ="+ result+" "+count);
    }

    private static int fibo(int n) {
        count++;
        if(n<=2)
            return 1;
        else
            return fibo(n-1)+fibo(n-2);
    }
}
