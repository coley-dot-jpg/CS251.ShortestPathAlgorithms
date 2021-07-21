import java.util.*;


public class Dist implements Comparable<Dist>{
    private int nodenum;//vertex number
    private int dist;//the distance from source to this vertex

    public Dist(int num, int distance){

        this.nodenum=num;
        this.dist = distance;
    }
    @Override
    public int compareTo(Dist o) {
        if (o.dist < this.dist) {
            return 1;
        } else if (o.dist > this.dist) {
            return -1;
        } else {
            return 0;
        }
    }
    public int getDist(){
        return dist;
    }

    public int getNodeNumber(){
        return nodenum;
    }
    public void updateDist(int newDistance){
        dist = newDistance;
    }

}
