package Functions;

import Main.Function;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Pair;

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

        if (b > 0) equation += " + " + b;
        else if (b < 0) equation += " - " + Math.abs(b);

        return equation;
    }

    @Override
    public double val(double x) {
        return a * Math.log(x - x1) + b;
    }

    @Override
    public boolean undefined(double x) {
        //checks if x is less than the vertical asymptote
        if(a != 0 && x < x1) return true;
        //checks if x is in the domain
        return super.undefined(x);
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(LINE_WIDTH);

        //finds min max of function
        Pair<Double, Double> minMax = super.findMinMax();
        double min = minMax.getKey();
        double max = minMax.getValue();

        double hRatio = canvas.getWidth() / (getEndDomain() - getStartDomain());
        double vRatio = canvas.getHeight() / (max - min);

        //finds the first value of the function within the domain when it is not undefined
        double prevX = Double.NaN, prevY = Double.NaN;
        for (double x = getStartDomain(); x <= getEndDomain(); x += DELTA_X) {
            if (!undefined(x)) {
                prevX = x;
                prevY = val(x);
                break;
            }
        }

        //if there is no value within the graph that is defined
        if (prevX == Double.NaN) return;

        for (double x = getStartDomain() + DELTA_X, screenX = 0; x <= getEndDomain(); x += DELTA_X, screenX += DELTA_X * hRatio) {
            double currentY = val(x) * vRatio * -1 + max * vRatio;
            gc.strokeLine(prevX, prevY, screenX, currentY);
            prevX = screenX;
            prevY = currentY;
        }

        super.drawAxis(canvas, min, max, vRatio, hRatio);
    }


}
