package gwtscheduler.client.widgets.view.common.overlay;

import gwtscheduler.client.interfaces.uievents.lasso.LassoCancelSelectionEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoEndSelectionEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoEventHandler;
import gwtscheduler.client.interfaces.uievents.lasso.LassoStartSelectionEvent;
import gwtscheduler.client.interfaces.uievents.lasso.LassoUpdateSelectionEvent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbsolutePanel;

/**
 * @author malp
 */
public class CalendarEventsPanel extends AbsolutePanel implements LassoEventHandler {

  @Override
  public void onCancelSelection(LassoCancelSelectionEvent event) {
    GWT.log("onCancelSelection", null);
  }

  @Override
  public void onEndSelection(LassoEndSelectionEvent event) {
    GWT.log("onEndSelection", null);
  }

  @Override
  public void onStartSelection(LassoStartSelectionEvent event) {
    GWT.log("onStartSelection", null);
  }

  @Override
  public void onUpdateSelection(LassoUpdateSelectionEvent event) {
    GWT.log("onUpdateSelection", null);
  }
}
