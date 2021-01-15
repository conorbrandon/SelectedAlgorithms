package Att1;

import java.util.Arrays;

public class ApproxStrMatching {
    static Cell[][] matrix = null;
    static String path = "";
    static final int MATCH = 0;
    static final int INSERT = 1;
    static final int DELETE = 2;

    public static void main(String[] args) {
        String s = "suny";
        String t = "snowy";
        System.out.println(asm(s, t));
    }

    public static String asm(String s, String t) {
        // matrix[i][j] is the first i chars of s, first j chars of t
        matrix = new Cell[s.length()+1][t.length()+1];
        matrix_init(matrix);
        column_init(matrix);
        row_init(matrix);
        matrix[0][0].parent = -1;

        int[] opt = new int[3];

        int i, j = 0;

        for (i = 1; i < matrix.length; i++) {
            for (j = 1; j < matrix[0].length; j++) {
                opt[MATCH] = matrix[i-1][j-1].cost + (s.charAt(i-1)==t.charAt(j-1)? 0:1);
                opt[INSERT] = matrix[i][j-1].cost + 1;
                opt[DELETE] = matrix[i-1][j].cost + 1;

                matrix[i][j].cost = opt[MATCH];
                matrix[i][j].parent = MATCH;

                for (int k = INSERT; k <= DELETE; k++) {
                    if (opt[k] < matrix[i][j].cost) {
                        matrix[i][j].cost = opt[k];
                        matrix[i][j].parent = k;
                    }
                }
            }
        }
        for (int k = 0; k < matrix.length; k++) {
            System.out.print("row: " + k);
            for (int l = 0; l < matrix[k].length; l++) {
                System.out.print(matrix[k][l].toString());
            }
            System.out.println();
        }
        reconstruct_path(s, t, s.length(), t.length());
        return "Cost: " + matrix[i-1][j-1].cost + ", Path: " + path;
    }

    private static void matrix_init(Cell[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Cell(0,1);
            }
        }
    }
    public static void column_init(Cell[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0].cost = i;
            matrix[i][0].parent = 2;
        }
    }
    public static void row_init(Cell[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[0][i].cost = i;
            matrix[0][i].parent = 1;
        }
    }
    public static void reconstruct_path(String s, String t, int i, int j) {
        if(matrix[i][j].parent == -1) {
            return;
        }
        if(matrix[i][j].parent == MATCH) {
            reconstruct_path(s, t, i-1, j-1);
            path += s.charAt(i-1) == t.charAt(j-1) ? 'M' : 'S';
            return;
        }
        if(matrix[i][j].parent == INSERT) {
            reconstruct_path(s, t, i, j-1);
            path += 'I';
            return;
        }
        if(matrix[i][j].parent == DELETE) {
            reconstruct_path(s, t, i-1, j);
            path += 'D';
            return;
        }
    }
}
class Cell {
    int cost;
    int parent;
    public Cell(int cost, int parent) {
        this.cost = cost;
        this.parent = parent;
    }

//    @Override
//    public String toString() {
//        return "{" +
//                "c=" + cost +
//                ", p=" + parent +
//                '}';
//    }

    @Override
    public String toString() {
        return "{" +
                "p=" + parent +
                "}   ";
    }
}
