package Backtracking;

public class Subsets {
    public static void main(String[] args) {
        generate_subsets(1);
    }
    public static void generate_subsets(int n) {
        int[] a = new int[1000];
        backtrack(a, 0, n);
    }
    static boolean finished = false;
    public static void backtrack(int[] a, int k, int n) {
        int[] c = new int[2];
        NCandidate ncandidates = new NCandidate(0);
        int i;

        if(is_a_solution(a, k, n)) {
            process_solution(a, k, n);
            return;
        }
        else {
            k++;
            construct_candidates(a, k, n, c, ncandidates);
            for(i = 0; i <ncandidates.getN(); i++) {
                a[k] = c[i];
                make_move(a, k, n);
                backtrack(a, k, n);
                unmake_move(a, k, n);
                if(finished) {
                    finished = false;
                    return;
                }
            }
        }
    }
    // Method stubs below for subsets
    public static boolean is_a_solution(int[] a, int k, int n) {
        return k == n;
    }
    public static void construct_candidates(int[] a, int k, int n, int[] c, NCandidate ncandidates) {
        c[0] = 1;
        c[1] = 0;
        ncandidates.setN(2);
    }
    public static void process_solution(int[] a, int k, int n) {
        System.out.print("{");
        for (int i = 1; i <= k; i++) {
            if (a[i] == 1) {
                System.out.print(i);
            }
        }
        System.out.println("}");
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

