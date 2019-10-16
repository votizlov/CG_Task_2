package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

import static java.lang.Math.abs;

public class WuLineDrawerMy implements LineDrawer {
    private PixelDrawer pixelDrawer;
    public WuLineDrawerMy(PixelDrawer pixelDrawer){
        this.pixelDrawer = pixelDrawer;
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
        pixelDrawer.drawPixel(x, y, c);//ставим первую точку
        pixelDrawer.drawPixel(x, y+1, c);

        double k = 0;

        double dk = el/es;//dx/dy*0.01;
        System.out.println(dk);
        for (int t = 0; t < el; t++)//идём по всем точкам
        {
            err -= es;
            if (err < 0) {
                k=dk;
                err += el;
                x += incx;//сместить вверх или вниз, если по х
                y += incy;//сместить влево-вправо, если по y
            } else {
                x += pdx;
                y += pdy;
            }
            if(k+dk<1)
                k+=dk;
            Color firstPixel = new Color((int) (c.getRed()*k),(int)(c.getGreen()*k),(int)(c.getBlue()*k));
            Color secondPixel = new Color((int) (c.getRed()*(1-k)),(int) (c.getGreen()*(1-k)),(int) (c.getBlue()*(1-k)));
            pixelDrawer.drawPixel(x, y, secondPixel);
            if(err>el/2)
                pixelDrawer.drawPixel(x,y+1,firstPixel);
            else
                pixelDrawer.drawPixel(x,y-1,firstPixel);
        }
    }
    private int sign(int x) {
        return Integer.compare(x, 0);
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
