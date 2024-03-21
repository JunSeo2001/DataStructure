package week3;

public class Maze{
    int[][] maze;
    int count=0;

    public Maze(int [][] in) {
        maze=in;
    }

    public int findMaxIter(int i, int j) {




        return 0;
    }

    public int findMaxRec(int i, int j) {
        count ++;
        if(i==0 && j ==0)
            return maze[i][j];
        else if (j==0)
            return maze[i][j] + findMaxRec(i-1, j);
        else if (i==0)
            return maze[i][j] + findMaxRec(i, j-1);
        else
            return maze[i][j] + Math.max(findMaxRec(i, j-1), findMaxRec(i-1, j));
    }

    public int getCount(){
        return count;
    }

    public static void main(String[] args) {
        int [][] maze = {
                {1,2,1,5,8,4},
                {4,1,9,4,2,3},
                {8,5,4,3,8,2},
                {1,5,3,5,7,3},
                {4,7,7,9,2,8},
                {2,4,6,3,1,4}
        };

        Maze me = new Maze(maze);

        System.out.println("MaxSum = "+me.findMaxIter(maze.length-1,maze.length-1));
        System.out.println("MaxSum = "+me.findMaxRec(maze.length-1,maze.length-1)+" "+"count = "+me.getCount());
    }
}
