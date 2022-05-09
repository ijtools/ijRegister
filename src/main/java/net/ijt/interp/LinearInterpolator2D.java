/**
 * 
 */
package net.ijt.interp;

import ij.process.ImageProcessor;


/**
 * Evaluates values within a 2D image using bi-linear interpolation.
 * 
 * This implementation allows to specify the value that will be returned when
 * evaluating outside of array bounds.
 * 
 * 
 * @see NearestNeighborInterpolator2D
 * 
 * @author dlegland
 */
public class LinearInterpolator2D implements ImageInterpolator2D
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
     * Creates a new linear interpolator for a 2D image.
     * 
     * @param image
     *            the image containing values to interpolate.
     */
	public LinearInterpolator2D(ImageProcessor image)
	{
		this.image = image;
	}
	
    /**
     * Creates a new linear interpolator for a 2D image.
     * 
     * @param image
     *            the image containing values to interpolate.
     * @param padValue
     *            the value returned when interpolating outside of array bounds.
     *            Default value is 0.0.
     */
	public LinearInterpolator2D(ImageProcessor image, double padValue)
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
	    // retrieve image size
        int sizeX = this.image.getWidth();
        int sizeY = this.image.getHeight();
        
		// select points located inside interpolation area
		// (smaller than image size)
		boolean isInside = x >= 0 && y >= 0 && x < (sizeX-1) && y < (sizeY-1);
		if (!isInside)
		{
			return this.padValue;
		}
		
		// compute indices
		int i = (int) Math.floor(x);
		int j = (int) Math.floor(y);
		
		// compute distances to lower-left corner of pixel
		double dx = (x - i);
		double dy = (y - j);
		
		// values of the 4 pixels around each current point
		double val11 = this.image.getf(i,   j) 	 * (1-dx) * (1-dy);
		double val12 = this.image.getf(i+1, j) 	 *    dx  * (1-dy);
		double val21 = this.image.getf(i,   j+1) * (1-dx) *    dy;
		double val22 = this.image.getf(i+1, j+1) *    dx  *    dy;
		
		// compute result values
		double val = val11 + val12 + val21 + val22;

		return val;
	}
}
