package cs3500.imageprocessor.model.filters;

import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFilter implements IFilter {
  protected final double[][] matrix;

  protected AbstractFilter(double[][] matrix) {
    this.matrix = matrix;
  }

  public abstract ImageInterface applyFilter(ImageInterface image);

  protected List<ArrayList<IPixel>> filtered(List<ArrayList<IPixel>> pixels, double[][] matrix,
      ImageInterface image) {
    List<ArrayList<IPixel>> filteredPixels = new ArrayList<>();
    for (int i = 0; i < pixels.size(); i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < pixels.get(0).size(); j++) {
        row.add(image.filter(pixels.get(i).get(j), matrix));
      }
      filteredPixels.add(row);
    }

    return filteredPixels;

  }
}
