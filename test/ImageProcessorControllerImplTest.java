import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cs3500.imageprocessor.controller.ImageProcessorController;
import cs3500.imageprocessor.controller.ImageProcessorControllerImpl;
import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.MultiLayerFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.model.ImageProcessorModelImpl;
import cs3500.imageprocessor.model.MultiLayerProcessorModelImpl;
import cs3500.imageprocessor.model.imagegenerating.CheckerboardGenerator;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing class for the ccontroller.
 */
public class ImageProcessorControllerImplTest {

  private Appendable out;
  private MultiLayerProcessorModelImpl model;

  @Before
  public void init() {
    Readable stringReader = new StringReader("create first res/desert ppm");
    this.out = new StringBuilder();
    this.model = new MultiLayerProcessorModelImpl();
    ImageProcessorController controller = new ImageProcessorControllerImpl(this.model, stringReader,
        this.out);
  }

  // CONSTRUCTOR TESTS

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------
       | Constructor Exception Tests |
        -----------------------------
  */


  // testing the controller constructor for a null model exception
  @Test(expected = IllegalArgumentException.class)
  public void imageProcessorControllerModelNullEx() {
    Readable stringReader = new StringReader(" ");
    Appendable out = new StringBuilder();
    new ImageProcessorControllerImpl(null, stringReader, out);
  }

  // testing the controller constructor for a null input exception
  @Test(expected = IllegalArgumentException.class)
  public void imageProcessorControllerInputNullEx() {
    MultiLayerProcessorModelImpl model = new MultiLayerProcessorModelImpl();
    Appendable out = new StringBuilder();
    new ImageProcessorControllerImpl(model, null, out);
  }

  // testing the controller constructor for a null output exception
  @Test(expected = IllegalArgumentException.class)
  public void imageProcessorControllerOutputNullEx() {
    MultiLayerProcessorModelImpl model = new MultiLayerProcessorModelImpl();
    Readable stringReader = new StringReader(" ");
    new ImageProcessorControllerImpl(model, stringReader, null);
  }

  // testing the controller constructor for when all params are null
  @Test(expected = IllegalArgumentException.class)
  public void imageProcessorControllerAllNullEx() {
    new ImageProcessorControllerImpl(null, null, null);
  }

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------------------------------
       | startEditor Exception and Message Tests |
        -----------------------------------------
  */


  // scanner no next exception
  @Test(expected = IllegalStateException.class)
  public void scannerNoNextEx() {
    Readable noIn = new StringReader("");
    ImageProcessorController newController = new ImageProcessorControllerImpl(
        new MultiLayerProcessorModelImpl(), noIn, new StringBuilder());
    newController.startEditor();
  }

  // renderHandler illegal state exception
  @Test(expected = IllegalStateException.class)
  public void renderHandlerBadOutEx() {
    Readable in = new StringReader(" ");
    ImageProcessorController newController = new ImageProcessorControllerImpl(
        new MultiLayerProcessorModelImpl(), in, new StringBuilder());
    newController.startEditor();
  }

  // testing message returned when inputting spaces then \n then a command
  @Test
  public void spaceBeforeCommandMessage() {
    Readable spaceIn = new StringReader("     \ncreate first add res/desert.ppm ppm");
    ImageProcessorController spaceController = new ImageProcessorControllerImpl(
        this.model, spaceIn, this.out);
    spaceController.startEditor();

    assertEquals(this.out.toString(), "Avoid putting spaces before commands.\n\n");
  }

  // testing message returned when invalid file path given in a create command
  @Test
  public void invalidFilePathCreate() {
    Readable invalidFileIn = new StringReader("create first add asd.ppm ppm");
    ImageProcessorController invalidFileController = new ImageProcessorControllerImpl(
        this.model, invalidFileIn, this.out);
    invalidFileController.startEditor();

    assertEquals(this.out.toString(), "File could not be found\n");
  }

