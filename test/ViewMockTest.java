import static org.junit.Assert.assertEquals;

import cs3500.imageprocessor.view.IViewListener;
import org.junit.Test;

public class ViewMockTest {

  @Test
  public void testLoadButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitLoadImageEvent();

    assertEquals(out.toString(), "handleLoadLayerEvent");

  }

  @Test
  public void testSaveButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitSaveEvent();

    assertEquals(out.toString(), "handleSaveLayerEvent");

  }

  @Test
  public void testSaveAllButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitSaveAllEvent();

    assertEquals(out.toString(), "handleSaveAllLayerEvent");

  }

  @Test
  public void testBlurButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitBlurLayerEvent();

    assertEquals(out.toString(), "handleBlurEvent");

  }

  @Test
  public void testSharpenButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitSharpenLayerEvent();

    assertEquals(out.toString(), "handleSharpenEvent");

  }

  @Test
  public void testGrayscaleButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitGrayscaleLayerEvent();

    assertEquals(out.toString(), "handleGrayscaleEvent");

  }

  @Test
  public void testSepiaButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitSepiaLayerEvent();

    assertEquals(out.toString(), "handleSepiaEvent");

  }

  @Test
  public void testDeleteLayerButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitDeleteLayerEvent();

    assertEquals(out.toString(), "removeLayerEvent");

  }

  @Test
  public void testSelectLayerButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitSelectLayerEvent();

    assertEquals(out.toString(), "setCurrentLayerEvent");

  }

  @Test
  public void testShowLayerButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitShowLayerEvent();

    assertEquals(out.toString(), "showEvent");

  }

  @Test
  public void testHideLayerButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitHideLayerEvent();

    assertEquals(out.toString(), "hideEvent");

  }

  @Test
  public void testLoadScriptButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitLoadScriptEvent();

    assertEquals(out.toString(), "runScriptEvent");

  }

  @Test
  public void testLoadMultiImageButtonClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitLoadAllLayerEvent();

    assertEquals(out.toString(), "handleLoadAllLayerEvent");

  }

  @Test
  public void testCheckerboardClick() {
    Appendable out = new StringBuilder();
    IViewListener controller = new ControllerMock(out);
    ViewMock mockView = new ViewMock(controller, new StringBuilder());
    mockView.emitCheckerboardEvent();

    assertEquals(out.toString(), "handleCheckerboardEvent");

  }




}
