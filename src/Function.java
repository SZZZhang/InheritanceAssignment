import javafx.scene.paint.Color;

public class Function {

    private double startDomain;
    private double endDomain;
    private String name;
    private Color color;

    public Function(double x1, double x2) {
        //sets domain
        startDomain = x1;
        endDomain = x2;

        name = "";
        //
        color = Color.BLACK;
    }

    public String toString() {
        return "";
    }

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
}
