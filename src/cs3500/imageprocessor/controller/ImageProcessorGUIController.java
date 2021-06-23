package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.model.MultiLayerProcessorModel;
import cs3500.imageprocessor.model.MultiLayerProcessorModelImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.view.IViewListener;
import cs3500.imageprocessor.view.ImageProcessorGUIView;
import cs3500.imageprocessor.view.ImageProcessorGUIViewImpl;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;


public class ImageProcessorGUIController implements ImageProcessorController, IViewListener {
  private final MultiLayerProcessorModel model;
  private final ImageProcessorGUIView view;
  private final String current;

  public ImageProcessorGUIController(MultiLayerProcessorModel model) {
    this.model = model;
    this.view = new ImageProcessorGUIViewImpl( this);
    this.current = null;
  }

  @Override
  public void startEditor() throws IllegalStateException {
    this.view.runGUI();
  }

  @Override
  public BufferedImage getCurrentImage() {
    return this.getTopmostVisibleLayer();
  }

  private BufferedImage getTopmostVisibleLayer() {
    for(Map.Entry<String, ImageInterface> layer : this.model.getLayers().entrySet()) {
      if (!this.model.getVisibility().contains(layer.getKey())) {
        return this.generateImage(layer.getValue());
      }
    }
    return new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
  }

  private BufferedImage generateImage(ImageInterface image) {
    int height = image.getPixels().size();
    int width = image.getPixels().get(0).size();

    BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int red = image.getPixels().get(i).get(j).getColor().getRed();
        int green = image.getPixels().get(i).get(j).getColor().getGreen();
        int blue = image.getPixels().get(i).get(j).getColor().getBlue();

        Color pixelColor = new Color(red, green, blue);
        outputImage.setRGB(j, i, pixelColor.getRGB());
      }
    }
    return outputImage;
  }


  @Override
  public void handleSaveLayerEvent() {

  }

  @Override
  public void handleLoadLayerEvent(String filename, String filetype, String layerName) {
    switch ((filetype)) {
      case "PNG":
      case "JPEG":
        try {
          addHandler(layerName, new ImageIOFileReader().readImageFromFile(filename));
        }
        catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
        break;
      case "PPM":
        try {
          addHandler(layerName, new PPMFileReader().readImageFromFile(filename));
        }
        catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
        break;
    }
  }

  private void addHandler(String fileName, ImageInterface image){
    try {
      this.model.addImage(fileName, image);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  private void renderHandler(String msg) {
    try {
      this.view.renderMessage(msg);
    }
    catch(IOException e) {
      throw new IllegalStateException();
    }
  }

  @Override
  public void handleSaveAllLayerEvent() {

  }

  @Override
  public void handleLoadAllLayerEvent() {

  }

  @Override
  public void handleBlurEvent() {

  }

  @Override
  public void handleSharpenEvent() {

  }

  @Override
  public void handleGrayscaleEvent() {

  }

  @Override
  public void handleSepiaEvent() {

  }

  @Override
  public void showEvent() {

  }

  @Override
  public void hideEvent() {

  }

  @Override
  public void removeLayerEvent() {

  }

  @Override
  public void setCurrentLayerEvent() {

  }

  @Override
  public void runScriptEvent() {

  }
}