  // testing message returned when third param for create is not add
  @Test
  public void invalidThirdParamCreate() {
    Readable invalidThirdParam = new StringReader("create first ad res/desert.ppm ppm");
    ImageProcessorController invalidThirdParamController = new ImageProcessorControllerImpl(
        this.model, invalidThirdParam, this.out);
    invalidThirdParamController.startEditor();

    assertEquals(this.out.toString(), "Invalid create command syntax.\n");
  }

  // testing message returned when create command length is not 5 nor 11
  @Test
  public void invalidLengthCreate() {
    Readable invalidLength = new StringReader("create first ad res/desert.ppm ppm ad");
    ImageProcessorController invalidLengthController = new ImageProcessorControllerImpl(
        this.model, invalidLength, this.out);
    invalidLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid create command syntax.\n");
  }

  // testing message returned when creating a checkerboard with invalid checkerboard params
  @Test
  public void invalidCheckerboardParams() {
    Readable invalidCheckerboard = new StringReader(
        "create first checkerboard 5 4 0 0 0 256 255 255");
    ImageProcessorController invalidCheckerboardController = new ImageProcessorControllerImpl(
        this.model, invalidCheckerboard, this.out);
    invalidCheckerboardController.startEditor();

    assertEquals(this.out.toString(), "Color value must be in range of 0-255\n"
        + "Arguments cannot be null.\n");
  }

  // testing message returned when creating a checkerboard with invalid syntax
  @Test
  public void invalidCheckerboardSyntax() {
    Readable invalidCheckerboardSyntax = new StringReader(
        "create first checkerboard ad 4 0 0 0 255 255 255");
    ImageProcessorController invalidCheckerboardSyntaxController = new ImageProcessorControllerImpl(
        this.model, invalidCheckerboardSyntax, this.out);
    invalidCheckerboardSyntaxController.startEditor();

    assertEquals(this.out.toString(), "Invalid checkerboard syntax.\n");
  }

  // testing remove command with invalid layer ID
  @Test
  public void invalidRemoveLayer() {
    Readable invalidRemove = new StringReader("remove first");
    ImageProcessorController invalidRemoveController = new ImageProcessorControllerImpl(
        this.model, invalidRemove, this.out);
    invalidRemoveController.startEditor();

    assertEquals(this.out.toString(), "Layer with id does not exist.\n");
  }

  // testing remove command that is not length of 2
  @Test
  public void invalidRemoveLength() {
    Readable invalidRemoveLength = new StringReader("remove first first");
    ImageProcessorController invalidRemoveLengthController = new ImageProcessorControllerImpl(
        this.model, invalidRemoveLength, this.out);
    invalidRemoveLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid remove command syntax.\n");
  }

  // testing current command with a layer that does not exist
  @Test
  public void currentLayerNonexistent() {
    Readable nonExistentLayer = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent second");
    ImageProcessorController nonExistentLayerController = new ImageProcessorControllerImpl(
        this.model, nonExistentLayer, this.out);
    nonExistentLayerController.startEditor();

    assertEquals(this.out.toString(), "This layer does not exist.\n");
  }

  // testing current command when no layers have been made yet
  @Test
  public void noLayerCreatedCurrent() {
    Readable noLayersCurrent = new StringReader("current first");
    ImageProcessorController noLayersCurrentController = new ImageProcessorControllerImpl(
        this.model, noLayersCurrent, this.out);
    noLayersCurrentController.startEditor();

    assertEquals(this.out.toString(), "No layers created.\n");
  }

  // testing current command with incorrect length
  @Test
  public void wrongLengthCurrent() {
    Readable wrongLength = new StringReader("current first first");
    ImageProcessorController wrongLengthController = new ImageProcessorControllerImpl(
        this.model, wrongLength, this.out);
    wrongLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid current command syntax.\n");
  }

