package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.model.MultiLayerProcessorModel;
import cs3500.imageprocessor.model.MultiLayerProcessorModelImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.view.IViewListener;
import cs3500.imageprocessor.view.ImageProcessorGUIView;
import cs3500.imageprocessor.view.ImageProcessorGUIViewImpl;
import java.awt.Color;
import java.awt.image.BufferedImage;
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
    return new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB);
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
  public BufferedImage loadImage() {
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
