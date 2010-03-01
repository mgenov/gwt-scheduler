package gwtscheduler.client.widgets.view.columns;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class ColumnPanel {
  interface Display{
    
    int getHeight();

    int getWidth();
  }
  public Display display;

  public ColumnPanel() {
  }

  public void bindDisplay(Display  display){
    this.display = display;
  }


}
