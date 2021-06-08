package cs3500.imageprocessor.model;

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

  public ImageProcessorModelImpl(String filename) throws IllegalArgumentException {
    this();
    images.putIfAbsent(filename, ImageUtil.readPPM(filename));
  }

  public ImageProcessorModelImpl(int size, int numPiles, List<IColor> colors) {
    this();
    images.putIfAbsent("Checkerboard", this.createCheckerboard(size, numPiles, colors));
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
  public ImageInterface filterBlur(ImageInterface image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    List<List<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    double[][] blur = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
    List<ArrayList<IPixel>> filteredPixels = filtered(imagePixels, blur, image);

    return new ImageImpl(filteredPixels);

  }

  @Override
  public ImageInterface filterSharpen(ImageInterface image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    List<List<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    double[][] sharpen = {{-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1, 0.25, -0.12},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}};
    List<ArrayList<IPixel>> filteredPixels = filtered(imagePixels, sharpen, image);

    return new ImageImpl(filteredPixels);

  }

  private List<ArrayList<IPixel>> filtered(List<List<IPixel>> pixels, double[][] matrix,
      ImageInterface image) {
    List<ArrayList<IPixel>> filteredPixels = new ArrayList<>();
    for (int i = 0; i < pixels.size(); i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < pixels.get(0).size(); j++) {
        row.add(image.filter(pixels.get(i).get(j), matrix));
      }
      filteredPixels.add(row);
    }

    return filteredPixels;

  }

  @Override
  public ImageInterface colorMonochrome(ImageInterface image) {
    List<ArrayList<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    double[][] monochrome = {{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};

    return new ImageImpl(transform(image, imagePixels, monochrome));

  }

  private List<ArrayList<IPixel>> transform(ImageInterface image, List<ArrayList<IPixel>> imagePixels, double[][] matrix) {
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();

    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        row.add(image.colorTransform(p, matrix));
      }
      updatedPixels.add(row);
    }

    return updatedPixels;

  }

  @Override
  public ImageInterface colorSepia(ImageInterface image) {
    List<ArrayList<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    double[][] sepia = {{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};

    return new ImageImpl(transform(image, imagePixels, sepia));

  }

  @Override
  public ImageInterface createCheckerboard(int width, int height, List<IColor> colors) {
    List<ArrayList<IPixel>> pixels = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        row.add(new PixelImpl(new Position2D(j, i), alternateColors(colors, i, j)));
      }
      pixels.add(row);
    }

    return new ImageImpl(pixels);

  }

  @Override
  public void exportImage(String filename, String id) throws IOException, IllegalArgumentException {
    if (id == null || filename == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    String ppmString = this.generatePPM(id);
    FileOutputStream file = new FileOutputStream(filename);
    file.write(ppmString.getBytes());
    file.close();
  }

  private String generatePPM(String id) {
    StringBuilder ppmString = new StringBuilder().append("P3\n")
        .append(this.getImage(id).getPixels().get(0).size()).append(" ")
        .append(this.getImage(id).getPixels().size()).append("\n255\n");
    for (int i = 0; i < this.getImage(id).getPixels().size(); i++) {
      for (int j = 0; j < this.getImage(id).getPixels().get(0).size(); j++) {
        IPixel currentPixel = this.getImage(id).getPixels().get(i).get(j);
        ppmString.append(currentPixel.getColor().getRed()).append(" ")
            .append(currentPixel.getColor().getGreen()).append(" ")
            .append(currentPixel.getColor().getBlue()).append(" ");
      }
      ppmString.append("\n");
    }
    return ppmString.toString();
  }

  private IColor alternateColors(List<IColor> colors, int rows, int columns) {
    int i = columns % colors.size() + rows % colors.size();
    if (i > 1) {
      i = 0;
    }

    return colors.get(i);
  }

}
