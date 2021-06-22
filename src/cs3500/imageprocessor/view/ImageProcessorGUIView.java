package cs3500.imageprocessor.view;

public interface ImageProcessorGUIView extends ImageProcessorView {

  void runGUI();

  void addViewListener(IViewListener listener);

  void setCurrentImage();

}
