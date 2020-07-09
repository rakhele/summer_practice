package project.orange;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GraphController {
    private Graph graph;
    GraphDrawer drawer;
    static final int maxVertices = 10;
    static final int maxWeight = 100;

    public boolean consoleReader(String input) {
        if (!syntaxAnalyzer(input)) {
            return false;
        }
        if(drawer != null) drawer.setNonVisile();
        graph = new Graph(input);
        drawer = new GraphDrawer(getVertex(), getMatrix());

        return true;
    }

    public boolean randomGraph(String input) {
        if (input == null) { return false; }
        if(drawer != null) drawer.setNonVisile();
        int verNum = Integer.valueOf(input);
        graph = GraphGenerator.generateRandom(verNum);
        if (graph == null){
            return false;
        }
        drawer = new GraphDrawer(getVertex(), getMatrix());
        return true;
    }

    protected boolean syntaxAnalyzer(String input) {
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

    public boolean doAll() {
        if (graph == null) {
            return false;
        }
        graph.FloydWarshall();
        return true;
    }

    public String doStep() {
        if (graph == null) {
            //сообщение об ошибке?
            return null;
        }
        String res = graph.FloydWarshallStep();
        getCurrentState();
        return res;
    }

    public String getCurrentState() {
        if (graph == null){
            return null;
        }

        int[][] matrix = graph.getMatrix();
        String vertexList = graph.getVertices();

        String res = "";
        for (int i = 0; i < vertexList.length(); i++) {
            for (int j = 0; j < vertexList.length(); j++) {
                res = res + vertexList.charAt(i) + " " + vertexList.charAt(j);
                res = res + " " + matrix[i][j] + "\n";
            }
        }
        return res;
    }

    public String getVertex(){
        return ((graph == null) ? null : graph.getVertices());
    }

    public int[][] getMatrix(){
        return ((graph == null) ? null : graph.getMatrix());
    }

    public boolean saveRes(FileWriter writer) {
        if (graph == null) {
            return false;
        }
        try {
            writer.write(getCurrentState());
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    public boolean drawGraph() {
        if (graph == null)
            return false;
        drawer.setVisile();
        return true;
    }

    public JTable drawMatrix() {
        String infinitySymbol = String.valueOf(Character.toString('\u221E'));

        int[][] g =  getMatrix();
        String in = getVertex();
        Object[][] m = new Object[in.length() + 1][in.length() + 1];
        Character[] f = new Character[in.length() + 1];
        Integer e = -1;
        for(int i  = 0; i < in.length() + 1; i++){
            if(i == 0) f[i] = ' ';
            else f[i] = in.charAt(i - 1);
        }


        for(int i  = 0; i < in.length() + 1; i++){
            for(int j  = 0; j < in.length() + 1; j++){
                if(i == 0 && j == 0) m[0][0] = ' ';
                else if(i == 0) m[0][j] = in.charAt(j - 1);
                else if(j == 0) m[i][0] = in.charAt(i - 1);
                else  m[i][j] = g[i - 1][j - 1];
                if(m[i][j] == e) m[i][j] = infinitySymbol;
            }
        }


        JTable graphMatrix = new JTable(m, f);
        for(int i  = 0; i < in.length() + 1; i++){
            graphMatrix.getColumnModel().getColumn(i).setPreferredWidth(30);
        }
        return graphMatrix;
    }

    public void weightChange(int i, int j, int weight) {
        graph.changeEdgeWeight(i, j, weight);
        drawer = new GraphDrawer(getVertex(), getMatrix());
    }

    public boolean addEdge(String string){

        if (graph == null){
            return consoleReader(string);
        }

        if (!syntaxAnalyzer(string)){
            return false;
        }

        boolean exist = false;
        for (Vertex v: graph.vertices){
            if (v.getName() == string.charAt(0) || v.getName() == string.charAt(2)){
                exist = true;
            }
        }
        if (!exist){
            return false;
        }

        graph.addEdge(string);
        drawer = new GraphDrawer(getVertex(), getMatrix());

        return true;
    }

    public boolean deleteEdge(String string){

        if (graph == null){
            return false;
        }

        string += " 1";
        if (!syntaxAnalyzer(string)){
            return false;
        }
        string = string.substring(0, 3);
        int count = 0;
        for (Vertex v: graph.vertices){
            if (v.getName() == string.charAt(0))
                count++;
            else if (v.getName() == string.charAt(2))
                count++;
        }

        if (count != 2)
            return false;

        graph.deleteEdge(string);
        drawer = new GraphDrawer(getVertex(), getMatrix());

        return true;
    }

}