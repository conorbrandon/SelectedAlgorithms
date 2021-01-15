package Att2;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BandwidthPruning {

    /* The following graph description files mark the following
types of graphs:
"p"	Simple paths, presumably easy, bandwidth 1
"t"	Random trees
"k"	Complete graphs, bandwidth n-1
"r"	Random graphs
"bt"	Complete binary trees
"gg"	Grid graphs
*/

    public static int minLengthBand = Integer.MAX_VALUE;
    public static int[] currentPermMaxDist = null;
    public static int[] bestPerm = null;
    public static GraphMatrix g = new GraphMatrix();

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String f = "g-r-13-15";
        File file = new File("/Users/conorbrandon/Documents/SBU/Fall2020/CSE373/hw4/files/" + f);
        GraphMatrix.read_graph(g, false, file);
        System.out.println(g.toString());
        currentPermMaxDist = new int[g.nVertices + 1];
        generate_bandwidth(g.nVertices);
        System.out.println("Best Perm : " + Arrays.toString(bestPerm) + " Bandwidth: " + minLengthBand);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000000000.0);
    }

    public static void generate_bandwidth(int n) {
        int[] a = new int[n + 1];
        backtrack(a, 0, n);
    }
    static boolean finished = false;
    public static void backtrack(int[] a, int k, int n) {
        int[] c = new int[n+1];
        NCandidate ncandidates = new NCandidate(0);
        int i;

        if(is_a_solution(a, k, n)) {
            process_solution(a, k, n);
            //currentPermMaxDist = 0;
        }
        else {
            k++;
            construct_candidates(a, k, n, c, ncandidates);
            for(i = 0; i <ncandidates.getN(); i++) {
                a[k] = c[i];
                currentPermMaxDist[k] = 0;
                //System.out.println(Arrays.toString(a));

                int newNode = a[k];
                for (int j = 1; j < k; j++) {
                    if(g.edges[newNode][a[j]] == 1) {
                        int distance = k - j;
                        if(distance >= minLengthBand) {
                            //currentPermMaxDist[a[j]] = 0;
                            return;
                        }
                        if(distance > currentPermMaxDist[a[j]]) {
                            currentPermMaxDist[a[j]] = distance;
                            //break;
                        }
                    }
                }
                //make_move(a, k, n);
                backtrack(a, k, n);
                //unmake_move(a, k, n);
//                if(finished) {
//                    finished = false;
//                    return;
//                }
            }
        }
    }
    // Method stubs below for subsets
    public static boolean is_a_solution(int[] a, int k, int n) {
        return k == n;
    }

    public static void construct_candidates(int[] a, int k, int n, int[] c, NCandidate ncandidates) {
        int i;
        boolean[] in_perm = new boolean[n+1];
//        for (i = 1; i < in_perm.length; i++) {
//            in_perm[i] = false;
//        }
        for (i = 0; i < k; i++) {
            in_perm[a[i]] = true;
        }
        ncandidates.setN(0);
        //ArrayList<Integer> intList = new ArrayList<>(ncandidates.getN());
        for (i = 1; i <= n; i++) {
            if (in_perm[i] == false) {
                c[ncandidates.getN()] = i;
                //intList.add(i);
                ncandidates.setN(ncandidates.getN() + 1);
            }
        }
//        Collections.shuffle(intList);
//        //System.out.println(intList.toString());
//        for (int j = 0; j < ncandidates.getN(); j++) {
//            c[j] = intList.get(j);
//        }
//        //System.out.println(Arrays.toString(c));
    }

    public static void process_solution(int[] a, int k, int n) {
        int max = 0;
        for (int j = 0; j < currentPermMaxDist.length; j++) {
            if(currentPermMaxDist[j] > max) max = currentPermMaxDist[j];
        }
        if(max < minLengthBand) {
            System.out.print(Arrays.toString(a));
            bestPerm = a.clone();
            minLengthBand = max;
            System.out.println(" Bandwidth: " + minLengthBand);
        }
    }
    public static void make_move(int[] a, int k, int n) {

    }
    public static void unmake_move(int[] a, int k, int n) {

    }
}

