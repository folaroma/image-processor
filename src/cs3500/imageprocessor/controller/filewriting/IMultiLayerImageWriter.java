package cs3500.imageprocessor.controller.filewriting;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IMultiLayerImageWriter {

  void writeFile(String filename, String type, Map<String, ImageInterface> layers, List<String> visibility)
      throws IllegalArgumentException, IOException;

}
