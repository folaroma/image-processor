package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.images.IColor;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiLayerProcessorModelImpl implements MultiLayerProcessorModel {

  private final ImageProcessorModel delegate;
  private final List<String> layers;
  private final List<String> hidden;

  public MultiLayerProcessorModelImpl(ImageProcessorModel delegate, List<String> layers,
      List<String> hidden) throws IllegalArgumentException {
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
  public void addImage(String id, ImageInterface image) throws IllegalArgumentException {
    if (id == null || image == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    if (this.layers.contains(id)) {
      throw new IllegalArgumentException("Id is already contained.");
    }
    this.sameDimensions(image);
    this.layers.add(id);
    this.delegate.addImage(id, image);
    this.hidden.add(id);
  }

  @Override
  public ImageInterface getImage(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    return this.delegate.getImage(id);
  }

  @Override
  public void replaceImage(String id, ImageInterface image) throws IllegalArgumentException {
    if (id == null || image == null) {
      throw new IllegalArgumentException("Arguments cannot be null.");
    }
    if (!this.layers.contains(id)) {
      throw new IllegalArgumentException("Id is not contained.");
    }
    this.sameDimensions(image);
    this.delegate.replaceImage(id, image);

  }

  private void sameDimensions(ImageInterface image) throws IllegalArgumentException {
    if (!this.layers.isEmpty() && (
        image.getPixels().size() != this.delegate.getImage(this.layers.get(0)).getPixels().size() ||
            image.getPixels().get(0).size() != this.delegate.getImage(this.layers.get(0))
                .getPixels().get(0).size())) {
      throw new IllegalArgumentException("Layers must all be the same dimensions.");
    }
  }

  @Override
  public ImageInterface blur(String id) throws IllegalArgumentException {
    return this.delegate.blur(id);
  }

  @Override
  public ImageInterface sharpen(String id) throws IllegalArgumentException {
    return this.delegate.sharpen(id);
  }

  @Override
  public ImageInterface grayscale(String id) throws IllegalArgumentException {
    return this.delegate.grayscale(id);
  }

  @Override
  public ImageInterface sepia(String id) throws IllegalArgumentException {
    return this.delegate.sepia(id);
  }

  @Override
  public ImageInterface generateCheckerboard(int rows, int columns, List<IColor> colors)
      throws IllegalArgumentException {
    return this.delegate.generateCheckerboard(rows, columns, colors);
  }


  @Override
  public void removeImage(String id) throws IllegalArgumentException {
    if (id == null) {
      throw new IllegalArgumentException("String cannot be null.");
    }
    if (!this.layers.contains(id)) {
      throw new IllegalArgumentException("Layer with id does not exist.");
    }
    this.layers.remove(id);
    if (this.hidden.contains(id)) {
      this.hidden.remove(id);
    }
    this.delegate.removeImage(id);
  }


  @Override
  public void showLayer(String id) {
    if (id == null) {
      throw new IllegalArgumentException("String cannot be null.");
    }
    if (this.hidden.contains(id)) {
      throw new IllegalArgumentException("Layer with id is already visible.");
    }
    this.hidden.add(id);
  }

  @Override
  public void hideLayer(String id) {
    if (id == null) {
      throw new IllegalArgumentException("String cannot be null.");
    }
    if (!this.hidden.contains(id)) {
      throw new IllegalArgumentException("Layer with id is already hidden.");
    }
    this.hidden.remove(id);
  }


  @Override
  public void addMultiLayer(Map<String, ImageInterface> images, List<String> invisibleLayers)
      throws IllegalArgumentException {
    if (images == null || invisibleLayers == null) {
      throw new IllegalArgumentException("Null parameters.");
    }
    this.layers.clear();
    this.hidden.clear();
    for (String id : this.layers) {
      this.removeImage(id);
    }
    for (Map.Entry<String, ImageInterface> item : images.entrySet()) {
      this.addImage(item.getKey(), item.getValue());
    }
    for (String layer : invisibleLayers) {
      this.hidden.add(layer);
    }

  }

  @Override
  public List<String> getVisibility() {
    return new ArrayList<>(this.hidden);
  }

  @Override
  public Map<String, ImageInterface> getLayers() throws IllegalArgumentException{
    Map<String, ImageInterface> layersMap = new HashMap<>();
    for (String id : this.layers) {
      layersMap.put(id, this.getImage(id));
    }
    return layersMap;
  }

}
