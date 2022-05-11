/**
 * 
 */
package net.ijt.interp;

import ij.ImageStack;
import ij.process.ImageProcessor;

/**
 * The common types of image interpolation methods managed by the package.
 * 
 * Each enumeration item also provides method to create an interpolator from a
 * 2D or 3D image.
 * 
 * @author dlegland
 *
 */
public enum InterpolationType
{
    NEAREST,
    LINEAR;
    
    public ImageInterpolator2D createInterpolator(ImageProcessor image)
    {
        switch(this)
        {
            case LINEAR:
                return new LinearInterpolator2D(image);
            case NEAREST:
                return new NearestNeighborInterpolator2D(image);
            default:
                throw new RuntimeException("Unknown interpolation type!");
        }
    }
    
    public ImageInterpolator3D createInterpolator(ImageStack image)
    {
        switch(this)
        {
            case LINEAR:
                return new LinearInterpolator3D(image);
            case NEAREST:
                return new NearestNeighborInterpolator3D(image);
            default:
                throw new RuntimeException("Unknown interpolation type!");
        }
            
    }

}
