import java.io.File;
import java.io.IOException;
import java.util.*;

public class KruGraph {
    private Vertex[] vertexArr;
    private ArrayList<MyEdge> edgeArr;
    private int vertexCount;
    private int edgeCount;

    //Implement the constructor for KruGraph
    //The format of the input file is the same as the format of the input file in Dijkstra
    public KruGraph(String graph_file)throws IOException{

        File file = new File(graph_file);
        Scanner sc = new Scanner(file);
        vertexCount = sc.nextInt();
        edgeCount = sc.nextInt();
        vertexArr = new Vertex[vertexCount + 1];
        edgeArr = new ArrayList<>(vertexCount + 1);
        for (int i = 0; i < edgeCount; i++) {
            int begin = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            edgeArr.add(i, new MyEdge(begin, end, weight));
        }
        for (int i = 1; i <= vertexCount; i++) {
            vertexArr[i] = new Vertex(i);
        }
    }

    //Could be a helper function
    private void addEdge(int from, int to, int weight){
        MyEdge edge = new MyEdge(from, to, weight);
        edgeArr.add(edge);
    }


    //Implement Kruskal with weighted union find algorithm
    public PriorityQueue<MyEdge> kruskalMST(){

        //PriorityQueue<MyEdge> myPQ = new PriorityQueue<>(vertexCount - 1);
        PriorityQueue<MyEdge> MST = new PriorityQueue<MyEdge>(vertexCount - 1);
        // sort edges
        Collections.sort(edgeArr, MyEdge :: compareTo);
        int i = 0;
        while (MST.size() < vertexCount - 1) {
            // get the next minimum edge
            MyEdge edge = edgeArr.get(i);
            // check if the edge is connected/would make a cycle
            if (find(vertexArr[edge.getS()]) != find(vertexArr[edge.getD()])) {
                MST.add(edge);
                union(vertexArr[edge.getS()], vertexArr[edge.getD()]);
            }
            i++;
        }
        return MST;
    }

    //Implement the recursion trick for the leaves to update the parent efficiently
    //Set it as static as always
    public static Vertex find(Vertex x) {

        // find the parent/root of the tree of x
        if (x != x.getParent()) {
            x.updateParent(find(x.getParent()));
        }
        return x.getParent();
    }


    //This function should union two vertices when an edge is added to the MST
    //Return true when the edge can be picked in the MST
    //Otherwise return false
    //Set it as static as always
    public static boolean union(Vertex x, Vertex y) {
        Vertex parentX = find(x);
        Vertex parentY = find(y);
        // if they have the same parent then they are connected so return false
        if (parentX.getVertexNumber() == parentY.getVertexNumber()) {
            return false;
        }
        //
        if (parentX.getSize() > parentY.getSize()) {
            parentY.updateParent(parentX);
            parentX.updateSize(parentX.getSize() + parentY.getSize());
        } else {
            parentX.updateParent(parentY);
            parentY.updateSize(parentX.getSize() + parentY.getSize());
        }
        return true;
    }

    //This is what we expect for the output format
    //The test cases will follow this format
    public static void printGraph(PriorityQueue<MyEdge> edgeList) {
        int turn = edgeList.size();
        for (int i = 0; i < turn; i++) {
            MyEdge edge = edgeList.poll();
            int source = edge.getS();
            int dest = edge.getD();
            if (source > dest) {
                int temp = source;
                source = dest;
                dest = temp;
            }
            System.out.println("from: " + source + " to: " + dest + " weight: " + edge.getWeight());
        }
    }

    public static void main(String[] args) throws IOException {
        KruGraph graph = new KruGraph("localtestk2.txt");
        printGraph(graph.kruskalMST());
    }

}
