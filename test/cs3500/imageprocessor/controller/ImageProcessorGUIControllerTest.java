package cs3500.imageprocessor.controller;

import static org.junit.Assert.*;

import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.MultiLayerProcessorModelImpl;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.view.ImageProcessorGUIViewImpl;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class ImageProcessorGUIControllerTest {

  MultiLayerProcessorModelImpl model;
  ImageProcessorGUIController controller;

  @Before
  public void init() {
    this.model = new MultiLayerProcessorModelImpl();
    this.controller = new ImageProcessorGUIController(this.model);
  }

  @Test
  public void startEditorFunctionalityTest() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    assertEquals(this.controller.getTopmostVisibleLayerID(), "a");
  }

  @Test
  public void getTopVisibleLayerCheckerboard() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);

    assertEquals(this.controller.getTopVisibleLayer().getRGB(0, 0), -16777216);
    assertEquals(this.controller.getTopVisibleLayer().getRGB(1, 0), -1);
    assertEquals(this.controller.getTopVisibleLayer().getWidth(), 5);
    assertEquals(this.controller.getTopVisibleLayer().getHeight(), 5);
  }

  @Test
  public void getTopVisibleLayerWhenNone() {
    this.controller.startEditor();

    assertEquals(this.controller.getTopVisibleLayer().getWidth(), 1);
    assertEquals(this.controller.getTopVisibleLayer().getHeight(), 1);
  }

  @Test
  public void getCurrentLayerIDNoneSet() {
    assertNull(this.controller.getCurrentLayerID(), null);
  }

  @Test
  public void getCurrentLayerIDCheckerboard() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    this.controller.setCurrentLayerEvent("a");
    assertEquals(this.controller.getCurrentLayerID(), "a");
  }

  @Test(expected = IllegalArgumentException.class)
  public void handleSaveLayerEventPNGError() {
    this.controller.startEditor();
    this.controller.handleSaveLayerEvent("a", "PNG");
  }

  @Test
  public void handleSaveLayerEventPNG() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    this.controller.handleSaveLayerEvent("a", "PNG");

    assertEquals(new ImageIOFileReader().readImageFromFile("a.png").getPixels().get(0).get(0).getColor().getRed(), 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void handleSaveLayerEventPPMError() {
    this.controller.startEditor();
    this.controller.handleSaveLayerEvent("a", "PPM");
  }

  @Test
  public void handleSaveLayerEventPPM() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    this.controller.handleSaveLayerEvent("a", "PPM");

    assertEquals(new PPMFileReader().readImageFromFile("a.ppm").getPixels().get(0).get(0).getColor().getRed(), 0);
  }

  @Test
  public void handleSaveLayerEventJPEG() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    this.controller.handleSaveLayerEvent("a", "JPEG");

    assertEquals(new ImageIOFileReader().readImageFromFile("a.jpeg").getPixels().get(0).get(0).getColor().getRed(), 65);
  }

  @Test(expected = IllegalArgumentException.class)
  public void handleSaveLayerEventJPEGError() {
    this.controller.startEditor();
    this.controller.handleSaveLayerEvent("a", "JPEG");
  }

  @Test(expected = IllegalArgumentException.class)
  public void getTopmostVisibleLayerIDError() {
    this.controller.startEditor();
    this.controller.getTopmostVisibleLayerID();
  }

  @Test
  public void getTopmostVisibleLayerIDFunctionalityTest() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    assertEquals(this.controller.getTopmostVisibleLayerID(), "a");
  }

  @Test
  public void handleLoadLayerEventPNG() {
    this.controller.startEditor();
    this.controller.handleLoadLayerEvent("res/checkerboard.png", "png", "checkerboard");

    assertEquals(this.controller.getTopmostVisibleLayerID(), "checkerboard");
  }

  @Test
  public void handleLoadLayerEventPPM() {
    this.controller.startEditor();
    this.controller.handleLoadLayerEvent("res/desert.ppm", "ppm", "desert");

    assertEquals(this.controller.getTopmostVisibleLayerID(), "desert");
  }

  @Test
  public void handleLoadLayerEventJPEG() {
    this.controller.startEditor();
    this.controller.handleLoadLayerEvent("res/checkerboard.jpeg", "jpeg", "checkerboard");

    assertEquals(this.controller.getTopmostVisibleLayerID(), "checkerboard");
  }

  @Test
  public void handleCheckerboardEvent() {
  }

  @Test
  public void handleSaveAllLayerEvent() {
  }

  @Test
  public void handleLoadAllLayerEvent() {
  }

  @Test
  public void handleBlurEvent() {
  }

  @Test
  public void handleSharpenEvent() {
  }

  @Test
  public void handleGrayscaleEvent() {
  }

  @Test
  public void handleSepiaEvent() {
  }

  @Test
  public void showEvent() {
  }

  @Test
  public void hideEvent() {
  }

  @Test
  public void removeLayerEvent() {
  }

  @Test
  public void setCurrentLayerEvent() {
  }

  @Test
  public void runScriptEvent() {
  }
}