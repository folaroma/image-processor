package cs3500.imageprocessor.model.filters;

import cs3500.imageprocessor.model.images.ImageInterface;

public interface IFilter {
  ImageInterface applyFilter(ImageInterface image);

}
