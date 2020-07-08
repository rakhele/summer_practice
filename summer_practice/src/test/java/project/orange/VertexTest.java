package project.orange;

import org.junit.Test;
import static org.junit.Assert.*;

public class VertexTest {

    Vertex vertex;

    @Test
    public void testGetName() {
        vertex = new Vertex('a');
        assertEquals('a', vertex.getName());
        vertex = new Vertex('!');
        assertEquals('!', vertex.getName());
        vertex = new Vertex('1');
        assertEquals('1', vertex.getName());
    }
}