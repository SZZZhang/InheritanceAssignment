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
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.x1 = x1;
    }

    @Override
    public String toString() {
        String equation = "";

        if (a != 0) {
            if (a == -1) equation += "-";
            else if (a != 1) equation += Double.toString(a);

            if(x1 == 0) equation += "x^3";
            else if(x1 < 0) equation += "(x + " + Double.toString(Math.abs(x1)) + ")^3";
            else if(x1 > 0) equation += "(x - " + Double.toString(x1) + ")^3";
        }

        if (b != 0) {
            if(a != 0){
                if (b > 0) equation += " + ";
                else if (b < 0) equation += " - ";
            }

            if (b != 1) equation += Double.toString(Math.abs(b));

            if(x1 == 0) equation += "x^2";
            else if(x1 < 0) equation += "(x + " + Double.toString(Math.abs(x1)) + ")^2";
            else if(x1 > 0) equation += "(x - " + Double.toString(x1) + ")^2";
        }

        if (c != 0) {
            if (a != 0 || b != 0){
                if (c > 0) equation += " + ";
                else if (c < 0) equation += " - ";
            }

            if (c != 1) equation += Double.toString(Math.abs(c));

            if(x1 == 0) equation += "x";
            else if(x1 < 0) equation += "(x + " + Double.toString(Math.abs(x1)) + ")";
            else if(x1 > 0) equation += "(x - " + Double.toString(x1) + ")";
        }

        if (d != 0) {
            if(a != 0 || b != 0 || c != 0) {
                if(d > 0) equation += " + ";
                if(d < 0) equation += " - ";
            }
            equation += Double.toString(Math.abs(d));
        }

        return equation;
    }

    @Override
    public double val(double x) {
        return a * Math.pow(x - x1, 3) + b * Math.pow(x - x1, 2) + c * (x - x1) + d;
    }

    @Override
    public boolean undefined(double x) {
        if (x < getStartDomain() || x > getEndDomain()) {
            return true;
        }
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
