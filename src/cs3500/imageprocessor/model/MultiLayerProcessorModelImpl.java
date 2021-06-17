package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.images.IColor;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.List;

public class MultiLayerProcessorModelImpl implements MultiLayerProcessorModel {
  private final ImageProcessorModel delegate;
  private final List<ImageInterface> layers;
  private final List<Integer> hidden;

  public MultiLayerProcessorModelImpl(ImageProcessorModel delegate, List<ImageInterface> layers,
      List<Integer> hidden) throws IllegalArgumentException {
    if (delegate == null || layers == null || hidden == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.delegate = delegate;
    this.layers = layers;
    this.hidden = hidden;

  }
  public MultiLayerProcessorModelImpl() {
    this.delegate = new ImageProcessorModelImpl();
    this.layers = new ArrayList<>();
    this.hidden = new ArrayList<>();
  }

  @Override
  public void replaceImage(String id, ImageInterface image) throws IllegalArgumentException {

  }

  @Override
  public void addImage(String id, ImageInterface image) throws IllegalArgumentException {

  }

  @Override
  public ImageInterface getImage(String id) throws IllegalArgumentException {
    return null;
  }

  @Override
  public ImageInterface blur(String id) throws IllegalArgumentException {
    return null;
  }

  @Override
  public ImageInterface sharpen(String id) throws IllegalArgumentException {
    return null;
  }

  @Override
  public ImageInterface grayscale(String id) throws IllegalArgumentException {
    return null;
  }

  @Override
  public ImageInterface sepia(String id) throws IllegalArgumentException {
    return null;
  }

  @Override
  public ImageInterface generateCheckerboard(int rows, int columns, List<IColor> colors)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public ImageInterface getLayer(int index) {
    return null;
  }

  @Override
  public void showLayer(int index) {

  }

  @Override
  public void hideLayer(int index) {

  }

  @Override
  public void removeLayer(int index) {

  }

  @Override
  public void addLayer(ImageInterface layer) {

  }

  @Override
  public void addMultiLayer(List<ImageInterface> layers) {

  }

}
