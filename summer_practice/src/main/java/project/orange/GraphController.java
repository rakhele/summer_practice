package project.orange;

import java.util.ArrayList;

public class GraphController {
    private /* ??? */ Graph graph;
    //GraphDrawer drawer;
    static final int maxVertices = 10;
    static final int maxWeight = 100;

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

    protected boolean syntaxAnalizer(String input) {
        if (input == null) {
            return false;
        }

        ArrayList<Character> vertices = new ArrayList<>();

        String[] edges = input.split("\n");
        for (String str: edges) {
            if (!str.matches("^[a-zA-Zа-яА-ЯёЁ] [a-zA-Zа-яА-ЯёЁ] \\d+")) {
                return false;
            }
            if (!vertices.contains(str.charAt(0))){
                vertices.add(str.charAt(0));
            }
            if (!vertices.contains(str.charAt(2))){
                vertices.add(str.charAt(2));
            }
            if (vertices.size() > maxVertices){
                return false;
            }
            if (Integer.valueOf(str.substring(4)) > maxWeight || Integer.valueOf(str.substring(4)) < 1){
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

    public String doStep() {
        if (graph == null) {
            return "Граф не существует!";
        }
        String res = graph.FloydWarshallStep();
        return res;
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
