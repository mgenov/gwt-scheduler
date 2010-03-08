package gwtscheduler.client.dragndrop;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class Zones {
  public static DragZone getDragZone(){
    DragZone dragZone = new DragZoneImpl(new DragZoneView());
    return dragZone;
  }
}
