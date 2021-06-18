package cs3500.imageprocessor.view;

import cs3500.imageprocessor.model.ImageProcessorModel;
import java.io.IOException;

public class ImageProcessorTextView implements ImageProcessorView {
  private final ImageProcessorModel model;
  private Appendable ap;

  public ImageProcessorTextView(ImageProcessorModel model, Appendable ap)
      throws IllegalArgumentException {
    if (model == null || ap == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.model = model;
    this.ap = ap;

  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.ap.append(message);
  }
}
