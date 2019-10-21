/*
Program by: Shirley Zhang
Course code: ICS4U
Date: Oct 20th, 2019
Instructor: Radulovic
Assignment: Inheritance Assignment

Description of Program:
This interface is used by all functions and contains several calculation methods
*/

package Main;

public interface Calculations {
	//returns the value of a function f at x
	public double val(double x);

	//returns true if the function is undefined at x
	public boolean undefined(double x);

	//returns the area from xStart to xEnd
	public double getArea(double xStart, double xEnd);

	//returns the slope at x
	public double getSlope(double x);
}
