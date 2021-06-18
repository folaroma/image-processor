package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.images.IColor;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.List;

/**
 * Interface to represent an image processing program.
 */
public interface ImageProcessorModel {

  /**
   * Replaces the image associated with the given id with the given image.
   *
   * @param id    Id of image to be replaced.
   * @param image Image to replace with.
   * @throws IllegalArgumentException If either argument is null or there is no such id in the map.
   */
  void replaceImage(String id, ImageInterface image) throws IllegalArgumentException;

  /**
   * Adds the given image to the map with the given id as the key.
   *
   * @param id    Key for the image.
   * @param image Image to be added.
   * @throws IllegalArgumentException If either argument is null or the id is already contained.
   */
  void addImage(String id, ImageInterface image) throws IllegalArgumentException;

  /**
   * Returns the image associated with the given id.
   *
   * @param id Key to grab the image.
   * @return The image associated with the id.
   * @throws IllegalArgumentException If the id is null, or there is no image associated with that
   *                                  id.
   */
  ImageInterface getImage(String id) throws IllegalArgumentException;

  /**
   * Applies the blur filter to the image in the map associated with the given id.
   *
   * @param id Id for image to filter.
   * @return The filtered image with blur applied.
   * @throws IllegalArgumentException If any argument is null or the id is invalid.
   */
  ImageInterface blur(String id) throws IllegalArgumentException;

  /**
   * Applies the sharpen filter to the image in the map associated with the given id.
   *
   * @param id Id for image to filter.
   * @return The filtered image with sharpen applied.
   * @throws IllegalArgumentException If any argument is null or the id is invalid.
   */
  ImageInterface sharpen(String id) throws IllegalArgumentException;

  /**
   * Applies the grayscale color transformation to the image associated with the id.
   *
   * @param id Id for the image to be transformed.
   * @return The transformed image in grayscale.
   * @throws IllegalArgumentException If any argument is null or the id is not contained in the
   *                                  map.
   */
  ImageInterface grayscale(String id)
      throws IllegalArgumentException;

  /**
   * Applies the sepia color transformation to the image associated with the id.
   *
   * @param id Id for the image to be transformed.
   * @return The transformed image in sepia.
   * @throws IllegalArgumentException If any argument is null or the id is not contained in the
   *                                  map.
   */
  ImageInterface sepia(String id)
      throws IllegalArgumentException;

  /**
   * Creates a checkerboard where each square is 1 pixel.
   *
   * @param rows    The amount of rows in the checkerboard
   * @param columns The amount of columns in the checkerboard.
   * @param colors  The two colors that the board will alternate through.
   * @return The generated image.
   * @throws IllegalArgumentException If the list of colors are null, there are more than 2 colors
   *                                  in the list, or the number of rows or columns is less than 2.
   */
  ImageInterface generateCheckerboard(int rows, int columns, List<IColor> colors)
      throws IllegalArgumentException;

  /**
   * Removes the given image associated with the string id from the model.
   * @param id String of image to remove.
   * @throws IllegalArgumentException If the string is null, or it is not contained in the model.
   */
  void removeImage(String id) throws IllegalArgumentException;
}

