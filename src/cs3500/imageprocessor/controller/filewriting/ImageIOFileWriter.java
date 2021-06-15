package cs3500.imageprocessor.controller.filewriting;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class ImageIOFileWriter implements IImageFileWriter {


  // TODO: check for type validity
  @Override
  public void writeFile(String filename, ImageInterface image)
      throws IOException, IllegalArgumentException {
    if (filename == null || image == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    if (!filename.contains(".") || filename.indexOf(".") == filename.length() - 1) {
      throw new IllegalArgumentException("Filename is does not have a type extension");
    }

    String type = filename.substring(filename.indexOf(".") + 1);
    if (!Arrays.asList(ImageIO.getWriterFileSuffixes()).contains(type)) {
      throw new IllegalArgumentException("File extension is not a valid file suffix for an image.");
    }

    FileOutputStream output = new FileOutputStream(filename);

    int height = image.getPixels().size();
    int width = image.getPixels().get(0).size();

    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image.getPixels().get(i).get(j).getColor().getRed();
        int green = image.getPixels().get(i).get(j).getColor().getGreen();
        int blue = image.getPixels().get(i).get(j).getColor().getBlue();

        Color pixelColor = new Color(red, green, blue);
        outputImage.setRGB(j, i, pixelColor.getRGB());
      }
    }

    ImageIO.write(outputImage, type, output);
    output.close();

  }
}
