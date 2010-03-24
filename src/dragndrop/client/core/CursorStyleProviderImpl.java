package dragndrop.client.core;

/**
 * Provides different cursor styles.
 *
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
class CursorStyleProviderImpl implements CursorStyleProvider {

  CursorStyleProviderImpl() {
  }

  /**
   * Get style name for default cursor style.
   *
   * @return cursor style. e.g. "default".
   */
  @Override
  public String getDefaultCursorStyle() {
    return CursorStyle.DEFAULT.toString();
  }

  /**
   * Get style name for not allowed cursor style.
   *
   * @return cursor style. e.g. "not-allowed".
   */
  @Override
  public String getNotAllowedStyle() {
    return CursorStyle.NOT_ALLOWED.toString();
  }

  /**
   * Get style name for cursor move.
   *
   * @return cursor style.
   */
  @Override
  public String getMoveStyle() {
    return CursorStyle.MOVE.toString();
  }

  /**
   * Get style name for pointer cursor style.
   *
   * @return cursor style.
   */
  @Override
  public String getOverDropZoneStyle() {
    return CursorStyle.POINTER.toString();
  }
}
