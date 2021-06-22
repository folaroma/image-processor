package cs3500.imageprocessor.view;

import cs3500.imageprocessor.controller.ImageProcessorGUIController;
import cs3500.imageprocessor.model.images.ImageInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import swingdemo.SwingFeaturesFrame;

public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView,
    ActionListener {
  private JPanel mainPanel;
  private JMenuBar menuBar;
  private JPanel rightPanel;

  private JLabel layers;

  private JMenu file;
  private JMenu edit;
  private JMenu layer;

  private JMenuItem newImage;
  private JMenuItem open;
  private JMenuItem load;
  private JMenuItem save;
  private JMenuItem saveAs;

  private JMenu filters;
  private JMenu transformations;

  private JMenuItem blur;
  private JMenuItem sharpen;
  private JMenuItem grayscale;
  private JMenuItem sepia;

  private JMenuItem selectLayer;
  private JMenuItem addLayer;
  private JMenuItem deleteLayer;
  private JMenuItem replaceLayer;

  private JMenuItem showLayer;
  private JMenuItem hideLayer;

  private JScrollPane mainScrollPane;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton grayscaleButton;
  private JButton sepiaButton;

  private JButton showButton;
  private JButton hideButton;

  private final ArrayList<IViewListener> listeners;

  public ImageProcessorGUIViewImpl() {
    super();
    this.listeners = new ArrayList<>();
    setTitle("Image Processor");
    setSize(1200, 800);

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

    newImage = new JMenuItem("New...");
    newImage.getAccessibleContext().setAccessibleDescription("New Image");
    file.add(newImage);

    open = new JMenuItem("Open...");
    open.getAccessibleContext().setAccessibleDescription("Open Image");
    file.add(open);

    load = new JMenuItem("Load Script...");
    load.getAccessibleContext().setAccessibleDescription("Load a Script");
    file.add(load);

    JSeparator s1 = new JSeparator();
    s1.setOrientation(SwingConstants.HORIZONTAL);

    file.add(s1);

    save = new JMenuItem("Save");
    save.getAccessibleContext().setAccessibleDescription("Save Image");
    file.add(save);

    saveAs = new JMenuItem("Save As...");
    saveAs.getAccessibleContext().setAccessibleDescription("Save Image As...");
    file.add(saveAs);

    menuBar.add(file);


    edit = new JMenu("Edit");
    edit.getAccessibleContext().setAccessibleDescription("Edit Menu");

    filters = new JMenu("Filters");
    filters.getAccessibleContext().setAccessibleDescription("Filters");
    blur = new JMenuItem("Blur");
    blur.getAccessibleContext().setAccessibleDescription("Blur");
    filters.add(blur);
    sharpen = new JMenuItem("Sharpen");
    sharpen.getAccessibleContext().setAccessibleDescription("Sharpen");
    filters.add(sharpen);
    edit.add(filters);

    transformations = new JMenu("Transform");
    transformations.getAccessibleContext().setAccessibleDescription("Color Transformations");
    grayscale = new JMenuItem("Grayscale");
    grayscale.getAccessibleContext().setAccessibleDescription("Grayscale");
    transformations.add(grayscale);
    sepia = new JMenuItem("Sepia");
    sepia.getAccessibleContext().setAccessibleDescription("Sepia");
    transformations.add(sepia);
    edit.add(transformations);

    menuBar.add(edit);


    layer = new JMenu("Layer");
    layer.getAccessibleContext().setAccessibleDescription("Layer Menu");

    addLayer = new JMenuItem("Add Layers...");
    addLayer.getAccessibleContext().setAccessibleDescription("Add a Layer or Layers");
    layer.add(addLayer);

    deleteLayer = new JMenuItem("Delete Layer...");
    deleteLayer.getAccessibleContext().setAccessibleDescription("Delete a Layer or Layers");
    layer.add(deleteLayer);

    selectLayer = new JMenuItem("Select Layer...");
    selectLayer.getAccessibleContext().setAccessibleDescription("Select a Layer");
    layer.add(selectLayer);

    replaceLayer = new JMenuItem("Replace Layer...");
    replaceLayer.getAccessibleContext().setAccessibleDescription("Replace a Layer");
    layer.add(replaceLayer);

    JSeparator s2 = new JSeparator();
    s2.setOrientation(SwingConstants.HORIZONTAL);
    layer.add(s2);

    showLayer = new JMenuItem("Show Layer");
    showLayer.getAccessibleContext().setAccessibleDescription("Show Selected Layer");
    layer.add(showLayer);

    hideLayer = new JMenuItem("Hide Layer");
    hideLayer.getAccessibleContext().setAccessibleDescription("Hide Selected Layer");
    layer.add(hideLayer);

    menuBar.add(layer);

    mainPanel.add(menuBar, BorderLayout.PAGE_START);


    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel, BorderLayout.CENTER);


    JPanel operationsPanel = new JPanel();
    operationsPanel.setBorder(BorderFactory.createTitledBorder("Image Operations"));
    mainPanel.add(operationsPanel, BorderLayout.PAGE_END);

    // Panel for import/export commands.
    JPanel IOPanel = new JPanel();
    IOPanel.setLayout(new GridLayout(1, 6, 10, 10));
    operationsPanel.add(IOPanel);


    JLayeredPane labels = new JLayeredPane();
    labels.setLayout(new FlowLayout());
    labels.setPreferredSize(new Dimension(200, 400));

    JLabel layers = new JLabel();
    layers.setText("1");
    layers.setPreferredSize(new Dimension(150, 50));
    layers.setHorizontalAlignment(JLabel.CENTER);
    layers.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    labels.add(layers, 1,0);

    JLabel layers2 = new JLabel();
    layers2.setText("2");
    layers2.setPreferredSize(new Dimension(150, 50));
    layers2.setHorizontalAlignment(JLabel.CENTER);
    layers2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    labels.add(layers2, 2,0);

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


    IOPanel.add(blurButton);
    IOPanel.add(sharpenButton);
    IOPanel.add(grayscaleButton);
    IOPanel.add(sepiaButton);

    IOPanel.add(showButton);
    IOPanel.add(hideButton);



  }

  @Override
  public void addViewListener(IViewListener listener) {
    this.listeners.add(listener);

  }

  @Override
  public void renderMessage(String msg) throws IllegalArgumentException, IOException {

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Open image")) {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter filter = new FileNameExtensionFilter(
          "JPEG, PNG, & PPM Images", "jpg", "png", "ppm");
      fileChooser.setFileFilter(filter);
      int retvalue = fileChooser.showOpenDialog(ImageProcessorGUIViewImpl.this);
      if (retvalue == JFileChooser.APPROVE_OPTION) {
        File f = fileChooser.getSelectedFile();
      }
    }
  }
}
