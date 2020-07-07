package project.orange;

public class GraphController {
    private /* ??? */ Graph graph;
    //GraphDrawer drawer;

    public void fileReader(String input) {
        if (!syntaxAnalizer(input)) {
            //нужно вывести сообщение об ошибке
            //можно попробовать возвращать тру/фолс в интерфейс
            //и там вызывать диалоговое окно или что-то в этом роде
            //или кидать и обрабатывать исключение
            return;
        }
        graph = new Graph(input);
        getCurrentState();
    }

    public void consoleReader(String input) {
        if (!syntaxAnalizer(input)) {
            return;
        }
        graph = new Graph(input);
        getCurrentState();
    }

    public void randomGraph(String input) {
        if (input == null) { return; }
        int verNum = Integer.valueOf(input);
        graph = GraphGenerator.generateRandom(verNum);
        getCurrentState();
    }

    private boolean syntaxAnalizer(String input) {
        if (input == null) {
            return false;
        }
        String[] edges = input.split("\n");
        for (String str: edges) {
            if (!str.matches("^[a-zA-Zа-яА-ЯёЁ] [a-zA-Zа-яА-ЯёЁ] \\d+")) {
                return false;
            }
        }
        return true;
    }

    public void doAll() {
        if (graph == null) {
            //сообщение об ошибке?
            return;
        }
        graph.FloydWarshall();
        System.out.println("Алгоритм выполнен\n");
        getCurrentState();
    }

    public void doStep() {
        if (graph == null) {
            //сообщение об ошибке?
            return;
        }
        graph.FloydWarshallStep();
        getCurrentState();
    }

    public void getCurrentState() {
        int[][] matrix = graph.getMatrix();
        String vertexList = graph.getVertices();

        String res = "";
        for (int i = 0; i < vertexList.length(); i++) {
            for (int j = 0; j < vertexList.length(); j++) {
                res = res + vertexList.charAt(i) + " " + vertexList.charAt(j);
                res = res + " " + matrix[i][j] + "\n";
            }
        }
        System.out.println(res);
    }

    public void saveRes() {
        int[][] matrix = graph.getMatrix();
        String vertexList = graph.getVertices();

        String res = "";
        for (int i = 0; i < vertexList.length(); i++) {
            for (int j = 0; j < vertexList.length(); j++) {
                res = res + vertexList.charAt(i) + " " + vertexList.charAt(j);
                res = res + " " + matrix[i][j] + "\n";
            }
        }
        System.out.println(res);
    }

    public void drawGraph() {

    }

    public void drawMatrix() {

    }
}
