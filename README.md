# ij_Register
A collection of utilities for image registration with ImageJ/Fiji.

The main contribution of this library its to provide a collection of image interpolation methods, 
as classes that implement a common "Interpolator2D/3D" interface. 
For example, linear interpolation of a value within a 2D image can be performed as follow:

    ImageInterpolator2D interp = new LinearInterpolator2D(image);
    dobule value = interp.evaluate(posX, posY);

The project is based on maven. It uses sci-java as parent configuration. The parent configuration 
is somewhat old (1.126), but I encountered configuration troubles with more recent ones. 

The library is based on the following dependencies

* ImageJ
* [ijGeometry](https://github.com/ijtools/ijGeometry)
* JUnit

