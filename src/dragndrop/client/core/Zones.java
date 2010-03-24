package dragndrop.client.core;

/**
 * Build drag zone where object to be dragged.
 * Example code.
 * <p>
 * Getting an instance.
 * </p>
 * <pre>
 *  DragZone dragZone = Zones.getDragZone();
 * </pre>
 * <p>
 * Make object draggable. There are two ways. To implements Draggable interface, or to implements HasMouseDownHandler interface.
 * If you use Draggable interface you have more flexibility.
 * </p>
 * <pre>
 *  dragZone.add(Draggable);
 *  dragZone.add(HasMouseDownHandlers, Object dropThisObject);
 * </pre>
 * <p>
 * Add container where drop zones to be searched. All added containers will be iterated and all child's on HasWidgets elements will be
 * iterated during searching the DropZone. If you have some Widget in the tail who doesn't implements HasWidgets interface
 * (something like Composite) the search finish at this widget and widgets attached to him is not searched for drop zones.
 * So make all widgets in tail to implements HasWidgets interface.
 *
 * For example:
 * In this case DropZone will be found.
 * <pre>
 *  HasWidgets     <--- DropZone Container
 *       |--------HasWidgets
 *       |            |---------HasWidgets
 *       |                          |----------DropZone
 *       |--------HasWidgets
 *                    |.....
 * </pre>
 * But in next example DropZone will not be found.
 * <pre>
 *  HasWidgets
 *       |--------HasWidgets
 *       |            |---------Composite
 *       |                          |-----X----DropZone
 *       |--------HasWidgets
 *                    |.....
 * </pre>
 * </p>
 * <pre>
 *  HasWidgets container = ...
 *  dragZone.addDropZoneContainer(container);
 *
 *  List<HasWidgets> containers = ...
 *  dragZone.addDropZoneContainer(containers);
 * </pre>
 * <p>
 * Attach something to drag zone. Drag zone is good to be positioned on the back of all widgets. Dragged frame
 * is placed on the drag zone.
 * </p>
 * <pre>
 *  dragZone.add(mainPanel);
 * </pre>
 * <p>
 * You must attach drag zone somewhere to make it visible.
 * </p>
 * <pre>
 *  dragZone.go(RootPanel.get("dragPanel"));
 * </pre>
 * <p>
 * By default for all dragged widgets used default frame. You can create your own draggable frame by implementing Frame interface and add it when the drag zone is created.
 * <pre>
 * Frame frame = new MyFrame(); // MyFrame implements Frame
 * DragZone dragZone = Zones.getDragZone(frame);
 * </pre>
 * </p>
 *
 * When dragged frame enter in drop zone DragInEvent is fired to {@link dragndrop.client.core.DropZone} and when dragged frame exit from drop zone DragOutEvent is fired.
 * While the frame is dragging over the drop zone DragOverEvent is fired all the time. When frame is dropped over the drop zone,
 * DropEvent is fired to the drop zone.
 * Read {@link dragndrop.client.core.DropZone} for more information how to use drop zones.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class Zones {
  public static final CursorStyleProvider cursorProvider = new CursorStyleProviderImpl();

  /**
   * Construct DragZone with default drag {@link dragndrop.client.core.Frame}.
   * 
   * @return new DragZone instance.
   */
  public static DragZone getDragZone(){
    return getDragZone(getDragFrame());
  }

  /**
   * Construct DragZone with custom {@link dragndrop.client.core.Frame}. This frame is used as default frame.
   *
   * @param frame used as default frame.
   * @return new DragZone instance.
   */
  public static DragZone getDragZone(Frame frame){
    return getDragZone(frame, cursorProvider);
  }

  /**
   * Construct DragZone with custom {@link dragndrop.client.core.CursorStyleProvider}. This provider provides different
   * cursor styles.
   *
   * @param cursorProvider used for changing cursor style for different events.
   * @return new DragZone instance.
   */
  public static DragZone getDragZone(CursorStyleProvider cursorProvider){
    return getDragZone(getDragFrame(), cursorProvider);
  }

  /**
   * Construct DragZone with custom {@link dragndrop.client.core.Frame} and custom {@link dragndrop.client.core.CursorStyleProvider}.
   * Frame is used as default frame. CursorStyleProvider is used for changing cursor styles for different events.
   * 
   * @param frame used as default frame.
   * @param cursorProvider used for changing cursor style for different events.
   * @return new DragZone instance.
   */
  public static DragZone getDragZone(Frame frame, CursorStyleProvider cursorProvider){
    DragZoneImpl dragZone = new DragZoneImpl(frame, cursorProvider);
    dragZone.bindDisplay(new DragZoneView());
    return dragZone;
  }

  /**
   * Construct default {@link dragndrop.client.core.Frame}. Instance of this frame is used as default frame for dragging.
   *
   * @return new Frame instance.
   */
  public static Frame getDragFrame(){
    return getDragFrame(getFrameDisplay());
  }

  /**
   * Construct {@link dragndrop.client.core.Frame} with custom frame widget.
   *
   * @param display used for a frame view.
   * @return new Frame instance bind to custom widget.
   */
  public static Frame getDragFrame(Frame.Display display){
    DragFrame frame = new DragFrame();
    frame.bindDisplay(display);
    return frame;
  }

  /**
   * Construct default {@link dragndrop.client.core.Frame.Display} widget.
   * 
   * @return new Frame.Display widget.
   */
  public static Frame.Display getFrameDisplay(){
    return new DragFrameWidget();
  }
}
