package Att1;

public class ChessProbability {
    static double w_w = .9;
    static double w_d = .05;
    static double w_l = .05;

    static double b_w = .9;
    static double b_d = .05;
    static double b_l = .05;

    public static void main(String[] args) {
        chessProbability(4, 4);
    }
    public static void chessProbability(int games, int points) {
        double[][] matrix = new double[games + 1][2*points + 1];
        // init conditions
        matrix[0][0] = 1;
        for (int i = 1; i < matrix[0].length; i++) {
            matrix[0][i] = 0;
        }
        matrix[1][0] = w_l;
        matrix[1][1] = w_d;
        matrix[1][2] = w_w;
        for (int i = 3; i < matrix[1].length; i++) {
            matrix[1][i] = 0;
        }
        for (int i = 2; i < matrix.length; i++) {
            if(i % 2 == 0) {
                matrix[i][0] = matrix[i-1][0] * b_l;
            } else {
                matrix[i][0] = matrix[i-1][0] * w_l;
            }
        }
//        for (int i = 2; i < matrix.length; i++) {
//            if(i % 2 == 0) {
//                matrix[i][1] = matrix[i-1][1] * b_d;
//            } else {
//                matrix[i][1] = matrix[i-1][1] * w_d;
//            }
//        }
//        for (int i = 2; i < matrix.length; i++) {
//            if(i % 2 == 0) {
//                matrix[i][2] = matrix[i-1][2] * b_w;
//            } else {
//                matrix[i][2] = matrix[i-1][2] * w_w;
//            }
//        }

        for (int i = 2; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if(i % 2 == 0) {
                    // use black probabilities
                    if(j >= 0) {
                        matrix[i][j] = b_l * matrix[i-1][j];
                    }
                    if(j-1 >= 0) {
                        matrix[i][j] += b_d * matrix[i-1][j-1];
                    }
                    if(j-2 >= 0) {
                        matrix[i][j] += b_w * matrix[i-1][j-2];
                    }
                } else {
                    // use white probabilities
                    if(j >= 0) {
                        matrix[i][j] = w_l * matrix[i-1][j];
                    }
                    if(j-1 >= 0) {
                        matrix[i][j] += w_d * matrix[i-1][j-1];
                    }
                    if(j-2 >= 0) {
                        matrix[i][j] += w_w * matrix[i-1][j-2];
                    }
                }
            }
        }
        System.out.println(matrix[games][2*points]);
    }
}
