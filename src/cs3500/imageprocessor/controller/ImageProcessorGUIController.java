package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.controller.filewriting.JPEGImageIOWriter;
import cs3500.imageprocessor.controller.filewriting.MultiLayerImageWriter;
import cs3500.imageprocessor.controller.filewriting.PNGImageIOWriter;
import cs3500.imageprocessor.controller.filewriting.PPMFileWriter;
import cs3500.imageprocessor.model.MultiLayerProcessorModel;
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
  private String current;

  public ImageProcessorGUIController(MultiLayerProcessorModel model) {
    this.model = model;
    this.view = new ImageProcessorGUIViewImpl(this);
    this.current = null;
  }

  @Override
  public void startEditor() throws IllegalStateException {
    this.view.runGUI();
  }

  @Override
  public BufferedImage getTopVisibleLayer() {
    for (Map.Entry<String, ImageInterface> layer : this.model.getLayers().entrySet()) {
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
  public String getCurrentLayerID() {
    return this.current;
  }

  @Override
  public void handleSaveLayerEvent(String filename, String filetype) {
    switch ((filetype)) {
      case "PNG":
        try {
          new PNGImageIOWriter().writeFile(filename + ".png",
              this.model.getImage(getTopmostVisibleLayerID()));
        } catch (IOException e) {
          throw new IllegalStateException();
        }
        break;
      case "JPEG":
        try {
          new JPEGImageIOWriter()
              .writeFile(filename + ".jpeg", this.model.getImage(getTopmostVisibleLayerID()));
        } catch (IOException e) {
          throw new IllegalStateException();
        }
        break;
      case "PPM":
        try {
          new PPMFileWriter()
              .writeFile(filename + ".ppm", this.model.getImage(getTopmostVisibleLayerID()));
        } catch (IOException e) {
          throw new IllegalStateException();
        }
        break;
    }
  }

  @Override
  public String getTopmostVisibleLayerID() {
    for (Map.Entry<String, ImageInterface> item : this.model.getLayers().entrySet()) {
      if (!this.model.getVisibility().contains(item.getKey())) {
        return item.getKey();
      }
    }
    throw new IllegalArgumentException("No visible layers.");
  }

  @Override
  public void handleLoadLayerEvent(String filename, String filetype, String layerName) {
    switch ((filetype)) {
      case "PNG":
      case "JPEG":
        try {
          addHandler(layerName, new ImageIOFileReader().readImageFromFile(filename));
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
        break;
      case "PPM":
        try {
          addHandler(layerName, new PPMFileReader().readImageFromFile(filename));
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
        break;
    }
  }

  private void addHandler(String fileName, ImageInterface image) {
    try {
      this.model.addImage(fileName, image);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }


  private void renderHandler(String msg) {
    try {
      this.view.renderMessage(msg);
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

  @Override
  public void handleSaveAllLayerEvent(String fileName, String fileType) {
    try {
      new MultiLayerImageWriter()
          .writeFile(fileName, fileType, this.model.getLayers(),
              this.model.getVisibility());
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    } catch (IOException io) {
      throw new IllegalStateException();
    }

  }

  @Override
  public void handleLoadAllLayerEvent() {

  }

  @Override
  public void handleBlurEvent(String current) {
    try {
      this.model.replaceImage(current, this.model.blur(current));
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  @Override
  public void handleSharpenEvent(String current) {
    try {
      this.model.replaceImage(current, this.model.sharpen(current));
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  @Override
  public void handleGrayscaleEvent(String current) {
    try {
      this.model.replaceImage(current, this.model.grayscale(current));
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  @Override
  public void handleSepiaEvent(String current) {
    try {
      this.model.replaceImage(current, this.model.sepia(current));
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  @Override
  public void showEvent() {
    try {
      this.model.showLayer(current);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  @Override
  public void hideEvent() {
    try {
      this.model.hideLayer(current);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  @Override
  public void removeLayerEvent() {
    try {
      this.model.removeImage(this.current);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  @Override
  public void setCurrentLayerEvent(String layerID) {
    if (this.model.getLayers().containsKey(layerID)) {
      this.current = layerID;
    } else {
      renderHandler("This layer does not exist");
    }
  }

  @Override
  public void runScriptEvent() {

  }

  @Override
  public boolean noneHidden() {
    return this.model.getVisibility().isEmpty();
  }

  @Override
  public boolean layerExists(String layerID) {
    return this.model.getLayers().containsKey(layerID);
  }


}
