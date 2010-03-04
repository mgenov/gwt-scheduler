package gwtscheduler.client.dragndrop;

/**
 * This interface can be implemented by object that need to be dragged over drag area.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface Draggable {
  /**
   * Send draggable to the dragger on given coordinates.
   * @param dragger implementation of given dragger.
   * @param left coordinate from left.
   * @param top coordinate from top.
   */
  void go(Dragger dragger, int left, int top);
}
