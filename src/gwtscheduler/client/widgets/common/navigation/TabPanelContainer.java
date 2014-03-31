package gwtscheduler.client.widgets.common.navigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import java.util.Iterator;

/**
 * @author Mihail Lesikov (mlesikov@gmail.com)
 */
public class TabPanelContainer extends Composite implements HasWidgets {
  interface TabPanelContainerImplUiBinder extends UiBinder<Widget, TabPanelContainer> {

  }

  private static TabPanelContainerImplUiBinder binder = GWT.create(TabPanelContainerImplUiBinder.class);

  @UiField
  HTMLPanel panel;

  public TabPanelContainer() {

    initWidget(binder.createAndBindUi(this));

  }

  @Override
  public void add(Widget w) {
    panel.add(w);
  }

  @Override
  public Iterator<Widget> iterator() {
    return panel.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return panel.remove(w);
  }

  @Override
  public void clear() {
    panel.clear();

  }
}