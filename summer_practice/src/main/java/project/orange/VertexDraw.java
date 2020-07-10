package project.orange;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class VertexDraw {
    private char name;
    private int x;
    private int y;
    private Color c;

    public VertexDraw(char name){
        this.name = name;

    }

    public VertexDraw(char name, int x, int y){
        this.name = name;
        setCoords(x, y);
        c = new Color(0xFFFF1A);
    }

    public void setColor(Color d){c = d;}

    public void setCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String getName(){
        String s=String.valueOf(name);
        return s;
    }

    public int getX(){
        return x ;
    }

    public int getY(){
        return y;
    }

    public void draw(Graphics2D g2, int radius){
        Ellipse2D ell;
            ell = new Ellipse2D.Float(x - radius/2, y - radius/2, radius, radius);
            g2.setColor(c);
            g2.fill(ell);
            g2.setColor(Color.BLACK);

            g2.drawString(getName(), x - 2, y + 5);


    }

}
