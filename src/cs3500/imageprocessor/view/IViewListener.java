package cs3500.imageprocessor.view;

import java.awt.image.BufferedImage;

public interface IViewListener {

  BufferedImage getCurrentImage();

  BufferedImage loadImage();

  void handleSaveLayerEvent();

  void handleLoadLayerEvent();

  void handleSaveAllLayerEvent();

  void handleLoadAllLayerEvent();

  void handleBlurEvent();

  void handleSharpenEvent();

  void handleGrayscaleEvent();

  void handleSepiaEvent();

  void showEvent();

  void hideEvent();

  void removeLayerEvent();

  void setCurrentLayerEvent();

  void runScriptEvent();
}
