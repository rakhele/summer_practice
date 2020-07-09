package project.orange;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {

    Graph graph;

    @Before
    public void setUp(){
        String string = "a b 1\nc d 2\nd a 3";
        graph = new Graph(string);
    }

    @Test
    public void testNotEmptyStrGetVertices(){
        assertEquals("abcd", graph.getVertices());
    }

    @Test
    public void testNotEmptyStrGetMatrix(){
        int[][] expectedGraph = new int[][] {{0, 1, -1, -1},
                                            {-1, 0, -1, -1},
                                            {-1, -1, 0, 2},
                                            {3, -1, -1, 0}};
        assertTrue(java.util.Arrays.deepEquals(expectedGraph, graph.getMatrix()));
        graph = new Graph("a b 3\na c 1\na d 3\nb e 5\nc d 1\nd e 4");
        assertFalse(java.util.Arrays.deepEquals(expectedGraph, graph.getMatrix()));
        expectedGraph = new int[][] {{0, 3, 1, 3, -1},
                                    {-1, 0, -1, -1, 5},
                                    {-1, -1, 0, 1, -1},
                                    {-1, -1, -1, 0, 4},
                                    {-1, -1, -1, -1, 0}};
        assertTrue(java.util.Arrays.deepEquals(expectedGraph, graph.getMatrix()));
    }

    @Test
    public void testFloydWarshall() {
        graph.FloydWarshall();
        int[][] expectedGraph = new int[][] {{0, 1, -1, -1},
                                            {-1, 0, -1, -1},
                                            {5, 6, 0, 2},
                                            {3, 4, -1, 0}};
        assertTrue(java.util.Arrays.deepEquals(expectedGraph, graph.getMatrix()));
        expectedGraph = new int[][] {{0, 3, 1, 3, -1},
                                    {-1, 0, -1, -1, 5},
                                    {-1, -1, 0, 1, -1},
                                    {-1, -1, -1, 0, 4},
                                    {-1, -1, -1, -1, 0}};
        assertFalse(java.util.Arrays.deepEquals(expectedGraph, graph.getMatrix()));
        graph = new Graph("a b 3\na c 1\na d 3\nb e 5\nc d 1\nd e 4");
        graph.FloydWarshall();
        expectedGraph = new int[][] {{0, 3, 1, 2, 6},
                                    {-1, 0, -1, -1, 5},
                                    {-1, -1, 0, 1, 5},
                                    {-1, -1, -1, 0, 4},
                                    {-1, -1, -1, -1, 0}};
        assertTrue(java.util.Arrays.deepEquals(expectedGraph, graph.getMatrix()));

    }

}