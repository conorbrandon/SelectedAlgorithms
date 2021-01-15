package Att2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class GraphMatrix {
    static final int MAXV = 3000;

    int[][] edges = null; // new int[MAXV + 1][MAXV + 1];
    int[] degree = null; // new int[MAXV + 1];
    int nVertices;
    int nEdges;
    boolean directed;

    public GraphMatrix() {
    }

    @Override
    public String toString() {
        String s =  "GraphMatrix{" +
                "edges=\n";
        for (int i = 1; i < edges.length; i++) {
            for (int j = 1; j < i; j++) {
                if(edges[i][j] == 1) {
                    s += " " + i + " " + j + "\n";
                }
            }
        }
                s += " degree=" + Arrays.toString(degree) +
                ",\n nVertices=" + nVertices +
                ",\n nEdges=" + nEdges +
                ",\n directed=" + directed +
                "}\n";
        return s;
    }

    public static void initialize_graph(GraphMatrix g, boolean directed) {
        g.nVertices = 0;
        g.nEdges = 0;
        g.directed = directed;

    }
    public static void read_graph(GraphMatrix g, boolean directed, File graphFile) {
        int m;
        int x, y;
        initialize_graph(g, directed);

        ArrayList<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(graphFile);
            while(scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(lines.toString());
        m = Integer.parseInt(lines.get(0));

        g.edges = new int[m + 1][m + 1];
        g.degree = new int[m + 1];
        for (int i = 1; i <= m; i++) {
            g.degree[i] = 0;
        }

        for (int i = 2; i < lines.size(); i++) {
            String l = lines.get(i);
            //System.out.println(l);
            l = l.replaceAll("\\s+", ";");
            //System.out.println(l);
            String[] line = l.split(";");
            //System.out.println(Arrays.toString(line));
            x = Integer.parseInt(line[0]);
            y = Integer.parseInt(line[1]);
            //System.out.println(x + " " + y);
            insert_edge(g, x, y, directed);
        }
        g.nVertices = Integer.parseInt(lines.get(0));

    }
    public static void insert_edge(GraphMatrix g, int x, int y, boolean directed) {
        g.edges[x][y] = 1;
        g.degree[x]++;
        if (directed == false) {
            insert_edge(g, y, x, true);
        } else g.nEdges++;
    }
}


