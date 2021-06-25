package cs3500.imageprocessor.controller;

import static org.junit.Assert.*;

import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.MultiLayerFileReader;
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
  public void handleCheckerboardEventFunctionalityTest() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);

    assertEquals(this.controller.getTopVisibleLayer().getHeight(), 5);
    assertEquals(this.controller.getTopVisibleLayer().getWidth(), 5);
    assertEquals(this.controller.getTopVisibleLayer().getRGB(0, 0), -16777216);
    assertEquals(this.controller.getTopVisibleLayer().getRGB(1, 0), -1);
  }

  @Test
  public void handleSaveAllLayerEventFunctionality() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.handleCheckerboardEvent("b", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.handleSaveAllLayerEvent("res/checkerboardMulti", "JPEG");

    assertEquals(new MultiLayerFileReader().readImages("res\\checkerboardMulti\\res\\checkerboardMulti.txt").get("a"), new ImageIOFileReader().readImageFromFile("res/checkerboardMulti/a.jpeg"));
  }

  /*
  @Test
  public void handleLoadAllLayerEvent() {
    this.controller.startEditor();
    this.controller.handleLoadAllLayerEvent("res/bugs/bugs.txt");

    assertEquals(this.model.getLayers().size(), 2);
  }
   */

  @Test
  public void handleBlurEvent() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");
    this.controller.handleBlurEvent();

    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getRed(), 62);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getGreen(), 62);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getBlue(), 62);

    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getRed(), 93);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getGreen(), 93);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getBlue(), 93);
  }

  @Test
  public void handleSharpenEvent() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");
    this.controller.handleSharpenEvent();

    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getRed(), 64);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getGreen(), 64);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getBlue(), 64);

    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getRed(), 255);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getGreen(), 255);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getBlue(), 255);
  }

  @Test
  public void handleGrayscaleEvent() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");
    this.controller.handleGrayscaleEvent();

    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getRed(), 0);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getBlue(), 0);

    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getRed(), 254);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getGreen(), 254);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getBlue(), 254);
  }

  @Test
  public void handleSepiaEvent() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");
    this.controller.handleSepiaEvent();

    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getRed(), 0);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getGreen(), 0);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(0).getColor().getBlue(), 0);

    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getRed(), 255);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getGreen(), 255);
    assertEquals(this.model.getImage("a").getPixels().get(0).get(1).getColor().getBlue(), 238);
  }

  @Test
  public void showEventFunctionalityTest() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");
    this.controller.hideEvent();
    this.controller.showEvent();
    assertEquals(this.controller.getTopmostVisibleLayerID(), "a");
  }

  @Test
  public void hideEventFunctionalityTest() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");
    this.controller.hideEvent();

    assertEquals(this.model.getVisibility().size(), 1);
  }

  @Test
  public void removeLayerEventFunctionality() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);

    assertEquals(this.model.getLayers().size(), 1);

    this.controller.setCurrentLayerEvent("a");
    this.controller.removeLayerEvent();

    assertEquals(this.model.getLayers().size(), 0);
  }

  @Test
  public void setCurrentLayerEventFunctionality() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");

    assertEquals(this.controller.getCurrentLayerID(), "a");
  }

  @Test
  public void runScriptEventFunctionality() {
    this.controller.startEditor();
    this.controller.runScriptEvent("res/script1.txt");

    assertEquals(new PPMFileReader().readImageFromFile("grayboard.ppm").getPixels().size(), 5);
  }
}