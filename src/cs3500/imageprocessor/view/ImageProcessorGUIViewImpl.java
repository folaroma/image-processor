package cs3500.imageprocessor.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ImageProcessorGUIViewImpl extends JFrame implements ImageProcessorGUIView,
    ActionListener {
  private JPanel mainPanel;
  private JScrollPane mainScrollPane;
  private JButton loadImageButton;
  private JButton saveImageButton;
  private JButton loadLayersButton;
  private JButton saveLayersButton;
  private final ArrayList<IViewListener> listeners;

  public ImageProcessorGUIViewImpl() {
    super();
    this.listeners = new ArrayList<>();
    setTitle("Image Processor");
    setSize(400, 400);


    mainPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);


    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing an image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    //imagePanel.setMaximumSize(null);
    mainPanel.add(imagePanel);


    JPanel operationsPanel = new JPanel();
    operationsPanel.setBorder(BorderFactory.createTitledBorder("Image Operations"));
    mainPanel.add(operationsPanel);

    // Panel for import/export commands.
    JPanel IOPanel = new JPanel();
    IOPanel.setBorder(BorderFactory.createTitledBorder("Importing/Exporting Images"));
    IOPanel.setLayout(new FlowLayout());
    operationsPanel.add(IOPanel);


    //Buttons
    loadImageButton = new JButton("Load Image Layer");
    loadImageButton.setActionCommand("Open image");
    loadImageButton.addActionListener(this);

    saveImageButton = new JButton("Save Image Layer");
    saveImageButton.setActionCommand("Save image");
    saveImageButton.addActionListener(this);

    loadLayersButton = new JButton("Load Multi Layer Text");
    loadLayersButton.setActionCommand("Open image text");
    loadLayersButton.addActionListener(this);

    saveLayersButton = new JButton("Save Multi Layer Text");
    saveLayersButton.setActionCommand("Save image text");
    saveLayersButton.addActionListener(this);

    IOPanel.add(loadImageButton);
    IOPanel.add(saveImageButton);
    IOPanel.add(loadLayersButton);
    IOPanel.add(saveLayersButton);


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

  }
}
