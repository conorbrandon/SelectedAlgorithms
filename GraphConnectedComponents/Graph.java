import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Graph {
    static final int MAXV = 3000;

    EdgeNode[] edges = new EdgeNode[MAXV + 1];;
    int[] degree = new int[MAXV + 1];
    int nVertices;
    int nEdges;
    boolean directed;

    public Graph() {
    }
    @Override
    public String toString() {
        String res = "Graph{" + "edges=[";
        for (int i = 1; i <= nVertices; i++) {
            EdgeNode edgeNode = edges[i];
            if(i < nVertices) {
                res += "\n";
                res += "List " + i + ": \t";
                if(edgeNode != null) {
                    res += edgeNode.toString() + ", ";
                }
            } else {
                res += "\n";
                res += "List " + i + ": \t";
                if(edgeNode != null) {
                    res += edgeNode.toString();
                }
            }
        }
        res += "\n], \ndegree=[";
        for (int i = 1; i <= nEdges+1 ; i++) {
            if (i < nEdges + 1) res += degree[i] + ", ";
            else res += degree[i];
        }
        res += "], \nnVertices=" + nVertices +
                ", nEdges=" + nEdges +
                ", directed=" + directed +
                '}';
        return res;
    }
    public static void initialize_graph(Graph g, boolean directed) {
        g.nVertices = 0;
        g.nEdges = 0;
        g.directed = directed;
        for (int i = 1; i <= MAXV; i++) {
            g.degree[i] = 0;
            g.edges[i] = null;
        }
    }
    public static void read_graph(Graph g, boolean directed, File graphFile) {
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
        for (int i = 2; i < lines.size(); i++) {
            String[] line = lines.get(i).split(" ");
            x = Integer.parseInt(line[0]);
            y = Integer.parseInt(line[1]);
            //System.out.println(x + " " + y);
            insert_edge(g, x, y, directed);
        }
        g.nVertices = Integer.parseInt(lines.get(1));
    }
    public static void insert_edge(Graph g, int x, int y, boolean directed) {
        EdgeNode p = new EdgeNode(y, null, g.edges[x]);
        g.edges[x] = p;
        g.degree[x]++;
        if (directed == false) {
            insert_edge(g, y, x, true);
        } else g.nEdges++;
    }
}
class EdgeNode {
    int y;
    Integer weight;
    EdgeNode next;

    public EdgeNode(int y, Integer weight, EdgeNode next) {
        this.y = y;
        this.weight = weight;
        this.next = next;
    }

    @Override
    public String toString() {
        return "EdgeNode{" +
                "y=" + y +
                ", weight=" + weight +
                ", next=" + next +
                '}';
    }
}
class BFS {
    static boolean[] processed = new boolean[Graph.MAXV + 1];
    static boolean[] discovered = new boolean[Graph.MAXV + 1];
    static int[] parent = new int[Graph.MAXV + 1];

    public BFS() {
    }
    public static void initialize_search(Graph g) {
        //BFS bfs = new BFS();
        for (int i = 1; i <= g.nVertices; i++) {
            processed[i] = false;
            discovered[i] = false;
            parent[i] = -1;
        }
        //return bfs;
    }
    public static boolean[] bfs(Graph g, int start) {
        Queue<Integer> queue = new LinkedList<>();
        int v; // current vertex
        int y; // successor vertex
        EdgeNode p;

        queue.add(start);
        discovered[start] = true;
        while(queue.isEmpty() == false) {
            v = queue.remove();
            process_vertex_early(v);
            processed[v] = true;
            p = g.edges[v];
            while(p != null) {
                y = p.y;
                if((processed[y] == false) || g.directed) process_edge(v, y);
                if (discovered[y] == false) {
                    queue.add(y);
                    discovered[y] = true;
                    parent[y] = v;
                }
                p = p.next;
            }
            process_vertex_late(v);
        }
        return discovered;
    }
    public static void process_vertex_early(int v) {

    }
    public static void process_vertex_late(int v) {
        System.out.print(v + ", ");
    }
    public static void process_edge(int v, int y) {

    }
    public static int countConnectedComponents(Graph g, int start) {
        BFS.initialize_search(g);
        int comp = 1;
        System.out.println("Connected Component # " + comp);
        boolean[] discovered = BFS.bfs(g, start);
        System.out.println();
        for (int i = 1; i <= g.nVertices; i++) {
            if(discovered[i] == false) {
                comp++;
                System.out.println("Connected Component # " + comp);
                BFS.bfs(g, i);
                System.out.println();
            }
        }
        return comp;
    }
}
class Driver {
    public static void main(String[] args) {
        Graph g = new Graph();
        String f1 = "s-1-9-10.txt";
        String f2 = "s-2-54-50.txt";
        String f3 = "s-3-96-100.txt";
        String f4 = "s-4-2450-100.txt";
        File file = new File("/Users/conorbrandon/Documents/SBU/Fall2020/CSE373/hw3/" + f4);
        Graph.read_graph(g, false, file);
        System.out.println(g.toString());
        System.out.println("Number of connected components is " + BFS.countConnectedComponents(g, 1));
    }
}

