package cs3500.imageprocessor.model.imagegenerating;

import cs3500.imageprocessor.model.images.ImageInterface;

/**
 * Interface to represent a function object that generates an ImageInterface programmatically.
 */
public interface IImageGenerator {

  /**
   * Creates the generate programmatic image.
   * @return The generated image.
   */
  ImageInterface generateImage();

}
