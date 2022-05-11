/**
 * 
 */
package net.ijt.interp;

import ij.ImageStack;


/**
 * Evaluates values within a 3D image using tri-linear interpolation.
 * 
 * This implementation allows to specify the value that will be returned when
 * evaluating outside of array bounds.
 * 
 * 
 * @see LinearInterpolator3D
 * 
 * @author dlegland
 */
public class LinearInterpolator3D implements ImageInterpolator3D
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
     * Creates a new linear interpolator for a 3D image.
     * 
     * @param image
     *            the image containing values to interpolate.
     */
	public LinearInterpolator3D(ImageStack image)
	{
		this.image = image;
	}
	
    /**
     * Creates a new linear interpolator for a 3D image.
     * 
     * @param image
     *            the image containing values to interpolate.
     * @param padValue
     *            the value returned when interpolating outside of array bounds.
     *            Default value is 0.0.
     */
	public LinearInterpolator3D(ImageStack image, double padValue)
	{
		this.image = image;
		this.padValue = padValue;
	}
	

	// ===================================================================
	// implementation of the TrivariateFunction interface

    /**
     * Evaluates value within a 3D image. Returns stored pad value if
     * evaluation is outside image bounds.
     * 
     * @param x
     *            the x-coordinate of the position to evaluate
     * @param y
     *            the y-coordinate of the position to evaluate
     * @return the value evaluated at the (x,y) position
     */
	@Override
	public double evaluate(double x, double y, double z)
	{
	    // retrieve image size
        int sizeX = this.image.getWidth();
        int sizeY = this.image.getHeight();
        int sizeZ = this.image.getSize();
        
		// return pad value for positions outside image bounds
        if (x < 0 || x >= sizeX - 1) return padValue;
        if (y < 0 || y >= sizeY - 1) return padValue;
        if (z < 0 || z >= sizeZ - 1) return padValue;
		
		// compute indices
		int i = (int) Math.floor(x);
        int j = (int) Math.floor(y);
        int k = (int) Math.floor(z);
		
		// compute distances to lower-left corner of pixel
		double dx = (x - i);
		double dy = (y - j);
		double dz = (z - k);
        
		// values of the 8 voxels around each current point
		double val111 = this.image.getVoxel(i,   j,   k)   * (1-dx) * (1-dy) * (1-dz);
		double val112 = this.image.getVoxel(i+1, j,   k)   *    dx  * (1-dy) * (1-dz);
		double val121 = this.image.getVoxel(i,   j+1, k)   * (1-dx) *    dy  * (1-dz);
		double val122 = this.image.getVoxel(i+1, j+1, k)   *    dx  *    dy  * (1-dz);
        double val211 = this.image.getVoxel(i,   j,   k+1) * (1-dx) * (1-dy) *     dz;
        double val212 = this.image.getVoxel(i+1, j,   k+1) *    dx  * (1-dy) *     dz;
        double val221 = this.image.getVoxel(i,   j+1, k+1) * (1-dx) *    dy  *     dz;
        double val222 = this.image.getVoxel(i+1, j+1, k+1) *    dx  *    dy  *     dz;
		
		// compute result values
		double val = val111 + val112 + val121 + val122 + val211 + val212 + val221 + val222;

		return val;
	}
}
