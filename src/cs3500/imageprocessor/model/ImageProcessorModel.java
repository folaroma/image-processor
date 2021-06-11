package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.colorTransformations.IColorTransformation;
import cs3500.imageprocessor.controller.fileWriting.IImageFileWriter;
import cs3500.imageprocessor.model.filters.IFilter;
import cs3500.imageprocessor.model.imageGenerating.IImageGenerator;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;

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
   * Applies the desired filter to the image associated with the id.
   *
   * @param id     Id for image to filter.
   * @param filter Filter to apply on the image.
   * @return The filtered image.
   * @throws IllegalArgumentException If any argument is null or the id is invalid.
   */
  ImageInterface filterImage(String id, IFilter filter) throws IllegalArgumentException;

  /**
   * Applies the desired color transformation to the image associated with the id.
   *
   * @param id             Id for the image to be transformed.
   * @param transformation Transformation to apply to the image.
   * @return The transformed image.
   * @throws IllegalArgumentException If any argument is null or the id is not contained in the
   *                                  map.
   */
  ImageInterface transformImage(String id, IColorTransformation transformation)
      throws IllegalArgumentException;

  /**
   * Creates an image from the given IImageGenerator.
   * @param generator Generator to use to programmatically create an image.
   * @return The generated image.
   * @throws IllegalArgumentException If the generator is null or fails to generate.
   */
  ImageInterface generateImage(IImageGenerator generator) throws IllegalArgumentException;
}

