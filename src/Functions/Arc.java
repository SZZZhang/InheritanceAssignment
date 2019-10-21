package Functions;

import Main.Function;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class Arc extends Function {
    private double r;
    private double xCenter;
    private double yCenter;

    public Arc(double r, double xCenter, double yCenter) {
        this.r = r;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }

    public String toString() {
        String equation = "sqrt(" + r + "^2 - ";
        if (xCenter > 0) equation += "(x - " + xCenter + ")^2)";
        else if (xCenter < 0) equation += "(x + " + Math.abs(xCenter) + ")^2)";
        else equation += "x^2)";

        if (yCenter > 0) equation += " + " + yCenter;
        else if (yCenter < 0) equation += " - " + Math.abs(yCenter);

        return equation;
    }

    public double val(double x) {
        return Math.sqrt(Math.pow(r, 2) - Math.pow((x - xCenter), 2)) + yCenter;
    }

    @Override
    public boolean undefined(double x) {
        return super.undefined(x);
    }

    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(LINE_WIDTH);

        //finds min max of function
        double min = yCenter;
        double max = r + yCenter;
        double startX = Math.max(getStartDomain(), xCenter - r);
        double endX = Math.min(getEndDomain(), xCenter + r);

        double hRatio = canvas.getWidth() / (getEndDomain() - getStartDomain());
        double vRatio = canvas.getHeight() / (max - min);

        //finds the first value of the function within the domain when it is not undefined
        double prevX = -1 * (getStartDomain() - startX) * hRatio, prevY = -1 * val(startX) * vRatio + max * vRatio;

        for (double x = startX + DELTA_X, screenX = prevX + DELTA_X * hRatio; x <= endX;
             x += DELTA_X, screenX += DELTA_X * hRatio) {
            double currentY = -1 * val(x) * vRatio + max * vRatio;
            gc.strokeLine(prevX, prevY, screenX, currentY);
            prevX = screenX;
            prevY = currentY;
        }

        drawAxis(canvas, min, max, vRatio, hRatio);
    }
}
