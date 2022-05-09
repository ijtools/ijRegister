/**
 * 
 */
package net.ijt.interp;

import ij.process.ImageProcessor;
import net.ijt.geom2d.Point2D;
import net.ijt.geom2d.Transform2D;

/**
 * Evaluate values within an image after applying a transform to the coordinates.
 * 
 * @author dlegland
 *
 */
public class TransformedImage2D implements Function2D
{
    ImageInterpolator2D interp;
    
    Transform2D transform;
    
    public TransformedImage2D(ImageProcessor image, Transform2D transform)
    {
        this.interp = new LinearInterpolator2D(image);
        this.transform = transform;
    }

    public TransformedImage2D(ImageProcessor image, Transform2D transform, InterpolationType type)
    {
        this.interp = type.createInterpolator(image);
        this.transform = transform;
    }
    
    @Override
    public double evaluate(double x, double y)
    {
        Point2D p2 = transform.transform(new Point2D(x, y));
        return interp.evaluate(p2.getX(), p2.getY());
    }
}
