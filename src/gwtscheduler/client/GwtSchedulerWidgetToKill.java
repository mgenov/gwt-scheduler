package gwtscheduler.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
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
public class GwtSchedulerWidgetToKill extends Composite implements GwtSchedulerToKill.Display, HasWidgets {

  /**
   * ui binder interface
   */
  interface GwtSchedulerWidgetUiBinder extends UiBinder<Widget, GwtSchedulerWidgetToKill> {
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
  TabLayoutPanel tabsPanel;


  public GwtSchedulerWidgetToKill() {
    initWidget(uiBinder.createAndBindUi(this));
    tabsPanel.addStyleName(CSS.gwtScheduler());
//    tabsPanel.setStyleName(CSS.gwtScheduler());
//    tabsPanel.getTabBar().setStyleName(CSS.gwtSchedulerTabBar());
//    tabsPanel.getDeckPanel().removeStyleName("gwt-TabPanelBottom");
//    tabsPanel.getTabBar().removeStyleName("gwt-DecoratedTabBar ");
//    tabsPanel.getDeckPanel().setStyleName(CSS.gwtSchedulerDeckPanel());
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
