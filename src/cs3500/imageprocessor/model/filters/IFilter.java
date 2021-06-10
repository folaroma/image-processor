package cs3500.imageprocessor.model.filters;
import cs3500.imageprocessor.model.images.ImageInterface;

/**
 * Interface to represent a function object for a filter to be applied to an ImageInterface.
 */
public interface IFilter {

  /**
   * Applies the specified filter to the given image.
   * @param image Image to be filtered.
   * @return The image with the filter applied.
   */
  ImageInterface applyFilter(ImageInterface image);

}