  // testing sharpen when no current has been set yet
  @Test
  public void noCurrentSharpen() {
    Readable noCurrent = new StringReader("sharpen");
    ImageProcessorController noCurrentController = new ImageProcessorControllerImpl(
        this.model, noCurrent, this.out);
    noCurrentController.startEditor();

    assertEquals(this.out.toString(), "No current set.\n");
  }

  // testing sharpen with an incorrect command length
  @Test
  public void wrongLengthSharpen() {
    Readable wrongLength = new StringReader("create first add res/desert.ppm ppm\nsharpen b");
    ImageProcessorController wrongLengthController = new ImageProcessorControllerImpl(
        this.model, wrongLength, this.out);
    wrongLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid sharpen command syntax.\n");
  }

  // testing sepia when no current has been set yet
  @Test
  public void noCurrentSepia() {
    Readable noCurrent = new StringReader("sepia");
    ImageProcessorController noCurrentController = new ImageProcessorControllerImpl(
        this.model, noCurrent, this.out);
    noCurrentController.startEditor();

    assertEquals(this.out.toString(), "No current set.\n");
  }

  // testing sepia with an incorrect command length
  @Test
  public void wrongLengthSepia() {
    Readable wrongLength = new StringReader("create first add res/desert.ppm ppm\nsepia b");
    ImageProcessorController wrongLengthController = new ImageProcessorControllerImpl(
        this.model, wrongLength, this.out);
    wrongLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid sepia command syntax.\n");
  }

  // testing grayscale when no current has been set yet
  @Test
  public void noCurrentGrayscale() {
    Readable noCurrent = new StringReader("grayscale");
    ImageProcessorController noCurrentController = new ImageProcessorControllerImpl(
        this.model, noCurrent, this.out);
    noCurrentController.startEditor();

    assertEquals(this.out.toString(), "No current set.\n");
  }

  // testing grayscale with an incorrect command length
  @Test
  public void wrongLengthGrayscale() {
    Readable wrongLength = new StringReader("create first add res/desert.ppm ppm\ngrayscale b");
    ImageProcessorController wrongLengthController = new ImageProcessorControllerImpl(
        this.model, wrongLength, this.out);
    wrongLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid grayscale command syntax.\n");
  }

  // testing blur when no current has been set yet
  @Test
  public void noCurrentBlur() {
    Readable noCurrent = new StringReader("blur");
    ImageProcessorController noCurrentController = new ImageProcessorControllerImpl(
        this.model, noCurrent, this.out);
    noCurrentController.startEditor();

    assertEquals(this.out.toString(), "No current set.\n");
  }

  // testing blur with an incorrect command length
  @Test
  public void wrongLengthBlur() {
    Readable wrongLength = new StringReader("create first add res/desert.ppm ppm\nblur b");
    ImageProcessorController wrongLengthController = new ImageProcessorControllerImpl(
        this.model, wrongLength, this.out);
    wrongLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid blur command syntax.\n");
  }

  // testing show command when the current is invalid
  @Test
  public void invalidCurrentShow() {
    Readable invalidCurrent = new StringReader("create first add res/desert.ppm ppm\nshow");
    ImageProcessorController invalidCurrentController = new ImageProcessorControllerImpl(
        this.model, invalidCurrent, this.out);
    invalidCurrentController.startEditor();

    assertEquals(this.out.toString(), "String cannot be null.\n");
  }

  // testing show command when no layers have been created
  @Test
  public void noLayersShow() {
    Readable noLayers = new StringReader("show");
    ImageProcessorController noLayersController = new ImageProcessorControllerImpl(
        this.model, noLayers, this.out);
    noLayersController.startEditor();

    assertEquals(this.out.toString(), "No layers created yet.\n");
  }

  // testing show command with incorrect length
  @Test
  public void invalidLengthShow() {
    Readable invalidLength = new StringReader("show show");
    ImageProcessorController invalidLengthController = new ImageProcessorControllerImpl(
        this.model, invalidLength, this.out);
    invalidLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid show command syntax.\n");
  }

