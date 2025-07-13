/**
 * 
 */
package net.ijt.interp;

import net.ijt.geometry.geom3d.Point3D;

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
    
    /**
     * Evaluates the function at the specified position.
     * 
     * @param p
     *            the position corresponding to the triplet of coordinates to
     *            evaluate
     * @return the result of evaluation
     */
    public default double evaluate(Point3D p)
    {
        return evaluate(p.x(), p.y(), p.z());
    }
}
