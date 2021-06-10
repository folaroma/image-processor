package cs3500.imageprocessor.model.images;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class representing a simple implementation of an Image, which is a 2D array of pixels. This array
 * is represneted by a 2D ArrayList.
 */
public class ImageImpl implements ImageInterface {

  private final List<ArrayList<IPixel>> pixels;

  /**
   * Creates a new image with the given 2d arraylist of pixels.
   * @param pixels Pixels to create the image with.
   * @throws IllegalArgumentException If the list of pixels is null.
   */
  public ImageImpl(List<ArrayList<IPixel>> pixels) throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("List of pixels cannot be null.");
    }
    this.pixels = this.deepCopyPixel(pixels);
  }

  @Override
  public List<ArrayList<IPixel>> getPixels() {
    return new ArrayList<>(pixels);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImageImpl)) {
      return false;
    }

    ImageImpl other = (ImageImpl) o;
    return this.pixels.equals(other.pixels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.pixels);
  }

  /**
   * Creates a deepy copy of the given 2d arraylist of pixels.
   * @param pixels Pixels to be copied.
   * @return The deep copy of the list.
   */
  private List<ArrayList<IPixel>> deepCopyPixel(List<ArrayList<IPixel>> pixels) {
    ArrayList<ArrayList<IPixel>> pixelsCopy = new ArrayList<>();
    for (ArrayList<IPixel> row : pixels) {
      ArrayList<IPixel> rowCopy = new ArrayList<>();
      for (IPixel pixel : row) {
        rowCopy.add(new PixelImpl(pixel.getPosition(), pixel.getColor()));
      }
      pixelsCopy.add(rowCopy);
    }
    return pixelsCopy;
  }
}
