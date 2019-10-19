package Main;

import javafx.scene.paint.Color;
import javafx.util.Pair;

public abstract class Function implements Drawable, Calculations {

    private final double defaultStartDomain = -200;
    private final double defaultEndDomain = 200;
    private final double deltaX = 1E-5;

    protected final double SCALE_TEXT_WIDTH = 10;
    protected final int NUMS_ON_SCALE = 5;

    protected double startDomain;
    protected double endDomain;
    protected String name;
    protected Color color;

    public Function(double x1, double x2) {
        //sets domain
        setDomain(x1, x2);
        name = "";
        color = Color.BLACK;
    }

    public Function() {
        setDomain(defaultStartDomain, defaultEndDomain);
    }

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
        if (x < getStartDomain() || x > getEndDomain()) {
            return true;
        }
        return false;
    }

    public double getArea(double xStart, double xEnd) {
        double area = 0;
        for (double currentX = xStart; currentX <= xEnd; currentX += deltaX) {
            area += deltaX * this.val(currentX);
            System.out.println(val(currentX));
        }
        return area;
    }

    public double getSlope(double x) {
        return (val(x + deltaX) - val(x - deltaX)) / (2 * deltaX);
    }

    //returns the minimum and maximum point in a function within the domain
    public Pair<Double, Double> findMinMax() {
        double max = 0, min = Double.POSITIVE_INFINITY;
        for (double x = getStartDomain(); x <= getEndDomain(); x += deltaX) {
            max = Math.max(max, val(x));
            min = Math.min(min, val(x));
        }
        return new Pair(min, max);
    }
}
