package cs3500.imageprocessor.view;

import java.awt.image.BufferedImage;

public interface IViewListener {

  BufferedImage getCurrentImage();

  BufferedImage loadImage();

  void handleSaveLayerEvent();

  void handleLoadLayerEvent();

  void handleSaveAllLayerEvent();

  void handleLoadAllLayerEvent();
}
