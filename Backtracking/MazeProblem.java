package BacktrackingPractice;

import java.util.Arrays;

public class MazeProblem {
    static boolean finished = false;

    public static void main(String[] args) {
        int[][] maze = new int[][] {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 0, 0, 0},
                {1, 0, 0, 1}};
        System.out.println("MAZE:");
        for (int i = 0; i < maze.length; i++) {
            System.out.println(Arrays.toString(maze[i]));
        }
        System.out.println();
        exitFromMaze(maze);
    }
    public static void exitFromMaze(int[][] maze ) {
        int[][] a = new int[maze.length][maze[0].length];
        a[0][0] = 1;
        backtrack(a, 0, maze, 0, 0);
        if(!finished) System.out.println("NO SOLUTION FOUND :(");
    }

    public static void backtrack(int[][] a, int k, int[][] maze, int xPos, int yPos) {
        int[] c = new int[2];
        NCandidate ncandidates = new NCandidate(0);
        int i;

        if(is_a_solution(a, k, maze, xPos, yPos)) {
            finished = true;
            a[xPos][yPos] = 1;
            process_solution(a, k, maze);
        }
        else {
            k++;
            construct_candidates(a, k, maze, c, ncandidates, xPos, yPos);
            a[xPos][yPos] = 1;
            for(i = 0; i <ncandidates.getN(); i++) {
                if(c[i] == 1 && maze[xPos][yPos+1] != 0) {
                    backtrack(a, k, maze, xPos, yPos+1);
                } else if(c[i] == -1 && maze[xPos+1][yPos] != 0) {
                    backtrack(a, k, maze, xPos+1, yPos);
                }
                //make_move(a, k, n);
                //unmake_move(a, k, n);
                if(finished) {
                    //finished = false;
                    return;
                }
            }
            a[xPos][yPos] = 0;
        }
    }
    // Method stubs below for subsets
    public static boolean is_a_solution(int[][] a, int k, int[][] maze, int x, int y) {
        return (x == maze.length-1 && y == maze.length-1);
    }
    public static void construct_candidates(int[][] a, int k, int[][] maze, int[] c, NCandidate ncandidates, int x, int y) {
        if(x+1 == maze[0].length) {
            ncandidates.setN(1);
            c[0] = 1; // corresponding to moving right (forward)
        } else if(y+1 == maze.length) {
            ncandidates.setN(1);
            c[0] = -1; // corresponding to moving down
        } else {
            ncandidates.setN(2);
            c[0] = -1; // corresponding to moving right (forward) and down
            c[1] = 1;
        }
    }
    public static void process_solution(int[][] a, int k, int[][] maze) {
        System.out.println("SOLUTION FOUND :)");
        for (int i = 0; i < a.length; i++) {
            System.out.println(Arrays.toString(a[i]));
        }
    }
    public static void make_move(int[] a, int k, int n) {

    }
    public static void unmake_move(int[] a, int k, int n) {

    }
}

class NCandidate {
    private int n;
    public NCandidate(int n) {
        this.n = n;
    }
    public int getN() {
        return n;
    }
    public int setN(int n) {
        this.n = n;
        return n;
    }
}