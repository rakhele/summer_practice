package project.orange;

import java.util.Random;

public class GraphGenerator {
    static Vertex[] vertices;
    static final int maxVertices = GraphController.maxVertices;
    static final int maxWeight = GraphController.maxWeight;
    static final Random random = new Random();

    public static Graph generateRandom(int numVertices) {
        if (numVertices > maxVertices || numVertices < 2) { // если одна вершина, то в Graph вылезают ошибки
            return null; // по-хорошему нужно кинуть исключение и в inter его обработать
        }

        vertices = new Vertex[numVertices];
        String graph = "";
        for (int i = 0; i < numVertices; i++) {
            vertices[i] = new Vertex((char) ('a' + i));
        }

        for (int i = 0; i < numVertices; i++){
            int numEdges = random.nextInt(numVertices-1) + 1;// кол-во ребер из вершины
            if(numEdges > numVertices/2)
                numEdges--;

            /*
            if(numEdges > numVertices - 3 && numVertices > 3)
                numEdges -= 2; */

            if (numVertices == 2 && i == 1){ // без этого условия в графе из двух вершин всегда будет два ребра
                numEdges = random.nextInt(2);
            }

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

        return new Graph(graph);

    }

}
