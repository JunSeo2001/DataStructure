package week4;

import java.util.Stack;

public class Hanoi {

    public void hanoi(int n, int f, int t) {
        if (n == 1) {
            System.out.println("moved from" + f + "to" + t);
            return;
        } else {
            hanoi(n - 1, f, 3 - f - t);
            hanoi(1, f, t);
            hanoi(n - 1, 3 - f - t, t);
        }
    }

    private void hanoiIter(int n, int f, int t) {
        Stack<Integer> s = new Stack<>();
        s.push(n);
        s.push(f);
        s.push(t);
        //환경 만듬

        int num, from, to;

        while (!s.isEmpty()) {
            to = s.pop();
            from = s.pop();
            num = s.pop();
            //집어넣은 3개를 다시 역순으로 뽑아낸 것
            System.out.println(num +" " + f + " " + t);

            if (num == 1)
                System.out.println("moved from" + from + "to" + to);
            else {

                s.push(num - 1);
                s.push(3 - from - to);
                s.push(to);

                s.push(1);
                s.push(from);
                s.push(to);

                s.push(num - 1);
                s.push(from);
                s.push(3 - from - to);

                //여기가 이제
                //            hanoi(n - 1, f, 3 - f - t);
                //            hanoi(1, f, t);
                //            hanoi(n - 1, 3 - f - t, t);}
                //이 부분 따라한거임 근데 stack이기에 다시 반대로 작성
            }
        }
    }


    public static void main(String[] arg) {
        Hanoi h = new Hanoi();
        h.hanoi(3, 0, 1);
        System.out.println("\n\n Iteration");
        h.hanoiIter(3, 0, 1);

    }
}
