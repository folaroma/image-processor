package cs3500.imageprocessor.controller;

import cs3500.imageprocessor.controller.ImageProcessorController;
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

public class ImageProcessorControllerImpl implements ImageProcessorController {

  private final MultiLayerProcessorModel model;
  private final Readable stringReader;
  private final Appendable out;
  private final ImageProcessorView view;
  private String current;

  public ImageProcessorControllerImpl(MultiLayerProcessorModel model, Readable stringReader,
      Appendable out) throws IllegalArgumentException {
    if (model == null || stringReader == null || out == null) {
      throw new IllegalArgumentException("Null parameter.");
    }
    this.model = model;
    this.stringReader = stringReader;
    this.out = out;
    this.view = new ImageProcessorTextView(this.out);

  }

  @Override
  public void startEditor() throws IllegalStateException {
    this.current = null;

    Scanner scan = new Scanner(this.stringReader);

    while (scan.hasNext()) {
      String str = scan.nextLine();

      String[] command = str.split("\\s+");
      if (command.length == 0) {
        renderHandler("Avoid putting spaces before commands.");
      } else {

        commandChecking(command);

      }
    }
  }

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

  private void sharpenCommand(String[] command) {
    if (command.length == 1) {
      if (this.current != null) {
        replaceHandler(this.current, this.model.sharpen(this.current));
      } else {
        renderHandler("No current set.");
      }
    } else {
      renderHandler("Invalid blur command syntax.");
    }
  }

  private void grayscaleCommand(String[] command) {
    if (command.length == 1) {
      if (this.current != null) {
        replaceHandler(this.current, this.model.grayscale(this.current));
      } else {
        renderHandler("No current set.");
      }
    } else {
      renderHandler("Invalid blur command syntax.");
    }
  }

  private void sepiaCommand(String[] command) {
    if (command.length == 1) {
      if (this.current != null) {
        replaceHandler(this.current, this.model.sepia(this.current));
      } else {
        renderHandler("No current set.");
      }
    } else {
      renderHandler("Invalid blur command syntax.");
    }
  }

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
      renderHandler("Invalid show command syntax.");
    }
  }

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

  private void writeFileHandler(String s, IImageFileWriter writer, String type) {
    String id = this.getTopmostVisible();
    if (id == null) {
      renderHandler("No visible layers.");
    } else {
      try {
        writer.writeFile(s + "." + type, this.model.getImage(id));
      } catch (IllegalArgumentException e) {
        renderHandler(e.getMessage());
      } catch (IOException io) {
        throw new IllegalStateException("Writing to file failed.");
      }
    }
  }

  private String getTopmostVisible() {
    for (Map.Entry<String, ImageInterface> item : this.model.getLayers().entrySet()) {
      if (!this.model.getVisibility().contains(item.getKey())) {
        return item.getKey();
      }
    }
    return null;
  }

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

  private void addHandler(String fileName, ImageInterface image) {
    try {
      this.model.addImage(fileName, image);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  private void replaceHandler(String current, ImageInterface image) {
    try {
      this.model.replaceImage(current, image);
    } catch (IllegalArgumentException e) {
      renderHandler(e.getMessage());
    }
  }

  private void renderHandler(String message) {
    try {
      view.renderMessage(message + "\n");
    } catch (IOException io) {
      throw new IllegalStateException();
    }
  }

}
