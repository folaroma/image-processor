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

      String[] command = str.split("\\s+");

      if (command.length == 4
          && command[0].equals("create")
          && command[2].equals("add")) {
        try {
          this.model.addImage(command[1], this.model.getImage(command[3]));
        }
        catch (IllegalArgumentException e) {

        }
      }

      if (command[0].equals("create")
          && command[2].equals("add")
          && command[3].equals("checkerboard")) {

        try {

          this.model.addImage(command[1],
              this.model.generateCheckerboard(Integer.parseInt(command[4]),
                  Integer.parseInt(command[5]),
                  new ArrayList<>(Arrays.asList(
                      new ColorImpl(Integer.parseInt(command[6]), Integer.parseInt(command[7]), Integer.parseInt(command[8])),
                      new ColorImpl(Integer.parseInt(command[9]), Integer.parseInt(command[10]), Integer.parseInt(command[11]))))));
        }
        catch (IllegalArgumentException e) {

        }
      }

    }

  }
}
