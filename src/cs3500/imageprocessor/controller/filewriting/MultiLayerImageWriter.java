package cs3500.imageprocessor.controller.filewriting;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Function object to facilitate the writing of txt files and images representing a multi layer
 * image.
 */
public class MultiLayerImageWriter implements IMultiLayerImageWriter {

  @Override
  public void writeFile(String filename, String type, Map<String, ImageInterface> layers,
      List<String> visibility) throws IllegalArgumentException, IOException {
    if (filename == null || type == null || layers == null || visibility == null) {
      throw new IllegalArgumentException("Null parameters.");
    }
    StringBuilder output = new StringBuilder().append(type + "\n");
    for (Map.Entry<String, ImageInterface> item : layers.entrySet()) {
      String imageFilename = "";
      switch (type) {
        case "png":
          imageFilename = "res\\" + filename + "\\" + item.getKey() + ".png";
          new PNGImageIOWriter().writeFile(imageFilename, item.getValue());
          output.append(imageFilename).append(" ").append(item.getKey()).append(" ")
              .append(this.visibilityStatus(item.getKey(), visibility));
          break;
        case "jpeg":
          imageFilename = "res\\" + filename + "\\" + item.getKey() + ".jpeg";
          new JPEGImageIOWriter().writeFile(imageFilename, item.getValue());
          output.append(imageFilename).append(" ").append(item.getKey()).append(" ")
              .append(this.visibilityStatus(item.getKey(), visibility));
          break;
        case "ppm":
          imageFilename = "res\\" + filename + "\\" + item.getKey() + ".ppm";
          new PPMFileWriter().writeFile(imageFilename, item.getValue());
          output.append(imageFilename).append(" ").append(item.getKey()).append(" ")
              .append(this.visibilityStatus(item.getKey(), visibility));
          break;
        default:
          throw new IllegalArgumentException("Invalid output type.");
      }
      output.append("\n");
    }
    FileOutputStream stream = new FileOutputStream(filename + ".txt");
    stream.write(output.toString().getBytes());
    stream.close();


  }

  /**
   * Sets the status of visiblity for the image.
   *
   * @param key        Id for the image.
   * @param visibility List of invisible image ids.
   * @return String representing the visibility status.
   */
  private String visibilityStatus(String key, List<String> visibility) {
    if (visibility.contains(key)) {
      return "invisible";
    } else {
      return "visible";
    }
  }
}
