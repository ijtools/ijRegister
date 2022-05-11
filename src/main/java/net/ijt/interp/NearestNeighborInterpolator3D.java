/**
 * 
 */
package net.ijt.interp;

import ij.ImageStack;


/**
 * Evaluates values within a 3D image using nearest-neighbor interpolation.
 * 
 * This implementation allows to specify the value that will be returned when
 * evaluating outside of array bounds.
 * 
 * 
 * @see LinearInterpolator2D
 * 
 * @author dlegland
 */
public class NearestNeighborInterpolator3D implements ImageInterpolator3D
{
	// ===================================================================
	// class variables

    /**
     * The image containing values to interpolate.
     */
	ImageStack image;
	
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
	public NearestNeighborInterpolator3D(ImageStack image)
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
	public NearestNeighborInterpolator3D(ImageStack image, double padValue)
	{
		this.image = image;
		this.padValue = padValue;
	}
	

	// ===================================================================
	// implementation of the BivariateFunction interface

    /**
     * Evaluates value within a 3D image. Returns stored pad value if
     * evaluation is outside image bounds.
     * 
     * @param x
     *            the x-coordinate of the position to evaluate
     * @param y
     *            the y-coordinate of the position to evaluate
     * @param z
     *            the z-coordinate of the position to evaluate
     * @return the value evaluated at the (x,y, z) position
     */
	@Override
	public double evaluate(double x, double y, double z)
	{
        // compute indices
        int i = (int) Math.round(x);
        int j = (int) Math.round(y);
        int k = (int) Math.round(z);
        
        // retrieve image size
        int sizeX = this.image.getWidth();
        int sizeY = this.image.getHeight();
        int sizeZ = this.image.getSize();
        
		// select points located inside interpolation area
		// (smaller than image size)
		boolean isInside = i >= 0 && j >= 0 && k >= 0 && i < sizeX && j < sizeY && k < sizeZ;
		if (!isInside)
		{
			return this.padValue;
		}
		
		// return value of nearest neighbor
		return this.image.getVoxel(i, j, k);
	}
}
