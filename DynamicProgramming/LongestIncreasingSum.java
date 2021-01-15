package DPPractice;

public class LongestIncreasingSum {
    public static void main(String[] args) {
        int[] sequence = new int[] {1, 101, 2, 3, 100, 4, 5};
        longestIncreasingSum(sequence);
    }
    public static void longestIncreasingSum(int[] sequence) {
        int[] table = new int[sequence.length];
        table[0] = sequence[0];
        for (int i = 1; i < table.length; i++) {
            int max = -1;
            for (int j = 0; j < i; j++) {
                if(sequence[i] > sequence[j] && table[j] > max) {
                    max = table[j];
                }
            }
            if(max != -1) {
                table[i] = max + sequence[i];
            } else {
                table[i] = sequence[i];
            }
        }
        int max = 0;
        for (int i = 0; i < table.length; i++) {
            if(table[i] > max) {
                max = table[i];
            }
        }
        System.out.println("Longest: " + max);
    }
}
