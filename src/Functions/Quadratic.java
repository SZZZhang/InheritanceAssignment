/*
Program by: Shirley Zhang
Course code: ICS4U
Date: Oct 20th, 2019
Instructor: Radulovic
Assignment: Inheritance Assignment

Description of Program:
This class represents a quadratic function.
*/

package Functions;

public class Quadratic extends Cubic {
    private double a;
    private double b;
    private double c;
    private double x1;

    public Quadratic(double a, double b, double c, double x1) {
        super(0, a, b, c, x1);
        this.a = a;
        this.b = b;
        this.c = c;
        this.x1 = x1;
    }

}
