/*
Program by: Shirley Zhang
Course code: ICS4U
Date: Oct 20th, 2019
Instructor: Radulovic
Assignment: Inheritance Assignment

Description of Program:
This class represents a linear function.
*/

package Functions;

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
