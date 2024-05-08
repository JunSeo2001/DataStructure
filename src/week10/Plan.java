package week10;

public class Plan implements Comparable<Plan>{
    int time;
    String task;

    Plan(int tm, String tk) {
        time = tm;
        task = tk;
    }
    @Override
    public boolean equals(Object obj) {
        return (this.time == ((Plan)obj).time) && (this.task.equals(((Plan)obj).task));
    }
    public String toString() {
        return ""+time+":"+task;
    }

    public int compareTo(Plan that) {
        if(this.time> that.time)
            return 1;
        else if(this.time> that.time)
            return -1;
        else
            return 0;
    }

}

