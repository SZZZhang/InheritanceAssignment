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
        return this.a * Math.pow(x - this.x1, 3) + this.b * Math.pow(x - this.x1, 2) + this.c * (x - this.x1) + this.d;
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

        //renders the x scale

        //checks if x axis is above, between, or below the function in order to find the y coordinate of the x scale
        double xScaleYCoor = 0;
        if (0 > min && 0 < max) {
            xScaleYCoor = max * vRatio;
            gc.strokeLine(0, xScaleYCoor, canvas.getWidth(), xScaleYCoor);
        } else if (min > 0) xScaleYCoor = canvas.getHeight();
        else if (max < 0) xScaleYCoor = 0;

        double xScaleXIncrement = ((getEndDomain() - getStartDomain()) / (NUMS_ON_SCALE + 1));
        double xScaleScreenXIncrement = (canvas.getWidth() / (NUMS_ON_SCALE + 1));

        for (double x = getStartDomain(), screenX = 0;
             screenX <= canvas.getWidth() - xScaleScreenXIncrement;
             x += xScaleXIncrement, screenX += xScaleScreenXIncrement) {
            gc.fillText("| " + Double.toString(Math.round(x)), screenX, xScaleYCoor);
        }

        //renders the y scale
        double yScaleXCoor = 0;
        if (0 > getStartDomain() && 0 < getEndDomain()) {
            yScaleXCoor = Math.abs(0 - getStartDomain()) * hRatio;
            gc.strokeLine(yScaleXCoor, 0, yScaleXCoor, canvas.getHeight());
        } else if (getEndDomain() < 0) yScaleXCoor = canvas.getWidth() - 50; // FIX THIS
        else if (getStartDomain() > 0) yScaleXCoor = 0;

        double yScaleYIncrement = ((max - min) / (NUMS_ON_SCALE + 1));
        double yScaleScreenYIncrement = (canvas.getHeight() / (NUMS_ON_SCALE + 1));

        for (double y = max, screenY = 0;
             screenY <= canvas.getHeight() - yScaleScreenYIncrement;
             y -= yScaleYIncrement, screenY += yScaleScreenYIncrement) {
            gc.fillText("– " + Double.toString(Math.round(y)), yScaleXCoor, screenY);
        }
    }

}
