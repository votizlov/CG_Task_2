package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

import static java.lang.Math.abs;

public class WuLineDrawerV4 implements LineDrawer {
    private PixelDrawer pixelDrawer;
    public WuLineDrawerV4(PixelDrawer pixelDrawer){
        this.pixelDrawer = pixelDrawer;
    }
        // swaps two numbers
    void swap(int a , int b)
    {
        int temp = a;
    a = b;
    b = temp;
    }

    // returns absolute value of number
    float absolute(float x )
    {
        if (x < 0) return -x;
        else return x;
    }

    //returns integer part of a floating point number
    int iPartOfNumber(float x)
    {
        return (int)x;
    }

    //rounds off a number
    int roundNumber(float x)
    {
        return iPartOfNumber((float) (x + 0.5)) ;
    }

    //returns fractional part of a number
    float fPartOfNumber(float x)
    {
        if (x>0) return x - iPartOfNumber(x);
        else return x - (iPartOfNumber(x)+1);

    }

    //returns 1 - fractional part of number
    float rfPartOfNumber(float x)
    {
        return 1 - fPartOfNumber(x);
    }

    // draws a pixel on screen of given brightness
// 0<=brightness<=1. We can use your own library
// to draw on screen
    void drawPixel( int x , int y , float brightness)
    {
        pixelDrawer.drawPixel(x,y,new Color(Color.HSBtoRGB(0,1,brightness)));
        //SDL_SetRenderDrawColor(pRenderer, c, c, c, 255);
        //SDL_RenderDrawPoint(pRenderer, x, y);
    }
    @Override
    public void drawLine(int x1 , int y1 , int x2 , int y2,Color color)
    {
        boolean steep = abs(y2 - y1) > abs(x2 - x1) ;

        // swap the co-ordinates if slope > 1 or we
        // draw backwards
        if (steep)
        {
            swap(x1 , y1);
            swap(x2 , y2);
        }
        if (x1 > x2)
        {
            swap(x1 ,x2);
            swap(y1 ,y2);
        }

        //compute the slope
        float dx = x2-x1;
        float dy = y2-y1;
        float gradient = dy/dx;
        if (dx == 0.0)
            gradient = 1;

        int xpxl1 = x1;
        int xpxl2 = x2;
        float intersectY = y1;

        // main loop
        if (steep)
        {
            int x;
            for (x = xpxl1 ; x <=xpxl2 ; x++)
            {
                // pixel coverage is determined by fractional
                // part of y co-ordinate
                drawPixel(iPartOfNumber(intersectY), x,
                        rfPartOfNumber(intersectY));
                drawPixel(iPartOfNumber(intersectY)-1, x,
                        fPartOfNumber(intersectY));
                intersectY += gradient;
            }
        }
        else
        {
            int x;
            for (x = xpxl1 ; x <=xpxl2 ; x++)
            {
                // pixel coverage is determined by fractional
                // part of y co-ordinate
                drawPixel(x, iPartOfNumber(intersectY),
                        rfPartOfNumber(intersectY));
                drawPixel(x, iPartOfNumber(intersectY)-1,
                        fPartOfNumber(intersectY));
                intersectY += gradient;
            }
        }

    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
