/*
Program by: Shirley Zhang
Course code: ICS4U
Date: Oct 20th, 2019
Instructor: Radulovic
Assignment: Inheritance Assignment

Description of Program:
This class represents a parabola in the form y = a(x - x1)^2 + b.
*/

package Functions;

public class Parabola extends Quadratic{
    private double a;
    private double b;
    private double x1;

    public Parabola(double a, double b, double x1) {
        super(a, 0, b, x1);
        this.a = a;
        this.b = b;
        this.x1 = x1;
    }
}
