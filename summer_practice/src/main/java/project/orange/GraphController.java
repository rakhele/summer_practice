package project.orange;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GraphController {
    private Graph graph;
    private GraphDrawer drawer;
    private GraphDrawer startDrawer;
    static final int maxVertices = 10;
    static final int maxWeight = 100;

    public boolean consoleReader(String input) {
        if (!syntaxAnalyzer(input)) {
            return false;
        }
        if(drawer != null) drawer.setNonVisile();
        if(startDrawer != null) startDrawer.setNonVisile();
        graph = new Graph(input);
        drawer = new GraphDrawer(getVertex(), getMatrix());
        startDrawer = new GraphDrawer(getVertex(), getEdges());

        return true;
    }

    public boolean randomGraph(String input) {
        if (input == null) { return false; }
        if(drawer != null) drawer.setNonVisile();
        if(startDrawer != null) startDrawer.setNonVisile();
        try {
            int verNum = Integer.valueOf(input);
            graph = GraphGenerator.generateRandom(verNum);
            if (graph == null){
                return false;
            }
            drawer = new GraphDrawer(getVertex(), getMatrix());
            startDrawer = new GraphDrawer(getVertex(), getEdges());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
            return null;
        }
        int[] e = graph.getIJK();
        drawer.drawStep(e);
        String res = graph.FloydWarshallStep();
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

    public int[][] getEdges(){
        return ((graph == null) ? null : graph.getEdges());
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
        int[] e = graph.getIJK();
        if(drawer != null) drawer.setNonVisile();
        drawer = new GraphDrawer(getVertex(), getMatrix());
        drawer.drawStep(e);
        drawer.setVisile();
        return true;
    }

    public boolean drawInitialGraph() {
        if (graph == null)
            return false;
        int[] e = {graph.getVertices().length(), graph.getVertices().length(), graph.getVertices().length()};
        if(startDrawer != null) startDrawer.setNonVisile();
        startDrawer.drawStep(e);
        startDrawer.setVisile();
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
        graphMatrix.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (row == 0 || column == 0 || row == column) { return; }
                TableModel model = (TableModel)e.getSource();
                Object data = model.getValueAt(row, column);
                weightChange(row - 1, column - 1, Integer.valueOf(data.toString()));
            }
        });
        return graphMatrix;
    }

    public void weightChange(int i, int j, int weight) {
        graph.changeEdgeWeight(i, j, weight);
        if(drawer != null) drawer.setNonVisile();
        drawer = new GraphDrawer(getVertex(), getMatrix());
        startDrawer = new GraphDrawer(getVertex(), getEdges());
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
        if(drawer != null) drawer.setNonVisile();
        if(startDrawer != null) startDrawer.setNonVisile();
        drawer = new GraphDrawer(getVertex(), getMatrix());
        startDrawer = new GraphDrawer(getVertex(), getEdges());

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
        if(drawer != null) drawer.setNonVisile();
        if(startDrawer != null) startDrawer.setNonVisile();
        drawer = new GraphDrawer(getVertex(), getMatrix());
        startDrawer = new GraphDrawer(getVertex(), getEdges());
        return true;
    }

    public boolean reset() {
        if (graph == null){
            return false;
        }
        graph.reset();
        if(drawer != null) drawer.setNonVisile();
        if(startDrawer != null) startDrawer.setNonVisile();
        drawer = new GraphDrawer(getVertex(), getMatrix());
        startDrawer = new GraphDrawer(getVertex(), getEdges());
        return true;
    }

}