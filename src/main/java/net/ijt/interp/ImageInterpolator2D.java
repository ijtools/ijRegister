/**
 * 
 */
package net.ijt.interp;

/**
 * Evaluates values within a 2D image.
 *  
 * @see LinearInterpolator2D
 *  
 * @author dlegland
 */
public interface ImageInterpolator2D extends Function2D
{
    /**
     * Evaluates value within a 2D image.
     * 
     * @param x
     *            the x-coordinate of the position to evaluate
     * @param y
     *            the y-coordinate of the position to evaluate
     * @return the value evaluated at the (x,y) position
     */
    public double evaluate(double x, double y);
}
