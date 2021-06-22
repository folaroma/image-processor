package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.controller.filereading.IMultiLayerReader;
import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.MultiLayerFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
import cs3500.imageprocessor.controller.filewriting.IImageFileWriter;
import cs3500.imageprocessor.controller.filewriting.JPEGImageIOWriter;
import cs3500.imageprocessor.controller.filewriting.MultiLayerImageWriter;
import cs3500.imageprocessor.controller.filewriting.PNGImageIOWriter;
import cs3500.imageprocessor.controller.filewriting.PPMFileWriter;
import cs3500.imageprocessor.model.MultiLayerProcessorModel;
import cs3500.imageprocessor.model.images.ColorImpl;
import cs3500.imageprocessor.model.images.ImageInterface;
import cs3500.imageprocessor.view.ImageProcessorTextView;
import cs3500.imageprocessor.view.ImageProcessorView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

/**
 * Class to represent the controller for the image processor. The controller uses the
 * MultiImageProcessorModel implementation, and allows the creation of one multi layer image that
 * can be edited and exported. PPN, Png, and JPEG files can be imported and exported. Each command
 * must be on its own line.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController {

  private final MultiLayerProcessorModel model;
  private final Readable stringReader;
  private final ImageProcessorView view;
  private String current;

  /**
   * Creates an instance of the controller.
   *
   * @param model        Model for the controller to receive info from.
   * @param stringReader Readable for which inputs are received.
   * @param out          The appendable for which outputs are sent to.
   * @throws IllegalArgumentException If any argument is null.
   */
  public ImageProcessorControllerImpl(MultiLayerProcessorModel model, Readable stringReader,
      Appendable out) throws IllegalArgumentException {
    if (model == null || stringReader == null || out == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.model = model;
    this.stringReader = stringReader;
    this.view = new ImageProcessorTextView(out);

  }

  @Override
  public void startEditor() throws IllegalStateException {
    this.current = null;

    Scanner scan = new Scanner(this.stringReader);

    if (!scan.hasNext()) {
      throw new IllegalStateException();
    }

    while (scan.hasNext()) {
      String str = scan.nextLine();

      String[] command = str.split("\\s+");
      if (command.length == 0) {
        renderHandler("Avoid putting spaces before commands.\n");
      } else {

        commandChecking(command);

      }
    }
  }

  /**
   * Switches on the commands that are received and recognized. Unrecognized commnands result in a
   * message being sent to the appendable.
   *
   * @param command Array of string on the line from input.
   */
  private void commandChecking(String[] command) {
    switch (command[0]) {
      case "create":
        createCommand(command);
        break;
      case "remove":
        removeCommand(command);
        break;
      case "current":
        currentCommand(command);
        break;
      case "blur":
        blurCommand(command);
        break;
      case "sharpen":
        sharpenCommand(command);
        break;
      case "grayscale":
        grayscaleCommand(command);
        break;
      case "sepia":
        sepiaCommand(command);
        break;
      case "show":
        showCommand(command);
        break;
      case "hide":
        hideCommand(command);
        break;
      case "save":
        saveCommand(command);
        break;
      case "saveall":
        saveAllCommand(command);
        break;
      case "addmulti":
        addMultiCommand(command);
        break;
      case "add":
        renderHandler("Add command must be used in the create command.");
        break;
      case "checkerboard":
        renderHandler("Checkerboard command must be used in the create command.");
        break;
      default:
        renderHandler("Unrecognizable command.");
    }

  }

  /**
   * Checks the syntax of the create commands and creates the layer if it is valid. Create can
   * either work with the add command or the checkerboard command.
   *
   * @param command Line containing the command.
   */
  private void createCommand(String[] command) {
    if (command.length == 5) {
      if (command[2].equals("add")) {
        try {
          if (command[4].equals("ppm")) {
            addHandler(command[1], new PPMFileReader()
                .readImageFromFile(command[3]));
          } else {
            addHandler(command[1], new ImageIOFileReader()
                .readImageFromFile(command[3]));
          }
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
      } else {
        renderHandler("Invalid create command syntax.");
      }
    } else if (command.length == 11) {
      createCheckerboard(command);
    } else {
      renderHandler("Invalid create command syntax.");
    }
  }

  /**
   * Checks the syntax for the remove command and removes the layer if it is valid.
   *
   * @param command Line containing the command.
   */
  private void removeCommand(String[] command) {
    if (command.length == 2) {
      try {
        this.model.removeImage(command[1]);
      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    } else {
      renderHandler("Invalid remove command syntax.");
    }
  }

  /**
   * Checks the syntax for the current command, and then sets the current layer if it is valid.
   *
   * @param command Line containing the command.
   */
  private void currentCommand(String[] command) {
    if (command.length == 2) {
      if (!this.model.getLayers().isEmpty()) {
        if (this.model.getLayers().containsKey(command[1])) {
          this.current = command[1];
        } else {
          renderHandler("This layer does not exist.");
        }
      } else {
        renderHandler("No layers created.");
      }
    } else {
      renderHandler("Invalid current command syntax.");
    }
  }

  /**
   * Checks the syntax for the blur command and blurs the current image if it is valid.
   *
   * @param command Line containing the command.
   */
  private void blurCommand(String[] command) {
    if (command.length == 1) {
      if (current != null) {
        replaceHandler(current, this.model.blur(current));
      } else {
        renderHandler("No current set.");
      }
    } else {
      renderHandler("Invalid blur command syntax.");
    }
  }

  /**
   * Checks the syntax for the sharpen command and sharpens the current image if it is valid.
   *
   * @param command Line containing the command.
   */
  private void sharpenCommand(String[] command) {
    if (command.length == 1) {
      if (this.current != null) {
        replaceHandler(this.current, this.model.sharpen(this.current));
      } else {
        renderHandler("No current set.");
      }
    } else {
      renderHandler("Invalid sharpen command syntax.");
    }
  }

  /**
   * Checks the syntax for the grayscale command and grayscales the current image if it is valid.
   *
   * @param command Line containing the command.
   */
  private void grayscaleCommand(String[] command) {
    if (command.length == 1) {
      if (this.current != null) {
        replaceHandler(this.current, this.model.grayscale(this.current));
      } else {
        renderHandler("No current set.");
      }
    } else {
      renderHandler("Invalid grayscale command syntax.");
    }
  }

  /**
   * Checks the syntax for the sepia command and sepia the current image if it is valid.
   *
   * @param command Line containing the command.
   */
  private void sepiaCommand(String[] command) {
    if (command.length == 1) {
      if (this.current != null) {
        replaceHandler(this.current, this.model.sepia(this.current));
      } else {
        renderHandler("No current set.");
      }
    } else {
      renderHandler("Invalid sepia command syntax.");
    }
  }

  /**
   * Checks the syntax for the show command and shows the current image if it is valid.
   *
   * @param command Line containing the command.
   */
  private void showCommand(String[] command) {
    if (command.length == 1) {
      if (!this.model.getLayers().isEmpty()) {
        try {
          this.model.showLayer(this.current);
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
      } else {
        renderHandler("No layers created yet.");
      }
    } else {
      renderHandler("Invalid show command syntax.");
    }
  }

  /**
   * Checks the syntax for the hide command and hides the current image if it is valid.
   *
   * @param command Line containing the command.
   */
  private void hideCommand(String[] command) {
    if (command.length == 1) {
      if (!this.model.getLayers().isEmpty()) {
        try {
          this.model.hideLayer(this.current);
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
      } else {
        renderHandler("No layers created yet.");
      }
    } else {
      renderHandler("Invalid hide command syntax.");
    }
  }

  /**
   * Checks the syntax for the save command and saves the given image if it is valid.
   *
   * @param command Line containing the command.
   */
  private void saveCommand(String[] command) {
    if (command.length == 3) {
      if (this.current != null) {
        switch (command[1]) {
          case "ppm":
            writeFileHandler(command[2], new PPMFileWriter(), command[1]);
            break;
          case "jpeg":
            writeFileHandler(command[2], new JPEGImageIOWriter(), command[1]);
            break;
          case "png":
            writeFileHandler(command[2], new PNGImageIOWriter(), command[1]);
            break;
          default:
            renderHandler("Invalid file type.");
        }
      }
    } else {
      renderHandler("Invalid save command syntax.");
    }
  }

  /**
   * Handles writing the given image into an image file.
   *
   * @param s      Name of the written file.
   * @param writer Writer object for the right file type.
   * @param type   File type for the image file.
   */
  private void writeFileHandler(String s, IImageFileWriter writer, String type) {
    String id = this.getTopmostVisible();
    try {
      writer.writeFile(s + "." + type, this.model.getImage(id));
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    } catch (IOException io) {
      throw new IllegalStateException("Writing to file failed.");
    }
  }

  /**
   * Gets the topmost visible layer in the multi layer image.
   *
   * @return Topmost visible layer.
   */
  private String getTopmostVisible() {
    for (Map.Entry<String, ImageInterface> item : this.model.getLayers().entrySet()) {
      if (!this.model.getVisibility().contains(item.getKey())) {
        return item.getKey();
      }
    }
    return null;
  }

  /**
   * Checks the syntax for the saveall command and saves all the layers if it is valid.
   *
   * @param command Line containing the command.
   */
  private void saveAllCommand(String[] command) {
    if (command.length == 3) {
      try {
        new MultiLayerImageWriter()
            .writeFile(command[2], command[1], this.model.getLayers(),
                this.model.getVisibility());
      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      } catch (IOException io) {
        throw new IllegalStateException();
      }
    } else {
      renderHandler("Invalid saveall command syntax.");
    }
  }

  /**
   * Checks the syntax for the addMulti command and adds all the layers if it is valid.
   *
   * @param command Line containing the command.
   */
  private void addMultiCommand(String[] command) {
    if (command.length == 2) {
      try {
        IMultiLayerReader newMulti = new MultiLayerFileReader();
        Map<String, ImageInterface> newMultiImages = newMulti.readImages(command[1]);
        try {
          this.model.addMultiLayer(newMultiImages, newMulti.readVisibility());
        } catch (IllegalArgumentException e) {
          renderHandler(e.getMessage());
        }
      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
    } else {
      renderHandler("Invalid addmulti command syntax.");
    }
  }

  /**
   * Checks the syntax for the checkerboard command, and creates and adds the image if it is valid.
   *
   * @param command Line containing the command.
   */
  private void createCheckerboard(String[] command) {
    if (command[2].equals("checkerboard")
        && command[3].matches("-?\\d+")
        && command[4].matches("-?\\d+")
        && command[5].matches("-?\\d+")
        && command[6].matches("-?\\d+")
        && command[7].matches("-?\\d+")
        && command[8].matches("-?\\d+")
        && command[9].matches("-?\\d+")
        && command[10].matches("-?\\d+")) {
      ImageInterface checkerboard = null;

      try {
        checkerboard = this.model
            .generateCheckerboard(Integer.parseInt(command[3]),
                Integer.parseInt(command[4]), new ArrayList<>(Arrays.asList(
                    new ColorImpl(Integer.parseInt(command[5]),
                        Integer.parseInt(command[6]),
                        Integer.parseInt(command[7])),
                    new ColorImpl(Integer.parseInt(command[8]),
                        Integer.parseInt(command[9]),
                        Integer.parseInt(command[10])))));
      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      }
      addHandler(command[1], checkerboard);
    } else {
      renderHandler("Invalid checkerboard syntax.");
    }
  }

  /**
   * Handles adding new layers to the model from the controller.
   *
   * @param fileName Name of the image to add
   * @param image    Image to add to the model.
   */
  private void addHandler(String fileName, ImageInterface image) {
    try {
      this.model.addImage(fileName, image);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  /**
   * Handles replacing the image associated with the id in the model with a new image.
   *
   * @param current Id of the current image.
   * @param image   New image to set to that id.
   */
  private void replaceHandler(String current, ImageInterface image) {
    try {
      this.model.replaceImage(current, image);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  /**
   * Handles rendering a message to the output appendable.
   *
   * @param message Messages to be rendered to the view.
   */
  private void renderHandler(String message) {
    try {
      view.renderMessage(message + "\n");
    } catch (IOException io) {
      throw new IllegalStateException();
    }
  }

}
