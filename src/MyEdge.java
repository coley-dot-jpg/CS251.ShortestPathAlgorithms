public class MyEdge implements Comparable<MyEdge> {
    private int source;
    private int destination;
    private int weight;

    public MyEdge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public int getS(){
        return source;
    }
    public int getD(){
        return  destination;
    }

    public int getWeight(){
        return  weight;
    }

    //Implement the comparable interface for MyEdge class
    //This will provide interface for PriorityQueue to compare the instances of MyEdge class
    @Override
    public int compareTo(MyEdge o) {
        if (o.weight < this.weight) {
            return 1;
        } else if (o.weight > this.weight) {
            return -1;
        } else {
            return 0;
        }
    }

}
