/**
 * 
 */
package net.ijt.interp;

import ij.IJ;
import ij.ImagePlus;
import ij.process.ByteProcessor;
import ij.process.ImageProcessor;
import net.ijt.geometry.geom2d.AffineTransform2D;

/**
 * @author dlegland
 *
 */
public class TransformedImage2D_Demo2_Rice
{
    
    public static final void main(String... args)
    {
        // Load input image
        ImagePlus imagePlus = IJ.openImage(TransformedImage2D_Demo2_Rice.class.getResource("/images/wheatGrain_tomo_slice.tif").getFile());
        ImageProcessor image = imagePlus.getProcessor();
        
        // retrieve image dimensions
        int sizeX = 400;
        int sizeY = 300;
        
        // create transform
        int refPointX = 410;
        int refPointY = 80;
        double angle = 17;
        
        // create elementary transforms
        AffineTransform2D trBoxCenter = AffineTransform2D.createTranslation(-sizeX/2, -sizeY/2);
        AffineTransform2D rot = AffineTransform2D.createRotation(Math.toRadians(angle));
        AffineTransform2D trRefPoint = AffineTransform2D.createTranslation(refPointX, refPointY);
        // concatenate into global display-image-to-source-image transform
        AffineTransform2D transfo = trRefPoint.concatenate(rot).concatenate(trBoxCenter);
        
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
