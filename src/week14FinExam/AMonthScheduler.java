package week14FinExam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public class AMonthScheduler {
    class APlan {
        int time;
        String task;

        public APlan(int d, String s) {
            time = d;
            task=s;
        }

        public String toString() {
            return ""+time+" : "+task;
        }
    }

    ArrayList<Integer> dateList ;
    ArrayList<LinkedList<APlan>> schedule ;

    public AMonthScheduler() {
        dateList = new ArrayList<>();
        schedule = new ArrayList<>();
    }

    public void addAPlan(int date, int time, String task) {
// 1-1)  add your code...
        APlan aPlan = new APlan(time, task);
        int index = dateList.indexOf(date);

        if (index == -1) {
            dateList.add(date);
            LinkedList<APlan> newList = new LinkedList<>();
            newList.add(aPlan);
            schedule.add(newList);

            int newIndex = dateList.size() - 1;
            while (newIndex > 0 && dateList.get(newIndex) < dateList.get(newIndex - 1)) {
                int tempDate = dateList.get(newIndex);
                dateList.set(newIndex, dateList.get(newIndex - 1));
                dateList.set(newIndex - 1, tempDate);
                LinkedList<APlan> tempList = schedule.get(newIndex);
                schedule.set(newIndex, schedule.get(newIndex - 1));
                schedule.set(newIndex - 1, tempList);

                newIndex--;
            }
        } else {
            LinkedList<APlan> existingList = schedule.get(index);
            existingList.add(aPlan);

            existingList.sort(Comparator.comparingInt(a -> a.time));
        }
    }

    public void showDaySchedule(int date) {
        // 1-2)  add your code...
        int index = dateList.indexOf(date);
        if (index != -1) {
            LinkedList<APlan> plans = schedule.get(index);
            System.out.print("\n\n>> Date " + date);
            for (APlan plan : plans) {
                System.out.print(" -> " + plan.toString());
            }
        }
    }

    public void showMonthSchedule() {
        System.out.println("\n\n <  Monthly Schedule  > ");
        if (dateList.size()==0) {
            System.out.println("  Nothing !");
        }
        else {
            for (int i=0;i<dateList.size();i++)
                showDaySchedule(dateList.get(i));
        }
    }

    public static void main(String[] args) {

        // test input : date(1~31), time(0~23), task(any String)
        Object [][] input = { 	{7, 9, "Seminar"}, {16, 9, "Seminar"}, {23, 9, "Seminar"}, {30, 9, "Seminar"},
                {12, 14, "Gym"}, {22, 14, "Gym"}, {20, 14, "Date"}, {8, 14, "Gym"}, {6, 14, "Gym"},
                {7, 12, "Hospital"}, {21, 7, "Jogging"}, {23, 8, "Date"},  {23, 23, "Movie"}, {16, 5, "Jogging"}, {1, 12, "Hospital"},
                {1, 15, "Date"}, {11, 4, "Coding"}, {12, 9, "Tennis"}, {1, 18, "Coding"},  {2, 6, "Jogging"},

        };

        AMonthScheduler ms = new AMonthScheduler();
        for (int i=0;i< input.length;i++) {
            ms.addAPlan((int)input[i][0], (int)input[i][1],(String) input[i][2]);
        }
        ms.showMonthSchedule();
    }
}
