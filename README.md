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

Many applications use color images. A good number of these applications provide a way to change
their appearance in different ways. For example, Instagram has ''filters" that convert a picture
into something more interesting. They do this by editing the colors of individual dots in the
image (called pixels). In this design, the model for the program allows the user to apply 
certain filters and transformations to the images held in the model, specifically through a
map with String ids for each image. Images are not mutated by the filters in order to preserve
the original versions, and images themselves are represented by a 2D arraylist of pixels in order
to apply the filter pixel by pixel. The application also supports programmatically generating images,
specifically the checkerboard for this assigment. IO work is done outside of the model, and can read and 
create image data from ASCII PPM files and also write image data into these files.

&nbsp;

## Usage

### Filtering

Filtering images, with support only for blurring or sharpening.

<details><summary><b>Show instructions</b></summary>

1. Create a new <code>ImageProcessorModel</code> with a given image:

    ```Java
    ImageProcessorModel testModel = new ImageProcessorModelImpl();
    ```
    
2. Add a new <code>ImageInterface</code> to the model:
    
    ```Java
    testmodel.addImage("res/Koala.ppm", new PPMFileReader().readImageFromFile("res/Koala.ppm"));
    ```

3. Create a new <code>ImageInterface</code> where you apply the filter to the model:

    ```Java
    ImageInterface sharpKoala = testModel.sharpen("res/Koala.ppm");
    ```

4. Add the new Image to the model:

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
    
2. Add a new <code>ImageInterface</code> to the model:
    
    ```Java
    testmodel.addImage("res/Koala.ppm", new PPMFileReader().readImageFromFile("res/Koala.ppm"));
    ```

3. Create a new <code>ImageInterface</code> where you apply the transformation to the model:

    ```Java
    ImageInterface monochromeKoala = testModel.grayscale("res/Koala.ppm");    
    ```

4. Add the new Image to the model:

    ```Java
    testModel.addImage("monochromeKoala", monochromeKoala);    
    ```

5. Finally, export the model image:

    ```Java
    new PPMFileWriter().writeFile("res\\monochromeKoala.ppm", testModel.getImage("monochromeKoala"));
    ```

</details>

&nbsp;

### Generating an Image

Generating an image, with support only for creating a basic checkerboard.

   ```Java
   ImageInterface checkerboard = testmodel.generateCheckerboard(100, 100, 
   new ArrayList<>(Arrays.asList(new ColorImpl(0, 0, 0), new ColorImpl(255, 255, 255))));
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

The interface to represent the image processing program. At this time, the model has support for blurring, sharpening, grayscale, and sepia, along with generating a checkerboard.

Contains the methods for replacing, adding, getting, filtering, and transforming images.

### ImageProcessorModelImpl

The implemenation of the ImageProcessorModel interface. Images are stored in a map with a String id. These images can then be retrieved and edited with the given filters and transformations to make new images.

&nbsp;

### IColorTransformation

The interface to represent image color transformations.

Contains the method to apply the transformation. Valid color transformations are done using a 3 x 3 matrix.

### AbstractColorTransformation

An abstraction of the color transformation classes.

Contains the methods to transform each pixel, the entire image, and ensure the RGB values do not
surpass the limits.

### GrayscaleTransformation

Function object for grayscale color transformations. Given an image, it will return the grayscaled version.

### SepiaTransformation

Function object for sepia color transformations. Given an image, it will return the sepia version of that image.

&nbsp;

### IFileReader

The interface to represent reading an image file. File read in can be of various file types.

Contains the method to read an image from a file.

### PPMFileReader

Function object to read a PPM image file. PPM must bein in the ASCII format, or else the reading will fail.

&nbsp;

### IImageFileWriter

The interface to represent writing an image file. File can be written to multiple file types.

Contains the method to write an image with a given filename and image.

### PPMFileWriter

Function object to write a PPM image file. Will specifically write the PPM ASCII file type. 

&nbsp;

### IFilter

The interface to represent filtering an image.

Contains the method to apply a filter to a given image. Filters must use an odd-dimension square matrix.

### AbstractFilter

An abstraction of the filtering classes.

Contains the methods to apply a filter to every pixel, the entire image, and ensure the RGB values
do not surpass the limits.

### FilterBlur

Function object for blur filters.

### FilterSharpen

Function object for sharp filters.

&nbsp;

### IImageGenerator

The interface to represent generating an image. Has support for multiple types of image generation.

Contains the method to generate an image.

### CheckerboardGenerator

Function object for generating a checkerboard image. Checkerboards are specified by the number of row, columns, and colors to choose from. Each square is 1 pixel and the board can have a max of two distinct colors.

&nbsp;

### ImageInterface

The interface to represent an image. Images must be rectangular and are made up of pixels of the IPixel implementation.

Contains the method to get the pixels of an image.

### IColor

The interface to represent colors. Colors are stored in 3 8-bit channels and can have values from 0-255 inclusive in each channel.

Contains the methods to get the red, green, and blue values of a color.

### IPixel

The interface to represent pixels. Pixels have a color and a position using the IColor and Position2D impelementations.

Contains the methods to get the position and color of a pixel.

### Position2D

Represents a 2D point represented in Cartesian coordinates.

### ImageImpl

The implementation of the image interface, which is a 2D array of pixels. Images are rectangular, and each pixel uses the IPixel implementation.

### ColorImpl

The implementation of the color interface, which has 3 8-bit channels for red, green, and blue. Valid color values can only be from 0-255 inclusive. 

### PixelImpl

The implementation of the pixel interface, which has a position and color.

&nbsp;

## Image Citations

By license, the images are free for use:

https://unsplash.com/photos/hPYiwTyEHhU

https://unsplash.com/photos/eijM2bJLD-k
