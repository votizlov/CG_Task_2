package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

import static java.lang.Math.abs;

public class BrezLineDrawer implements LineDrawer {//todo make paintable in all directions
    private PixelDrawer pd;

    public BrezLineDrawer(PixelDrawer pixelDrawer){
        this.pd = pixelDrawer;
    }
    public void drawBrezLine(int x0,int y0,int x1,int y1, Color color){

        int deltax = abs(x1 - x0);
        int deltay = abs(y1 - y0);
        int error = 0;
        int deltaerr = deltay;
        int y = y0;
        int diry = y1 - y0;
        if (diry > 0) {
            diry = 1;
        }
        if (diry < 0) {
            diry = -1;
        }
        for (int i = x0; i < x1; i++) {
            pd.drawPixel(i,y,color);
            //raster[w * y + i] = SHAPES_COLOR;
            error = error + deltaerr;
            if (2 * error >= deltax) {
                y = y + diry;
                error = error - deltax;
            }
        }
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        int x = x1;
        int y = y1;
        int dX = x2-x1;
        int dY = y2-y1;
        int e = 2*dY - dX;
        for(int i=0;i<dX;i++){
            pd.drawPixel(x,y,color);
            if(e>=0){
                y++;
                e+=2*(dY-dX);
            } else {
                e+=2*dY;
            }
            x++;
        }
    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
