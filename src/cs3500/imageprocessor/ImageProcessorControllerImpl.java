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
    String current = null;
    ImageProcessorView view = new ImageProcessorTextView(this.model, this.out);

    Scanner scan = new Scanner(this.stringReader);


    while (scan.hasNext()) {
      String str = scan.nextLine();

      String[] command = str.split("\\s+");

      switch (command[0]) {
        case "create":
          if (command.length == 5) {
            if (command[2].equals("add")) {
              if (!this.model.getLayers().containsKey(command[1])) {
                if (command[4].equals("ppm")) {
                  try {
                    this.model.addImage(command[1],
                        new PPMFileReader()
                            .readImageFromFile("res/" + command[3] + "." + command[4]));
                  } catch (IllegalArgumentException e) {
                    try {
                      view.renderMessage(e.getMessage());
                    } catch (IOException io) {
                      throw new IllegalStateException();
                    }
                  }
                } else {
                  try {
                    this.model.addImage(command[1],
                        new ImageIOFileReader()
                            .readImageFromFile("res/" + command[3] + "." + command[4]));
                  } catch (IllegalArgumentException e) {
                    try {
                      view.renderMessage(e.getMessage());
                    } catch (IOException io) {
                      throw new IllegalStateException();
                    }
                  }
                }
              } else {
                try {
                  view.renderMessage("Layer with the same name already created.\n");
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            } else {
              try {
                view.renderMessage("Third parameter must be \"add\" in a create command.\n");
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else if (command.length == 11) {
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
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }

              try {
                this.model.addImage(command[1], checkerboard);
              } catch (IllegalArgumentException e) {
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            }
          } else {
            try {
              view.renderMessage("Invalid create command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;

        case "remove":
          if (command.length == 2) {
            try {
              this.model.removeImage(command[1]);
            } catch (IllegalArgumentException e) {
              try {
                view.renderMessage(e.getMessage());
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid remove command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "current":
          if (command.length == 2) {
            if (!this.model.getLayers().isEmpty()) {
              if (this.model.getLayers().containsKey(command[1])) {
                try {
                  current = command[1];
                } catch (IllegalArgumentException e) {
                  try {
                    view.renderMessage(e.getMessage());
                  } catch (IOException io) {
                    throw new IllegalStateException();
                  }
                }
              }
              else {
                try {
                  view.renderMessage("This layer does not exist.\n");
                }
                catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            } else {
              try {
                view.renderMessage("No layers created.\n");
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid create command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "blur":
          if (command.length == 1) {
            if (current != null) {
              try {
                this.model.replaceImage(current, this.model.blur(current));
              } catch (IllegalArgumentException e) {
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            } else {
              try {
                view.renderMessage("No current set.\n");
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid blur command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "sharpen":
          if (command.length == 1) {
            if (current != null) {
              try {
                this.model.replaceImage(current, this.model.sharpen(current));
              } catch (IllegalArgumentException e) {
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            } else {
              try {
                view.renderMessage("No current set.\n");
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid blur command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "grayscale":
          if (command.length == 1) {
            if (current != null) {
              try {
                this.model.replaceImage(current, this.model.grayscale(current));
              } catch (IllegalArgumentException e) {
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            } else {
              try {
                view.renderMessage("No current set.\n");
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid blur command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "sepia":
          if (command.length == 1) {
            if (current != null) {
              try {
                this.model.replaceImage(current, this.model.sepia(current));
              } catch (IllegalArgumentException e) {
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            } else {
              try {
                view.renderMessage("No current set.\n");
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid blur command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "show":
          if (command.length == 1) {
            if (this.model.getVisibility().contains(current)) {
              try {
                this.model.showLayer(current);
              } catch (IllegalArgumentException e) {
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            } else {
              try {
                view.renderMessage("Layer is already visible.\n");
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid show command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "hide":
          if (command.length == 1) {
            if (!this.model.getVisibility().contains(current)) {
              try {
                this.model.hideLayer(current);
              } catch (IllegalArgumentException e) {
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            } else {
              try {
                view.renderMessage("Layer already hidden.\n");
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid hide command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "save":
          if (command.length == 3) {
            if (current != null) {
              switch (command[1]) {
                case "ppm":
                  try {
                    new PPMFileWriter().writeFile("res/" + command[2] + ".ppm", this.model.getImage(
                        current));
                  } catch (IllegalArgumentException e) {
                    try {
                      view.renderMessage(e.getMessage());
                    } catch (IOException io) {
                      throw new IllegalStateException();
                    }
                  } catch (IOException io) {
                    throw new IllegalStateException();
                  }
                  break;
                case "jpeg":
                  try {
                    new JPEGImageIOWriter()
                        .writeFile("res/" + command[2] + ".jpeg", this.model.getImage(
                            current));
                  } catch (IllegalArgumentException e) {
                    try {
                      view.renderMessage(e.getMessage());
                    } catch (IOException io) {
                      throw new IllegalStateException();
                    }
                  } catch (IOException io) {
                    throw new IllegalStateException();
                  }
                  break;
                case "png":
                  try {
                    new PNGImageIOWriter()
                        .writeFile("res/" + command[2] + ".png", this.model.getImage(
                            current));
                  } catch (IllegalArgumentException e) {
                    try {
                      view.renderMessage(e.getMessage());
                    } catch (IOException io) {
                      throw new IllegalStateException();
                    }
                  } catch (IOException io) {
                    throw new IllegalStateException();
                  }
                  break;
                default:
                  try {
                    view.renderMessage("Invalid file type.\n");
                  } catch (IOException io) {
                    throw new IllegalStateException();
                  }
                  break;
              }
            }
          } else {
            try {
              view.renderMessage("Invalid save command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "saveall":
          if (command.length == 3) {
            if (command[1].equals("jpeg") || command[1].equals("ppm") || command[1].equals("png")) {
              try {
                new MultiLayerImageWriter()
                    .writeFile(command[2], command[1], this.model.getLayers(),
                        this.model.getVisibility());
              } catch (IllegalArgumentException e) {
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            } else {
              try {
                view.renderMessage("Invalid file type.\n");
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid saveall command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        case "addmulti":
          if (command.length == 2) {
            try {
              IMultiLayerReader newMulti = new MultiLayerFileReader();
              Map<String, ImageInterface> newMultiImages = newMulti.readImages(command[1]);
              try {
                this.model.addMultiLayer(newMultiImages, newMulti.readVisibility());
              } catch (IllegalArgumentException e) {
                try {
                  view.renderMessage(e.getMessage());
                } catch (IOException io) {
                  throw new IllegalStateException();
                }
              }
            } catch (IllegalArgumentException e) {
              try {
                view.renderMessage(e.getMessage());
              } catch (IOException io) {
                throw new IllegalStateException();
              }
            }
          } else {
            try {
              view.renderMessage("Invalid addmulti command syntax.\n");
            } catch (IOException io) {
              throw new IllegalStateException();
            }
          }
          break;
        default:
          try {
            view.renderMessage("Unrecognizable command.\n");
          } catch (IOException io) {
            throw new IllegalStateException();
          }
      }

    }

  }
}
