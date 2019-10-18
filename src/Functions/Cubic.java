package Functions;

import Main.Function;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

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

            if (x1 == 0) equation += "x^3";
            else if (x1 < 0) equation += "(x + " + Double.toString(Math.abs(x1)) + ")^3";
            else if (x1 > 0) equation += "(x - " + Double.toString(x1) + ")^3";
        }

        if (b != 0) {
            if (a != 0) {
                if (b > 0) equation += " + ";
                else if (b < 0) equation += " - ";
            }

            if (b != 1) equation += Double.toString(Math.abs(b));

            if (x1 == 0) equation += "x^2";
            else if (x1 < 0) equation += "(x + " + Double.toString(Math.abs(x1)) + ")^2";
            else if (x1 > 0) equation += "(x - " + Double.toString(x1) + ")^2";
        }

        if (c != 0) {
            if (a != 0 || b != 0) {
                if (c > 0) equation += " + ";
                else if (c < 0) equation += " - ";
            }

            if (c != 1) equation += Double.toString(Math.abs(c));

            if (x1 == 0) equation += "x";
            else if (x1 < 0) equation += "(x + " + Double.toString(Math.abs(x1)) + ")";
            else if (x1 > 0) equation += "(x - " + Double.toString(x1) + ")";
        }

        if (d != 0) {
            if (a != 0 || b != 0 || c != 0) {
                if (d > 0) equation += " + ";
                if (d < 0) equation += " - ";
            }
            equation += Double.toString(Math.abs(d));
        }

        return equation;
    }

    @Override
    public double val(double x) {
        if (undefined(x)) return Double.NaN; // returns NaN if undefined
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
        double deltaX = 1;
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        //finds min max of function
        Pair<Double, Double> minMax = findMinMax();
        double min = minMax.getKey();
        double max = minMax.getValue();

        double hRatio = canvas.getWidth() / (getEndDomain() - getStartDomain());
        double vRatio = canvas.getHeight() / (max - min);
        double prevX = 0, prevY = val(getStartDomain()) * vRatio * -1 + max * vRatio;
        for (double x = getStartDomain() + deltaX, screenX = 0; x <= getEndDomain(); x++, screenX += hRatio) {
            double currentY = val(x) * vRatio * -1 + max * vRatio;
            gc.strokeLine(prevX, prevY, screenX, currentY);
            prevX = screenX;
            prevY = currentY;
        }

        double xScaleYCoor = 0; //the y coordinate of the numbers on the x scale
        //x axis is between the min and max points
        if (0 > min && 0 < max) xScaleYCoor = max * vRatio;
            //function is above x axis, x scale will display at the bottom of the window
        else if (min > 0) xScaleYCoor = canvas.getHeight();
            //function is below x axis, x scale will display at the top of the window
        else if (max < 0) xScaleYCoor = 0;

        double xScaleXIncrememt = (canvas.getWidth() / (NUMS_ON_SCALE + 1));
        double xScaleXValueIncrement = ((getEndDomain() - getStartDomain())/ (NUMS_ON_SCALE + 1)) * hRatio;

        for (double x = xScaleXValueIncrement, screenX = xScaleXIncrememt;
             screenX <= canvas.getWidth() - xScaleXIncrememt; x += xScaleXValueIncrement, screenX += xScaleXIncrememt) {
            gc.fillText("| " + Double.toString(Math.round(screenX)), x, xScaleYCoor);
        }

    }

    //returns the minimum and maximum point in a function within the domain
    protected Pair<Double, Double> findMinMax() {
        double deltaX = 0.01, max = 0, min = Double.POSITIVE_INFINITY;
        for (double x = getStartDomain(); x <= getEndDomain(); x += deltaX) {
            max = Math.max(max, val(x));
            min = Math.min(min, val(x));
        }
        return new Pair(min, max);
    }
}
