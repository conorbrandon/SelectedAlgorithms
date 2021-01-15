package DPPractice;

public class FindingMax {
    public static void main(String[] args) {
        int[] sequence = new int[] {4, 5, 3, 2, 1, 6, 9, 8, 7};
        findMax(sequence);
        System.out.println(findMaxR(sequence, sequence.length-1));
    }
    public static void findMax(int[] sequence) {
        int[] table = new int[sequence.length];
        table[0] = sequence[0];
        for (int i = 1;  i<table.length ; i++) {
            table[i] = Math.max(table[i-1], sequence[i]);
        }
        System.out.println(table[table.length-1]);
    }
    public static int findMaxR(int[] sequence, int n) {
        if(n == 0) return sequence[0];
        else return Math.max(sequence[n], findMaxR(sequence, n-1));
    }
}
