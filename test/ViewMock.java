import cs3500.imageprocessor.view.IViewListener;
import cs3500.imageprocessor.view.ImageProcessorGUIView;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class ViewMock implements ImageProcessorGUIView {

  private final IViewListener listener;
  private final Appendable ap;


  public ViewMock(IViewListener listener, Appendable ap) {
    this.listener = Objects.requireNonNull(listener);
    this.ap = ap;

  }

  @Override
  public void runGUI() {
  }

  @Override
  public void updateImage(BufferedImage image){
    try {
      ap.append("updated image ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void updateLayers(String layerName){
    try {
      ap.append("updated layers ");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException, IOException {
    ap.append("rendered msg ");
  }

  public void emitLoadScriptEvent() {
    listener.runScriptEvent("");
  }

  public void emitSaveEvent() {
    listener.handleSaveLayerEvent("", "");
  }

  public void emitSaveAllEvent() {
    listener.handleSaveAllLayerEvent("", "");
  }

  public void emitBlurLayerEvent() {
    listener.handleBlurEvent();
  }

  public void emitSharpenLayerEvent() {
    listener.handleSharpenEvent();
  }

  public void emitGrayscaleLayerEvent() {
    listener.handleGrayscaleEvent();
  }

  public void emitSepiaLayerEvent() {
    listener.handleSepiaEvent();
  }

  public void emitDeleteLayerEvent() {
    listener.removeLayerEvent();
  }

  public void emitSelectLayerEvent() {
    listener.setCurrentLayerEvent("");
  }

  public void emitShowLayerEvent() {
    listener.showEvent();
  }

  public void emitHideLayerEvent() {
    listener.hideEvent();
  }

  public void emitLoadImageEvent() {
    listener.handleLoadLayerEvent("", "", "");
  }

  public void emitLoadAllLayerEvent() {
    listener.handleLoadAllLayerEvent("");
  }

  public void emitCheckerboardEvent() {
    listener.handleCheckerboardEvent("", 1, 1, Color.RED, Color.RED);
  }

}
