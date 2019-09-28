package sample;

public class BrezLineDrawer implements LineDrawer {//todo make paintable in all directions
    public void drawBrezLine(int x1,int y1,int x2,int y2){
        int x = x1;
        int y = y1;
        int dX = x2-x1;
        int dY = y2-y1;
        int e = 2*dY - dX;
        for(int i=0;i<dX;i++){
            //drawPixel()
            if(e>=0){
                y++;
                e+=2*(dY-dX);
            } else {
                e+=2*dY;
            }
            x++;
        }
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {

    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