  // testing hide command when the current is invalid
  @Test
  public void invalidCurrentHide() {
    Readable invalidCurrent = new StringReader("create first add res/desert.ppm ppm\nhide");
    ImageProcessorController invalidCurrentController = new ImageProcessorControllerImpl(
        this.model, invalidCurrent, this.out);
    invalidCurrentController.startEditor();

    assertEquals(this.out.toString(), "String cannot be null.\n");
  }

  // testing hide command when no layers have been created
  @Test
  public void noLayersHide() {
    Readable noLayers = new StringReader("hide");
    ImageProcessorController noLayersController = new ImageProcessorControllerImpl(
        this.model, noLayers, this.out);
    noLayersController.startEditor();

    assertEquals(this.out.toString(), "No layers created yet.\n");
  }

  // testing hide command with incorrect length
  @Test
  public void invalidLengthHide() {
    Readable invalidLength = new StringReader("hide hide");
    ImageProcessorController invalidLengthController = new ImageProcessorControllerImpl(
        this.model, invalidLength, this.out);
    invalidLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid hide command syntax.\n");
  }

  // testing save command with invalid file type
  @Test
  public void invalidFileTypeSave() {
    Readable invalidFileType = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nsave abc desert1\n");
    ImageProcessorController invalidFileTypeController = new ImageProcessorControllerImpl(
        this.model, invalidFileType, this.out);
    invalidFileTypeController.startEditor();

    assertEquals(this.out.toString(), "Invalid file type.\n");
  }

  // testing save command with invalid length
  @Test
  public void invalidLengthSave() {
    Readable invalidLength = new StringReader("save");
    ImageProcessorController invalidLengthController = new ImageProcessorControllerImpl(
        this.model, invalidLength, this.out);
    invalidLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid save command syntax.\n");
  }

  // testing saveAll invalid file type
  @Test
  public void invalidFileTypeSaveAll() {
    Readable invalidFileType = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nsaveall abc desert1\n");
    ImageProcessorController invalidFileTypeController = new ImageProcessorControllerImpl(
        this.model, invalidFileType, this.out);
    invalidFileTypeController.startEditor();

    assertEquals(this.out.toString(), "Invalid output type.\n");
  }

  // testing saveAll invalid command length
  @Test
  public void invalidLengthSaveAll() {
    Readable invalidLength = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nsaveall desert1\n");
    ImageProcessorController invalidLengthController = new ImageProcessorControllerImpl(
        this.model, invalidLength, this.out);
    invalidLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid saveall command syntax.\n");
  }

  // testing addMulti invalid file name
  @Test
  public void invalidFileNameAddMulti() {
    Readable invalidFileName = new StringReader("addmulti asf");
    ImageProcessorController invalidFileNameController = new ImageProcessorControllerImpl(
        this.model, invalidFileName, this.out);
    invalidFileNameController.startEditor();

    assertEquals(this.out.toString(), "File could not be found\n");
  }

  // testing addMulti invalid length
  @Test
  public void invalidLengthAddMulti() {
    Readable invalidLength = new StringReader("addmulti");
    ImageProcessorController invalidLengthController = new ImageProcessorControllerImpl(
        this.model, invalidLength, this.out);
    invalidLengthController.startEditor();

    assertEquals(this.out.toString(), "Invalid addmulti command syntax.\n");
  }

  // testing add command outside of create
  @Test
  public void invalidUseOfAdd() {
    Readable invalidUseAdd = new StringReader("add");
    ImageProcessorController invalidUseAddController = new ImageProcessorControllerImpl(
        this.model, invalidUseAdd, this.out);
    invalidUseAddController.startEditor();

    assertEquals(this.out.toString(), "Add command must be used in the create command.\n");
  }

