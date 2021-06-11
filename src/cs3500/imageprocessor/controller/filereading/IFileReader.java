package cs3500.imageprocessor.controller.filereading;

import cs3500.imageprocessor.model.images.ImageInterface;

/**
 * Class to represent a function object that can read in a file and create image data from it using
 * the ImageInterface implementation.
 */
public interface IFileReader {

  /**
   * Read an image file in the specific format associated with the class and creates an
   * ImageInterface from that data.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException If the given filename is null, the file cannot be found, or if
   *                                  it is not a valid file of the desired type.
   */
  ImageInterface readImageFromFile(String filename);

}
