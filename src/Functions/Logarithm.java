package Functions;

import Main.Function;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Logarithm extends Function {
    private double a;
    private double b;
    private double x1;

    public Logarithm(double a, double b, double x1) {
        this.a = a;
        this.b = b;
        this.x1 = x1;
    }

    @Override
    public double val(double x) {
        return 0;
    }

    @Override
    public boolean undefined(double x) {
        return false;
    }

    @Override
    public double getArea(double xStart, double xEnd) {
        return 0;
    }

    @Override
    public double getSlope(double x) {
        return 0;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public String toString() {
        return null;
    }
}
