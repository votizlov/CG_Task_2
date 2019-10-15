package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

import static java.lang.Math.abs;

public class DDALineDrawer implements LineDrawer {
    PixelDrawer pd;

    public void drawLine(int x1, int y1, int x2, int y2) {

    }

    public DDALineDrawer(PixelDrawer pd){
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        // calculate dx & dy
        int dx = x2 - x1;
        int dy = y2 - y1;

        // calculate steps required for generating pixels
        int steps = abs(dx) > abs(dy) ? abs(dx) : abs(dy);

        // calculate increment in x & y for each steps
        float Xinc = dx / (float) steps;
        float Yinc = dy / (float) steps;

        // Put pixel for each step
        float X = x1;
        float Y = y1;
        for (int i = 0; i <= steps; i++)
        {
            pd.drawPixel((int) X,(int)Y,color);  // put pixel at (X,Y)
            X += Xinc;           // increment in x at each step
            Y += Yinc;           // increment in y at each step
            // generation step by step
        }
    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {
        this.pd = pixelDrawer;
    }
}
