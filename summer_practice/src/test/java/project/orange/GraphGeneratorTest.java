package project.orange;

import org.junit.Test;
import static org.junit.Assert.*;

public class GraphGeneratorTest {

    @Test
    public void testFalseGenerateRandom() {
        assertNull(GraphGenerator.generateRandom(0));
        assertNull(GraphGenerator.generateRandom(1));
        assertNull(GraphGenerator.generateRandom(-1));
        assertNull(GraphGenerator.generateRandom(GraphGenerator.maxVertices+1));
    }

    @Test
    public void testTrueGenerateRandom() {
        assertNotNull(GraphGenerator.generateRandom(2));
        assertNotNull(GraphGenerator.generateRandom(GraphGenerator.maxVertices));
    }

    @Test
    public void testGenerateRandomNumVertices() {
        assertEquals(2, GraphGenerator.generateRandom(2).getVertices().length());
        assertEquals(4, GraphGenerator.generateRandom(4).getVertices().length());
        assertEquals(GraphGenerator.maxVertices, GraphGenerator.generateRandom(GraphGenerator.maxVertices).getVertices().length());
    }

    @Test
    public void testGenerateRandomNumEdges() {
        assertTrue(GraphGenerator.generateRandom(2).getMatrix().length > 0 &&
                GraphGenerator.generateRandom(2).getMatrix().length < 3);
        assertTrue(GraphGenerator.generateRandom(GraphGenerator.maxVertices).getMatrix().length > GraphGenerator.maxVertices-1 &&
                GraphGenerator.generateRandom(9).getMatrix().length <= GraphGenerator.maxVertices * (GraphGenerator.maxVertices-1));
    }

}