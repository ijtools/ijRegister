/**
 * 
 */
package net.ijt.interp;

import net.ijt.geom2d.Point2D;

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
    
    /**
     * Evaluates the function at the specified position.
     * 
     * @param p
     *            the position corresponding to the pair of coordinates to
     *            evaluate
     * @return the result of evaluation
     */
    public default double evaluate(Point2D p)
    {
        return evaluate(p.x(), p.y());
    }
}
