package gwtscheduler.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
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
  protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

  /**
   * widget delegate
   */
  @UiField
  DecoratedTabPanel tabsPanel;


  public GwtSchedulerWidget() {
    initWidget(uiBinder.createAndBindUi(this));

  }


  @Override
  public void selectTab(int i) {
    tabsPanel.selectTab(i);
  }

  @Override
  public void add(CalendarPresenter.Display display, String title) {
    TabPanelContainer container = new TabPanelContainer();
    container.add((Widget) display);

    tabsPanel.add(container, title);
  }

  @Override
  public void addBeforeSelectionHandler(BeforeSelectionHandler<Integer> handler) {
    tabsPanel.addBeforeSelectionHandler(handler);
  }

  @Override
  public void add(Widget widget) {
    tabsPanel.add(widget);
  }

  @Override
  public void clear() {
    tabsPanel.clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return tabsPanel.iterator();
  }

  @Override
  public boolean remove(Widget widget) {
    return tabsPanel.remove(widget);
  }
}
