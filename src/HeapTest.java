import static org.junit.Assert.*;

import java.util.PriorityQueue;
import java.util.Random;

import org.junit.Test;
public class HeapTest {

    @Test(timeout = 1000)
    public void testInsert(){
        Random rnd = new Random();
        Dist[] yoursolpq= new Dist[5];
        PriorityQueue<Dist> checkpq = new PriorityQueue<Dist>();
        for(int x =0; x < 5; x++){
            Dist temp = new Dist(x, rnd.nextInt(100)+1);
            DijGraph.insert(yoursolpq, temp, x);
            checkpq.add(temp);
        }
        Dist[] expectedpq =(Dist[])checkpq.toArray(new Dist[0]);
        assertArrayEquals("Your insert method is not correct in testInsertCorrect", expectedpq, yoursolpq);
    }

    @Test(timeout = 1000)
    public void testextractMin(){
        int numOfElements = 10;
        Random rnd = new Random();
        Dist[] yoursolpq= new Dist[numOfElements];
        PriorityQueue<Dist> checkpq = new PriorityQueue<Dist>();
        for(int x =0; x < numOfElements; x++){
            Dist temp = new Dist(x, rnd.nextInt(100)+1);
            DijGraph.insert(yoursolpq, temp, x);
            checkpq.add(temp);
        }
        for(int x = 0; x < 3; x++){
            Dist yoursolmin = DijGraph.extractMin(yoursolpq, numOfElements);
            numOfElements--;
            Dist expectedmin = checkpq.poll();
            assertEquals("Your extractMin method is not correct in testextractMinCorrect since the return value is not correct",expectedmin, yoursolmin);
            Dist[] expectedpq1 =(Dist[])checkpq.toArray(new Dist[0]);
            Dist[] yoursolpqAfterExtract = new Dist[numOfElements];
            for(int y =0; y < numOfElements;y++){
                yoursolpqAfterExtract[y] = yoursolpq[y];
            }
            assertArrayEquals("Your extractMin method is not correct in testextractMinCorrect since the heap is not correctly minheapified",expectedpq1, yoursolpqAfterExtract);
        }
    }


}
