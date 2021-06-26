package cs3500.imageprocessor.view;

import java.awt.Color;

/**
 * Interface to represent a listener to the GUI view for an image processing program that can react
 * to high level events received by the view.
 */
public interface IViewListener {

  /**
   * Returns the current layer of the image processing program from the model.
   *
   * @return String id of the current layer in the program.
   */
  String getCurrentLayerID();

  /**
   * Handles the logic of saving the topmost layer. Saves it as an image at the specified filepath
   * and of the given file type, one of PNG, JPEG, or PPM.
   *
   * @param fileName Path of the file to be created.
   * @param fileType Type of image to create.
   */
  void handleSaveLayerEvent(String fileName, String fileType);

  /**
   * Handles the logic of loading a given image file as a layer into the program. Files loaded can
   * be PNG, JPEG, or PPM.
   *
   * @param fileName  Path of the file to be loaded in.
   * @param fileType  Type of file to be loaded.
   * @param layerName Name of the layer to be placed into the model.
   */
  void handleLoadLayerEvent(String fileName, String fileType, String layerName);

  /**
   * Handles adding a generated checkerboard to the model.
   *
   * @param layerName Name to be given to the checkerboard.
   * @param rows      Number of rows in the checkerboard.
   * @param columns   Number of columns in the checkerboard.
   * @param color1    First color for the checkerboard.
   * @param color2    Second color for the checkerboard.
   */
  void handleCheckerboardEvent(String layerName, int rows, int columns, Color color1, Color color2);

  /**
   * Handles saving all of the layers to a folder with a text file containing the information of the
   * image.
   *
   * @param fileName File path for the new folder where the image will be placed.
   * @param fileType Type of image file for the images to be exported too.
   */
  void handleSaveAllLayerEvent(String fileName, String fileType);

  /**
   * Handles the logic for an event that calls to load a multi layer image into the program.
   *
   * @param filename File path for the text file for the multi layer image.
   */
  void handleLoadAllLayerEvent(String filename);

  /**
   * Handles blurring the current image in the program.
   */
  void handleBlurEvent();

  /**
   * Handles an event that calls the sharpen the current image.
   */
  void handleSharpenEvent();

  /**
   * Handles an event that calls the grayscale the current image.
   */
  void handleGrayscaleEvent();

  /**
   * Handles an event that calls the sepia the current image.
   */
  void handleSepiaEvent();

  /**
   * Handles an event that tells the listener to show the current image.
   */
  void showEvent();

  /**
   * Handles an event that tells the listener to hide the current image in the program.
   */
  void hideEvent();

  /**
   * Handles the logic for an event that tells the listener to remove the current layer.
   */
  void removeLayerEvent();

  /**
   * Handles the logic for an event that tells the listener to set the current layer to the
   * specified id.
   *
   * @param layerID Id for the current layer.
   */
  void setCurrentLayerEvent(String layerID);

  /**
   * Handles the logic for an event that tells the listener to run a script containing batch
   * commands for the text version of the program.
   *
   * @param filename File path for the txt script file.
   */
  void runScriptEvent(String filename);
}
