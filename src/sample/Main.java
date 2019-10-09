package sample;


import sample.arcDrawers.ArcDrawer;
import sample.arcDrawers.BrezArcDrawer;
import sample.arcDrawers.DDAArcDrawer;
import sample.arcDrawers.WuArcDrawer;
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
        private LineDrawer currentLineDrawer;
        private EllipsDrawer ed;
        private EllipsDrawer fed;
        private ArcDrawer arcDrawer;
        private PixelDrawer pixelDrawer;
        private boolean isMouseLineActive = true;
        private DrawMode drawMode = DrawMode.BREZ;

        public MyPanel() throws HeadlessException {
            super();
            this.addMouseMotionListener(this);
            this.addKeyListener(this);
        }

        @Override
        public void paint(Graphics g) {
            w = getWidth();
            h = getHeight();
            bufferedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            pixelDrawer = new ImageBufferPixelDrawer(bufferedImage);
            switch (drawMode) {
                case DDA:
                    ld = new DDALineDrawer(pixelDrawer);
                    ed = new DDAEllipsDrawer(pixelDrawer);
                    fed = new DDAFilledEllipsDrawer(pixelDrawer);
                    arcDrawer = new DDAArcDrawer(pixelDrawer);
                    break;
                case WU:
                    ld = new WuLineDrawer(pixelDrawer);
                    ed = new WuEllipsDrawer(pixelDrawer);
                    fed = new WuFilledEllipsDrawer(pixelDrawer);
                    arcDrawer = new WuArcDrawer(pixelDrawer);
                    break;
                case BREZ:
                    ld = new BrezLineDrawer(pixelDrawer);
                    ed = new BrezEllipsDrawer(pixelDrawer);
                    fed = new BrezFilledEllipsDrawer(pixelDrawer);
                    arcDrawer = new BrezArcDrawer(pixelDrawer);
                    break;
            }
            ld.drawLine(1, 1, 100, 100, Color.CYAN);
            arcDrawer.drawArc(100,100,1,90,50);
            ed.drawEllips(100,100,100,100,Color.cyan);
            fed.drawEllips(100,100,1001,100,Color.cyan);
            if (isMouseLineActive)
                ld.drawLine(400, 300, cx, cy, Color.CYAN);
            g.drawImage(bufferedImage, 0, 0, null);
            ++framesDrawed;
        }


        @Override
        public void mouseDragged(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseMoved(MouseEvent mouseEvent) {
            if (isMouseLineActive) {
                cx = mouseEvent.getX();
                cy = mouseEvent.getY();
                this.repaint();
            }
        }

        @Override
        public void keyTyped(KeyEvent keyEvent) {

        }

        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyChar() == 'd') {
                drawMode = DrawMode.DDA;
            } else if (keyEvent.getKeyChar() == 'b') {
                drawMode = DrawMode.BREZ;
            } else if (keyEvent.getKeyChar() == 'w') {
                drawMode = DrawMode.WU;
            } else if (keyEvent.getKeyChar() == 'm') {
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
