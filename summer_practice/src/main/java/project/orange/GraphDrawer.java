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
    private int EdgesCount;
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
        EdgesCount = 0;
        for(int i = 0; i < vertexCount; i++){
            for(int j = 0; j < vertexCount; j++){
                if(i == j){}
                else{
                    if(sizes[i][j] > 0){
                        dLines[EdgesCount] = new LineDraw(dVertexes[i], dVertexes[j], sizes[i][j]);
                        EdgesCount++;
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
        int minGap = 10;
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
                Rectangle2D p;

                for(int i = 0; i < EdgesCount; i++){
                    l = new Line2D.Float(dLines[i].getFromX() + radius/2, dLines[i].getFromY()+radius/2, dLines[i].getToX()+radius/2, dLines[i].getToY()+radius/2);
                    g2.draw(l);
                    //p = new Rectangle2D.Float(dLines[i].getToX(), dLines[i].getToY(), 5, 5);
                   // g2.fill(p);
                }

                Ellipse2D ell;
                for(int i = 0; i < vertexCount; i++){
                    ell = new Ellipse2D.Float(dVertexes[i].getX(), dVertexes[i].getY(), radius, radius);


                    g2.setColor(new Color(0xFFDF48));
                    g2.fill(ell);
                    g2.setColor(new Color(0x0C0C06));

                    g2.drawString(dVertexes[i].getName(), (int)ell.getCenterX() -2, (int)ell.getCenterY()+ 3);
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
