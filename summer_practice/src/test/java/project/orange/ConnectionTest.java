package project.orange;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConnectionTest {

    Connection connection;
    @Before
    public void setUp(){
        connection = new Connection(1, "path");
    }

    @Test
    public void testGetWeight() {
        assertEquals(1, connection.getWeight());
        connection.changePath(5, "newPath");
        assertEquals(1, connection.getWeight());
    }

    @Test
    public void testChangePath() {
        assertEquals("path", connection.getPath());
        assertEquals(1, connection.getPathLength());

        connection.changePath(5, "newPath");
        assertEquals("newPath", connection.getPath());
        assertEquals(5, connection.getPathLength());
    }
}