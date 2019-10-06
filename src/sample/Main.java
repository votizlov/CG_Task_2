package sample;


import sample.ellipseDrawers.*;
import sample.lineDrawers.BrezLineDrawer;
import sample.lineDrawers.DDALineDrawer;
import sample.lineDrawers.LineDrawer;
import sample.lineDrawers.WuLineDrawer;
import sample.pixelDrawers.ImageBufferPixelDrawer;
import sample.pixelDrawers.PixelDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;


class TestFillRasterRate {//todo make to different class

    static class MyPanel extends JPanel implements MouseMotionListener, KeyListener {
        //Graphics2D PixelDrawer LineDrawer EllipsDrawer BufferedImage = new BufferedImage(getw,geth,typergb)
        //b
        long framesDrawed;
        private int cx, cy = 0;//mouse coordinates

        int w, h;
        private BufferedImage bufferedImage;
        private LineDrawer ld;
        private EllipsDrawer ed;
        private EllipsDrawer fed;
        private PixelDrawer pixelDrawer;
        private boolean isMouseLineActive = true;

        public MyPanel() throws HeadlessException {
            super();
            this.addMouseMotionListener(this);
            this.addKeyListener(this);
        }


        public void draw() {
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
            ld.drawLine(1, 1, 100, 100, Color.CYAN);
            this.getGraphics().drawImage(bufferedImage, 0, 0, null);
            ++framesDrawed;
        }

        @Override
        public void paint(Graphics g){
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

            bufferedImage.flush();
            // produce raster
            //fillShapes();
            // draw raster
            ld.drawLine(1, 1, 100, 100, Color.CYAN);
            g.drawImage(bufferedImage, 0, 0, null);
            ++framesDrawed;
        }


        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            if(isMouseLineActive) {
                cx = mouseEvent.getX();
                cy = mouseEvent.getY();
                ld.drawLine(400, 300, cx, cy, Color.CYAN);
                //getGraphics().drawImage(bufferedImage,0,0,null);
                repaint();
                //this.getGraphics().drawImage(bufferedImage, 0, 0, null);
            }
        }

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            System.out.print("b");
            if (keyEvent.getKeyChar() == 'd') {
                ld = new DDALineDrawer(pixelDrawer);
            } else if (keyEvent.getKeyChar() == 'b') {
                ld = new BrezLineDrawer(pixelDrawer);//фабрика типа
                ed = new BrezEllipsDrawer(pixelDrawer);
                fed = new BrezFilledEllipsDrawer();
            } else if (keyEvent.getKeyChar() == 'w') {
                ld = new WuLineDrawer(pixelDrawer);
                ed = new WuEllipsDrawer(pixelDrawer);
                fed = new WuFilledEllipsDrawer();
            } else if (keyEvent.getKeyChar() =='m'){
                isMouseLineActive = !isMouseLineActive;
            }
            this.repaint();
        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }


    public static void main(String[] args) {
        final MyPanel myPanel = new MyPanel();
        final JFrame frame = new JFrame();

        myPanel.setSize(800, 600);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(myPanel);
        frame.setVisible(true);
        myPanel.setVisible(true);

        // draw FPS in title
        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setTitle(Long.toString(myPanel.framesDrawed));
                myPanel.framesDrawed = 0;
            }
        }).start();


        //frame.createBufferStrategy(2);
        myPanel.grabFocus();
        myPanel.paint(myPanel.getGraphics());
    }
}
