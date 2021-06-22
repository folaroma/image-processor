package cs3500.imageprocessor.view;

import java.awt.image.BufferedImage;

public interface IViewListener {

  BufferedImage getCurrentImage();

  void handleSaveLayerEvent();

  void handleLoadLayerEvent();

  void handleSaveAllLayerEvent();

  void handleLoadAllLayerEvent();
}
