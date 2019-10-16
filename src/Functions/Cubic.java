package Functions;

import Main.Function;
import javafx.scene.canvas.Canvas;

public class Cubic extends Function {
    private double a;
    private double b;
    private double c;
    private double d;
    private double x1;

    public Cubic(double a, double b, double c, double d, double x1) {

    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public double val(double x) {
        return a * Math.pow(x - x1, 3) + b * Math.pow(x - x1, 2) + c * (x - x1) + d;
    }

    @Override
    public boolean undefined(double x) {
        return false;
    }

    @Override
    public double getArea(double xStart, double xEnd) {
        double deltaX = 0.01, area = 0;
        for (double currentX = xStart; currentX <= xEnd; currentX += deltaX) {
            area += deltaX * val(currentX);
        }
        return area;
    }

    @Override
    public double getSlope(double x) {
        double deltaX = 0.01;
        return (val(x - deltaX) - val(x + deltaX)) / ((x - deltaX) - val(x + deltaX));
    }

    @Override
    public void draw(Canvas canvas) {
    }
}
