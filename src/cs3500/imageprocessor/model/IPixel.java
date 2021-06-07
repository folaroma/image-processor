package cs3500.imageprocessor.model;

import java.util.List;

/**
 * Interface to represent a pixel in an image. Pixels have a location in the image and a color.
 */
public interface IPixel {

  Position2D getPosition();

  IColor getColor();

}
