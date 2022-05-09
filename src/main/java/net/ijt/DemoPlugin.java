/**
 * 
 */
package net.ijt;

import ij.IJ;
import ij.plugin.PlugIn;

/**
 * @author dlegland
 *
 */
public class DemoPlugin implements PlugIn
{

    @Override
    public void run(String arg)
    {
        IJ.showMessage("Demo Plugin", "This is a message from the Plugin_Template project!");
    }

}
