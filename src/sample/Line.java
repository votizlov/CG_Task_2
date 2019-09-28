package sample;

public class Line {
    public Line(int x1, int x2, int y1, int y2, LineDrawer ld) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.ld = ld;
    }

    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private LineDrawer ld;
    public void draw(){
        ld.drawLine(x1,y1,x2,y2);//todo add color
    }
}
