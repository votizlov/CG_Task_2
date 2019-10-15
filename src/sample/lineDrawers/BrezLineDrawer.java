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
        int dirx = x1 - x0;
        if (diry > 0) {
            diry = 1;
        }
        if (diry < 0) {
            diry = -1;
        }
        if(dirx>0){
            dirx=1;
        }
        if(dirx<0){
            dirx = -1;
        }
        int actualX =x0;
        for (int i = x0; i < abs(x1); i++) {
            actualX+=dirx;
            pd.drawPixel(actualX,y,color);
            error = error + deltaerr;
            if (2 * error >= deltax) {
                y = y + diry;
                error = error - deltax;
            }
        }
    }

    private int sign(int x) {
        return Integer.compare(x, 0);
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }

    @Override
    public void drawLine(int xstart, int ystart, int xend, int yend, Color c) {
        int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

        dx = xend - xstart;
        dy = yend - ystart;

        incx = sign(dx);
        incy = sign(dy);

        dx = Math.abs(dx);
        dy = Math.abs(dy);
        if (dx > dy) //определяем наклон отрезка:
        {
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
        {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;//тогда в цикле будем двигаться по y
        }

        x = xstart;
        y = ystart;
        err = el / 2;
        pd.drawPixel(x, y, c);//ставим первую точку

        for (int t = 0; t < el; t++)//идём по всем точкам
        {
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;//сместить вверх или вниз, если по х
                y += incy;//сместить влево-вправо, если по y
            } else {
                x += pdx;
                y += pdy;
            }

            pd.drawPixel(x, y, c);
        }
    }

    private void wikiBrezLine(int x1,int y1,int x2,int y2,Color color){
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
