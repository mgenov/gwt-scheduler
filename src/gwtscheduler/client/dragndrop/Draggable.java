package gwtscheduler.client.dragndrop;

/**
 * This interface can be implemented by object that need to be dragged over drag area.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface Draggable {
  /**
   * Send draggable to the dragZone on given coordinates.
   * @param dragZone implementation of given dragZone.
   */
  void go(DragZone dragZone);
}
