package dragndrop.client.core;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public enum CursorStyle {
  NOT_ALLOWED("not-allowed"), DEFAULT ("default"), MOVE("move"), POINTER("pointer");

  private final String cursorStyle;

  CursorStyle() {
    this("default"); // by default is default
  }

  CursorStyle(String cursorStyle) {
    this.cursorStyle = cursorStyle;
  }


  @Override
  public String toString() {
    return cursorStyle;
  }
}