  // testing add command outside of create
  @Test
  public void invalidUseOfCheckerboard() {
    Readable invalidUseCheckerboard = new StringReader("checkerboard");
    ImageProcessorController invalidUseCheckerboardController = new ImageProcessorControllerImpl(
        this.model, invalidUseCheckerboard, this.out);
    invalidUseCheckerboardController.startEditor();

    assertEquals(this.out.toString(), "Checkerboard command must be used in the create command.\n");
  }

  // testing unrecognized command message
  @Test
  public void unrecognizedCommand() {
    Readable unrecognizedCommand = new StringReader("asd");
    ImageProcessorController unrecognizedCommandController = new ImageProcessorControllerImpl(
        this.model, unrecognizedCommand, this.out);
    unrecognizedCommandController.startEditor();

    assertEquals(this.out.toString(), "Unrecognizable command.\n");
  }

  // testing adding an image of different dimensions
  @Test
  public void differentDimensionsEx() {
    Readable differentDimensions = new StringReader("create first add res/desert.ppm ppm\n"
        + "create second add res/bug.ppm ppm\n");
    ImageProcessorController differentDimensionsController = new ImageProcessorControllerImpl(
        this.model, differentDimensions, this.out);
    differentDimensionsController.startEditor();

    assertEquals(this.out.toString(), "Layers must all be the same dimensions.\n");
  }

  // -----------------------------------------------------------------------------------------------


  /*
        --------------
       | create Tests |
        --------------
  */


  // testing create with a given image
  @Test
  public void givenImageCreate() {
    Readable givenImage = new StringReader("create first add res/desert.ppm ppm\n");
    ImageProcessorController givenImageController = new ImageProcessorControllerImpl(
        this.model, givenImage, this.out);
    givenImageController.startEditor();

    assertEquals(this.model.getLayers().get("first"),
        new PPMFileReader().readImageFromFile("res/desert.ppm"));
  }

  // testing create with a checkerboard
  @Test
  public void checkerboardCreate() {
    Readable checkerboard = new StringReader("create first checkerboard 5 5 0 0 0 255 255 255");
    ImageProcessorController checkerboardController = new ImageProcessorControllerImpl(
        this.model, checkerboard, this.out);
    checkerboardController.startEditor();

    assertEquals(this.model.getImage("first"), new CheckerboardGenerator(5, 5,
        new ArrayList<>(Arrays.asList(
            new ColorImpl(0, 0, 0),
            new ColorImpl(255, 255, 255)))).generateImage());
  }

  // -----------------------------------------------------------------------------------------------


  /*
        --------------
       | remove Tests |
        --------------
  */


  // testing removing a layer
  @Test
  public void layerRemove() {
    Readable remove = new StringReader("create first add res/desert.ppm ppm\nremove first\n");
    ImageProcessorController removeController = new ImageProcessorControllerImpl(
        this.model, remove, this.out);
    removeController.startEditor();

    assertTrue(this.model.getLayers().isEmpty());
  }

  // -----------------------------------------------------------------------------------------------


  /*
        ---------------
       | current Tests |
        ---------------
  */


  // testing current
  @Test
  public void currentFunctionality() {
    Readable currentHide = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nhide");
    ImageProcessorController currentHideCheckController = new ImageProcessorControllerImpl(
        this.model, currentHide, this.out);
    currentHideCheckController.startEditor();

    assertEquals(this.model.getVisibility().size(), 1);
  }

  // -----------------------------------------------------------------------------------------------


  /*
        ------------
       | blur Tests |
        ------------
  */


  // testing blur
  @Test
  public void generalBlur() {
    Readable blur = new StringReader("create first add res/desert.ppm ppm\ncurrent first\nblur\n");
    ImageProcessorController blurController = new ImageProcessorControllerImpl(
        this.model, blur, this.out);
    blurController.startEditor();

    ImageInterface desert = new PPMFileReader().readImageFromFile("res/desert.ppm");
    ImageProcessorModelImpl desertBlur = new ImageProcessorModelImpl();
    desertBlur.addImage("desert", desert);
    desertBlur.replaceImage("desert", desertBlur.blur("desert"));

    for (int i = 0; i < this.model.getImage("first").getPixels().size(); i++) {
      for (int j = 0; j < this.model.getImage("first").getPixels().get(0).size(); j++) {
        assertEquals(this.model.getImage("first").getPixels().get(i).get(j).getColor(),
            desertBlur.getImage("desert").getPixels().get(i).get(j).getColor());
      }
    }

  }

