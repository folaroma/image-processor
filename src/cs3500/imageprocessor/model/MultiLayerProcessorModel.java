package cs3500.imageprocessor.model;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.util.List;
import java.util.Map;

/**
 * Interface to represent the model of an image program that can edit a multi layered image. Model
 * can hold exactly one multi layer image at a time, and can add, remove, or replace layers. Each
 * layer is represented with a string name and an image interface image. Layers can also be marked
 * as either invisible or visible.
 */
public interface MultiLayerProcessorModel extends ImageProcessorModel {

  /**
   * Gets the layer represented by the given id and marks it as visible.
   *
   * @param id String of layer to mark.
   * @throws IllegalArgumentException If the id is null, the id is already shown, or no layer with
   *                                  the id exists.
   */
  void showLayer(String id) throws IllegalArgumentException;

  /**
   * Gets the layer represented by the given id and marks it as invisible.
   *
   * @param id String of layer to mark.
   * @throws IllegalArgumentException If the id is null, the id is already hidden,  or no layer with
   *                                  the id exists.
   */
  void hideLayer(String id) throws IllegalArgumentException;

  /**
   * Adds a multi layer image to this model. The current multi layer image will be cleared when a
   * new one is added.
   *
   * @param images          Strings and images to make up the layers of the image.
   * @param invisibleLayers List containing the ids of invisible layers for this multi layer image.
   * @throws IllegalArgumentException If any argument is null, if all layers do not have the same
   *                                  dimensions, or layers share the same id.
   */
  void addMultiLayer(Map<String, ImageInterface> images, List<String> invisibleLayers)
      throws IllegalArgumentException;

  /**
   * Returns the list of ids of invisible layers for this multi layer image.
   *
   * @return The list of invisible image ids.
   */
  List<String> getVisibility();

  /**
   * Gets a map assigning the layer ids to the image represented by it.
   *
   * @return Map of all ids and their respective images.
   */
  Map<String, ImageInterface> getLayers();

}
