package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.colorTransformations.IColorTransformation;
import cs3500.imageprocessor.model.fileReading.IFileReader;
import cs3500.imageprocessor.model.fileWriting.IImageFileWriter;
import cs3500.imageprocessor.model.filters.IFilter;
import cs3500.imageprocessor.model.imageGenerating.IImageGenerator;
import cs3500.imageprocessor.model.images.IColor;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.PixelImpl;
import cs3500.imageprocessor.model.images.Position2D;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class representing a simple image processing program model. Images are represented using the
 * ImageInterface implementation, and images can either be created programmatically or read in
 * through PPM format.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {

  private final Map<String, ImageInterface> images;

  public ImageProcessorModelImpl() throws IllegalArgumentException {
    this.images = new HashMap<>();
  }

  public ImageProcessorModelImpl(String filename, IFileReader reader)
      throws IllegalArgumentException {
    this();
    if (filename == null || reader == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    images.putIfAbsent(filename, reader.readImageFromFile(filename));
  }

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
  public void exportImage(String filename, String id, IImageFileWriter writer)
      throws IOException, IllegalArgumentException {
    if (id == null || filename == null || writer == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    writer.writeFile(filename, this.getImage(id));
  }


}
