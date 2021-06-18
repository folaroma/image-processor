package cs3500.imageprocessor.view;

import java.io.IOException;

public class ImageProcessorViewImpl implements ImageProcessorView{
  private final Appendable output;


  public ImageProcessorViewImpl(Appendable output) {
    this.output = output;
  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException, IOException {
    if (msg == null) {
      throw new IllegalArgumentException("Null message.");
    }

    this.output.append(msg);
  }
}
