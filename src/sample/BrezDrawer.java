package sample;

import java.awt.*;

public class BrezDrawer implements PixelDrawer {
    Graphics g;
    BrezDrawer(Graphics g){
        this.g = g;
    }
    public void drawBrezLine(int x1,int y1,int x2,int y2){
        int x = x1;
        int y = y1;
        int dX = x2-x1;
        int dY = y2-y1;
        int e = 2*dY - dX;
        for(int i=0;i<dX;i++){
            //drawPixel()
            if(e>=0){
                y++;
                e+=2*(dY-dX);
            } else {
                e+=2*dY;
            }
            x++;
        }
    }
    public void draBrezCircle(int x,int y,int angle1,int angle2){

    }
    public void drawBrezEllips(){

    }

    @Override
    public void drawPixel(int x, int y, Color c) {

    }
}
