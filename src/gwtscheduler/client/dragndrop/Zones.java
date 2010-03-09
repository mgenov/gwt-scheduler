package gwtscheduler.client.dragndrop;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class Zones {
  public static DragZone getDragZone(){
    DragZoneImpl dragZone = new DragZoneImpl(new DragFrame());
    dragZone.bindDisplay(new DragZoneView());
    return dragZone;
  }
}