  // -----------------------------------------------------------------------------------------------


  /*
        ---------------
       | sharpen Tests |
        ---------------
  */

  // testing sharpen
  @Test
  public void generalSharpen() {
    Readable sharpen = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nsharpen\n");
    ImageProcessorController sharpenController = new ImageProcessorControllerImpl(
        this.model, sharpen, this.out);
    sharpenController.startEditor();

    ImageInterface desert = new PPMFileReader().readImageFromFile("res/desert.ppm");
    ImageProcessorModelImpl desertSharpen = new ImageProcessorModelImpl();
    desertSharpen.addImage("desert", desert);
    desertSharpen.replaceImage("desert", desertSharpen.sharpen("desert"));

    for (int i = 0; i < this.model.getImage("first").getPixels().size(); i++) {
      for (int j = 0; j < this.model.getImage("first").getPixels().get(0).size(); j++) {
        assertEquals(this.model.getImage("first").getPixels().get(i).get(j).getColor(),
            desertSharpen.getImage("desert").getPixels().get(i).get(j).getColor());
      }
    }
  }

  // -----------------------------------------------------------------------------------------------


  /*
        -----------------
       | grayscale Tests |
        -----------------
  */

  // testing grayscale
  @Test
  public void generalGrayscale() {
    Readable grayscale = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\ngrayscale\n");
    ImageProcessorController grayscaleController = new ImageProcessorControllerImpl(
        this.model, grayscale, this.out);
    grayscaleController.startEditor();

    ImageInterface desert = new PPMFileReader().readImageFromFile("res/desert.ppm");
    ImageProcessorModelImpl dessertGrayscale = new ImageProcessorModelImpl();
    dessertGrayscale.addImage("desert", desert);
    dessertGrayscale.replaceImage("desert", dessertGrayscale.grayscale("desert"));

    for (int i = 0; i < this.model.getImage("first").getPixels().size(); i++) {
      for (int j = 0; j < this.model.getImage("first").getPixels().get(0).size(); j++) {
        assertEquals(this.model.getImage("first").getPixels().get(i).get(j).getColor(),
            dessertGrayscale.getImage("desert").getPixels().get(i).get(j).getColor());
      }
    }
  }

  // -----------------------------------------------------------------------------------------------


  /*
        -------------
       | sepia Tests |
        -------------
  */
  // testing sepia
  @Test
  public void generalSepia() {
    Readable sepia = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nsepia\n");
    ImageProcessorController sepiaController = new ImageProcessorControllerImpl(
        this.model, sepia, this.out);
    sepiaController.startEditor();

    ImageInterface desert = new PPMFileReader().readImageFromFile("res/desert.ppm");
    ImageProcessorModelImpl desertSepia = new ImageProcessorModelImpl();
    desertSepia.addImage("desert", desert);
    desertSepia.replaceImage("desert", desertSepia.sepia("desert"));

    for (int i = 0; i < this.model.getImage("first").getPixels().size(); i++) {
      for (int j = 0; j < this.model.getImage("first").getPixels().get(0).size(); j++) {
        assertEquals(this.model.getImage("first").getPixels().get(i).get(j).getColor(),
            desertSepia.getImage("desert").getPixels().get(i).get(j).getColor());
      }
    }
  }

  // -----------------------------------------------------------------------------------------------


  /*
        ------------
       | show Tests |
        ------------
  */


