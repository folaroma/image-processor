package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.view.IViewListener;
import java.awt.image.BufferedImage;

public class ImageProcessorGUIController implements ImageProcessorController, IViewListener {

  @Override
  public void startEditor() throws IllegalStateException {

  }

  @Override
  public BufferedImage getCurrentImage() {
    return null;
  }

  @Override
  public void handleSaveLayerEvent() {

  }

  @Override
  public void handleLoadLayerEvent() {

  }

  @Override
  public void handleSaveAllLayerEvent() {

  }

  @Override
  public void handleLoadAllLayerEvent() {

  }
}
