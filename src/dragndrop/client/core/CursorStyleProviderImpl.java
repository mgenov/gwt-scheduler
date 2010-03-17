package dragndrop.client.core;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class CursorStyleProviderImpl implements CursorStyleProvider{
  
  @Override
  public String getDefaultCursor() {
    return CursorStyle.DEFAULT.toString();
  }

  @Override
  public String getNotAllowed() {
    return CursorStyle.NOT_ALLOWED.toString();
  }

  @Override
  public String getMove() {
    return CursorStyle.MOVE.toString();
  }

  @Override
  public String getPointer() {
    return CursorStyle.POINTER.toString();
  }
}