  // show general test
  @Test
  public void showLayer() {
    Readable show = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nhide\nshow\n");
    ImageProcessorController showController = new ImageProcessorControllerImpl(
        this.model, show, this.out);
    showController.startEditor();

    assertTrue(this.model.getVisibility().isEmpty());
  }

  // -----------------------------------------------------------------------------------------------


  /*
        ------------
       | hide Tests |
        ------------
  */


  // hide general test
  @Test
  public void hideLayer() {
    Readable hide = new StringReader("create first add res/desert.ppm ppm\ncurrent first\nhide");
    ImageProcessorController hideController = new ImageProcessorControllerImpl(
        this.model, hide, this.out);
    hideController.startEditor();

    assertEquals(this.model.getVisibility().size(), 1);
  }

  // -----------------------------------------------------------------------------------------------


  /*
        ------------
       | save Tests |
        ------------
  */


  // save general test png
  @Test
  public void saveImagePNG() {
    Readable generalImage = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nsave png test\\testreaderfiles\\desert2");
    ImageProcessorController generalImageController = new ImageProcessorControllerImpl(
        this.model, generalImage, this.out);
    generalImageController.startEditor();

    assertEquals(new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\desert2.png"),
        this.model.getImage("first"));
  }

  // save general test ppm
  @Test
  public void saveImagePPM() {
    Readable generalImage = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nsave ppm test\\testreaderfiles\\desert2");
    ImageProcessorController generalImageController = new ImageProcessorControllerImpl(
        this.model, generalImage, this.out);
    generalImageController.startEditor();

    assertEquals(new PPMFileReader().readImageFromFile("test\\testreaderfiles\\desert2.ppm"),
        this.model.getImage("first"));
  }

  // save general test jpeg
  @Test
  public void saveImageJPEG() {
    Readable generalImage = new StringReader(
        "create first add res/desert.ppm ppm\ncurrent first\nsave jpeg test\\testreaderfiles\\desert2");
    ImageProcessorController generalImageController = new ImageProcessorControllerImpl(
        this.model, generalImage, this.out);
    generalImageController.startEditor();

    assertEquals(
        new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\desert2.jpeg").getClass(),
        ImageImpl.class);
  }

  // -----------------------------------------------------------------------------------------------


  /*
        ---------------
       | saveall Tests |
        ---------------
  */


  // saveAll general test
  @Test
  public void saveAllImage() {
    Readable generalImage = new StringReader(
        "create first add res/desert.ppm ppm\ncreate second add res/desert.ppm ppm\ncurrent first\nsaveall jpeg test\\testreaderfiles\\desert3\n");
    ImageProcessorController generalImageController = new ImageProcessorControllerImpl(
        this.model, generalImage, this.out);
    generalImageController.startEditor();

    assertEquals(new MultiLayerFileReader()
            .readImages("test\\testreaderfiles\\desert3\\test\\testreaderfiles\\desert3.txt")
            .get("first"),
        new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\desert3\\first.jpeg"));
    assertEquals(new MultiLayerFileReader()
            .readImages("test\\testreaderfiles\\desert3\\test\\testreaderfiles\\desert3.txt")
            .get("second"),
        new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\desert3\\second.jpeg"));
  }

  // -----------------------------------------------------------------------------------------------


  /*
        ----------------
       | addmulti Tests |
        ----------------
  */
  // general add multi test
  @Test
  public void generatAddMultiTest() {
    Readable generalImage = new StringReader(
        "addmulti test\\testreaderfiles\\bugs\\bugs.txt");
    ImageProcessorController addMultiController = new ImageProcessorControllerImpl(
        this.model, generalImage, this.out);
    addMultiController.startEditor();

    assertEquals(this.model.getImage("first"),
        new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\bugs\\first.png"));
    assertEquals(this.model.getImage("second"),
        new ImageIOFileReader().readImageFromFile("test\\testreaderfiles\\bugs\\second.png"));
    assertEquals(this.model.getVisibility().size(), 0);
  }


}