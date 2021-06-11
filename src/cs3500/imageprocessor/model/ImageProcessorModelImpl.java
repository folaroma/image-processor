package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.colorTransformations.GrayscaleTransformation;
import cs3500.imageprocessor.model.colorTransformations.IColorTransformation;
import cs3500.imageprocessor.controller.fileReading.IFileReader;
import cs3500.imageprocessor.controller.fileWriting.IImageFileWriter;
import cs3500.imageprocessor.model.colorTransformations.SepiaTransformation;
import cs3500.imageprocessor.model.filters.FilterBlur;
import cs3500.imageprocessor.model.filters.FilterSharpen;
import cs3500.imageprocessor.model.filters.IFilter;
import cs3500.imageprocessor.model.imageGenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.imageGenerating.IImageGenerator;
import cs3500.imageprocessor.model.images.IColor;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing a simple image processing program model. Images are represented using the
 * ImageInterface implementation, and images can either be created programmatically or read in
 * through a file.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {

  private final Map<String, ImageInterface> images;

  /**
   * Default constructor for the model. Sets the map as an empty hashmap.
   */
  public ImageProcessorModelImpl() {
    this.images = new HashMap<>();
  }


  @Override
  public void replaceImage(String id, ImageInterface image) throws IllegalArgumentException {
    if (id == null || image == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }

    if (!this.images.containsKey(id)) {
      throw new IllegalArgumentException("No such image is contained");
    }

    this.images.replace(id, image);

  }

  @Override
  public void addImage(String id, ImageInterface image) throws IllegalArgumentException {
    if (id == null || image == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }

    if (this.images.containsKey(id)) {
      throw new IllegalArgumentException("Image with id already exits.");
    }

    this.images.putIfAbsent(id, image);

  }

  @Override
  public ImageInterface getImage(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("ID cannot be null.");
    }

    if (!this.images.containsKey(id)) {
      throw new IllegalArgumentException("Image with id does not exist.");
    }

    return this.images.get(id);
  }

  @Override
  public ImageInterface blur(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    return new FilterBlur().applyFilter(this.getImage(id));
  }

  @Override
  public ImageInterface sharpen(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    return new FilterSharpen().applyFilter(this.getImage(id));
  }

  @Override
  public ImageInterface grayscale(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    return new GrayscaleTransformation().applyTransformation(this.getImage(id));
  }

  @Override
  public ImageInterface sepia(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    return new SepiaTransformation().applyTransformation(this.getImage(id));
  }

  @Override
  public ImageInterface generateCheckerboard(int rows, int columns, List<IColor> colors)
      throws IllegalArgumentException {
    if (colors == null) {
      throw new IllegalArgumentException("Colors cannot be null.");
    }
    if (rows < 2 || columns < 2) {
      throw new IllegalArgumentException("There must be at least two columns and two rows in a checkerboard.");
    }
    return new CheckerboardGenerator(rows, columns, colors).generateImage();
  }




}
