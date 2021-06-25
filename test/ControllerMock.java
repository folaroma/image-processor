import cs3500.imageprocessor.controller.ImageProcessorController;
import cs3500.imageprocessor.view.IViewListener;
import cs3500.imageprocessor.view.ImageProcessorGUIView;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ControllerMock implements ImageProcessorController, IViewListener, ActionListener {

  private final Appendable log;

  public ControllerMock(Appendable log) {
    this.log = Objects.requireNonNull(log);

  }

  @Override
  public BufferedImage getTopVisibleLayer() {
    return null;
  }

  @Override
  public String getCurrentLayerID() {
    return null;
  }

  @Override
  public void handleSaveLayerEvent(String fileName, String fileType) {
    try {
      this.log.append("handleSaveLayerEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleLoadLayerEvent(String fileName, String fileType, String layerName) {
    try {
      this.log.append("handleLoadLayerEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleCheckerboardEvent(String layerName, int rows, int columns, Color color1,
      Color color2) {
    try {
      this.log.append("handleCheckerboardEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleSaveAllLayerEvent(String fileName, String fileType) {
    try {
      this.log.append("handleSaveAllLayerEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<String> handleLoadAllLayerEvent(String filename) {
    try {
      this.log.append("handleLoadAllLayerEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public void handleBlurEvent() {
    try {
      this.log.append("handleBlurEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleSharpenEvent() {
    try {
      this.log.append("handleSharpenEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleGrayscaleEvent() {
    try {
      this.log.append("handleGrayscaleEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleSepiaEvent() {
    try {
      this.log.append("handleSepiaEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void showEvent() {
    try {
      this.log.append("showEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void hideEvent() {
    try {
      this.log.append("hideEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void removeLayerEvent() {
    try {
      this.log.append("removeLayerEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void setCurrentLayerEvent(String layerID) {
    try {
      this.log.append("setCurrentLayerEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void runScriptEvent(String filename) {
    try {
      this.log.append("runScriptEvent");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getTopmostVisibleLayerID() {
    return null;
  }

  @Override
  public void startEditor() throws IllegalStateException {
  }

  @Override
  public void actionPerformed(ActionEvent e) {
  }
}
