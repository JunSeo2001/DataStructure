package week4;

import java.util.Scanner;

public class CodingTest {

    public int[] solution(String s) {
        int count;
        int zeroCount = 0;
        int loopCount =0;
        while (s!="1"){
            count = getCount(s);
//            System.out.println(count);
            zeroCount += s.length()-count;

            s = Integer.toBinaryString(count);
//            System.out.println(s);
            loopCount++;
        }

        return new int[]{loopCount,zeroCount};
    }

    private static int getCount(String s) {
        int count = 0;
        System.out.println(s.length());
        for(int i = 0; i<= s.length(); i++){
            if( s.charAt(i)=='1'){
                count++;
                System.out.println(count);
            }
        }
        return count;
    }


    public static void main(String[] args) {

        //input
        CodingTest main = new CodingTest();

        ;
        System.out.println(main.solution("0111010"));

    }

}