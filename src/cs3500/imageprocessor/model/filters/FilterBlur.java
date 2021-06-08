package cs3500.imageprocessor.model.filters;

import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.List;

public class FilterBlur extends AbstractFilter {

  public FilterBlur() {
    super(new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}});
  }

  @Override
  public ImageInterface applyFilter(ImageInterface image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null.");
    }
    List<ArrayList<IPixel>> imagePixels = new ArrayList<>(image.getPixels());
    List<ArrayList<IPixel>> filteredPixels = filtered(imagePixels, this.matrix, image);

    return new ImageImpl(filteredPixels);
  }


}
