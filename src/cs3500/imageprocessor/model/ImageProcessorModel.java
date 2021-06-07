package cs3500.imageprocessor.model;

import java.io.IOException;
import java.util.List;

/**
 * Interface to represent an image processing program.
 */
public interface ImageProcessorModel {

void filterBlur();

void filterSharpen();

void colorMonochrome();

void colorSepia();

ImageInterface createCheckerboard(int size, int numTiles, List<IColor> colors);

void exportImage(String filename) throws IOException;

}