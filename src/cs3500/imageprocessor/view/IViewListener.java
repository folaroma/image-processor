package cs3500.imageprocessor.view;

import java.awt.image.BufferedImage;

public interface IViewListener {

  BufferedImage getCurrentImage();

  void handleSaveLayerEvent();

  void handleLoadLayerEvent(String filename, String filetype, String layerName);

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
