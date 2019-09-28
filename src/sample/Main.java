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
import java.awt.event.*;
import java.awt.image.*;

import static java.lang.Math.abs;

class TestFillRasterRate {//todo make to different class

    static class MyFrame extends JFrame implements MouseMotionListener, KeyListener {
        //Graphics2D PixelDrawer LineDrawer EllipsDrawer BufferedImage = new BufferedImage(getw,geth,typergb)
        //b
        final int OUTLINE = 1;
        final int SHAPES_COLOR = 2281337;
        long framesDrawed;
        int col = 0;
        private int cx,cy = 0;//mouse coordinates

        int w, h;
        int[] raster;
        BufferedImage bufferedImage;

        public MyFrame() throws HeadlessException {
            super();
            this.addMouseMotionListener(this);
            this.addKeyListener(this);
        }


        public void draw(Graphics g) {
            //передаём актуальгый pixelDrawer в костыле if(lineDrawer!=null)
            // reinitialize all if resized
            if (w != getWidth() || h != getHeight()) {
                w = getWidth();
                h = getHeight();

                bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                PixelDrawer pixelDrawer = new ImageBufferPixelDrawer(bufferedImage);

                raster = new int[w * h];

                //cm = new DirectColorModel(24, 255, 255<<8, 255<<16);
                //cm = ColorModel.getRGBdefault();
                //buffer = new DataBufferInt(raster, raster.length);
                //sm = cm.createCompatibleSampleModel(w, h);
                //wrRaster = Raster.createWritableRaster(sm, buffer, null);
                //backBuffer = new BufferedImage(cm, wrRaster, false, null);
                raster = ((DataBufferInt) bufferedImage.getRaster().getDataBuffer()).getData();
            }

            // produce raster
            drawBrezLine(100, 200,cx, cy);
            drawBrezLine(100, 200, 601, 300);
            drawBrezCircle(200, 400, 300);
            //fillShapes();
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
                raster[w * y + i] = SHAPES_COLOR;
                error = error + deltaerr;
                if (2 * error >= deltax) {
                    y = y + diry;
                    error = error - deltax;
                }
            }
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
                raster[w * (y + y0) + x + x0] = SHAPES_COLOR;

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

        private void drawVooLine() {

        }

        private void drawVooCircle() {

        }

        private void fillShapes() {
            boolean isInsideShape = false;
            for (int i = 0; i < raster.length - 1; i++) {
                if (raster[i] != 0 && !isInsideShape && !isConsecutivePainted(i)) {
                    isInsideShape = true;
                } else if (raster[i] != 0 && isInsideShape || isConsecutivePainted(i)) {
                    isInsideShape = false;
                }
                if (!isInsideShape) {
                    raster[i] = SHAPES_COLOR;
                }
            }
        }

        private void fillShape(int x, int y) {
            if (raster[y * w + x] != SHAPES_COLOR && raster[y * w + x] != 1) {
                raster[y * w + x] = SHAPES_COLOR;
                //fillShape(x-1,y-1);
                fillShape(x - 1, y);
                //fillShape(x+1,y+1);
                fillShape(x, y - 1);
            } else {
                raster[y * w + x] = 1;
            }
        }

        private boolean isConsecutivePainted(int i) {
            return raster[i + 1] != 0;
        }

        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            cx=mouseEvent.getX();
            cy=mouseEvent.getY();
            this.repaint();
        }
        private LineDrawer ld;
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            if(keyEvent.getKeyChar()=='d'){
                //ld = new DDALineDrawer();
            } else if(keyEvent.getKeyChar()=='b'){
                ld = new BrezDrawer();//фабрика типа
            } else {

            }
        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

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
