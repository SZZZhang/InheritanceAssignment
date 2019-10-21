/*
Program by: Shirley Zhang
Course code: ICS4U
Date: Oct 20th, 2019
Instructor: Radulovic
Assignment: Inheritance Assignment

Description of Program:
This class represents a natural logarithm function.
*/

package Functions;

import Main.Function;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Logarithm extends Function {
    private double a;
    private double b;
    private double x1;

    //minimum x bound prevents the function from going into infinity
    private double MIN_X = 1E-5;

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

            if (b > 0) equation += " + " + b;
            else if (b < 0) equation += " - " + Math.abs(b);
        } else {
            equation += b;
        }

        return equation;
    }

    @Override
    public double val(double x) {
        return a * Math.log(x - x1) + b;
    }

    @Override
    public boolean undefined(double x) {
        //checks if x is less than the vertical asymptote
        if (a != 0 && x < x1) return true;
        //checks if x is in the domain
        return super.undefined(x);
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(getColor());
        gc.setLineWidth(LINE_WIDTH);

        double min = 0, max = 0; //min & max of function
        double prevX = 0, prevY = 0; //first coordinate

        //finds min & max of function
        if (a > 0) {
            if (!Double.isNaN(val(getStartDomain()))) {
                min = Math.max(val(MIN_X + x1), val(getStartDomain()));
                prevX = Math.max(MIN_X + x1, getStartDomain());
            } else {
                min = val(MIN_X + x1);
                prevX = MIN_X + x1;
            }

            min += b;
            max = val(getEndDomain()) + b;
            prevY = min;
        } else {
            if (!Double.isNaN(val(getStartDomain()))) {
                max = Math.min(val(MIN_X + x1), val(getStartDomain()));
                prevX = Math.max(MIN_X + x1, getStartDomain());
            } else {
                max = val(MIN_X + x1);
                prevX = MIN_X + x1;
            }
            max += b;
            System.out.println(getEndDomain());
            min = val(getEndDomain()) + b;
            prevY = max;
        }

        //horizontal and vertical ratios between the domain/range and the width/height of the screen
        double hRatio = canvas.getWidth() / (getEndDomain() - getStartDomain());
        double vRatio = canvas.getHeight() / (max - min);

        //draws function
        for (double x = getStartDomain() + DELTA_X, screenX = 0; x <= getEndDomain(); x += DELTA_X,
                screenX += DELTA_X * hRatio) {
            double currentY = -1 * val(x) * vRatio + max * vRatio;
            gc.strokeLine(prevX, prevY, screenX, currentY);
            prevX = screenX;
            prevY = currentY;
        }

        drawAxes(canvas, min, max, vRatio, hRatio);
    }
}
