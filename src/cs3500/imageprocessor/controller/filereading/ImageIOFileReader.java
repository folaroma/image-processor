package cs3500.imageprocessor.controller.filereading;

import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.PixelImpl;
import cs3500.imageprocessor.model.images.Position2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Class to represent a function object to read in file types supported by the ImageIO library.
 * Files are read in through a given file name.
 */
public class ImageIOFileReader implements IFileReader {

  @Override
  public ImageInterface readImageFromFile(String filename)
      throws IllegalArgumentException {
    if (filename == null) {
      throw new IllegalArgumentException("Filename cannot be null.");
    }
    try {
      File imageFile = new File(filename);
      BufferedImage image = ImageIO.read(imageFile);
      if (image == null) {
        throw new IllegalArgumentException("File could not be read.");
      }
      int imageHeight = image.getHeight();
      int imageWidth = image.getWidth();

      List<ArrayList<IPixel>> pixels = new ArrayList<>();

      for (int i = 0; i < imageHeight; i++) {
        ArrayList<IPixel> row = new ArrayList<>();
        for (int j = 0; j < imageWidth; j++) {
          Color pixelRGB = new Color(image.getRGB(j, i));
          row.add(new PixelImpl(new Position2D(j, i),
              new ColorImpl(pixelRGB.getRed(), pixelRGB.getGreen(), pixelRGB.getBlue())));
        }
        pixels.add(row);
      }

      return new ImageImpl(pixels);


    } catch (IOException e) {
      throw new IllegalArgumentException("File does not exist.");
    }

  }


}
