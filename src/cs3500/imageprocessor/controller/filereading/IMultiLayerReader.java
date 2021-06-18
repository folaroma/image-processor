package cs3500.imageprocessor.controller.filereading;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.List;
import java.util.Map;

public interface IMultiLayerReader {

  Map<String, ImageInterface> readImages(String filename) throws IllegalArgumentException;

  List<String> readVisibility(String filename) throws IllegalArgumentException;

}
