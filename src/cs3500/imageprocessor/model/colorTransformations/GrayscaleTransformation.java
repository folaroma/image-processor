package cs3500.imageprocessor.model.colorTransformations;

import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.List;

public class GrayscaleTransformation extends AbstractColorTransformation{

  @Override
  public ImageInterface applyTransformation(ImageInterface image) {
    List<ArrayList<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    double[][] monochrome = {{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};

    return new ImageImpl(transform(image, imagePixels, monochrome));
  }
}
