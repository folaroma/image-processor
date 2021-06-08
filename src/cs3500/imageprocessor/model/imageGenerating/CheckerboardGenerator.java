package cs3500.imageprocessor.model.imageGenerating;

import cs3500.imageprocessor.model.images.IColor;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.PixelImpl;
import cs3500.imageprocessor.model.images.Position2D;
import java.util.ArrayList;
import java.util.List;

public class CheckerboardGenerator implements IImageGenerator{

  private final int size;
  private final int numPiles;
  private final List<IColor> colors;

  public CheckerboardGenerator(int size, int numPiles,
      List<IColor> colors) {
    this.size = size;
    this.numPiles = numPiles;
    this.colors = colors;
  }

  @Override
  public ImageInterface generateImage() {
    List<ArrayList<IPixel>> pixels = new ArrayList<>();

    for (int i = 0; i < this.numPiles; i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < this.size; j++) {
        row.add(new PixelImpl(new Position2D(j, i), alternateColors(this.colors, i, j)));
      }
      pixels.add(row);
    }

    return new ImageImpl(pixels);
  }

  private IColor alternateColors(List<IColor> colors, int rows, int columns) {
    int i = columns % colors.size() + rows % colors.size();
    if (i > 1) {
      i = 0;
    }

    return colors.get(i);
  }
}
