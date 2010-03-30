/**
 * Simple using drag and drop example.
 *
 * Drag zone is the zone who cares about dragging and dropping objects. To get a instance of DragZone use
 * {@link dragndrop.client.core.Zones}. Read Zones documentation for more information about building a DragZone.
 * Get new DragZone instance:
 * <pre>
 * DragZone dragZone = Zones.getDragZone();
 * </pre>
 * This construct a DragZone with default frame, and default panel for dragging widgets.
 * Draggable objects is any object who implement {@link com.google.gwt.event.dom.client.HasMouseDownHandlers}
 * <pre>
 * class MyWidget extends Composite implements HasMouseDownHandlers {
 * ...
 *   public HandlerRegistration addMouseDownHandler(MouseDownHandler mouseDownHandler) {
 *     return addDomHandler(mouseDownHandler, MouseDownEvent.getType());
 *   }
 * ...
 * }
 * </pre>
 * or {@link dragndrop.client.core.Draggable} (in this example a presenter implements Draggable interface)
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
 * Draggable objects must be registered in DragZone that is draggable so to be able to drag frame for that objects.
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
 * Registering DropZones is with implementing {@link dragndrop.client.core.DropZone} interface by Widget who will accept
 * events when something is dropped. Read {@link dragndrop.client.core.DropZone} for more information abouth drop zone.
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
 * DropZones need to be registered in DragZone for sending events to DragZone.
 * <pre>
 * MyDropZone dropZone = new MyDropZone();
 * dragZone.addDropZone(dropZone);
 * </pre>
 *
 * DragZone need to be attached to some panel.
 * <pre>
 * dragZone.go(RootPanel.get());
 * </pre>
 * 
 */
package dragndrop;
