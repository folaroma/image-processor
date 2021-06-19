# USEME

To run the program, either double-click the JAR file, or open a terminal and navigate to the folder it's in. Then from there, enter `java -jar image-processor.jar interactive` or `java -jar image-processor.jar script [filename]`and the program should run accordingly.

Commands supported by the Image Processor program:

1. `create [layer name] [layer image]`

    Creates a new layer with the name and the image. Image can either be a generated checkerboard or an added image:

    
    create first add desert ppm

    create second checkerboard 5 5 0 0 0 255 255 255
    


2. `checkerboard [rows] [columns] [r] [g] [b] [r] [g] [b]` - only used in `create`

    Generates a checkerboard image:

    ```
    create cb checkerboard 5 5 0 0 0 255 255 255
    ```


3. `add [filename] [filetype]` - only used in `create`:

    Adds an image:

    ```
    create desert add desert ppm
    ```

4. `remove [layer name]` - Removes the layer with given ID:

    ```
    create first add desert ppm
    remove first
    ```

4. `current [layer name]` - Sets the given layer as the current layer:

    ```
    create first add desert ppm
    current first
    ```

5. `blur` - blurs the current layer:

    ```
    create first add desert ppm
    current first
    blur
    ```

6. `sharpen` - sharpens the current layer:

    ```
    create first add desert ppm
    current first
    sharpen
    ```

7. `grayscale` - grayscales the current layer:

    ```
    create first add desert ppm
    current first
    grayscale
    ```

8. `sepia` - sepia the current layer:

    ```
    create first add desert ppm
    current first
    sepia
    ```

9. `show` - makes the current layer visible, only if it is hidden:

    ```
    create first add desert ppm
    current first
    hide
    show
    ```

10. `hide` - makes the current layer invisible, only if it is visible:

    ```
    create first add desert ppm
    current first
    hide
    ```

11. `save [type] [filename]` - saves the topmost visible layer as an image with the file types of PNG, JPEG, and PPM:

    ```
    create first add desert ppm
    create second add desert ppm
    current second
    hide
    save png oneLayerDesert
    ```

12. `saveall [type] [filename]` - saves all of the layers into images of the given file type and creates a text file representing the whole image with the given file name:

    ```
    create first add desert ppm
    create second add desert ppm
    saveall jpeg twoLayerDesert
    ```


13. `addmulti [file directory]` - Adds a new multi layer image from the given text file and replaces the currernt multi layer with this one:

    ```
    create first add desert ppm
    create second add desert ppm
    addmulti res/bugs/bugs.txt
    ```
