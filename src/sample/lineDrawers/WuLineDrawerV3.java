package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

public class WuLineDrawerV3 implements LineDrawer {
    private PixelDrawer pixelDrawer;

    public WuLineDrawerV3(PixelDrawer pixelDrawer){
        this.pixelDrawer = pixelDrawer;
    }
    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        DDALineDrawer lineDrawer = new DDALineDrawer(pixelDrawer);
        lineDrawer.drawLine(x1,  y1, x2,  y2, color);
        int d = 1;
        Color color1 = new Color(Color.HSBtoRGB(0, 1, 0.3f));
        //lineDrawer.drawLine(x1-d,  y1+d, x2-d,  y2+d, color1);
        lineDrawer.drawLine(x1,  y1-d, x2,  y2-d, color1);
        lineDrawer.drawLine(x1, y1+d, x2, y2+d, color1);
        //lineDrawer.drawLine(x1-d,  y1-d, x2-d,  y2-d, color1);
    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
