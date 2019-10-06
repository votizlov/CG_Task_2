package sample;


import sample.ellipseDrawers.*;
import sample.lineDrawers.BrezLineDrawer;
import sample.lineDrawers.LineDrawer;
import sample.lineDrawers.WuLineDrawer;
import sample.pixelDrawers.ImageBufferPixelDrawer;
import sample.pixelDrawers.PixelDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;


class TestFillRasterRate {//todo make to different class

    static class MyFrame extends JFrame implements MouseMotionListener, KeyListener {
        //Graphics2D PixelDrawer LineDrawer EllipsDrawer BufferedImage = new BufferedImage(getw,geth,typergb)
        //b
        long framesDrawed;
        private int cx,cy = 0;//mouse coordinates

        int w, h;
        private BufferedImage bufferedImage;
        private LineDrawer ld;
        private EllipsDrawer ed;
        private EllipsDrawer fed;
        private PixelDrawer pixelDrawer;

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
                pixelDrawer = new ImageBufferPixelDrawer(bufferedImage);

                ld = new BrezLineDrawer(pixelDrawer);
                ed = new BrezEllipsDrawer(pixelDrawer);
            }

            //g.clearRect(0,0,getWidth(),getHeight());
            // produce raster
            //fillShapes();
            // draw raster
            ld.drawLine(1,1,cx,cy   , Color.CYAN);
            g.drawImage(bufferedImage, 0, 0, null);
            ++framesDrawed;
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
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            if(keyEvent.getKeyChar()=='d'){
                //ld = new DDALineDrawer();
            } else if(keyEvent.getKeyChar()=='b'){
                ld = new BrezLineDrawer(pixelDrawer);//фабрика типа
                ed = new BrezEllipsDrawer(pixelDrawer);
                fed = new BrezFilledEllipsDrawer();
            } else if(keyEvent.getKeyChar()=='w'){
                ld = new WuLineDrawer(pixelDrawer);
                ed = new WuEllipsDrawer(pixelDrawer);
                fed = new WuFilledEllipsDrawer();
            }
            this.repaint();
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
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        Graphics2D graphics2D = (Graphics2D) bs.getDrawGraphics();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        for (; ; )
            frame.draw(g);
        /**/
    }
}
