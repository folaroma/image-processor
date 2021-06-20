package cs3500.imageprocessor.view;

import java.io.IOException;

/**
 * Interface to represent a textual view for an image processor program. Text is used to display
 * error and information messages to the user.
 */
public interface ImageProcessorView {

  /**
   * Appends the given message to the appendable.
   * @param msg Message to be added.
   * @throws IllegalArgumentException If the message is null.
   * @throws IOException If writing fails.
   */
  void renderMessage(String msg) throws IllegalArgumentException, IOException;

}
