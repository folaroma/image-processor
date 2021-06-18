package cs3500.imageprocessor;

import cs3500.imageprocessor.controller.ImageProcessorController;
import cs3500.imageprocessor.model.ImageProcessorModel;
import cs3500.imageprocessor.model.images.ColorImpl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ImageProcessorControllerImpl implements ImageProcessorController {
  private final ImageProcessorModel model;
  private final Readable stringReader;
  private final Appendable out;

  public ImageProcessorControllerImpl(ImageProcessorModel model, Readable stringReader,
      Appendable out) throws IllegalArgumentException {
    if (model == null || stringReader == null || out == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.model = model;
    this.stringReader = stringReader;
    this.out = out;

  }

  @Override
  public void startEditor() {

    Scanner scan = new Scanner(this.stringReader).useDelimiter("\n");

    if (!scan.hasNext()) {
      throw new IllegalStateException();
    }

    while (scan.hasNext()) {
      String str = scan.next();
      Scanner stringScan = new Scanner(str);

      String[] command = str.split("\\s+");

      switch (command[0]) {
        case "create":
        case "remove":
        case "current":
        case "blur":
        case "sharpen":
        case "grayscale":
        case "sepia":
        case "show":
        case "hide":
      }

      if (command.length == 4
          && command[0].equals("create")
          && command[2].equals("add")) {
        try {
          this.model.addImage(command[1], this.model.getImage(command[3]));
        }
        catch (IllegalArgumentException e) {

        }
      }

      if (command.length == 11
          && command[0].equals("create")
          && command[2].equals("add")
          && command[3].equals("checkerboard")) {

        try {

          this.model.addImage(command[1],
              this.model.generateCheckerboard(Integer.parseInt(command[4]),
                  Integer.parseInt(command[5]), new ArrayList<>(Arrays.asList(
                      new ColorImpl(stringScan.nextInt(), stringScan.nextInt(),
                          stringScan.nextInt()),
                      new ColorImpl(stringScan.nextInt(), stringScan.nextInt(),
                          stringScan.nextInt())))));
        }
        catch (IllegalArgumentException e) {

        }
      }

    }

  }
}
