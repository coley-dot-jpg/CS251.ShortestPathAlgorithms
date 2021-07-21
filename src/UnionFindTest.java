import static org.junit.Assert.*;
import java.util.PriorityQueue;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class UnionFindTest {



    @Test
    public void testLeftSmall (){
        Vertex test1 = new Vertex(1);
        Vertex test2 = new Vertex(2);
        Vertex test3 = new Vertex(3);
        Vertex test4 = new Vertex(4);
        Vertex test5 = new Vertex(5);
        Vertex sol1 = new Vertex(1);
        Vertex sol2 = new Vertex(2);
        Vertex sol3 = new Vertex(3);
        Vertex sol4 = new Vertex(4);
        Vertex sol5 = new Vertex(5);
        test1.updateParent(test2);
        test2.updateParent(test3);
        test2.updateSize(2);
        test3.updateSize(3);
        test4.updateParent(test5);
        test5.updateSize(2);
        sol1.updateParent(sol3);
        sol2.updateParent(sol3);
        sol3.updateSize(5);
        sol4.updateParent(sol5);
        sol5.updateParent(sol3);
        KruGraph.union(test4,test1);
        assertEquals("the left small tree should bind to the right large tree!", sol1.getParent().getVertexNumber(), test1.getParent().getVertexNumber());
        assertEquals("the left small tree should bind to the right large tree!", sol2.getParent().getVertexNumber(), test2.getParent().getVertexNumber());
        assertEquals("the left small tree should bind to the right large tree!", sol3.getParent().getVertexNumber(), test3.getParent().getVertexNumber());
        assertEquals("the left small tree should bind to the right large tree!", sol4.getParent().getVertexNumber(), test4.getParent().getVertexNumber());
        assertEquals("the left small tree should bind to the right large tree!", sol5.getParent().getVertexNumber(), test5.getParent().getVertexNumber());
        assertEquals("The root of the tree has wrong size", sol3.getSize(), test3.getSize());
    }

    @Test
    public void testRightSmall (){
        Vertex test1 = new Vertex(1);
        Vertex test2 = new Vertex(2);
        Vertex test3 = new Vertex(3);
        Vertex test4 = new Vertex(4);
        Vertex test5 = new Vertex(5);
        Vertex sol1 = new Vertex(1);
        Vertex sol2 = new Vertex(2);
        Vertex sol3 = new Vertex(3);
        Vertex sol4 = new Vertex(4);
        Vertex sol5 = new Vertex(5);
        test1.updateParent(test2);
        test2.updateParent(test3);
        test2.updateSize(2);
        test3.updateSize(3);
        test4.updateParent(test5);
        test5.updateSize(2);
        sol1.updateParent(sol3);
        sol2.updateParent(sol3);
        sol3.updateSize(5);
        sol4.updateParent(sol5);
        sol5.updateParent(sol3);
        KruGraph.union(test1,test4);
        assertEquals("the right small tree should bind to the left large tree", sol1.getParent().getVertexNumber(), test1.getParent().getVertexNumber());
        assertEquals("the right small tree should bind to the left large tree", sol2.getParent().getVertexNumber(), test2.getParent().getVertexNumber());
        assertEquals("the right small tree should bind to the left large tree", sol3.getParent().getVertexNumber(), test3.getParent().getVertexNumber());
        assertEquals("the right small tree should bind to the left large tree", sol4.getParent().getVertexNumber(), test4.getParent().getVertexNumber());
        assertEquals("the right small tree should bind to the left large tree", sol5.getParent().getVertexNumber(), test5.getParent().getVertexNumber());
        assertEquals("The root of the tree has wrong size", sol3.getSize(), test3.getSize());

    }

    @Test
    public void testEqualHeight(){
        Vertex test1 = new Vertex(1);
        Vertex test2 = new Vertex(2);
        Vertex test3 = new Vertex(3);
        Vertex test4 = new Vertex(4);
        Vertex test5 = new Vertex(5);
        Vertex test6 = new Vertex(6);
        Vertex sol1 = new Vertex(1);
        Vertex sol2 = new Vertex(2);
        Vertex sol3 = new Vertex(3);
        Vertex sol4 = new Vertex(4);
        Vertex sol5 = new Vertex(5);
        Vertex sol6 = new Vertex(6);
        test1.updateParent(test2);
        test2.updateSize(2);
        test2.updateParent(test3);
        test3.updateSize(3);
        test4.updateParent(test5);
        test5.updateSize(2);
        test5.updateParent(test6);
        test6.updateSize(3);


        sol1.updateParent(sol3);
        sol2.updateParent(sol3);
        sol3.updateParent(sol6);
        sol4.updateParent(sol6);
        sol5.updateParent(sol6);
        sol6.updateSize(6);
        KruGraph.union(test1,test4);


        assertEquals("the left tree should bind to the right tree when height is the same, the leaves should update their parent to the root!", sol1.getParent().getVertexNumber(), test1.getParent().getVertexNumber());
        assertEquals("the left tree should bind to the right tree when height is the same, the leaves should update their parent to the root!", sol2.getParent().getVertexNumber(), test2.getParent().getVertexNumber());
        assertEquals("the left tree should bind to the right tree when height is the same, the leaves should update their parent to the root!", sol3.getParent().getVertexNumber(), test3.getParent().getVertexNumber());
        assertEquals("the left tree should bind to the right tree when height is the same, the leaves should update their parent to the root!", sol4.getParent().getVertexNumber(), test4.getParent().getVertexNumber());
        assertEquals("the left tree should bind to the right tree when height is the same, the leaves should update their parent to the root!", sol5.getParent().getVertexNumber(), test5.getParent().getVertexNumber());
        assertEquals("the left tree should bind to the right tree when height is the same, the leaves should update their parent to the root!", sol6.getSize(), test6.getSize());
        assertEquals("the left tree should bind to the right tree when height is the same, the leaves should update their parent to the root!", sol6.getParent().getVertexNumber(), test6.getParent().getVertexNumber());
    }


}
