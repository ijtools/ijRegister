/**
 * 
 */
package net.ijt.interp;

import ij.process.ImageProcessor;


/**
 * Evaluates values within a 2D image using nearest-neighbor interpolation.
 * 
 * This implementation allows to specify the value that will be returned when
 * evaluating outside of array bounds.
 * 
 * 
 * @see LinearInterpolator2D
 * 
 * @author dlegland
 */
public class NearestNeighborInterpolator2D implements ImageInterpolator2D
{
	// ===================================================================
	// class variables

    /**
     * The image containing values to interpolate.
     */
	ImageProcessor image;
	
	/**
	 * The state returned when sampling point is outside image bounds.
	 */
	double padValue = 0;
	
	
	// ===================================================================
	// constructors

	/**
     * Creates a new nearest-neighbor interpolator for a 2D image.
     * 
     * @param image
     *            the image containing values to interpolate.
     */
	public NearestNeighborInterpolator2D(ImageProcessor image)
	{
		this.image = image;
	}
	
    /**
     * Creates a new nearest-neighbor interpolator for a 2D image.
     * 
     * @param image
     *            the image containing values to interpolate.
     * @param padValue
     *            the value returned when interpolating outside of array bounds.
     *            Default value is 0.0.
     */
	public NearestNeighborInterpolator2D(ImageProcessor image, double padValue)
	{
		this.image = image;
		this.padValue = padValue;
	}
	

	// ===================================================================
	// implementation of the BivariateFunction interface

    /**
     * Evaluates value within a 2D image. Returns stored pad value if
     * evaluation is outside image bounds.
     * 
     * @param x
     *            the x-coordinate of the position to evaluate
     * @param y
     *            the y-coordinate of the position to evaluate
     * @return the value evaluated at the (x,y) position
     */
	@Override
	public double evaluate(double x, double y)
	{
        // compute indices
        int i = (int) Math.round(x);
        int j = (int) Math.round(y);
        
        // retrieve image size
        int sizeX = this.image.getWidth();
        int sizeY = this.image.getHeight();
        
		// select points located inside interpolation area
		// (smaller than image size)
		boolean isInside = i >= 0 && j >= 0 && i < sizeX && j < sizeY;
		if (!isInside)
		{
			return this.padValue;
		}
		
		// return value of nearest neighbor
		return this.image.getf(i, j);
	}
}
