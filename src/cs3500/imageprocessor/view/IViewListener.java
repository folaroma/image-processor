package cs3500.imageprocessor.view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.List;

public interface IViewListener {

  BufferedImage getTopVisibleLayer();

  String getCurrentLayerID();

  void handleSaveLayerEvent(String fileName, String fileType);

  void handleLoadLayerEvent(String fileName, String fileType, String layerName);

  void handleCheckerboardEvent(String layerName, int rows, int columns, Color color1, Color color2);

  void handleSaveAllLayerEvent(String fileName, String fileType);

  List<String> handleLoadAllLayerEvent(String filename);

  void handleBlurEvent();

  void handleSharpenEvent();

  void handleGrayscaleEvent();

  void handleSepiaEvent();

  void showEvent();

  void hideEvent();

  void removeLayerEvent();

  void setCurrentLayerEvent(String layerID);

  void runScriptEvent(String filename);

  String getTopmostVisibleLayerID();


}
