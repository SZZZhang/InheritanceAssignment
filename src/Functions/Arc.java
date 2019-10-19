package Functions;

import Main.Function;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Arc extends Function {
    private double r;
    private double xCenter;
    private double yCenter;

    public Arc(double r, double xCenter, double yCenter) {
        this.r = r;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }

    @Override
    public String toString() {
        String equation = "sqrt(" + r + "^2 - ";
        if(xCenter > 0) equation += "(x - " + xCenter + ")^2)";
        else if(xCenter < 0) equation += "(x + " + Math.abs(xCenter) + ")^2)";
        else equation += "x^2)";

        if(yCenter > 0) equation += " + " + yCenter;
        else if(yCenter < 0) equation += " - " + Math.abs(yCenter);

        return equation;
    }

    @Override
    public double val(double x) {
        return Math.sqrt(Math.pow(this.r, 2) - Math.pow((x - this.xCenter), 2)) + this.yCenter;
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
