package project.orange;

import java.util.ArrayList;

public class Graph {
    private int verNum;
    private int currI;
    private int currJ;
    private int currK;
    Connection[][] matrix;
    ArrayList<Vertex> vertices;

    public Graph(String str) {
        currI = currJ = currK = 0;
        vertices = new ArrayList<Vertex>();
        String[] edges = str.split("\n");
        Connection[][] tmp = new Connection[edges.length * 2][];
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = new Connection[edges.length * 2];
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
            tmp[ind1][ind2] = new Connection(Integer.valueOf(edge.substring(4)), "" + edge.charAt(0) + edge.charAt(2));
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
        verNum = vertices.size();
    }

    public void reset() {
        currI = currJ = currK = 0;
        for (int n = 0; n < matrix.length; n++) {
            for (int m = 0; m < matrix.length; m++) {
                String path = "";
                if (n == m) {
                    path += vertices.get(m).getName();
                } else if (matrix[n][m].getWeight() > 0) {
                    path = path + vertices.get(n).getName() + vertices.get(m).getName();
                }
                matrix[n][m].changePath(matrix[n][m].getWeight(), path);
            }
        }
    }

    public void FloydWarshall() {
        for (currK = 0; currK < verNum; currK++) {
            for (currI = 0; currI < verNum; currI++) {
                for (currJ = 0; currJ < verNum; currJ++) {

                    if (matrix[currI][currK].getPathLength() < 0 || matrix[currK][currJ].getPathLength() < 0) {
                        continue;
                    }

                    if (matrix[currI][currJ].getPathLength() < 0 ||  matrix[currI][currJ].getPathLength() >
                            (matrix[currI][currK].getPathLength() + matrix[currK][currJ].getPathLength())) {
                        matrix[currI][currJ].changePath(matrix[currI][currK].getPathLength() + matrix[currK][currJ].getPathLength(),
                                matrix[currI][currK].getPath() + matrix[currK][currJ].getPath().substring(1));
                    }
                }
            }
        }
    }

    public String FloydWarshallStep() {
        String res;
        if (currI >= (verNum - 1) && currJ >= (verNum - 1) && currK >= (verNum - 1)) {
            res = "Алгоритм был завершен.";
            return res;
        }
        if (matrix[currI][currK].getPathLength() < 0 || matrix[currK][currJ].getPathLength() < 0) {
            res = "Нельзя построить новый путь из вершины " + vertices.get(currI).getName() + " в вершину " + vertices.get(currJ).getName() + "\nчерез вершину " + vertices.get(currK).getName();
        } else if (matrix[currI][currJ].getPathLength() < 0 ||  matrix[currI][currJ].getPathLength() >
                (matrix[currI][currK].getPathLength() + matrix[currK][currJ].getPathLength())) {
            res = "Путь из вершины " + vertices.get(currI).getName() + " в вершину "
                    + vertices.get(currJ).getName() + " был изменен.\nСтарый путь: " + matrix[currI][currJ].getPath() + "; \n";
            matrix[currI][currJ].changePath(matrix[currI][currK].getPathLength() + matrix[currK][currJ].getPathLength(),
                    matrix[currI][currK].getPath() + matrix[currK][currJ].getPath().substring(1));
            res = res + "Hовый путь: " + matrix[currI][currJ].getPath() + "; длина нового пути: "
                    + matrix[currI][currJ].getPathLength();
        } else {
            res = "Путь не был изменен.\nПуть из вершины " + vertices.get(currI).getName() + " в вершину " + vertices.get(currJ).getName() + ": " + matrix[currI][currJ].getPath() + "; длина пути: " + matrix[currI][currJ].getPathLength();
        }

        if (currJ < (verNum - 1)) {
            currJ++;
        } else if (currI < (verNum - 1)) {
            currJ = 0;
            currI++;
        } else if (currK < (verNum - 1)) {
            currJ = 0;
            currI = 0;
            currK++;
        }
        return res;
    }

    public int[] getIJK(){
        int[] ew = new int[]{currI, currJ, currK};
        return ew;
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

    public int[][] getEdges() {
        if (matrix.length == 0) {
            return null;
        }
        int[][] edges = new int[matrix.length][];
        for (int n = 0; n < matrix.length; n++) {
            edges[n] = new int[matrix.length];
            for (int m = 0; m < matrix[n].length; m++) {
                if (m == n) {
                    edges[n][m] = 0;
                } else if (matrix[n][m] == null) {
                    edges[n][m] = -1;
                } else {
                    edges[n][m] = matrix[n][m].getWeight();
                }
            }
        }
        return edges;
    }

    public void changeEdgeWeight(int i, int j, int newWeight) {
        if (i != j) {
            currI = currJ = currK = 0;
            matrix[i][j].changeWeight(newWeight);
            matrix[i][j].changePath(newWeight, "" + vertices.get(i).getName() + vertices.get(j).getName());
        }
    }

    public boolean addEdge(String edge) {
        if (edge == null) { return false; }
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

        if (vertices.size() > matrix.length) {
            Connection[][] tmp = new Connection[vertices.size()][];
            for (int n = 0; n < vertices.size(); n++) {
                tmp[n] = new Connection[vertices.size()];
                for (int m = 0; m < vertices.size(); m++) {
                    if (m < matrix.length && n < matrix.length) {
                        tmp[n][m] = matrix[n][m];
                    } else if (m == n) {
                        tmp[n][m] = new Connection(0, "" + vertices.get(m).getName());
                    } else {
                        tmp[n][m] = new Connection(-1, "");
                    }
                }
            }
            matrix = tmp;
            verNum = vertices.size();
        }
        currI = currJ = currK = 0;
        matrix[ind1][ind2] = new Connection(Integer.valueOf(edge.substring(4)), "" + edge.charAt(0) + edge.charAt(2));
        return true;
    }

    public boolean deleteEdge(String edge) {
        if (edge == null) { return false; }
        Vertex ver = new Vertex(edge.charAt(0));
        if (!vertices.contains(ver)) {
            return false;
        }
        int ind1 = vertices.indexOf(ver);
        ver = new Vertex(edge.charAt(2));
        if (!vertices.contains(ver)) {
            return false;
        }
        int ind2 = vertices.indexOf(ver);

        currI = currJ = currK = 0;
        matrix[ind1][ind2] = new Connection(-1, "");
        return true;
    }

}
