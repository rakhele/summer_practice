package project.orange;

import java.awt.geom.Line2D;

public class LineDraw {
    private VertexDraw start;
    private VertexDraw end;
    private int length;

    public LineDraw(VertexDraw first, VertexDraw second, int len){
        length = len;
        start = first;
        end = second;
    }

    public int getLength(){
        return length;
    }

    public int getFromX(){
        return start.getX();
    }

    public int getFromY(){
        return start.getY();
    }

    public int getToX(){
        return end.getX();
    }

    public int getToY(){
        return end.getY();
    }
}
