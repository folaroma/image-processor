package cs3500.imageprocessor.model.imageGenerating;

import cs3500.imageprocessor.model.images.IColor;
import cs3500.imageprocessor.model.images.IPixel;
import cs3500.imageprocessor.model.images.ImageImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.model.images.PixelImpl;
import cs3500.imageprocessor.model.images.Position2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a function object that generates a checkerboard style image where each square
 * is 1 pixel.
 */
public class CheckerboardGenerator implements IImageGenerator {

  private final int rows;
  private final int columns;
  private final List<IColor> colors;

  /**
   * Creates an instance of the checkerboard generator.
   *
   * @param rows    Number of rows for the checkerboard.
   * @param columns Number of columns for the board.
   * @param colors  Colors for the board.
   * @throws IllegalArgumentException If the colors are null or there are not only 2 colors.
   */
  public CheckerboardGenerator(int rows, int columns,
      List<IColor> colors) throws IllegalArgumentException {
    if (colors == null) {
      throw new IllegalArgumentException("Argument cannot be null.");
    }
    if (colors.size() != 2) {
      throw new IllegalArgumentException("There can only be two colors for the checkerboard.");
    }
    this.rows = rows;
    this.columns = columns;
    this.colors = colors;
  }

  @Override
  public ImageInterface generateImage() {
    List<ArrayList<IPixel>> pixels = new ArrayList<>();

    for (int i = 0; i < this.rows; i++) {
      ArrayList<IPixel> row = new ArrayList<>();
      for (int j = 0; j < this.columns; j++) {
        row.add(new PixelImpl(new Position2D(j, i), alternateColors(i, j)));
      }
      pixels.add(row);
    }

    return new ImageImpl(pixels);
  }

  /**
   * Alternates between the two colors in the list each time a new square is made.
   *
   * @param row    The current row the square is being made in.
   * @param column The current column the sqaure is being made in.
   * @return The color to apply to the square.
   */
  private IColor alternateColors(int row, int column) {
    int i = columns % colors.size() + rows % colors.size();
    if (i > 1) {
      i = 0;
    }

    return colors.get(i);
  }
}
