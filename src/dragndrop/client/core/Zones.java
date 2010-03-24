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
 * Make object draggable. There is two ways. To implements Draggable interface, or to implements HasMouseDownHandler interface.
 * If you use Draggable interface you have more flexibility.
 * </p>
 * <pre>
 *  dragZone.add(Draggable);
 *  dragZone.add(HasMouseDownHandlers, Object dropThisObject);
 * </pre>
 * <p>
 * Add root where drop zones to be searched. All elements in the list will be iterated and all child's on HasWidgets elements will be
 * iterated during search DropZone. If you have some Widget in the tail who doesn't implements HasWidgets interface
 * (something like Composite) the search finished at this widget and widgets attached to him is not searched for drop zones.
 * So make all widgets in tail to implements HasWidgets interface.
 * </p>
 * <pre>
 *  dragZone.addDropZoneRoot(HasWidgets);
 * </pre>
 * <p>
 * Attach something to drag zone. Drag zone is good to be positioned on the back of all widgets. Dragged frame
 * is positioned on the drag zone.
 * </p>
 * <pre>
 *  dragZone.addWidget(mainPanel);
 * </pre>
 * <p>
 * You must attach drag zone somewhere do make it visible.
 * </p>
 * <pre>
 *  dragZone.go(RootPanel.attachResizeHelper());
 * </pre>
 * <p>
 * By default for all dragged widgets used default frame. You can create your own draggable frame by implementing Frame interface and add it when the drag zone is created.
 * <pre>
 * Frame frame = new MyFrame(); // MyFrame implements Frame
 * DragZone dragZone = Zones.getDragZone(frame);
 * </pre>
 * </p>
 *
 * When dragged frame enter in drop zone DragInEvent is fired to DropZone and when dragged frame exit from drop zone DragOutEvent is fired.
 * While the frame is dragging over the drop zone DragOverEvent is fired all the time. When frame is dropped over the drop zone,
 * DropEvent is fired to the drop zone.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class Zones {
  public static final CursorStyleProvider cursorProvider = new CursorStyleProviderImpl();

  public static DragZone getDragZone(){
    return getDragZone(getDragFrame());
  }

  public static DragZone getDragZone(Frame frame){
    return getDragZone(frame, cursorProvider);
  }

  public static DragZone getDragZone(Frame frame, CursorStyleProvider cursorProvider){
    DragZoneImpl dragZone = new DragZoneImpl(frame, cursorProvider);
    dragZone.bindDisplay(new DragZoneView());
    return dragZone;
  }

  public static Frame getDragFrame(){
    return getDragFrame(getFrameDisplay());
  }

  public static Frame getDragFrame(Frame.Display display){
    DragFrame frame = new DragFrame();
    frame.bindDisplay(display);
    return frame;
  }

  public static Frame.Display getFrameDisplay(){
    return new DragFrameWidget();
  }
}
