import java.io.File;
import java.io.IOException;
import java.util.*;

public class DijGraph {
    static int MAXWEIGHT = 10000000;//The weight of edge will not exceed this number
    private Node[] nodeArr;//The vertices set in the graph
    private int nodeCount;//number of total vertices
    private int edgeCount;//number of total edges

    //Two option for the DijGraph constructor
    //Option 0 is used to build graph with for part 1: implementation for Dijkstra
    //Option 1 is used to build graph with for part 2: simple application of Dijkstra
    public DijGraph(String graph_file, int option)throws IOException{
        if (option == 0){
            File file = new File(graph_file);
            Scanner sc = new Scanner(file);
            nodeCount = sc.nextInt();
            edgeCount = sc.nextInt();
            nodeArr = new Node[nodeCount + 1];
            for(int i =0; i < nodeCount + 1; i ++){
                if(i != 0) {
                    nodeArr[i] = new Node(i);
                }
            }
            for(int i = 0; i < edgeCount; i++) {
                int begin = sc.nextInt();
                int end = sc.nextInt();
                int weight = sc.nextInt();
                nodeArr[begin].addEdge(end, weight);
                nodeArr[end].addEdge(begin,weight);
            }
        }
        else if (option == 1){
            File file = new File(graph_file);
            Scanner sc = new Scanner(file);
            nodeCount = sc.nextInt();
            edgeCount = sc.nextInt();
            nodeArr = new Node[nodeCount + 1];
            for(int i =0; i < nodeCount + 1; i ++){
                if(i != 0){
                    nodeArr[i]= new Node(i, sc.next());
                }
            }
            for(int i = 0;i < edgeCount; i ++){
                String begin = sc.next();
                String end = sc.next();
                int weight = sc.nextInt();
                Node beginNode = findByName(begin);
                Node endNode = findByName(end);
                beginNode.addEdge(endNode.getNodeNumber(), weight);
                endNode.addEdge(beginNode.getNodeNumber(),weight);
            }
        }

    }

    //Finding the single source shortest distances by implementing dijkstra.
    //Using min heap to find the next smallest target
    public  Dist[] dijkstra( int source){

        // create an initialize each array. values in Q = max weight unless index = source
        Dist[] heap = new Dist[nodeCount];
        Dist[] result = new Dist[nodeCount + 1];
        result[source] = new Dist(source, 0);
        int size = 0;
        // for each vertex in G
        for (int i = 1; i <= nodeCount; i++) {
            if (i != source) {
                result[i] = new Dist(i, 100000);
            }
            insert(heap, result[i], i - 1);
            size++;
        }
        while (size != 0) {
            Dist min = extractMin(heap, size);
            size--;
            // find hash map of adjacent vertices
            HashMap<Integer, Integer> adj = nodeArr[min.getNodeNumber()].getEdges();
            for (HashMap.Entry<Integer, Integer> node : adj.entrySet()) {
                // check if the node is in the heap
                int heapInd = inHeap(heap, node.getKey(), size);
                if (heapInd != -1) {
                    // if it is, relax the edge if need be
                    int relax = min.getDist() + node.getValue();
                    // check if the newly calculated edge weight is lower than the current weight of that edge
                    if (relax < result[node.getKey()].getDist()) {
                        result[node.getKey()].updateDist(relax);
                        //update distance in heap
                        heap[heapInd].updateDist(relax);
                        // swim if necessary
                        while (heap[heapInd].getDist() < heap[(heapInd - 1) / 2].getDist()) {
                            swap(heap, heapInd, (heapInd - 1) / 2);
                            heapInd = (heapInd - 1) / 2;
                        }
                    }
                }

            }
        }
        return result;
    }

    //Find the vertex by the location name
    public Node findByName(String name){
        for (int x =1; x < nodeCount + 1; x++) {
            if(nodeArr[x].getLocation().equals(name)){
                return nodeArr[x];
            }
        }
        return null;
    }

    //Implement insertion in min heap
    //first insert the element to the end of the heap
    //then swim up the element if necessary
    //Set it as static as always
    public static void insert(Dist [] arr, Dist value, int index) {

        //insert value in array
        if (index >= arr.length){
            return;
        }
        arr[index] = value;
        if (index == 0) {
            return;
        }
        // swim if necessary
        while (arr[index].getDist() < arr[(index - 1) / 2].getDist()) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(Dist []arr, int index1, int index2){
        Dist temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    //Extract the minimum element in the min heap
    //replace the last element with the root
    //then do minheapify
    //Set it as static as always
    public static Dist extractMin (Dist[] arr, int size){
        // swap the first and last element and delete the min
        Dist min = arr[0];
        arr[0] = arr[size - 1];
        arr[size - 1] = null;
        size--;

        // minheapify
        minHeapify(arr, 0, size);
        return min;
    }
    public static void minHeapify(Dist[] arr, int parent, int size) {
        // find indices of left and right children
        int left = (parent * 2) + 1;
        int right = (parent * 2) + 2;
        if (parent < size / 2) {
            // swap the parent with the minimum child
            if ((arr[left] != null && arr[right] == null && arr[parent].getDist() > arr[left].getDist())
                    || (arr[left] != null && arr[parent].getDist() > arr[left].getDist() && arr[left].getDist() <= arr[right].getDist())) {
                swap(arr, parent, left);
                minHeapify(arr, left, size);
            } else if (arr[right] != null && arr[parent].getDist() > arr[right].getDist()) {
                swap(arr, parent, right);
                minHeapify(arr, right, size);
            }
        }
    }
    // my method to check if the vertex/node is in the help
    // helper for dijkstras()
    private static int inHeap(Dist[] arr, int nodeNum, int size) {
        for (int i = 0; i < size; i++) {
            if (arr[i].getNodeNumber() == nodeNum) {
                return i;
            }
        }
        return -1;
    }

    //This will print the shortest distance result
    //The output format will be what we expect to pass the test cases
    public static void printResult(Dist[] result, int source){
        for(int x = 1;  x < result.length; x++){
            if(x != source){
                System.out.println(result[x].getNodeNumber() + " " +result[x].getDist());
            }
        }
    }

    public static void main(String[] args)throws IOException {
        DijGraph graph = new DijGraph("localtest1.txt", 0);
        Dist[] result  = graph.dijkstra(7);
        printResult(result, 7);
    }
}
