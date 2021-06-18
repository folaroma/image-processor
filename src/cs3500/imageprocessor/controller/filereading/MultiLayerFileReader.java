package cs3500.imageprocessor.controller.filereading;

import cs3500.imageprocessor.model.images.ImageInterface;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MultiLayerFileReader implements IMultiLayerReader {

  private final List<String> visibility;

  public MultiLayerFileReader() {
    this.visibility = new ArrayList<>();
  }

  @Override
  public Map<String, ImageInterface> readImages(String filename) throws IllegalArgumentException {
    Scanner sc;
    Map<String, ImageInterface> layers = new HashMap<>();

    if (filename == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File could not be found");
    }

    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;
    String type = sc.next();

    if (!type.equals("png") || !type.equals("jpeg") || !type.equals("ppm")) {
      throw new IllegalArgumentException("Invalid multi layer text.");
    }

    while (sc.hasNextLine()) {
      token = sc.nextLine();
      String[] line = token.split(" ");

      ImageInterface image = null;
      switch (type) {
        case "png":
        case "jpeg":
          image = new ImageIOFileReader().readImageFromFile(line[0]);
        case "ppm":
          image = new PPMFileReader().readImageFromFile(line[0]);

      }
      String id = line[1];
      this.checkVisibility(id, line[2]);
      layers.put(id, image);
    }
    return layers;
  }

  private void checkVisibility(String id, String status) {
    if (status.equals("invisible")) {
      this.visibility.add(id);
    }
  }

  @Override
  public List<String> readVisibility(String filename) {
    return new ArrayList<>(this.visibility);
  }
}
