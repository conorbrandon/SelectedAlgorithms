package DPPractice;

import java.util.Arrays;

public class MaximumThief {
    public static void main(String[] args) {
        int[] houses = new int[] {5, 3, 4, 11, 2};
        maximumThief(houses);
    }
    public static void maximumThief(int[] houses) {
        int[] table = new int[houses.length];
        int[] prev = new int[houses.length];
        Arrays.fill(prev, -1);
        table[0] = houses[0];
        table[1] = houses[1];
        for (int i = 2; i < houses.length; i++) {
            int max = -1;
            int maxPos = -1;
            for (int j = 0; j < i-1; j++) {
                if(table[j] > max) {
                    max = table[j];
                    maxPos = j;
                }
            }
            if(max != -1) {
                table[i] = max + houses[i];
            } else table[i] = houses[i];
            prev[i] = maxPos;
        }

        int max = -1;
        int maxPos = -1;
        for (int i = 0; i < table.length; i++) {
            if(table[i] > max) {
                max = table[i];
                maxPos = i;
            }
        }
        System.out.println("Max: " + max);
        System.out.println(houses[maxPos]);
        while(maxPos != -1) {
            maxPos = prev[maxPos];
            System.out.println(houses[maxPos]);
        }
    }
}
