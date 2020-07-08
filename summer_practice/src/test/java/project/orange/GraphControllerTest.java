package project.orange;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphControllerTest {

    GraphController controller = new GraphController();

    @Test
    public void consoleReader() {
        assertTrue(controller.consoleReader("a b 1"));
        assertTrue(controller.consoleReader("a w 5\nd a 2"));
        assertFalse(controller.consoleReader("a b 0"));
        assertFalse(controller.consoleReader("aq we 200\n"));
    }

    @Test
    public void randomGraph() {
        assertFalse(controller.randomGraph(null));
        assertTrue(controller.randomGraph("4"));
    }

    @Test
    public void testSyntaxAnalizer(){
        assertTrue(controller.syntaxAnalizer("a v 4\nf q 3"));
        assertTrue(controller.syntaxAnalizer("r t 1"));
        assertFalse(controller.syntaxAnalizer(""));
        assertFalse(controller.syntaxAnalizer("ar f 4\na b 2"));
        assertFalse(controller.syntaxAnalizer("a d 4 a s 4"));
    }

    @Test
    public void testNumVertices(){
        String string = "";
        for (int i = 0; i < (GraphController.maxVertices); i = i+2){
            string += (char)('a'+i) + " " + (char)('b'+i) + " 1\n";
        }
        assertTrue(controller.syntaxAnalizer(string));
        assertTrue(controller.syntaxAnalizer("r t 4"));
        assertFalse(controller.syntaxAnalizer("av c 4\nf q 3\nw e 3\nz x 0\np v 5"));
        assertFalse(controller.syntaxAnalizer("a v 4\nf q 3\nw e 3\nz x 0\np v 5"));
        assertTrue(controller.syntaxAnalizer("a v 4\nf q " + Integer.toString(GraphController.maxWeight)));
        assertFalse(controller.syntaxAnalizer("a v 4\nf q " + Integer.toString(GraphController.maxWeight+1)));
        assertFalse(controller.syntaxAnalizer("a d 4 a s 4"));
    }

}
