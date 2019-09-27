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
import java.util.HashMap;
import java.util.Hashtable;

class TestFillRasterRate {
    static class MyFrame extends JFrame {
        long framesDrawed;
        int col = 0;

        int w, h;
        int[] raster;
        ColorModel cm;
        DataBuffer buffer;
        SampleModel sm;
        WritableRaster wrRaster;
        BufferedImage backBuffer;

        //@Override public void paint(Graphics g)
        public void draw(Graphics g) {
            // reinitialize all if resized
            if (w != getWidth() || h != getHeight()) {
                w = getWidth();
                h = getHeight();

                raster = new int[w * h];

                //cm = new DirectColorModel(24, 255, 255<<8, 255<<16);
                cm = ColorModel.getRGBdefault();
                buffer = new DataBufferInt(raster, raster.length);
                sm = cm.createCompatibleSampleModel(w, h);
                wrRaster = Raster.createWritableRaster(sm, buffer, null);
                backBuffer = new BufferedImage(cm, wrRaster, false, null);
                BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            }

            // produce raster
            for (int ptr = 0, x = 0; x < w; x++)
                for (int y = 0; y < h; y++)
                    raster[ptr++] = col++;

            // draw raster
            g.drawImage(backBuffer, 0, 0, null);
            ++framesDrawed;


        }

        private void drawFigure(int[] raster) {
            double R = 20;
            int DELTA = 1;
            for (double x = 0.0; x < R; x += DELTA) {// draw quarter of circle, then 2 lines; which depends on pie length + fill

                //y = Math.sqrt(Math.pow(R, 2) - Math.pow(x - X, 2)) + Y;

            }
        }
    }


    public static void main(String[] args) {
        final MyFrame frame = new MyFrame();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

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
