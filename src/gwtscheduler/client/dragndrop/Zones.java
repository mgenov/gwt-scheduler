package gwtscheduler.client.dragndrop;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com) 
 */
public class Zones {
  public static DragZone getDragZone(){
    DragFrame frame = new DragFrame();
    frame.bindDisplay(new DragFrameWidget());
    return getDragZone(frame);
  }

  public static DragZone getDragZone(Frame frame){
    DragZoneImpl dragZone = new DragZoneImpl(frame);
    dragZone.bindDisplay(new DragZoneView());
    return dragZone;
  }
}
