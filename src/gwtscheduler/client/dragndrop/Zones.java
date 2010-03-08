package gwtscheduler.client.dragndrop;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class Zones {
  public static DragZone getDragZone(int width, int height){
    DragZone dragZone = new DragZoneImpl(new DragZoneView());
    dragZone.setSize(width, height);
    return dragZone;
  }
}
