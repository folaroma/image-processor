package cs3500.imageprocessor.model.fileReading;

import cs3500.imageprocessor.model.images.ImageInterface;

public interface IFileReader {

  public ImageInterface readImageFromFile(String filename);

}
