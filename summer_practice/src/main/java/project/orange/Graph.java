package project.orange;

import java.util.Random;

public class Graph {
    private int edgeNum;
    Connection[][] matrix;
    Vertex[] vertices;
    static final int maxVertices = 10;
    static final int maxWeight = 100;
    final Random random = new Random();

    public Graph() {} //will be done at some point

    public void FloydWarshall() {
        for (int k = 0; k < edgeNum; k++) {
            for (int i = 0; i < edgeNum; i++) {
                for (int j = 0; j < edgeNum; j++) {
                    if (matrix[i][j].getPathLength() < 0 || matrix[i][j].getPathLength() >
                            (matrix[i][k].getPathLength() + matrix[k][j].getPathLength())) {
                        matrix[i][j].changePath(matrix[i][k].getPathLength() + matrix[k][j].getPathLength(),
                                matrix[i][k].getPath() + matrix[k][j].getPath());
                    }
                }
            }
        }
    }

    public void generateRandom(int numVertices) {
        if (numVertices > maxVertices || numVertices < 0) {
            return; // по-хорошему нужно кинуть исключение и в inter его обработать
        }

        vertices = new Vertex[numVertices];
        String graph = "";
        for (int i = 0; i < numVertices; i++) {
            vertices[i] = new Vertex((char) ('a' + i));
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
                    graph += vertices[i].getName() + " " + vertices[vertex].getName() + " " + (random.nextInt(maxWeight)+1) + "\n";
                    count = 0;
                    break;
                }
            }
        }

        // передать конструктору графа строку graph

    }

}
