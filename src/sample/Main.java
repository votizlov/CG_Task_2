package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;

import static java.lang.Math.abs;

class TestFillRasterRate {
    static class MyFrame extends JFrame {
        final int OUTLINE = 1;
        long framesDrawed;
        int col = 0;

        int w, h;
        int[] raster;
        BufferedImage bufferedImage;


        public void draw(Graphics g) {
            // reinitialize all if resized
            if (w != getWidth() || h != getHeight()) {
                w = getWidth();
                h = getHeight();

                raster = new int[w * h];

                //cm = new DirectColorModel(24, 255, 255<<8, 255<<16);
                //cm = ColorModel.getRGBdefault();
                //buffer = new DataBufferInt(raster, raster.length);
                //sm = cm.createCompatibleSampleModel(w, h);
                //wrRaster = Raster.createWritableRaster(sm, buffer, null);
                //backBuffer = new BufferedImage(cm, wrRaster, false, null);
                bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                raster = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
            }

            // produce raster
            //for (int ptr = 0, x = 0; x < w; x++)
            //  for (int y = 0; y < h; y++)
            //    raster[ptr++] = 67854;
            drawBrezLine(100, 200, 400, 500);
            drawBrezLine(100, 200, 600, 300);
            drawBrezCircle(200, 400, 300);
            // draw raster
            g.drawImage(bufferedImage, 0, 0, null);
            ++framesDrawed;

        }

        private void drawBrezLine(int x0, int y0, int x1, int y1) {
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
                raster[w * y + i] = 100000;

                raster[w * y + x0] = 0;
                error = error + deltaerr;
                if (2 * error >= deltax) {
                    y = y + diry;
                    error = error - deltax;
                }
            }

            //for (double x = 0.0; x < R; x += DELTA) {// draw quarter of circle, then 2 lines; which depends on pie length + fill

            //y = Math.sqrt(Math.pow(R, 2) - Math.pow(x - X, 2)) + Y;

            //}
        }

        private void drawBrezCircle(int r, int x0, int y0) {
            int x = 0;
            int y = r;
            int delta = 1 - 2 * r;
            int error = 0;
            while (y >= 0) {
                //       drawpixel(X1 + x, Y1 + y);
                //       drawpixel(X1 + x, Y1 - y);
                //       drawpixel(X1 - x, Y1 + y);
                raster[w * (y+y0) + x+x0] = 100000;

                error = 2 * (delta + y) - 1;
                if ((delta < 0) && (error <= 0)) {
                    delta += 2 * ++x + 1;
                    continue;
                }
                if ((delta > 0) && (error > 0)) {
                    delta -= 2 * --y + 1;
                    continue;
                }
                delta += 2 * (++x - y--);
            }
        }
    }


    public static void main(String[] args) {
        final MyFrame frame = new MyFrame();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        //frame.setResizable(false);

        // draw FPS in title
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setTitle(Long.toString(frame.framesDrawed));
                frame.framesDrawed = 0;
            }
        }).start();

        /**/
        frame.createBufferStrategy(1);
        BufferStrategy bs = frame.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        for (; ; )
            frame.draw(g);
        /**/
    }
}
