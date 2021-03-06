package cs3500.imageprocessor.controller.filewriting;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;

/**
 * Interface to represent a function object that writes an image file type from the ImageInterface
 * data.
 */
public interface IImageFileWriter {

  /**
   * Writes a file of the given filename using the data from the given image.
   *
   * @param filename Filename and path of the desired output image
   * @param image    Image data to be written.
   * @throws IOException              If the file writing fails.
   * @throws IllegalArgumentException If the image or filename is null.
   */
  void writeFile(String filename, ImageInterface image)
      throws IOException, IllegalArgumentException;

}
