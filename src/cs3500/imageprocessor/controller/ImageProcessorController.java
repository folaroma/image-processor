package cs3500.imageprocessor.controller;

/**
 * Interface to represent the controller for the image processor. The controller uses the
 * MultiImageProcessorModel implementation, and allows the creation of one multi layer image that
 * can be edited and exported. PPN, Png, and JPEG files can be imported and exported.
 */
public interface ImageProcessorController {

  /**
   * Runs the controller with the given commands in the appendable. Possible commands are create,
   * add, remove, current, blur, sharpen, grayscale, sepia, show, hide, save, saveall, addmulti.
   * Inputs can be taken through the console or read through a script file. See the USEME for all
   * instructions of commands.
   * @throws IllegalStateException If there is no input.
   */
  void startEditor() throws IllegalStateException;

}
