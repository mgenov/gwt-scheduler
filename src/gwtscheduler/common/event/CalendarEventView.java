package gwtscheduler.common.event;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.cobogw.gwt.user.client.ui.RoundedLinePanel;
import org.cobogw.gwt.user.client.ui.RoundedPanel;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */
public class CalendarEventView extends Composite implements CalendarEvent.Display {

    private Label headerTitle = new Label("Title");
    RoundedLinePanel panel = new RoundedLinePanel(RoundedPanel.ALL, 9);

    public CalendarEventView() {
      panel.setCornerColor("#FAD163");
      initWidget(panel);
      
      VerticalPanel vp = new VerticalPanel();
      vp.setStyleName("cbg-RP");
      vp.setWidth("100%");
      vp.add(headerTitle);
      vp.add(new Label("test1"));
      vp.add(new Label("test2"));
      vp.add(new Label("test3"));
      vp.add(new Label("test4"));
      vp.add(new Label("test5"));

      panel.setWidget(vp);
      this.getElement().getStyle().setZIndex(33);
    }

    @Override
    public void setViewWidth(int width) {
      setWidth(width + "px");
    }

    @Override
    public void setViewHeight(int height) {
      setHeight(height + "px");
    }

    @Override
    public void setHeaderTitle(String title) {
      headerTitle.setText(title);
    }


}
