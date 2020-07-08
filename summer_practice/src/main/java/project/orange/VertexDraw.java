package project.orange;

public class VertexDraw {
    private char name;
    private int x;
    private int y;

    public VertexDraw(char name){
        this.name = name;

    }

    public VertexDraw(char name, int x, int y){
        this.name = name;
        setCoords(x, y);

    }
    public void setCoords(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String getName(){
        String s=String.valueOf(name);
        return s;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

}
