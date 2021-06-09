package cs3500.imageprocessor.model.colorTransformations;

import cs3500.imageprocessor.model.images.ImageInterface;

public interface IColorTransformation {

  public ImageInterface applyTransformation(ImageInterface image);

}
