package cs3500.imageprocessor.model.colorTransformations;

import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.List;

public class SepiaTransformation extends AbstractColorTransformation{

  @Override
  public ImageInterface applyTransformation(ImageInterface image) {
    List<ArrayList<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    double[][] sepia = {{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};

    return new ImageImpl(transform(image, imagePixels, sepia));
  }
}
