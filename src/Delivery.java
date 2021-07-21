import java.util.*;


public class Delivery {
    private DijGraph westLafayette;//The graph
    private Node restaurant;//The vertex that the driver start
    private Node[] customer;//The vertices that the driver need to pass through
    private double slope;//Tip percentage function slope
    private double intercept;//Tip percentage function intercept
    private double [] order;//The order amount from each customer
    public Delivery (DijGraph graph, Node restaurant, Node[] customer, double slope, double intercept, double[] order){
        this.westLafayette = graph;
        this.restaurant = restaurant;
        this.customer = customer;
        this.slope = slope;
        this.intercept  = intercept;
        this.order = order;
    }

    //Finding the best path that the driver can earn most tips
    //Each time the driver only picks up three orders
    //Picking up N orders and find the maximum tips will be NP-hard
    public double bestPath(){
        // array for all tips
        double[] allTips = new double[6];
        double max = 0.0;
        // calculate tips from each path
        for (int i = 0; i < 6; i++) {
            double[] tips = new double[3];
            int[] distances = new int[3];
            for (int j = 0; j < 3; j++) {
                int to = 0;
                int from;
                int dist = -1;
                Dist[] routes;
                // j == 0 from the restaurant
                // for i = 0 to 2
                if (i < 3) {
                    if (j == 0) {
                        from = restaurant.getNodeNumber();
                    } else {
                        // from customer (i + j - 1) % 3
                        // to customer (i + j) % 3
                        from = customer[(i + j - 1) % 3].getNodeNumber();
                    }
                    to = (i + j) % 3;
                }
                // for i = 3 to 5
                else {
                     if (j == 0) {
                         from = restaurant.getNodeNumber();
                     } else {
                         // from customer (i - j + 1) % 3
                         // to customer (i - j) % 3
                         from = customer[(i - j + 1) % 3].getNodeNumber();
                     }
                    to = (i - j) % 3;
                }
                routes = this.westLafayette.dijkstra(from);
                dist = routes[customer[to].getNodeNumber()].getDist();
                for (int k = j; k < 3; k++) {
                    distances[k] += dist;
                }
                double percent = ((slope * distances[j]) + intercept) / 100;
                tips[j] = percent * order[to];
            }
            allTips[i] = tips[0] + tips[1] + tips[2];
            if (allTips[i] > max) {
                max = allTips[i];
            }
        }
        return max;
    }
}
