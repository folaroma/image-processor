package cs3500.imageprocessor.model.colortransformations;

import cs3500.imageprocessor.model.images.ImageInterface;

/**
 * Function Object class that represents a color transformation performed on an image. It uses the
 * ImageInterface representation of an image. A color transformation performs math on only the
 * specific pixels rgb data and does not use the surrounding pixels.
 */
public interface IColorTransformation {

  /**
   * Applies the desired transformation to the supplied image.
   *
   * @param image Image to apply the transformation to.
   * @return The transformed image.
   * @throws IllegalArgumentException If the supplied image is null.
   */
   ImageInterface applyTransformation(ImageInterface image) throws IllegalArgumentException;

}
