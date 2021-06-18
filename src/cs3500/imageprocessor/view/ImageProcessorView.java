package cs3500.imageprocessor.view;

import java.io.IOException;

public interface ImageProcessorView {

  void renderMessage(String msg) throws IllegalArgumentException, IOException;
  
}
