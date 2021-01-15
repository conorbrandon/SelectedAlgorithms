package Backtracking;

public class Permutations {
    public static void main(String[] args) {
        generate_permutations(3);
    }
    public static void generate_permutations(int n) {
        int[] a = new int[1000];
        backtrack(a, 0, n);
    }
    static boolean finished = false;
    public static void backtrack(int[] a, int k, int n) {
        int[] c = new int[1000];
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
//                if(a[k] == k && k > 1) return;
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
        int i;
        boolean[] in_perm = new boolean[5];
        for (i = 1; i < in_perm.length; i++) {
            in_perm[i] = false;
        }
        for (i = 0; i < k; i++) {
            in_perm[a[i]] = true;
        }
        ncandidates.setN(0);
        for (i = 1; i <= n ; i++) {
            if(in_perm[i] == false) {
                c[ncandidates.getN()] = i;
                ncandidates.setN(ncandidates.getN() + 1);
            }
        }
    }
    public static boolean valid_candidates(int[] c) {
        for (int i = 0; i < c.length; i++) {
            if(c[i] - 1 == i) return false;
        }
        return true;
    }
    public static void process_solution(int[] a, int k, int n) {
//        for (int i = 1; i <= k; i++) {
//            if(a[i] == i) return;
//        }
        if(a[k] == k && k == 1) return;
        for (int i = 1; i <= k; i++) {
            System.out.print(a[i]);
        }
        System.out.println();
    }
    public static void make_move(int[] a, int k, int n) {

    }
    public static void unmake_move(int[] a, int k, int n) {

    }
}
