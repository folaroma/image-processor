# USEME

To run the program, either double-click the JAR file, or open a terminal and navigate to the folder
it's in. Then from there, enter `java -jar image-processor.jar -interactive` if you wish to open 
the interactive GUI, `java -jar image-processor.jar -text` if you wish to open an 
interactive text mode, and `java -jar image-processor.jar -script [path-of-script-file]` if you wish
to run a script file.

Commands supported by the Image Processor program:

1. `create [layer name] [layer image]`

   Creates a new layer with the name and the image. Image can either be a generated checkerboard or
   an added image:

    ```
    create first add res/desert.ppm ppm
   
   or

    create first checkerboard 5 5 0 0 0 255 255 255
    ```


2. `checkerboard [rows] [columns] [r] [g] [b] [r] [g] [b]` - only used in `create`

   Generates a checkerboard image:

    ```
    create cb checkerboard 5 5 0 0 0 255 255 255
    ```


3. `add [file directory] [file type]` - only used in `create`:

   Adds an image:

    ```
    create desert add res/desert.ppm ppm
    ```

4. `remove [layer name]` - Removes the layer with given ID:

    ```
    create first add res/desert.ppm ppm
    remove first
    ```

4. `current [layer name]` - Sets the given layer as the current layer:

    ```
    create first add res/desert.ppm ppm
    current first
    ```

5. `blur` - blurs the current layer:

    ```
    create first add res/desert.ppm ppm
    current first
    blur
    ```

6. `sharpen` - sharpens the current layer:

    ```
    create first add res/desert.ppm ppm
    current first
    sharpen
    ```

7. `grayscale` - grayscales the current layer:

    ```
    create first add res/desert.ppm ppm
    current first
    grayscale
    ```

8. `sepia` - sepia the current layer:

    ```
    create first add res/desert.ppm ppm
    current first
    sepia
    ```

9. `show` - makes the current layer visible, only if it is hidden:

    ```
    create first add res/desert.ppm ppm
    current first
    hide
    show
    ```

10. `hide` - makes the current layer invisible, only if it is visible:

    ```
    create first add res/desert.ppm ppm
    current first
    hide
    ```

11. `save [type] [file directory]` - saves the topmost visible layer as an image with the file types
    of PNG, JPEG, and PPM. Paths do not need file extensions:

    ```
    create first add res/desert.ppm ppm
    create second add res/desert.ppm ppm
    current second
    hide
    save png res/oneLayerDesert #this is the "first" layer as it is topmost and visible
    ```

12. `saveall [type] [file directory]` - saves all of the layers into images of the given file type
    and creates a text file representing the whole image with the given file name. This and the
    images are then put into a folder. Paths do not need file extensions:

    ```
    create first add res/desert.ppm ppm
    create second add res/desert.ppm ppm
    saveall jpeg res/twoLayerDesert
    ```


13. `addmulti [file directory]` - Adds a new multi layer image from the given text file and replaces
    the currernt multi layer with this one:

    ```
    create first add res/desert.ppm ppm
    create second add res/desert.ppm ppm
    addmulti res/bugs/bugs.txt
    ```
    
&nbsp;

## Using the GUI

Instructions on how to use the interactive GUI for this program:

&nbsp;

### Buttons:

![Buttons](USEME%20Images/buttons.png)

There are 6 buttons at the bottom of the main menu which handle the filters, transformations, 
and basic layer operations. A layer must be added and selected before these can be used.

&nbsp;

### Image Display:

![Image Display](USEME%20Images/image%20display.png)

In the center is where the topmost visible image is displayed. T All layer operations will also be visible here,
including showing, hiding, deleting, adding layers, etc.

Example Display:
![Example Display](USEME%20Images/example%20display.png)

&nbsp;

### Layer Menu:

![Layer Menu](USEME%20Images/layer%20menu.png)

On the right side of the main menu, there is a menu which displays all the layers.
These blocks are selectable via click, and once clicked you are able to apply any layer/image
operations that you wish. When a layer is selected, it will be outlined in red as shown.

&nbsp;

### Menu Bar:

![Menu Bar](USEME%20Images/menu%20bar.png)

Shown is the menu bar that is displayed at the top of the GUI.
These contain the submenus shown below:
- Under File: Load Multi Layer Image, Load Script, Save Topmost Visible Image, Save All Layers
- Under Edit: Filters(Blur and Sharpen), Transformations(Grayscale and Sepia)
- Under Layers: Add Layer(Load image or checkerboard), Select Layer, Remove Layer, Show Layer, Hide Layer

### Usage:

To Load a new layer from an image file:
Click on the menu item under layers tab, click on Load PPM/PNG/JPEG, select the file, and enter the name for the layer.

To load a new layer from a checkerboard:
Click on the menu item under layers tab, select Load Checkerboard, enter the name of the layer, the number of rows and columns, and select two colors for the board.

To delete a layer:
Click on the layer menu, set a current layer on the side list, and click the remove layer menu item.

To show a layer:
Click on the layer menu, set a current layer on the side list, and click the show layer menu item.

To hide a layer:
Click on the layer menu, set a current layer on the side list, and click the hide layer menu item.

To Filter/Transform:
Select a current layer, click on the buttons at the bottom of the program or under the edit menu bar for the corresponding edit to the image.

To Run a Script:
Click on the file menu, select load script, and select the txt file you want to load.

To Load a multi layer image:
Click on the file menu, select load multi image, and select the txt file you want to load.

How to save the top visible layer:
Click on the file menu, select save, and select save topmost visible layer, then select the save path you want and select the type of image file you want to save it as.

How to save the whole iamge:
Click on the file menu, select save and select save multi layer image, then select the save path and the file type for the images.




