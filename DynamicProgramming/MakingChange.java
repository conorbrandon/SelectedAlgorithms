package HomeworkProbs;

import java.util.Arrays;

public class MakingChange {
    public static void main(String[] args) {
        System.out.println(makingChange(new int[] {1, 6, 10}, 20));
    }
    public static int makingChange(int[] denoms, int n) {
        int[][] table = new int[denoms.length+1][n+1];

        int[] denomsCopy = new int[denoms.length+1];
        for (int i = 1; i < denomsCopy.length; i++) {
            denomsCopy[i] = denoms[i-1];
        }

        // row init
        for (int j = 0; j < table[0].length; j++) {
            table[0][j] = 0;
        }
        // column init
        for (int i = 0; i < table.length; i++) {
            table[i][0] = 1;
        }

        for (int j = 1; j < table[0].length; j++) {
            for (int i = 1; i < table.length; i++) {
                int cellAbove = table[i-1][j];
                table[i][j]= cellAbove + detPos(i, j-denomsCopy[i], table);
            }
        }

        for (int i = 0; i < table.length; i++) {
            System.out.println(Arrays.toString(table[i]));
        }
        return table[denoms.length][n];
    }
    public static int detPos(int i, int subPart, int[][] table) {
        if(i >= 0 && subPart >= 0){
            return table[i][subPart];
        }
		else return 0;
    }
}
