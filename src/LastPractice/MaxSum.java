package LastPractice;

public class MaxSum {
    int[] card;

    public MaxSum(int[] d) {
        card = d;
    }

    public int selectNSum() {
        if (card.length <= 0 || (card.length % 2 != 0)) {
            return -1;
        } else {
            int[][] memo = new int[card.length][card.length];
            return selectNSum(0, card.length - 1, memo);
        }
    }

    private int selectNSum(int i, int j, int[][] memo) {
        if (i > j) {
            return 0;
        }
        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        // 선택할 수 있는 카드가 두 개 남은 경우
        if (i == j) {
            return card[i];
        }

        // 상대방이 최소화하려고 하기 때문에 상대방이 취할 수 있는 최소값을 고려합니다.
        int takeLeft = card[i] + Math.min(selectNSum(i + 2, j, memo), selectNSum(i + 1, j - 1, memo));
        int takeRight = card[j] + Math.min(selectNSum(i + 1, j - 1, memo), selectNSum(i, j - 2, memo));

        memo[i][j] = Math.max(takeLeft, takeRight);
        return memo[i][j];
    }

    public static void main(String[] args) {
        System.out.println("\n==[Q4]=================");

        int[] d = {11, 21, 3, 4, 5, 9, 8, 7, 6, 10};
        MaxSum q4 = new MaxSum(d);
        System.out.println("\nMaxSum = " + q4.selectNSum());
    }
}
