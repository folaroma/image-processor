package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.List;
import java.util.Map;

public interface MultiLayerProcessorModel extends ImageProcessorModel {

  void showLayer(String id);

  void hideLayer(String id);

  void addMultiLayer(Map<String, ImageInterface> images, List<String> invisibleLayers);

  List<String> getVisibility();

  Map<String, ImageInterface> getLayers();

}
