import java.util.*;


public  class Node{
    private HashMap<Integer, Integer> adj = null;//adjacent vertices
    private int nodenumber;//vertex number
    private String locationName;//Name of vertex, will be used in part2
    public Node(int number){
        this.nodenumber = number;
        this.adj = new HashMap<Integer, Integer>();
    }
    public Node(int number, String name){
        this.nodenumber = number;
        this.adj = new HashMap<Integer, Integer>();
        this.locationName = name;
    }
    public void addEdge(int end, int edgedist){
        adj.put(end, edgedist);
    }
    public int contains(int index){
        if(adj.containsKey(index)){
            return adj.get(index);
        }
        else
            return -1;
    }
    public HashMap<Integer,Integer> getEdges(){
        return adj;
    }
    public int getNodeNumber(){
        return nodenumber;
    }

    public String getLocation(){
        return locationName;
    }
}
