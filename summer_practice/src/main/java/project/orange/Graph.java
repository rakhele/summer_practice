package project.orange;

import java.util.ArrayList;
import java.util.Random;

public class Graph {
    private int verNum;
    Connection[][] matrix;
    ArrayList<Vertex> vertices;
    static final int maxVertices = 10;
    static final int maxWeight = 100;
    final Random random = new Random();

    public Graph(String str) {
        /* по-хорошему, нужно добавить синтаксический анализатор,
         * но пока будем считать, что пользователь знает, что делает */
        String[] edges = str.split("\n");
        Connection[][] tmp = new Connection[edges.length * 2][];
        for (Connection[] conn : tmp) {
            conn = new Connection[edges.length * 2];
        }
        for (String edge: edges) {
            Vertex ver = new Vertex(edge.charAt(0));
            if (!vertices.contains(ver)) {
                vertices.add(ver);
            }
            int ind1 = vertices.indexOf(ver);
            ver = new Vertex(edge.charAt(2));
            if (!vertices.contains(ver)) {
                vertices.add(ver);
            }
            int ind2 = vertices.indexOf(ver);
            tmp[ind1][ind2] = new Connection(edge.charAt(4) - '0', "" + edge.charAt(0) + edge.charAt(2));
        }
        matrix = new Connection[vertices.size()][];
        for (int n = 0; n < matrix.length; n++) {
            matrix[n] = new Connection[vertices.size()];
            for (int m = 0; m < matrix[n].length; m++) {
                if (m == n) {
                    matrix[n][m] = new Connection(0, "" + vertices.get(m).getName());
                } else if (tmp[n][m] == null) {
                    matrix[n][m] = new Connection(-1, "");
                } else {
                    matrix[n][m] = tmp[n][m];
                }
            }
        }

    }

    public void FloydWarshall() {
        for (int k = 0; k < verNum; k++) {
            for (int i = 0; i < verNum; i++) {
                for (int j = 0; j < verNum; j++) {
                    if (matrix[i][j].getPathLength() < 0 || matrix[i][j].getPathLength() >
                            (matrix[i][k].getPathLength() + matrix[k][j].getPathLength())) {
                        matrix[i][j].changePath(matrix[i][k].getPathLength() + matrix[k][j].getPathLength(),
                                matrix[i][k].getPath() + matrix[k][j].getPath().substring(1));
                    }
                }
            }
        }
    }

    public void generateRandom(int numVertices) {
        if (numVertices > maxVertices || numVertices < 0) {
            return; // по-хорошему нужно кинуть исключение и в inter его обработать
        }

        vertices = new ArrayList<Vertex>();
        //vertices = new Vertex[numVertices];
        String graph = "";
        for (int i = 0; i < numVertices; i++) {
            vertices.add(new Vertex((char) ('a' + i)));
            //vertices[i] = new Vertex((char) ('a' + i));
        }

        for (int i = 0; i < numVertices; i++){
            int numEdges = random.nextInt(numVertices-1) + 1; // кол-во ребер из вершины

            int[] edges = new int[numVertices]; // используемые вершины (изначально все 0 - не используемые)
            edges[i] = 1; // в себя ребро не должно входить
            for (int j = 0; j < numEdges; j++){
                int count = 0; // счетчик для цикла, чтобы не генерировать вечно
                int vertex = 0; // вершина, в которую входит ребро
                while (count < 3){ // всего три попытки
                    vertex = random.nextInt(numVertices);

                    if (edges[vertex] > 0){
                        count++;
                        if (count == 3){
                            for (int v : edges){
                                if (v == 0){ // просто берем первую попавшуюся нулевую вершину
                                    vertex = v;
                                    break;
                                }
                            }
                        }
                        continue;
                    }
                    edges[vertex] = 1;
                    graph += vertices.get(i).getName() + " " + vertices.get(vertex).getName() + " " + (random.nextInt(maxWeight)+1) + "\n";
                    //graph += vertices[i].getName() + " " + vertices[vertex].getName() + " " + (random.nextInt(maxWeight)+1) + "\n";
                    count = 0;
                    break;
                }
            }
        }

        // передать конструктору графа строку graph

    }

}
