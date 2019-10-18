package Functions;
import Main.Function;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Linear extends Quadratic {
    private double m;
    private double b;
    private double x1;

    public Linear(double m, double b, double x1){
        super(0, m, b, x1);
        this.m = m;
        this.b = b;
        this.x1 = x1;
    }
}
