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

class TestFillRasterRate
{
    static class MyFrame extends JFrame
    {
        long framesDrawed;
        int col=0;

        int w, h;
        int[] raster;
        int[] testRaster;
        ColorModel cm;
        DataBuffer buffer;
        SampleModel sm;
        WritableRaster wrRaster;
        BufferedImage backBuffer;
        BufferedImage bufferedImage;


        public void draw(Graphics g)
        {
            // reinitialize all if resized
            if( w!=getWidth() || h!=getHeight() )
            {
                w = getWidth();
                h = getHeight();

                raster = new int[w*h];

                cm = new DirectColorModel(24, 255, 255<<8, 255<<16);
                buffer = new DataBufferInt(raster, raster.length);
                sm = cm.createCompatibleSampleModel(w,h);
                wrRaster = Raster.createWritableRaster(sm, buffer, null);
                backBuffer = new BufferedImage(cm, wrRaster, false, null);
                bufferedImage = new BufferedImage(800,600,BufferedImage.TYPE_INT_RGB);
                testRaster = ((DataBufferInt)bufferedImage.getRaster().getDataBuffer()).getData();
            }

            // produce raster
            for(int ptr=0, x=0; x<w; x++)
                for(int y=0; y<h; y++)
                    testRaster[ptr++] = 100000;

            // draw raster
            g.drawImage(bufferedImage,  0,0, null);
            ++framesDrawed;

        }
    }

    public static void main(String[] args)
    {
        final MyFrame frame = new MyFrame();

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // draw FPS in title
        new Timer(1000, new ActionListener()
        {   @Override public void actionPerformed(ActionEvent e)
        {   frame.setTitle(Long.toString(frame.framesDrawed));
            frame.framesDrawed = 0;
        }
        }).start();

        /**/
        frame.createBufferStrategy(1);
        BufferStrategy bs = frame.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        for(;;)
            frame.draw(g);
        /**/
    }
}
