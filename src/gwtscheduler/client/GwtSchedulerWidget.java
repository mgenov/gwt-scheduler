package gwtscheduler.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.SchedulerCssResource;
import gwtscheduler.client.widgets.common.CalendarPresenter;
import gwtscheduler.client.widgets.common.navigation.TabPanelContainer;

import java.util.Iterator;

/**
 * @author mlesikov  {mlesikov@gmail.com}
 */
public class GwtSchedulerWidget extends Composite implements GwtScheduler.Display, HasWidgets {
   /**
   * ui binder interface
   */
  interface GwtSchedulerWidgetUiBinder extends UiBinder<Widget, GwtSchedulerWidget> {
  }

  /**
   * ui binder instance
   */
  private static GwtSchedulerWidgetUiBinder uiBinder = GWT.create(GwtSchedulerWidgetUiBinder.class);

  /**
   * static ref to css
   */
  protected static final SchedulerCssResource CSS = Resources.schedulerCss();

  /**
   * widget delegate
   */
  @UiField
  TabPanelContainer container;


  public GwtSchedulerWidget() {
    initWidget(uiBinder.createAndBindUi(this));
    container.addStyleName(CSS.gwtScheduler());
  }

  @Override
  public void addCalendarDisplay(CalendarPresenter.Display display) {
//    container = new TabPanelContainer();
    container.add((Widget) display);
  }

  @Override
  public void add(Widget w) {
    container.add(w);
  }

  @Override
  public void clear() {
    container.clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return container.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return container.remove(w);  
  }
}
