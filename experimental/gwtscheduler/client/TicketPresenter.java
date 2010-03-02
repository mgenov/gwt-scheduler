package gwtscheduler.client;

/**
 * @author Lazo Apostolovski (lazo.apostolovski@gmail.com)
 */
public class TicketPresenter {
  public interface Display {

    void setText(String string);
  }

  private Display display;

  public TicketPresenter(Display display) {
    this.display = display;
  }

  public void setInfo(String string){
    display.setText(string);
  }

  public int getDuration(){
    return 4;
  }
}
