package project.orange;

import java.util.ArrayList;

public class Graph {
    private int verNum;
    Connection[][] matrix;
    ArrayList<Vertex> vertices;

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

    public String getVertices() {
        if (vertices.size() == 0) {
            return null;
        }
        String str = new String("");
        for (Vertex ver : vertices) {
            str = str + ver.getName();
        }
        return str;
    }

    public int[][] getMatrix() {
        if (matrix.length == 0) {
            return null;
        }
        int[][] paths = new int[matrix.length][];
        for (int n = 0; n < matrix.length; n++) {
            paths[n] = new int[matrix.length];
            for (int m = 0; m < matrix[n].length; m++) {
                if (m == n) {
                    paths[n][m] = 0;
                } else if (matrix[n][m] == null) {
                    paths[n][m] = -1;
                } else {
                    paths[n][m] = matrix[n][m].getPathLength();
                }
            }
        }
        return paths;
    }

}
