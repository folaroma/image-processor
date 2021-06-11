package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.colorTransformations.IColorTransformation;
import cs3500.imageprocessor.controller.fileReading.IFileReader;
import cs3500.imageprocessor.controller.fileWriting.IImageFileWriter;
import cs3500.imageprocessor.model.filters.IFilter;
import cs3500.imageprocessor.model.imageGenerating.IImageGenerator;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.util.HashMap;
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

  /**
   * Convenience constructor to create a model with a programmatically generated image in the map.
   *
   * @param id        Id to be associated with the image.
   * @param generator Image generator function object to use.
   * @throws IllegalArgumentException If any argument is null or the generator cannot properly
   *                                  generate the image.
   */
  public ImageProcessorModelImpl(String id, IImageGenerator generator)
      throws IllegalArgumentException {
    this();
    if (id == null || generator == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    images.putIfAbsent(id, generator.generateImage());
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
  public ImageInterface filterImage(String id, IFilter filter) throws IllegalArgumentException {
    if (id == null || filter == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    return filter.applyFilter(this.getImage(id));
  }

  @Override
  public ImageInterface transformImage(String id, IColorTransformation transformation) {
    if (id == null || transformation == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    return transformation.applyTransformation(this.getImage(id));
  }

  @Override
  public ImageInterface generateImage(IImageGenerator generator) throws IllegalArgumentException {
    if(generator == null) {
      throw new IllegalArgumentException("Generator cannot be null.");
    }
    return generator.generateImage();
  }


}
