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

    public String toString() {
        String equation = "";
        if (a != 0) {
            if (a == -1) equation += "-";
            else if (a != 1) equation += a;

            if (x1 == 0) equation += "ln(x)";
            else if (x1 > 0) equation += "ln(x - " + x1 + ")";
            else equation += "ln(x +" + Math.abs(x1) + ")";
        }

        if(b > 0) equation += " + " + b;
        else if(b < 0) equation += " - " + Math.abs(b);

        return equation;
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


}
