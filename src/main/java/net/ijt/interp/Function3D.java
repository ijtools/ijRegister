/**
 * 
 */
package net.ijt.interp;

/**
 * A function of three variables that returns floating point values.
 * 
 * @author dlegland
 */
public interface Function3D
{
    /**
     * Evaluates the functions for the specified pair of variables.
     * 
     * @param x
     *            the value of the first variable
     * @param y
     *            the value of the second variable
     * @param z
     *            the value of the third variable
     * @return the result of evaluation
     */
    public double evaluate(double x, double y, double z);
}
