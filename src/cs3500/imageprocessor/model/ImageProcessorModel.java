package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.fileWriting.IImageFileWriter;
import cs3500.imageprocessor.model.filters.IFilter;
import cs3500.imageprocessor.model.images.IColor;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.util.List;

/**
 * Interface to represent an image processing program.
 */
public interface ImageProcessorModel {

  void replaceImage(String id, ImageInterface image) throws IllegalArgumentException;

  void addImage(String id, ImageInterface image) throws IllegalArgumentException;

  ImageInterface getImage(String id) throws IllegalArgumentException;

  ImageInterface filterImage(String id, IFilter filter) throws IllegalArgumentException;

  ImageInterface colorMonochrome(ImageInterface image);

  ImageInterface colorSepia(ImageInterface image);

  void exportImage(String filename, String id, IImageFileWriter writer)
      throws IOException, IllegalArgumentException;

}
