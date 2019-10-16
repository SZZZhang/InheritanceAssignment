package Functions;
import Main.Function;
import javafx.scene.canvas.GraphicsContext;

public class Linear extends Function {
    private double m;
    private double b;
    private double x1;

    public Linear(double m, double b, double x1){
        this.m = m;
        this.b = b;
        this.x1 = x1;
    }

    public String toString(){

        String equation = "";

        if(this.m == 0) return Double.toString(this.b);

        //checks m value
        if(this.m == -1) equation += "-";
        else if(this.m != 1) equation += Double.toString(m);



        return (String.format("%f(x - %f) + %f", this.m, this.x1, this.b));
    }

    @Override
    public double val(double x) {
        return m * (x - x1) + b;
    }

    @Override
    public double getArea(double xStart, double xEnd) {
        return 0;
    }

    @Override
    public double getSlope(double x) {
        return m;
    }

    @Override
    public void draw(GraphicsContext gc, Function f) {

    }
}
