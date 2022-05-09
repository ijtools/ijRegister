/**
 * 
 */
package net.ijt.interp;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import net.ijt.geom2d.AffineTransform2D;
import net.ijt.geom2d.Transform2D;

/**
 * @author dlegland
 *
 */
public class TransformedImage2D_Demo_Rice
{
    
    public static final void main(String... args)
    {
        // Load input image
        ImagePlus imagePlus = IJ.openImage(TransformedImage2D_Demo_Rice.class.getResource("/images/grains.tif").getFile());
        ImageProcessor image = imagePlus.getProcessor();
        
        // retrieve image dimensions
        int sizeX = image.getWidth();
        int sizeY = image.getHeight();
        
        // create transform
        Transform2D transfo = AffineTransform2D.createRotation(sizeX/2, sizeY/2, Math.toRadians(30.0));
        
        // Create interpolation class, that encapsulates both the image and the transform
        Function2D interp = new TransformedImage2D(image, transfo);
        
        // allocate result image
        ImageProcessor res = new ByteProcessor(sizeX, sizeY);

        // iterate over pixel of target image
        for (int y = 0; y < sizeY; y++)
        {
            for (int x = 0; x < sizeX; x++)
            {
                res.setf(x, y, (float) interp.evaluate(x, y));
            }
        }
        
        // encapsulate into an ImagePlus for display
        ImagePlus resPlus = new ImagePlus("result", res);
        resPlus.show();
    }

}
