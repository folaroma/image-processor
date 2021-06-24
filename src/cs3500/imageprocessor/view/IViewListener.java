package cs3500.imageprocessor.view;

import java.awt.image.BufferedImage;

public interface IViewListener {

  BufferedImage getTopVisibleLayer();

  String getCurrentLayerID();

  void handleSaveLayerEvent(String fileName, String fileType);

  void handleLoadLayerEvent(String fileName, String fileType, String layerName);

  void handleSaveAllLayerEvent(String fileName, String fileType);

  void handleLoadAllLayerEvent();

  void handleBlurEvent(String current);

  void handleSharpenEvent(String current);

  void handleGrayscaleEvent(String current);

  void handleSepiaEvent(String current);

  void showEvent();

  void hideEvent();

  void removeLayerEvent();

  void setCurrentLayerEvent(String layerID);

  void runScriptEvent();

  boolean noneHidden();

  String getTopmostVisibleLayerID();

  boolean layerExists(String layerID);

}
