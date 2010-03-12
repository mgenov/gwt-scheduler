package gwtscheduler.client.widgets.view.columns;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface ContentChange {
  void onDrop(int[] newCell, Object droppedObject);

  void onMove(int[] oldCell, int[] newCell, Object droppedObject);
}
