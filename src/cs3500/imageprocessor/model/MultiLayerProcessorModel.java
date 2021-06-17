package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.List;

public interface MultiLayerProcessorModel extends ImageProcessorModel {

  ImageInterface getLayer(int index);

  void showLayer(int index);

  void hideLayer(int index);

  void removeLayer(int index);

  void addLayer(ImageInterface layer);

  void addMultiLayer(List<ImageInterface> layers);

}
