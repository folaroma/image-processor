import static org.junit.Assert.*;

import cs3500.imageprocessor.controller.ImageProcessorGUIController;
import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.MultiLayerFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.controller.filewriting.PNGImageIOWriter;
import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.MultiLayerProcessorModelImpl;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.view.ImageProcessorGUIViewImpl;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for the gui controller.
 */
public class ImageProcessorGUIControllerTest {

  MultiLayerProcessorModelImpl model;
  ImageProcessorGUIController controller;

  @Before
  public void init() {
    this.model = new MultiLayerProcessorModelImpl();
    this.controller = new ImageProcessorGUIController(this.model);
  }

  // Tests the current layer id when there is no current
  @Test
  public void getCurrentLayerIDNoneSet() {
    assertNull(this.controller.getCurrentLayerID(), null);
  }

  // tests the current layer id when there is a current.
  @Test
  public void getCurrentLayerIDCheckerboard() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    this.controller.setCurrentLayerEvent("a");
    assertEquals(this.controller.getCurrentLayerID(), "a");
  }

  // tests saving to a png
  @Test
  public void handleSaveLayerEventPNG() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    this.controller.handleSaveLayerEvent("test\\testreaderfiles\\a", "PNG");

    assertEquals(new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\a.png"),
        this.model.getImage("a"));
  }

  // tests saving to a ppm
  @Test
  public void handleSaveLayerEventPPM() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    this.controller.handleSaveLayerEvent("test\\testreaderfiles\\a", "PPM");

    assertEquals(new PPMFileReader().readImageFromFile("test\\testreaderfiles\\a.ppm"),
        this.model.getImage("a"));
  }

  // tests saving to a jpeg
  @Test
  public void handleSaveLayerEventJPEG() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.RED);
    this.controller.handleSaveLayerEvent("test\\testreaderfiles\\a", "JPEG");

    assertEquals(
        new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\a.jpeg").getPixels()
            .get(0).get(0).getColor(),
        new ColorImpl(65, 0, 0));
  }

  // handles adding a generated checkerboard to the model
  @Test
  public void handleCheckerboardEventFunctionalityTest() throws IOException {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);

    new PNGImageIOWriter().writeFile("test/testreaderfiles/blackwhitecheckerboard.png", this.model.getImage("a"));
    ImageInterface image = new ImageIOFileReader().readImageFromFile("test/testreaderfiles/blackwhitecheckerboard.png");

    assertEquals(
        image.getPixels().size(), 5);
    assertEquals(
        image.getPixels().get(0).size(), 5);
    assertEquals(image.getPixels().get(0).get(0).getColor(), new ColorImpl(0,0,0));
    assertEquals(image.getPixels().get(0).get(1).getColor(), new ColorImpl(255,255,255));
  }

  // handles saving the whole image in the controller
  @Test
  public void handleSaveAllLayerEventFunctionality() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.handleCheckerboardEvent("b", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.handleSaveAllLayerEvent("test\\testreaderfiles\\checkerboardMulti", "JPEG");

    assertEquals(
        new MultiLayerFileReader()
            .readImages("test\\testreaderfiles\\checkerboardMulti\\checkerboardMulti.txt")
            .get("a"), new ImageIOFileReader()
            .readImageFromFile("test\\testreaderfiles\\checkerboardMulti\\a.jpeg"));
  }

  // tests that the controller can load a multi image into the model
  @Test
  public void handleLoadAllLayerEvent() {
    this.controller.startEditor();
    this.controller
        .handleLoadAllLayerEvent("test\\testreaderfiles\\checkerboardMulti\\checkerboardMulti.txt");

    assertEquals(this.model.getLayers().size(), 2);
    assertEquals(this.model.getImage("a"), new ImageIOFileReader()
        .readImageFromFile("test\\testreaderfiles\\checkerboardMulti\\a.jpeg"));
    assertEquals(this.model.getImage("b"), new ImageIOFileReader()
        .readImageFromFile("test\\testreaderfiles\\checkerboardMulti\\b.jpeg"));
  }

  // tests bluring from the controller
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

  // tests sharpen from the controller
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

  // tests grayscale from the controller
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

  // tests sepia from the controller
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

  // tests showing a layer from the controller.
  @Test
  public void showEventFunctionalityTest() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");
    this.controller.hideEvent();
    this.controller.showEvent();
    assertFalse(this.model.getVisibility().contains("a"));
  }

  // tests hiding a layer from the controller
  @Test
  public void hideEventFunctionalityTest() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");
    this.controller.hideEvent();

    assertEquals(this.model.getVisibility().size(), 1);
  }

  // tests removing a layer from the controller
  @Test
  public void removeLayerEventFunctionality() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);

    assertEquals(this.model.getLayers().size(), 1);

    this.controller.setCurrentLayerEvent("a");
    this.controller.removeLayerEvent();

    assertEquals(this.model.getLayers().size(), 0);
  }

  // tests setting the current layer from the controller
  @Test
  public void setCurrentLayerEventFunctionality() {
    this.controller.startEditor();
    this.controller.handleCheckerboardEvent("a", 5, 5, Color.BLACK, Color.WHITE);
    this.controller.setCurrentLayerEvent("a");

    assertEquals(this.controller.getCurrentLayerID(), "a");
  }

  // tests running a script from the controller
  @Test
  public void runScriptEventFunctionality() {
    this.controller.startEditor();
    this.controller.runScriptEvent("test\\testreaderfiles\\testscript.txt");

    assertEquals(new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\grayboard.png")
        .getPixels().size(), 4);
  }
}