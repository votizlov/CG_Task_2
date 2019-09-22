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
import java.awt.image.*;
import java.util.HashMap;
import java.util.Hashtable;

public class Main {
    private final double R = 200.0;
    private final double DELTA = 0.05;
    private final double X = 250.0;
    private final double Y = 100.0;
    private final static int WIDTH = 400;
    private final static int HEIGHT = 400;
    public static JFrame frame;
    BufferedImage img;
    int[] raster;
    ColorModel cm;
    DataBuffer buffer;
    SampleModel sm;
    WritableRaster wrRaster;
    BufferedImage backBuffer;



    public void start(Graphics g) {
        raster = new int[WIDTH*HEIGHT];

        cm = new DirectColorModel(24, 255, 255<<8, 255<<16);
        buffer = new DataBufferInt(raster, raster.length);
        sm = cm.createCompatibleSampleModel(WIDTH,HEIGHT);
        wrRaster = Raster.createWritableRaster(sm, buffer, null);
        backBuffer = new BufferedImage(cm, wrRaster, false, null);
        //code differential
        img = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
        int[] pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
        boolean running=true;
        while(running){
            int col = 0;
            for(int i = 0, ptr = 0; i<WIDTH;i++){
                for(int j=0;j<HEIGHT;j++){
                    pixels[ptr++] = col++;
                }
            }
            g.drawImage(img, 0, 0, null);
            g.dispose();
        }
    }

    public void calcPoints(){
        for (double x = 0.0; x < R; x += DELTA) {// draw quarter of circle, then 2 lines; which depends on pie length + fill

            Path path = new Path();
            //y = Math.sqrt(Math.pow(R, 2) - Math.pow(x - X, 2)) + Y;

            MoveTo moveTo = new MoveTo();
            //moveTo.setX(prevX);
            //moveTo.setY(prevY);
            path.getElements().add(moveTo);

            LineTo lineTo = new LineTo();
            lineTo.setX(x);
            //lineTo.setY(y);
            path.getElements().add(lineTo);

            //prevX = x;
            //prevY = y;

            path.setStrokeWidth(2);
            path.setStroke(Color.BLACK);

            //root.getChildren().add(path);
        }
    }

    public Image getImage() {
        return img;
    }

    public static void main(String[] args) {
        Main t=new Main();

        frame = new JFrame("WINDOW");

        frame.setSize(WIDTH,HEIGHT);
        frame.setVisible(true);
        frame.createBufferStrategy(1);
        BufferStrategy bs = frame.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();

        t.start(g);
        frame.add(new JLabel(new ImageIcon(t.getImage())));


        // Better to DISPOSE than EXIT
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
