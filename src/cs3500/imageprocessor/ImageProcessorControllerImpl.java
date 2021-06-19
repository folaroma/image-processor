package cs3500.imageprocessor;

import cs3500.imageprocessor.controller.ImageProcessorController;
import cs3500.imageprocessor.controller.filereading.IMultiLayerReader;
import cs3500.imageprocessor.controller.filereading.ImageIOFileReader;
import cs3500.imageprocessor.controller.filereading.MultiLayerFileReader;
import cs3500.imageprocessor.controller.filereading.PPMFileReader;
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
  private ImageProcessorView view;
  private String current;

  public ImageProcessorControllerImpl(MultiLayerProcessorModel model, Readable stringReader,
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
    this.current = null;
    this.view = new ImageProcessorTextView(this.model, this.out);

    Scanner scan = new Scanner(this.stringReader);

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
        renderHandler("Add command must be used in the create command.\n");
        break;
      case "checkerboard":
        renderHandler("Checkerboard command must be used in the create command.\n");
        break;
      default: renderHandler("Unrecognizable command.\n");
    }

  }




  private void createCommand(String[] command) {
    if (command.length == 5) {
      if (command[2].equals("add")) {
        if (!this.model.getLayers().containsKey(command[1])) {
          if (command[4].equals("ppm")) {
            addHandler(command[1], new PPMFileReader()
                .readImageFromFile("res/" + command[3] + "." + command[4]));
          } else {
            addHandler(command[1], new ImageIOFileReader()
                .readImageFromFile("res/" + command[3] + "." + command[4]));
          }
        } else {
          renderHandler("Layer with the same name already created.\n");
        }
      } else {
        renderHandler("Third parameter must be \"add\" in a create command.\n");
      }
    } else if (command.length == 11) {
      createCheckerboard(command);
    } else {
      renderHandler("Invalid create command syntax.\n");
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
      renderHandler("Invalid remove command syntax.\n");
    }
  }

  private void currentCommand(String[] command) {
    if (command.length == 2) {
      if (!this.model.getLayers().isEmpty()) {
        if (this.model.getLayers().containsKey(command[1])) {
          try {
            this.current = command[1];
          } catch (IllegalArgumentException e) {
            try {
              view.renderMessage(e.getMessage());
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
        } else {
          renderHandler("This layer does not exist.\n");
        }
      } else {
        renderHandler("No layers created.\n");
      }
    } else {
      renderHandler("Invalid current command syntax.\n");
    }
  }

  private void blurCommand(String[] command) {
    if (command.length == 1) {
      if (current != null) {
        replaceHandler(current, this.model.blur(current));
      } else {
        renderHandler("No current set.\n");
      }
    } else {
      renderHandler("Invalid blur command syntax.\n");
    }
  }

  private void sharpenCommand(String[] command) {
    if (command.length == 1) {
      if (this.current != null) {
        replaceHandler(this.current, this.model.sharpen(this.current));
      } else {
        renderHandler("No current set.\n");
      }
    } else {
      renderHandler("Invalid blur command syntax.\n");
    }
  }

  private void grayscaleCommand(String[] command) {
    if (command.length == 1) {
      if (this.current != null) {
        replaceHandler(this.current, this.model.grayscale(this.current));
      } else {
        renderHandler("No current set.\n");
      }
    } else {
      renderHandler("Invalid blur command syntax.\n");
    }
  }

  private void sepiaCommand(String[] command) {
    if (command.length == 1) {
      if (this.current != null) {
        replaceHandler(this.current, this.model.sepia(this.current));
      } else {
        renderHandler("No current set.\n");
      }
    } else {
      renderHandler("Invalid blur command syntax.\n");
    }
  }

  private void showCommand(String[] command) {
    if (command.length == 1) {
      if (!this.model.getLayers().isEmpty()) {
        if (this.model.getVisibility().contains(this.current)) {
          try {
            this.model.showLayer(this.current);
          } catch (IllegalArgumentException e) {
            renderHandler(e.getMessage());
          }
        } else {
          renderHandler("Layer is already visible.\n");
        }
      }
      else {
        renderHandler("No layers created yet.\n");
      }
    } else {
      renderHandler("Invalid show command syntax.\n");
    }
  }

  private void hideCommand(String[] command) {
    if (command.length == 1) {
      if (!this.model.getLayers().isEmpty()) {
        if (!this.model.getVisibility().contains(this.current)) {
          try {
            this.model.hideLayer(this.current);
          } catch (IllegalArgumentException e) {
            renderHandler(e.getMessage());
          }
        } else {
          renderHandler("Layer already hidden.\n");
        }
      }
      else {
        renderHandler("No layers created yet.\n");
      }
    } else {
      renderHandler("Invalid hide command syntax.\n");
    }
  }

  private void saveCommand(String[] command) {
    if (command.length == 3) {
      if (this.current != null) {
        switch (command[1]) {
          case "ppm":
            try {
              new PPMFileWriter()
                  .writeFile("res/" + command[2] + ".ppm", this.model.getImage(
                      this.current));
            } catch (IllegalArgumentException e) {
              renderHandler(e.getMessage());
            } catch (IOException io) {
              throw new IllegalStateException();
            }
            break;
          case "jpeg":
            try {
              new JPEGImageIOWriter()
                  .writeFile("res/" + command[2] + ".jpeg", this.model.getImage(
                      this.current));
            } catch (IllegalArgumentException e) {
              renderHandler(e.getMessage());
            } catch (IOException io) {
              throw new IllegalStateException();
            }
            break;
          case "png":
            try {
              new PNGImageIOWriter()
                  .writeFile("res/" + command[2] + ".png", this.model.getImage(
                      this.current));
            } catch (IllegalArgumentException e) {
              renderHandler(e.getMessage());
            } catch (IOException io) {
              throw new IllegalStateException();
            }
            break;
          default: renderHandler("Invalid file type.\n");
        }
      }
    } else {
      renderHandler("Invalid save command syntax.\n");
    }
  }

  private void saveAllCommand(String[] command) {
    if (command.length == 3) {
      if (command[1].equals("jpeg") || command[1].equals("ppm") || command[1]
          .equals("png")) {
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
        renderHandler("Invalid file type.\n");
      }
    } else {
      renderHandler("Invalid saveall command syntax.\n");
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
      renderHandler("Invalid addmulti command syntax.\n");
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
      view.renderMessage(message);
    } catch (IOException io) {
      throw new IllegalStateException();
    }
  }

}
