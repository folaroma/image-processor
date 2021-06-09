package cs3500.imageprocessor.model.colorTransformations;

import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractColorTransformation implements IColorTransformation {

  public abstract ImageInterface applyTransformation(ImageInterface image);

  protected List<ArrayList<IPixel>> transform(ImageInterface image,
      List<ArrayList<IPixel>> imagePixels, double[][] matrix) {
    List<ArrayList<IPixel>> updatedPixels = new ArrayList<>();

    for (List<IPixel> l : imagePixels) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (IPixel p : l) {
        row.add(image.colorTransform(p, matrix));
      }
      updatedPixels.add(row);
    }

    return updatedPixels;


  }

}
