package sample.ellipseDrawers;

import sample.pixelDrawers.PixelDrawer;

import java.awt.*;

public class BrezEllipsDrawer implements EllipsDrawer {
    private PixelDrawer pixelDrawer;

    public BrezEllipsDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawEllips(int x, int y, int a, int b, Color color) {
        int _x = 0; // Компонента x
        int _y = b; // Компонента y
        int a_sqr = a * a; // a^2, a - большая полуось
        int b_sqr = b * b; // b^2, b - малая полуось
        int delta = 4 * b_sqr * ((_x + 1) * (_x + 1)) + a_sqr * ((2 * _y - 1) * (2 * _y - 1)) - 4 * a_sqr * b_sqr; // Функция координат точки (x+1, y-1/2)
        while (a_sqr * (2 * _y - 1) > 2 * b_sqr * (_x + 1)) // Первая часть дуги
        {
            //pixel4(x, y, _x, _y, color_a);
            if (delta < 0) // Переход по горизонтали
            {
                _x++;
                delta += 4 * b_sqr * (2 * _x + 3);
            } else // Переход по диагонали
            {
                _x++;
                delta = delta - 8 * a_sqr * (_y - 1) + 4 * b_sqr * (2 * _x + 3);
                _y--;
            }
        }
        delta = b_sqr * ((2 * _x + 1) * (2 * _x + 1)) + 4 * a_sqr * ((_y + 1) * (_y + 1)) - 4 * a_sqr * b_sqr; // Функция координат точки (x+1/2, y-1)
        while (_y + 1 != 0) // Вторая часть дуги, если не выполняется условие первого цикла, значит выполняется a^2(2y - 1) <= 2b^2(x + 1)
        {
            //pixel4(x, y, _x, _y, color_b);
            if (delta < 0) // Переход по вертикали
            {
                _y--;
                delta += 4 * a_sqr * (2 * _y + 3);
            } else // Переход по диагонали
            {
                _y--;
                delta = delta - 8 * b_sqr * (_x + 1) + 4 * a_sqr * (2 * _y + 3);
                _x++;
            }
        }
    }


    @Override
    public void drawPie(int x, int y, int r, int angle) {

    }

    @Override
    public void setPixelDrawer(PixelDrawer pixelDrawer) {

    }
}
