package dragndrop.client.core;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface CursorStyleProvider {
  String getDefaultCursor();

  String getNotAllowed();

  String getMove();

  String getPointer();
}
