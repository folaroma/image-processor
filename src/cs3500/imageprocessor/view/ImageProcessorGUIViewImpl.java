package cs3500.imageprocessor.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView,
    ActionListener {

  private final JPanel mainPanel;
  private final JMenuBar menuBar;
  private final JScrollPane imageScrollPane;
  private final JLabel imageLabel;
  private final List<JTextField> layers;
  private final JMenu file;
  private final JMenu edit;
  private final JMenu layer;
  private final JMenuItem load;
  private final JMenuItem save;
  private final JMenu filters;
  private final JMenu transformations;
  private final JMenuItem blur;
  private final JMenuItem sharpen;
  private final JMenuItem grayscale;
  private final JMenuItem sepia;
  private final JMenuItem selectLayer;
  private final JMenuItem addLayer;
  private final JMenuItem loadMulti;
  private final JMenuItem deleteLayer;
  private final JMenuItem showLayer;
  private final JMenuItem hideLayer;
  private final JScrollPane mainScrollPane;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton grayscaleButton;
  private final JButton sepiaButton;
  private final JButton showButton;
  private final JButton hideButton;
  private final IViewListener listener;
  private final JPanel imagePanel;
  private final JPanel operationsPanel;
  private final JPanel ioPanel;
  private final JLayeredPane labels;
  private BufferedImage topImage;

  public ImageProcessorGUIViewImpl(IViewListener listener) {
    super();
    this.listener = listener;
    this.topImage = listener.getTopVisibleLayer();
    setTitle("Image Processor");
    setSize(1200, 800);
    this.layers = new ArrayList<>();

    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BorderLayout());
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    menuBar = new JMenuBar();

    file = new JMenu("File");
    file.getAccessibleContext().setAccessibleDescription(
        "File Menu");

    loadMulti = new JMenuItem("Load Layered Image...");
    loadMulti.getAccessibleContext().setAccessibleDescription("Load a Multi-Layered Image");
    loadMulti.setActionCommand("Load Multi");
    loadMulti.addActionListener(this);
    file.add(loadMulti);

    load = new JMenuItem("Load Script...");
    load.getAccessibleContext().setAccessibleDescription("Load a Script");
    load.setActionCommand("Load Script");
    load.addActionListener(this);
    file.add(load);

    save = new JMenuItem("Save...");
    save.getAccessibleContext().setAccessibleDescription("Save Image");
    save.setActionCommand("Save");
    save.addActionListener(this);
    save.setEnabled(false);
    file.add(save);

    menuBar.add(file);

    edit = new JMenu("Edit");
    edit.getAccessibleContext().setAccessibleDescription("Edit Menu");

    filters = new JMenu("Filters");
    filters.getAccessibleContext().setAccessibleDescription("Filters");
    filters.setEnabled(false);

    blur = new JMenuItem("Blur");
    blur.getAccessibleContext().setAccessibleDescription("Blur");
    blur.setActionCommand("Blur");
    blur.addActionListener(this);
    filters.add(blur);

    sharpen = new JMenuItem("Sharpen");
    sharpen.getAccessibleContext().setAccessibleDescription("Sharpen");
    sharpen.setActionCommand("Sharpen");
    sharpen.addActionListener(this);
    filters.add(sharpen);
    edit.add(filters);

    transformations = new JMenu("Transform");
    transformations.getAccessibleContext().setAccessibleDescription("Color Transformations");
    transformations.setEnabled(false);

    grayscale = new JMenuItem("Grayscale");
    grayscale.getAccessibleContext().setAccessibleDescription("Grayscale");
    grayscale.setActionCommand("Grayscale");
    grayscale.addActionListener(this);
    transformations.add(grayscale);

    sepia = new JMenuItem("Sepia");
    sepia.getAccessibleContext().setAccessibleDescription("Sepia");
    sepia.setActionCommand("Sepia");
    sepia.addActionListener(this);
    transformations.add(sepia);
    edit.add(transformations);

    menuBar.add(edit);

    layer = new JMenu("Layer");
    layer.getAccessibleContext().setAccessibleDescription("Layer Menu");

    addLayer = new JMenuItem("Add Layer...");
    addLayer.getAccessibleContext().setAccessibleDescription("Add a Layer");
    addLayer.setActionCommand("Add Layer");
    addLayer.addActionListener(this);
    layer.add(addLayer);

    deleteLayer = new JMenuItem("Delete Layer");
    deleteLayer.getAccessibleContext().setAccessibleDescription("Delete Current Layer");
    deleteLayer.setActionCommand("Delete Layer");
    deleteLayer.addActionListener(this);
    deleteLayer.setEnabled(false);
    layer.add(deleteLayer);

    selectLayer = new JMenuItem("Select Layer...");
    selectLayer.getAccessibleContext().setAccessibleDescription("Select a Layer");
    selectLayer.setActionCommand("Select Layer");
    selectLayer.addActionListener(this);
    selectLayer.setEnabled(false);
    layer.add(selectLayer);

    JSeparator s2 = new JSeparator();
    s2.setOrientation(SwingConstants.HORIZONTAL);
    layer.add(s2);

    showLayer = new JMenuItem("Show Layer");
    showLayer.getAccessibleContext().setAccessibleDescription("Show Selected Layer");
    showLayer.setActionCommand("Show");
    showLayer.addActionListener(this);
    showLayer.setEnabled(false);
    layer.add(showLayer);

    hideLayer = new JMenuItem("Hide Layer");
    hideLayer.getAccessibleContext().setAccessibleDescription("Hide Selected Layer");
    hideLayer.setActionCommand("Hide");
    hideLayer.addActionListener(this);
    hideLayer.setEnabled(false);
    layer.add(hideLayer);

    menuBar.add(layer);

    mainPanel.add(menuBar, BorderLayout.PAGE_START);

    imagePanel = new JPanel();
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel, BorderLayout.CENTER);

    imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon(this.topImage));
    imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imageLabel.setHorizontalAlignment(imageScrollPane.getWidth() / 2);
    imagePanel.add(imageScrollPane, BorderLayout.CENTER);

    operationsPanel = new JPanel();
    operationsPanel.setBorder(BorderFactory.createTitledBorder("Image Operations"));
    mainPanel.add(operationsPanel, BorderLayout.PAGE_END);

    // Panel for import/export commands.
    ioPanel = new JPanel();
    ioPanel.setLayout(new GridLayout(1, 6, 10, 10));
    operationsPanel.add(ioPanel);

    labels = new JLayeredPane();
    labels.setLayout(new FlowLayout());
    labels.setPreferredSize(new Dimension(200, 400));

    JScrollPane labelScroll = new JScrollPane(labels);

    mainPanel.add(labelScroll, BorderLayout.LINE_END);

    //Buttons

    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    blurButton.addActionListener(this);

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    sharpenButton.addActionListener(this);

    grayscaleButton = new JButton("Grayscale");
    grayscaleButton.setActionCommand("Grayscale");
    grayscaleButton.addActionListener(this);

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    sepiaButton.addActionListener(this);

    showButton = new JButton("Show");
    showButton.setActionCommand("Show");
    showButton.addActionListener(this);

    hideButton = new JButton("Hide");
    hideButton.setActionCommand("Hide");
    hideButton.addActionListener(this);

    ioPanel.add(blurButton);
    ioPanel.add(sharpenButton);
    ioPanel.add(grayscaleButton);
    ioPanel.add(sepiaButton);

    ioPanel.add(showButton);
    ioPanel.add(hideButton);


  }

  @Override
  public void runGUI() {
    ImageProcessorGUIViewImpl.setDefaultLookAndFeelDecorated(false);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  @Override
  public void updateImage() {
    this.topImage = listener.getTopVisibleLayer();
    this.imageLabel.setIcon(new ImageIcon(this.topImage));
    save.setEnabled(true);
    repaint();
    revalidate();
  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException {
    JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Load Script":
        emitLoadScriptEvent();
        break;
      case "Save":
        emitSaveEvent();
        break;
      case "Blur":
        emitBlurLayerEvent();
        break;
      case "Sharpen":
        emitSharpenLayerEvent();
        break;
      case "Grayscale":
        emitGrayscaleLayerEvent();
        break;
      case "Sepia":
        emitSepiaLayerEvent();
        break;
      case "Add Layer":
        emitLoadImageEvent();
        break;
      case "Delete Layer":
        emitDeleteLayerEvent();
        break;
      case "Select Layer":
        emitSelectLayerEvent();
        break;
      case "Show":
        emitShowLayerEvent();
        break;
      case "Hide":
        emitHideLayerEvent();
        break;
    }
  }

  private void emitLoadScriptEvent() {
    listener.runScriptEvent();
  }

  private void emitSaveEvent() {
    if (!this.layers.isEmpty()) {
      String[] optionsSaveType = {"Save Topmost Visible Layer", "Save Entire Image"};
      int typeSaveValue = JOptionPane.showOptionDialog(this, "Choose Save Type", "Save",
          JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsSaveType,
          null);

      if (typeSaveValue != -1) {
        String[] optionsFileType = {"PPM", "PNG", "JPEG"};
        int filetypeValue = JOptionPane
            .showOptionDialog(this, "Choose the file type to save as", "File Types",
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                optionsFileType,
                null);
        final JFileChooser fileChooser = new JFileChooser(".");
        if (typeSaveValue == 0) {
          if (filetypeValue != -1) {
            if (filetypeValue == 0) {
              FileNameExtensionFilter filter = new FileNameExtensionFilter(
                  "PPM", "ppm");
              fileChooser.setFileFilter(filter);
            } else if (filetypeValue == 1) {
              FileNameExtensionFilter filter = new FileNameExtensionFilter(
                  "PNG", "png");
              fileChooser.setFileFilter(filter);
            } else if (filetypeValue == 2) {
              FileNameExtensionFilter filter = new FileNameExtensionFilter(
                  "JPEG", "jpeg");
              fileChooser.setFileFilter(filter);
            }

            int retvalue = fileChooser.showOpenDialog(this);

            if (retvalue == JFileChooser.APPROVE_OPTION) {
              File f = fileChooser.getSelectedFile();
              listener.handleSaveLayerEvent(f.getAbsolutePath(), optionsFileType[filetypeValue]);
            }
          }
        } else {
          if (filetypeValue != -1) {
              FileNameExtensionFilter filter = new FileNameExtensionFilter(
                  "TXT", "txt");
              fileChooser.setFileFilter(filter);
            int retvalue = fileChooser.showOpenDialog(this);
            if (retvalue == JFileChooser.APPROVE_OPTION) {
              File f = fileChooser.getSelectedFile();
              listener.handleSaveAllLayerEvent(f.getAbsolutePath(), optionsFileType[filetypeValue]);
            }
          }
        }
      }
    } else {
      JOptionPane.showMessageDialog(null, "Add an image before saving");
    }
  }

  private void emitBlurLayerEvent() {
    listener.handleBlurEvent(listener.getCurrentLayerID());
    this.updateImage();
  }

  private void emitSharpenLayerEvent() {
    listener.handleSharpenEvent(listener.getCurrentLayerID());
    this.updateImage();
  }

  private void emitGrayscaleLayerEvent() {
    listener.handleGrayscaleEvent(listener.getCurrentLayerID());
    this.updateImage();
  }

  private void emitSepiaLayerEvent() {
    listener.handleSepiaEvent(listener.getCurrentLayerID());
    this.updateImage();
  }

  private void emitDeleteLayerEvent() {
    listener.removeLayerEvent();
    this.layers.removeIf(jtf -> jtf.getText().equals(this.listener.getCurrentLayerID()));
    this.labels.removeAll();

    for (JTextField jtf : this.layers) {
      this.labels.add(jtf);
    }

    this.deleteLayer.setEnabled(false);
    this.filters.setEnabled(false);
    this.transformations.setEnabled(false);
    this.showLayer.setEnabled(false);
    this.hideLayer.setEnabled(false);

    if (this.layers.isEmpty()) {
      this.selectLayer.setEnabled(false);
    }

    this.updateImage();

  }

  private void emitSelectLayerEvent() {
    String layerName = JOptionPane.showInputDialog("Layer Name");
    if (!layerName.equals("")) {
      listener.setCurrentLayerEvent(layerName);
      for (JTextField jtf : this.layers) {
        if (!jtf.getText().equals(layerName)) {
          jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
          jtf.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
      }
    }
    this.deleteLayer.setEnabled(true);
    this.filters.setEnabled(true);
    this.transformations.setEnabled(true);
    this.showLayer.setEnabled(true);
    this.hideLayer.setEnabled(true);
  }

  private void emitShowLayerEvent() {
    listener.showEvent();
    this.updateImage();
  }

  private void emitHideLayerEvent() {
    listener.hideEvent();
    this.updateImage();
  }

  private void emitLoadImageEvent() {
    String[] optionsTypeImage = {"Add Image", "Generate Checkerboard"};
    int typeImageValue = JOptionPane
        .showOptionDialog(this, "Choose Type of Image to Add", "Add Layer",
            JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsTypeImage,
            null);

    if (typeImageValue == 0) {
      String[] optionsFileType = {"PPM", "PNG", "JPEG"};
      int filetypeValue = JOptionPane
          .showOptionDialog(this, "Please choose the file type to import", "File Types",
              JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, optionsFileType,
              null);

      if (filetypeValue != -1) {
        String layerName = JOptionPane.showInputDialog("Please enter the name of the layer.");

        if (layerName != null) {
          final JFileChooser fileChooser = new JFileChooser(".");

          if (filetypeValue == 0) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PPM", "ppm");
            fileChooser.setFileFilter(filter);
          } else if (filetypeValue == 1) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG", "png");
            fileChooser.setFileFilter(filter);
          } else if (filetypeValue == 2) {
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPEG", "jpeg");
            fileChooser.setFileFilter(filter);
          }

          int retvalue = fileChooser.showOpenDialog(this);

          if (retvalue == JFileChooser.APPROVE_OPTION) {
            File f = fileChooser.getSelectedFile();
            listener
                .handleLoadLayerEvent(f.getAbsolutePath(), optionsFileType[filetypeValue],
                    layerName);

            this.updateImage();

            JTextField layerLabel = new JTextField();
            layerLabel.setText(layerName);
            layerLabel.setPreferredSize(new Dimension(150, 50));
            layerLabel.setHorizontalAlignment(JLabel.CENTER);
            layerLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            layerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            layerLabel.setEditable(false);

            this.layers.add(layerLabel);
            selectLayer.setEnabled(true);


            layerLabel.addMouseListener(new MouseAdapter() {
              @Override
              public void mousePressed(MouseEvent e) {
                listener.setCurrentLayerEvent(layerName);
                deleteLayer.setEnabled(true);
                showLayer.setEnabled(true);
                hideLayer.setEnabled(true);
                filters.setEnabled(true);
                transformations.setEnabled(true);

                for (JTextField jtf : layers) {
                  if (!jtf.equals(layerLabel)) {
                    jtf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                  } else {
                    jtf.setBorder(BorderFactory.createLineBorder(Color.RED));
                  }
                }
              }
            });
            labels.add(layerLabel, labels.getComponentCount() + 1, 0);
            repaint();
            revalidate();
          }
        }
      }
    }

  }

}
