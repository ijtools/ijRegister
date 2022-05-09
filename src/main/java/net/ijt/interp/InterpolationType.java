/**
 * 
 */
package net.ijt.interp;

import ij.process.ImageProcessor;

/**
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

}
