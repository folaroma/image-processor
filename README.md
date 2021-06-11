# Image processor - Manipulation and Enhancement

A project for CS3500 at Northeastern University. 

&nbsp;

## Index

- [About](#about)
- [Usage](#usage)
- [Class Overviews](#class-overviews)
- [Image Citations](#image-citations)

&nbsp;

## About

Many applications use color images. A good number of these applications 
provide a way to change their appearance in different ways. For example, 
Instagram has ''filters" that convert a picture into something more 
interesting. They do this by editing the colors of individual dots in 
the image (called pixels).

&nbsp;

## Usage

### Filtering

Filtering images, with support only for blurring or sharpening.

<details><summary><b>Show instructions</b></summary>

1. Create a new <code>ImageProcessorModel</code> with a given image:

    ```Java
    ImageProcessorModel testModel = new ImageProcessorModelImpl();
    ```

2. Create a new <code>ImageInterface</code> where you apply the filter to the model:

    ```Java
    ImageInterface sharpKoala = testModel.sharpen("res/Koala.ppm");
    ```

3. Add the new Image to the model:

    ```Java
    testModel.addImage("sharpKoala", sharpKoala);
    ```

4. Finally, write the model image:

    ```Java
    new PPMFileWriter().writeFile("res/KoalaSharpen.ppm", testModel.getImage("sharpKoala"));
    ```

</details>

&nbsp;

### Color Transformations

Transforming an image's color, with support only for grayscale and sepia.

<details><summary><b>Show instructions</b></summary>

1. Create a new <code>ImageProcessorModel</code> with a given image:

    ```Java
    ImageProcessorModel testModel = new ImageProcessorModelImpl();
    ```

2. Create a new <code>ImageInterface</code> where you apply the transformation to the model:

    ```Java
    ImageInterface monochromeKoala = testModel.grayscale("res/Koala.ppm");    
    ```

3. Add the new Image to the model:

    ```Java
    testModel.addImage("monochromeKoala", monochromeKoala);    
    ```

4. Finally, export the model image:

    ```Java
    new PPMFileWriter().writeFile("res\\monochromeKoala.ppm", testModel.getImage("monochromeKoala"));
    ```

</details>

&nbsp;

### Generating an Image

Generating an image, with support only for creating a basic checkerboard.

   ```Java
   ImageInterface checkerboard = new CheckerboardGenerator(100, 100, 
   new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0), new ColorImpl(255, 255, 255)))).generateImage();
   ```

&nbsp;

## Class Overviews

  * [ImageProcessorModel](#imageprocessormodel)
  * [ImageProcessorModelImpl](#imageprocessormodelimpl)
  * [colorTransformations]
    - [IColorTransformation](#icolortransformation)
    - [AbstractColorTransformation](#abstractcolortransformation)
    - [GrayscaleTransformation](#grayscaletransformation)
    - [SepiaTransformation](#sepiatransformation)
  - [fileReading]
    - [IFileReader](#ifilereader)
    - [PPMFileReader](#ppmfilereader)
  - [fileWriting]
    - [IImageFileWriter](#iimagefilewriter)
    - [PPMFileWriter](#ppmfilewriter)
  - [filters]
    - [IFilter](#ifilter)
    - [AbstractFilter](#abstractfilter)
    - [FilterBlur](#filterblur)
    - [FilterSharpen](#filtersharpen)
  - [imageGenerating]
    - [IImageGenerator](#iimagegenerator)
    - [CheckerboardGenerator](#checkerboardgenerator)
  - [images]
    - [ImageInterface](#imageinterface)
    - [IColor](#icolor)
    - [IPixel](#ipixel)
    - [Position2D](#position2d)
    - [ImageImpl](imageimpl)
    - [ColorImpl](#colorimpl)
    - [PixelImpl](#pixelimpl)

&nbsp;

## ImageProcessorModel

The interface to represent the image processing program.

Contains the methods for replacing, adding, getting, filtering, and transforming images.

### ImageProcessorModelImpl

The implemenation of the ImageProcessorModel interface.

&nbsp;

### IColorTransformation

The interface to represent image color transformations.

Contains the method to apply the transformation.

### AbstractColorTransformation

An abstraction of the color transformation classes.

Contains the methods to transform each pixel, the entire image, and ensure the RGB values do not surpass the limits.

### GrayscaleTransformation

Function object for grayscale color transformations.

### SepiaTransformation

Function object for sepia color transformations.

&nbsp;

### IFileReader

The interface to represent reading an image file.

Contains the method to read an image from a file.

### PPMFileReader

Function object to read a PPM image file.

&nbsp;

### IImageFileWriter

The interface to represent writing an image file.

Contains the method to write an image with a given filename and image.

### PPMFileWriter

Function object to write a PPM image file.

&nbsp;

### IFilter

The interface to represent filtering an image.

Contains the method to apply a filter to a given image.

### AbstractFilter

An abstraction of the filtering classes.

Contains the methods to apply a filter to every pixel, the entire image, and ensure the RGB values do not surpass the limits.

### FilterBlur

Function object for blur filters.

### FilterSharpen

Function object for sharp filters.

&nbsp;

### IImageGenerator

The interface to represent generating an image.

Contains the method to generate an image.

### CheckerboardGenerator

Function object for generating a checkerboard image.

&nbsp;

### ImageInterface

The interface to represent an image.

Contains the method to get the pixels of an image.

### IColor

The interface to represent colors.

Contains the methods to get the red, green, and blue values of a color.

### IPixel

The interface to represent pixels.

Contains the methods to get the position and color of a pixel.

### Position2D

Represents a 2D point represented in Cartesian coordinates.

### ImageImpl

The implementation of the image interface, which is a 2D array of pixels.

### ColorImpl

The implementation of the color interface, which has 3 8-bit channels for red, green, and blue values as ints.

### PixelImpl

The implementation of the pixel interface, which has a position and color.


&nbsp;


## Image Citations

By license, the images are free for use:

https://unsplash.com/photos/hPYiwTyEHhU

https://unsplash.com/photos/eijM2bJLD-k
