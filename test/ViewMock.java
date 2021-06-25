import cs3500.imageprocessor.view.IViewListener;
import cs3500.imageprocessor.view.ImageProcessorGUIView;
import java.awt.Color;
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
  private final JFrame guiView;
  private final List<JTextField> layers;
  private final JLayeredPane labels;
  private final JMenuItem deleteLayer;
  private final JMenuItem showLayer;
  private final JMenuItem hideLayer;
  private final JMenuItem selectLayer;
  private final JMenu filters;
  private final JMenu transformations;


  public ViewMock(IViewListener listener) {
    this.listener = Objects.requireNonNull(listener);
    this.guiView = new JFrame();
    this.layers = new ArrayList<>();
    this.labels = new JLayeredPane();
    this.deleteLayer = new JMenuItem();
    this.showLayer = new JMenuItem();
    this.hideLayer = new JMenuItem();
    this.selectLayer = new JMenuItem();
    this.filters = new JMenu();
    this.transformations = new JMenu();

  }

  @Override
  public void runGUI() {

  }

  @Override
  public void updateImage() {

  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException, IOException {

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
