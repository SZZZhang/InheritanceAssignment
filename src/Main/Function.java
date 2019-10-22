/*
Program by: Shirley Zhang
Course code: ICS4U
Date: Oct 20th, 2019
Instructor: Radulovic
Assignment: Inheritance Assignment

Description of Program:
This is the parent class for all functions. It includes
*/

package Main;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public abstract class Function implements Drawable, Calculations {

    //constants
    private final double DEFAULT_START_DOMAIN = -300;
    private final double DEFAULT_END_DOMAIN = 300;
    protected final double DELTA_X = 1E-2;
    protected final double LINE_WIDTH = 1;
    private final int NUMS_ON_SCALE = 4;
    private final int X_SCALE_SHIFT = -2;
    private final int Y_SCALE_SHIFT = -50;
    private final int SCALE_ROUND_FACTOR = 10;

    private double startDomain;
    private double endDomain;
    private String name;
    private Color color;

    public Function(double x1, double x2) {
        setDomain(x1, x2);
        name = "";
        color = Color.BLACK;
    }

    public Function() {
        setDomain(DEFAULT_START_DOMAIN, DEFAULT_END_DOMAIN);
    }

    //string representation of functions
    public abstract String toString();

    public double getStartDomain() {
        return startDomain;
    }

    public double getEndDomain() {
        return endDomain;
    }

    public void setDomain(double x1, double x2) {
        this.startDomain = x1;
        this.endDomain = x2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color col) {
        this.color = col;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //returns true if the function is undefined
    public boolean undefined(double x) {
        if (x < getStartDomain() || x > getEndDomain()) return true;
        return false;
    }

    //gets area within xStart and xEnd
    public double getArea(double xStart, double xEnd) {
        //returns NaN if undefined
        if (undefined(xStart) || undefined(xEnd)) return Double.NaN;

        double area = 0;
        for (double currentX = xStart; currentX < xEnd; currentX += DELTA_X) {
            area += DELTA_X * Math.abs(val(currentX));
        }
        return area;
    }

    //gets slope at point x
    public double getSlope(double x) {
        //returns NaN if undefined
        if (undefined(x + DELTA_X) || undefined(x - DELTA_X)) return Double.NaN;

        return (val(x + DELTA_X) - val(x - DELTA_X)) / (2 * DELTA_X);
    }

    //returns the minimum and maximum point in a function within the domain
    protected Pair<Double, Double> findMinMax() {
        double max = Double.NEGATIVE_INFINITY, min = Double.POSITIVE_INFINITY;
        for (double x = startDomain; x <= endDomain; x += DELTA_X) {
            if (!undefined(x)) {
                max = Math.max(max, val(x));
                min = Math.min(min, val(x));
            }
        }
        return new Pair(min, max);
    }

    //draws function on canvas
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(getColor());
        gc.setLineWidth(LINE_WIDTH);

        //finds min & max of function
        Pair<Double, Double> minMax = findMinMax();
        double min = minMax.getKey();
        double max = minMax.getValue();

        //horizontal and vertical ratios for scaling: windowWidth/domain, windowHeight/range
        double hRatio = canvas.getWidth() / (getEndDomain() - getStartDomain());
        double vRatio = canvas.getHeight() / (max - min);
        double prevX = 0, prevY = val(getStartDomain()) * vRatio * -1 + max * vRatio; //first coordinates

        //draws function
        for (double x = getStartDomain() + DELTA_X, screenX = 0; x <= getEndDomain(); x += DELTA_X, screenX += DELTA_X * hRatio) {
            double currentY = val(x) * vRatio * -1 + max * vRatio;
            gc.strokeLine(prevX, prevY, screenX, currentY);
            prevX = screenX;
            prevY = currentY;
        }

        drawAxes(canvas, min, max, vRatio, hRatio);
    }

    //draws x & y scales and axes
    public void drawAxes(Canvas canvas, Double min, Double max, Double vRatio, Double hRatio) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);

        //renders the x scale
        //checks if x axis is above, between, or below the function in order to find the y coordinate of the x scale
        double xScaleYCoor = 0;
        if (0 > min && 0 < max) {
            xScaleYCoor = max * vRatio;
            gc.strokeLine(0, xScaleYCoor, canvas.getWidth(), xScaleYCoor);
        } else if (min > 0) {
            xScaleYCoor = canvas.getHeight();
        } else if (max < 0) {
            xScaleYCoor = 0;
        }

        double xScaleXIncrement = ((getEndDomain() - getStartDomain()) / (NUMS_ON_SCALE));
        double xScaleScreenXIncrement = (canvas.getWidth() / (NUMS_ON_SCALE));

        for (double x = getStartDomain(), screenX = 0; screenX <= canvas.getWidth() - xScaleScreenXIncrement;
             x += xScaleXIncrement, screenX += xScaleScreenXIncrement) {
            gc.fillText("| " + (double) Math.round(x * SCALE_ROUND_FACTOR) / SCALE_ROUND_FACTOR,
                    screenX + X_SCALE_SHIFT, xScaleYCoor);
        }

        //renders the y scale
        double yScaleXCoor = 0;
        if (0 > getStartDomain() && 0 < getEndDomain()) {
            yScaleXCoor = Math.abs(0 - getStartDomain()) * hRatio;
            gc.strokeLine(yScaleXCoor, 0, yScaleXCoor, canvas.getHeight());
        } else if (getEndDomain() < 0) {
            yScaleXCoor = canvas.getWidth() + Y_SCALE_SHIFT;
        } else if (getStartDomain() > 0) {
            yScaleXCoor = 0;
        }

        double yScaleYIncrement = ((max - min) / (NUMS_ON_SCALE + 1));
        double yScaleScreenYIncrement = (canvas.getHeight() / (NUMS_ON_SCALE + 1));

        for (double y = max, screenY = 0; screenY <= canvas.getHeight() - yScaleScreenYIncrement;
             y -= yScaleYIncrement, screenY += yScaleScreenYIncrement) {
            gc.fillText("– " + (double) Math.round(y * SCALE_ROUND_FACTOR) / SCALE_ROUND_FACTOR,
                    yScaleXCoor, screenY);
        }
    }
}
