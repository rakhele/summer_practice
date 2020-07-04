package project.orange;

public class Graph {
    private int edgeNum;
    Connection[][] matrix;
    Vertex[] vertices;

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
}
