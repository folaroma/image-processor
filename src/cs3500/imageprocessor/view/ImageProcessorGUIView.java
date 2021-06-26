package cs3500.imageprocessor.view;

import java.awt.image.BufferedImage;

/**
 * Interface to represent the view for an image processing programs that uses an interactive GUI to
 * receive user inputs. GUI exposes all features of the program to the user, which can be activated
 * using these buttons.
 */
public interface ImageProcessorGUIView extends ImageProcessorView {

  /**
   * Makes the GUI visible to the user.
   */
  void runGUI();

  /**
   * Sets the viewable image in the GUI to the given buffered image.
   *
   * @param image
   */
  void updateImage(BufferedImage image);

  /**
   * Adds the given layer name to the list of layers contained in the GUI.
   *
   * @param layerName Name of the layer to add.
   */
  void updateLayers(String layerName);


}
