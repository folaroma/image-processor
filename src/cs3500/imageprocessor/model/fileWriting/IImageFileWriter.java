package cs3500.imageprocessor.model.fileWriting;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.IOException;

public interface IImageFileWriter {

  public void writeFile(String filename, ImageInterface image) throws IOException;

}
