package Backtracking;

import java.util.HashMap;

public class Multiset {
    static HashMap<Integer, Integer> map = new HashMap();
    public static void main(String[] args) {
        int[] ints = new int[] {1, 2, 1, 2};
        for (int i = 0; i < ints.length; i++) {
            if(map.containsKey(ints[i])) {
                int val = map.get(ints[i]);
                map.put(ints[i], val + 1);
            }
            else map.put(ints[i], 1);
        }
        //System.out.println(map.toString());
        generate_perms_multiset(ints);
    }
    public static void generate_perms_multiset(int[] ints) {
        int[] a = new int[1000];
        backtrack(a, 0, ints);
    }
    static boolean finished = false;
    public static void backtrack(int[] a, int k, int[] ints) {
        int[] c = new int[1000];
        NCandidate ncandidates = new NCandidate(0);
        int i;

        if(is_a_solution(a, k, ints.length)) {
            process_solution(a, k, ints.length);
            return;
        }
        else {
            k++;
            boolean res = construct_candidates(a, k, ints, c, ncandidates);
            if(!res) return;
            for(i = 0; i <ncandidates.getN(); i++) {
                //if (a[k] == c[i]) break;
                //else
                a[k] = c[i];
                make_move(a, k, ints.length);
                backtrack(a, k, ints);
                unmake_move(a, k, ints.length);
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
    public static boolean construct_candidates(int[] a, int k, int[] ints, int[] c, NCandidate ncandidates) {
        int i;
        boolean[] in_perm = new boolean[ints.length + 1];
        for (i = 1; i < in_perm.length; i++) {
            in_perm[i] = false;
        }
        for (i = 0; i < k; i++) {
            if (a[i] > 0) in_perm[i] = true;
            //in_perm[a[i]] = true;
        }
        ncandidates.setN(0);
        int j = 0;
        for (i = 1; i <= ints.length ; i++, j++) {
            if(in_perm[i] == false) {
                if (a[k] == ints[j]) return false;
                c[ncandidates.getN()] = ints[j];
                ncandidates.setN(ncandidates.getN() + 1);
            }
        }
        return true;
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

