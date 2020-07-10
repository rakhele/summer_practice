package project.orange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class GraphDrawer implements MouseListener, MouseMotionListener {
    private int radius = 30;
    private double bigRadius;
    private int vertexCount;
    private int edgesCount;
    private VertexDraw[] dVertexes;
    private LineDraw[] dLines;
    private JFrame frame;
    private Graphics2D g2;
    Point2D offSet;


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
        frame.setTitle("Заданный граф (можно двигать вершины)");
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
                    dVertexes[i].draw(g2, radius);
                }
            }
        }


        frame.add(new GComp());
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.addMouseListener(this);
        frame.addMouseMotionListener(this);
    }

    public void drawStep(int[] info){
        for(int f = 0; f < vertexCount; f++){
            if(f == info[0] ){dVertexes[f].setColor(new Color(0xFFB24D));}
            else if(f == info[1]){dVertexes[f].setColor(new Color(0xFA8C69));}
            else if(f == info[2]){dVertexes[f].setColor(new Color(0xC1FF4D));}
            else dVertexes[f].setColor(new Color(0xFFFF1A));
            refresh();
        }

    }

    public void refresh(){
        frame.update(g2);
        frame.repaint();
        frame.invalidate();
        frame.revalidate();}

    public void setVisile(){
        frame.setVisible(true);
    }

    public void setNonVisile(){
        frame.setVisible(false);
    }

    private VertexDraw selected = null;

    public void mousePressed(MouseEvent e) {
        selected = null;
        int x = e.getX();
        int y = e.getY();
        for (VertexDraw el : dVertexes) {
            double coordinates = Math.pow((x - el.getX() - radius/2), 2) + Math.pow((y - el.getY()- radius/2), 2);

            if (coordinates <= Math.pow(radius, 2)) {
                selected = el;
                int newX, newY;
                newX = (x > el.getX() ? x - el.getX() : el.getX() - x);
                newY = (y > el.getY() ? y - el.getY() : el.getY() - y);
                offSet = new Point2D.Double(newX, newY);
                //offSet = new Point2D.Double(x - el.getX(), y - el.getY());
                break;
            }
        }
    }

    public void mouseClicked(MouseEvent e){}
    public void mouseReleased(MouseEvent e){
        selected = null;
        refresh();

    }
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

    public void mouseDragged(MouseEvent e) {
        if (selected != null){
            double x = e.getX() - offSet.getX();
            double y = e.getY() - offSet.getY();

            selected.setCoords((int)x, (int)y);

        }
    }
    public void mouseMoved(MouseEvent e){}
}
