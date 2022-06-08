/**
 * 
 */
package net.ijt.interp;

import ij.ImageStack;
import net.ijt.geom3d.Point3D;
import net.ijt.geom3d.Transform3D;

/**
 * Evaluate values within an image after applying a transform to the coordinates.
 * 
 * @see TransformedImage2D
 * 
 * @author dlegland
 *
 */
public class TransformedImage3D implements Function3D
{
    ImageInterpolator3D interp;
    
    Transform3D transform;
    
    public TransformedImage3D(ImageStack image, Transform3D transform)
    {
        this.interp = new LinearInterpolator3D(image);
        this.transform = transform;
    }

    public TransformedImage3D(ImageStack image, Transform3D transform, InterpolationType type)
    {
        this.interp = type.createInterpolator(image);
        this.transform = transform;
    }
    
    @Override
    public double evaluate(double x, double y, double z)
    {
        Point3D p2 = transform.transform(new Point3D(x, y, z));
        return interp.evaluate(p2);
    }
}
