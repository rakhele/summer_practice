package project.orange;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class GraphDrawer {
    private int radius = 30;
    private double bigRadius;
    private int vertexCount;
    private int edgesCount;
    private VertexDraw[] dVertexes;
    private LineDraw[] dLines;
    private JFrame frame;
    private Graphics2D g2;


    public GraphDrawer(String vertexes, int[][] sizes){
        vertexCount = vertexes.length();
        dVertexes = new VertexDraw[vertexCount];
        dLines = new LineDraw[vertexCount*vertexCount];
        for(int i = 0; i < vertexCount; i++){
            dVertexes[i] = new VertexDraw(vertexes.charAt(i));
        }
        setVertexCoords();
        edgesCount = 0;
        for(int i = 0; i < vertexCount; i++){
            for(int j = 0; j < vertexCount; j++){
                if(i == j){}
                else{
                    if(sizes[i][j] > 0){
                        dLines[edgesCount] = new LineDraw(dVertexes[j] , dVertexes[i], sizes[i][j]);
                        edgesCount++;
                        if(i > j && sizes[j][i] > 0) dLines[edgesCount - 1].setPareModificate(7);
                        if(i < j && sizes[j][i] > 0) dLines[edgesCount - 1].setPareModificate(-7);
                    }
                }
            }
        }


        makeFrame();

    }

    private void setVertexCoords(){
        //bigRadius = (vertexCount/2 + 1)*radius;
        double ang = 0;
        double step = (2 * Math.PI )/ vertexCount;
        int minGap = 25;
        bigRadius =  (Math.sqrt(2) * radius) / step + radius + minGap;
        int curx, cury;
        int centerx =  (int)bigRadius + radius + 5;
        int centery =  (int)bigRadius + radius + 5;

        if(vertexCount == 1) {
            bigRadius = 0;
        }

        for (int i = 0; i < vertexCount; i++) {
            curx = centerx + (int)(bigRadius * Math.cos(ang));
            cury = centery + (int)(bigRadius * Math.sin(ang));
            ang += step;

            dVertexes[i].setCoords(curx, cury);

            System.out.println("Vertex "+ i + " at (" + dVertexes[i].getX() + "," + dVertexes[i].getY() + ")");
        }


    }


    private void makeFrame(){
        frame = new JFrame();
        frame.setBounds(200,180, 500, 450);

        class GComp extends JComponent {
            protected void paintComponent(Graphics g) {
                g2 = (Graphics2D) g;
                Line2D l;
                boolean edgePair = false;

                for(int i = 0; i < edgesCount; i++){
                    dLines[i].drawEdge(g2, radius);
                }
                for(int i = 0; i < vertexCount; i++){
                    dVertexes[i].draw(g2, radius, new Color(0xFFEA2D));
                }
            }
        }


        frame.add(new GComp());
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

    }


    public void setVisile(){
        frame.setVisible(true);
    }

    public void setNonVisile(){
        frame.setVisible(false);
    }

}
