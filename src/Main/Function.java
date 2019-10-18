package Main;

import javafx.scene.paint.Color;

public abstract class Function implements Drawable, Calculations{

    private final int defaultStartDomain = -500;
    private final int defaultEndDomain = 500;
    private final double areaInterval = 0.1;

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

    public Function(){
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

    public abstract boolean undefined(double x);
}
