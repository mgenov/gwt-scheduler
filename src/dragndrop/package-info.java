/**
 * Using drag and drop is simply realized with {@link dragndrop.client.core.DragZone} object.
 * DragZone cares about dragging and dropping objects. To get an instance of DragZone use
 * {@link dragndrop.client.core.Zones}. Read Zones documentation for more information about getting different DragZone implementations.
 * Get new DragZone instance with default parameters use:
 * <pre>
 * DragZone dragZone = Zones.getDragZone();
 * </pre>
 * This construct a DragZone with default frame, and default panel for dragging widgets.
 * Draggable objects is any object implementing {@link com.google.gwt.event.dom.client.HasMouseDownHandlers}
 * <pre>
 * class MyWidget extends Composite implements HasMouseDownHandlers {
 * ...
 *   public HandlerRegistration addMouseDownHandler(MouseDownHandler mouseDownHandler) {
 *     return addDomHandler(mouseDownHandler, MouseDownEvent.getType());
 *   }
 * ...
 * }
 * </pre>
 * or implementing {@link dragndrop.client.core.Draggable} (in this example a presenter implements Draggable interface)
 * <pre>
 * class MyDraggable implements Draggable{
 *    interface Display {
 *      ...
 *    }
 *    public void bindDisplay(Display display){
 *      ...
 *    }
 *    &#064;Override
 *    public HasMouseDownHandlers getHasMouseDownHandler() {
 *      return display.getDragField();
 *    }
 *
 *    &#064;Override
 *    public Object getDropObject() {
 *      return this;
 *    }
 *
 *    &#064;Override
 *    public int getWidth() {
 *      return display.getWidth();
 *    }
 *
 *    &#064;Override
 *    public int getHeight() {
 *      return display.getHeight();
 *    }
 *
 *    &#064;Override
 *    public Widget getSourceWidget() {
 *      return (Widget)display;
 *    }
 * ...
 * }
 * </pre>
 * Draggable objects must be registered in DragZone who drag them..
 * Registering HasMouseDownHandlers elements for dragging:
 * <pre>
 * MyWidget myWidget = new MyWidget();
 * Object object = new Object(); This object is dropped over DropZone when drag stops. 
 * dragZone.add(myWidget, object);
 * </pre>
 * Registering Draggable elements for dragging:
 * <pre>
 * MyDraggable draggable = new MyDraggable();
 * dragZone.add(draggable);
 * </pre>
 *
 * Register DropZones by implementing {@link dragndrop.client.core.DropZone} interface. DropZone must be implements by Widget.
 * Widget accept events when something is dropped. Read {@link dragndrop.client.core.DropZone} for more information about drop zone.
 * Warning: If some other object who is not widget implements DropZone, drop zone can't be found.
 * <pre>
 * class MyDropZone extends Composite implements DropZone {
 * ....
 *    &#064;Override
 *    public HandlerRegistration addDropHandler(DropHandler handler) {
 *      return addHandler(handler, DropEvent.TYPE);
 *    }
 *
 *    &#064;Override
 *    public HandlerRegistration addDragInHandler(DragInHandler handler) {
 *      return addHandler(handler, DragInEvent.TYPE);
 *    }
 *
 *    &#064;Override
 *    public HandlerRegistration addDragOutHandler(DragOutHandler handler) {
 *      return addHandler(handler, DragOutEvent.TYPE);
 *    }
 *
 *    &#064;Override
 *    public HandlerRegistration addDragOverHandler(DragOverHandler handler) {
 *      return addHandler(handler, DragOverEvent.TYPE);
 *    }
 * }
 * </pre>
 *
 * Register DropZones in DragZone.
 * <pre>
 * MyDropZone dropZone = new MyDropZone();
 * dragZone.addDropZone(dropZone);
 * </pre>
 * If you have one widget who have more then one DropZone and you don't have direct instance, you can use DragZones containers.
 * DragZone container is simple Widget implements {@link com.google.gwt.user.client.ui.HasWidgets}. All widgets in container is
 * searched for matching DropZone.
 * Registering DropZone containers:
 * <pre>
 * HasWidgets panel = new VerticalPanel();
 * dragZone.addDropZoneContainer(panel);
 * </pre>
 *
 * Attach DragZone to some panel.
 * <pre>
 * dragZone.go(RootPanel.get());
 * </pre>
 * In two words, DragZone only visualise dragged frame over it. Draggable object and drop zones can be attached to some other panel,
 * different from DragZone. Its a good practise do attach DragZone on the back of all widgets but not nessesery.
 *
 * Attach something to DragZone:
 * <pre>
 * dragZone.add(new Label()); // attach an label on DragZone.
 * </pre>
 * Attach something to DragZone on position 10 left and 20 top. This means on the DragZone. Not on the window coordinates.
 * <pre>
 * dragZone.add(new Label(), 10, 20);
 * </pre>
 *
 * Change frame for some Dragged objects can be done by defining own frame object who implements {@link dragndrop.client.core.Frame} interface.
 * <pre>
 * class MyFrame implements Frame{
 * ...
 * {implements Frame methods}
 * ...
 * }
 * </pre>
 * Add this frame to DragZone for different dragged objects can be performed like this:
 * <pre>
 * MyFrame frame = new MyFrame();
 * dragZone.registerFrame(frame, Object.class, MyDraggable.class);
 * </pre>
 */
package dragndrop;
