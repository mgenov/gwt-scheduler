package dragndrop.client.core;

/**
 * Provides different cursor styles for different events.
 * 
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public interface CursorStyleProvider {
  /**
   * Get style name for default cursor style.
   *
   * @return cursor style. e.g. "default".
   */
  String getDefaultCursorStyle();

  /**
   * Get style name for not allowed cursor style.
   *
   * @return cursor style. e.g. "not-allowed".
   */
  String getNotAllowedStyle();

  /**
   * Get style name for cursor move.
   *
   * @return cursor style.
   */
  String getMoveStyle();

  /**
   * Get style name for pointer cursor style.
   *
   * @return cursor style.
   */
  String getOverDropZoneStyle();
}
