package sample.lineDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

public class WuLineDrawerV2 implements LineDrawer {
    private PixelDrawer pixelDrawer;

    public WuLineDrawerV2(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        if (x2 < x1) {
            x1 += x2;
            x2 = x1 - x2;
            x1 -= x2;
            y1 += y2;
            y2 = y1 - y2;
            y1 -= y2;
        }
        int dx = x2 - x1;
        int dy = y2 - y1;
        DDALineDrawer lineDrawer = new DDALineDrawer(pixelDrawer);
        //Горизонтальные и вертикальные линии не нуждаются в сглаживании
        if (dx == 0 || dy == 0) {
            lineDrawer.drawLine(x1,y1,x2,y2,color);
            return;
        }
        float gradient = 0;
        if (dx > dy) {
            gradient = (float) dy / dx;
            float intery = y1 + gradient;
            lineDrawer.drawLine(x1,y1,x2,y2,color);
            for (int x = x1; x < x2; ++x) {
                Color color1 = new Color(Color.HSBtoRGB(0, 1, (float) (255 - fractionalPart(intery) * 255))); //Меняем прозрачность
                pixelDrawer.drawPixel(x, (int) intery, color1);
                color1 = new Color(Color.HSBtoRGB(0, 1, (float) (fractionalPart(intery) * 255)));
                pixelDrawer.drawPixel(x, (int) intery + 1,color1);
                intery += gradient;
            }
            pixelDrawer.drawPixel(x2, y2, Color.BLACK);
        } else {
            gradient = (float) dx / dy;
            float interx = x1 + gradient;
            pixelDrawer.drawPixel(x1, y1, Color.black);
            for (int y = y1; y < y2; ++y) {
                Color color1 = new Color(Color.HSBtoRGB(0, 1, (float) (255 - fractionalPart(interx) * 255)));
                pixelDrawer.drawPixel((int) interx, y, color1);
                color1 = new Color(Color.HSBtoRGB(0, 1, (float) (fractionalPart(interx) * 255)));
                pixelDrawer.drawPixel((int) interx + 1, y, color1);
                interx += gradient;
            }
            pixelDrawer.drawPixel(x2,y2,Color.black);
        }
    }

    private float fractionalPart(float x) {
        int tmp = (int) x;
        return x - tmp; //вернёт дробную часть числа
    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }
}
