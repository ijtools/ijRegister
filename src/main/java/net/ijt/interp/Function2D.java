/**
 * 
 */
package net.ijt.interp;

/**
 * A function of two variables that returns floating point values.
 * 
 * @author dlegland
 */
public interface Function2D
{
    /**
     * Evaluates the functions for the specified pair of variables.
     * 
     * @param x
     *            the value of the first variable
     * @param y
     *            the value of the second variable
     * @return the result of evaluation
     */
    public double evaluate(double x, double y);
}
