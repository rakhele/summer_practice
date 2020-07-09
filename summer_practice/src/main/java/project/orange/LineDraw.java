package project.orange;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class LineDraw {
    private VertexDraw start;
    private VertexDraw end;
    private int modificate;

    private int length;

    public LineDraw(VertexDraw first, VertexDraw second, int len){
        length = len;
        start = first;
        end = second;
        modificate = 0;
    }

    public void setPareModificate(int i){modificate = i;}

    public int getLength(){
        return length;
    }

    public int getFromX(){
        return start.getX() + modificate;
    }

    public int getFromY(){
        return start.getY() + modificate ;
    }

    public int getToX(){
        return end.getX() + modificate;
    }

    public int getToY(){
        return end.getY() + modificate;
    }

    public void drawEdge(Graphics2D g, int radius){
        int ARROW_WIDTH = 8;
        Point d = getToPort(radius);
        Point f = getFromPort(radius);
        //exhange();
        int dx = f.x - d.x,
                dy = f.y - d.y;
        double D = Math.sqrt(dx*dx + dy*dy);
        double xm = D - ARROW_WIDTH,
                xn = xm,
                ym = ARROW_WIDTH,
                yn = -ARROW_WIDTH,

                x;
        double sin = dy/D,
                cos = dx/D;

        x = xm*cos - ym*sin + d.x ;
        ym = xm*sin + ym*cos + d.y;
        xm = x;

        x = xn*cos - yn*sin + d.x;
        yn = xn*sin + yn*cos + d.y;
        xn = x;


        g.setColor(Color.BLACK);
        g.drawLine(getFromX(), getFromY(),getToX(),getToY());
        d = getFromPort(radius);
        g.drawLine((int) xm, (int) ym, d.x, d.y);
        g.drawLine((int) xn, (int) yn, d.x, d.y);
        g.drawLine((int) xm, (int) ym,(int) xn, (int) yn);


        int xc = (getFromX() + getToX())/2;
        int yc = (getFromY() + getToY())/2;
        g.setColor(new Color(0xFFF2FF));
        g.fill(new Ellipse2D.Float(xc - 9, yc - 9, 18, 18));
        g.setColor(Color.BLACK);
        g.drawString((String.valueOf(getLength())), xc -5, yc +5);

        //exhange();


    }

    private Point getToPort(int radius) {
        double angle = getAngle();
        double trgAngle;

        if(getFromY() < getToY() ||
                (Math.abs(getFromY() - getToY()) < 0.0001 && getFromX() < getToX())) {
            trgAngle = angle ;
        } else {
            trgAngle = angle + 180;
        }
        double trgRadius = radius / 2.0;

        trgAngle = Math.toRadians(trgAngle);

        var trgPort = new Point(getToX() + (int) (trgRadius * Math.cos(trgAngle)),
                getToY() + (int) (trgRadius * Math.sin(trgAngle)));

        Point point = new Point();
        point = trgPort;

        return point;
    }
    private Point getFromPort(int radius) {
        double angle = getAngle();
        double Angle;

        if(getFromY() < getToY() ||
                (Math.abs(getFromY() - getToY()) < 0.0001 && getFromX() < getToX())) {
            Angle = angle;
        } else {
            Angle = angle + 180;
        }
        double trgRadius = radius / 2.0;

        Angle = Math.toRadians(Angle);

        var trgPort = new Point(getFromX() + (int) (trgRadius * Math.cos(Angle)),
                getFromY() + (int) (trgRadius * Math.sin(Angle)));

        Point point = new Point();
        point = trgPort;

        return point;
    }

    private double getAngle() {
        double angle = Math.toDegrees(Math.atan2(getFromY()- getToY(), getFromX() - getToX()));
        if(angle < 0){
            angle += 360;
        }
        if(angle > 180) {
            angle -= 180;
        }
        return angle;
    }
}
