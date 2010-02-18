package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawEvent;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawHandler;
import gwtscheduler.client.interfaces.uievents.resize.HasWidgetResizeHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.utils.Constants;

import java.util.Iterator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Lasso panel that extends the redrawable panel in order to support lasso-style
 * selection.
 * @author malp
 */
public class LassoAwarePanel extends Composite implements HasWidgets, HasWidgetResizeHandlers, HasWidgetRedrawHandlers {

  /** adaptable viewport */
  @UiField
  AdaptableWindowPanel windowPanel;
  /** the lasso widget itself */
  @UiField
  LassoPanel lasso;

  private boolean isOverflowY = true;
  private LassoHandler handler;

  /** hack */
  private WidgetResizeEvent lastEvt;

  /** ui binder instance */
  private static LassoAwarePanelUiBinder uiBinder = GWT.create(LassoAwarePanelUiBinder.class);

  /** ui binder interface */
  interface LassoAwarePanelUiBinder extends UiBinder<Widget, LassoAwarePanel> {
  }

  /**
   * Default constructor.
   */
  public LassoAwarePanel() {
    initWidget(uiBinder.createAndBindUi(this));
    styleWindowPanel(windowPanel);

    //this makes sure the outer window is properly resized
    addWidgetResizeHandler(new WidgetResizeHandler() {
      public void onResize(WidgetResizeEvent event) {
        lastEvt = event;
        windowPanel.doDeferredResize();
      }
    });

    //this makes sure my childs are resized and redrawed
    windowPanel.addWidgetResizeHandler(new WidgetResizeHandler() {
      @Override
      public void onResize(final WidgetResizeEvent event) {
        if (event.equals(lastEvt)) {
          return;
        }
        doDeferRedrawResize(event, new WidgetRedrawEvent());
      }
    });

    addWidgetResizeHandler(new WidgetResizeHandler() {
      @Override
      public void onResize(WidgetResizeEvent event) {
        if (handler != null) {
          handler.positionLasso(lasso, event);
          handler.resizeLasso(lasso, event);
        }
      }
    });
    //don't know why but the UiBinder doesn't assume this
    lasso.getElement().getStyle().setPosition(Position.ABSOLUTE);
    lasso.getElement().getStyle().setZIndex(Constants.LASSO_ZINDEX);
  }

  /**
   * Defers a resize and then a redraw.
   * @param resize
   * @param redraw
   */
  public void doDeferRedrawResize(final WidgetResizeEvent resize, final WidgetRedrawEvent redraw) {
    DeferredCommand.addCommand(new Command() {
      @Override
      public void execute() {
        delegateEvent(LassoAwarePanel.this, resize); //fires a resize 
        delegateEvent(LassoAwarePanel.this, redraw); //fires a redraw
      }
    });
  }

  /**
   * Sets the lasso handler.
   * @param handler the handler
   */
  public void setLassoHandler(LassoHandler handler) {
    this.handler = handler;
  }

  /**
   * This method is used to style the window panel.
   * @param window the window panel
   */
  private void styleWindowPanel(Widget window) {
    window.getElement().getStyle().setProperty("position", "relative");
    if (!isOverflowY()) {
      DOM.setStyleAttribute(windowPanel.getElement(), "overflowY", "hidden");
    }
  }

  /**
   * @param overflow
   */
  public void setOverflowY(boolean overflow) {
    this.isOverflowY = overflow;
  }

  /**
   * Indicates if y-axis overflow is to be visible or hidden
   * @return <code>true</code> to show a scroll bar, <code>false</code> to hide
   */
  protected boolean isOverflowY() {
    return isOverflowY;
  }

  @Override
  public HandlerRegistration addWidgetRedrawHandler(WidgetRedrawHandler handler) {
    return addHandler(handler, WidgetRedrawEvent.getType());
  }

  @Override
  public HandlerRegistration addWidgetResizeHandler(WidgetResizeHandler handler) {
    return addHandler(handler, WidgetResizeEvent.getType());
  }

  /**
   * Inits the lasso.
   * @param strat the strategy
   * @param subject the subject
   */
  public void initLasso(LassoStrategy strat, LassoSubject subject) {
    lasso.setStrategy(strat);
    lasso.setLassoSubject(subject);
  }

  @Override
  public void add(Widget w) {
    windowPanel.add(w);
  }

  @Override
  public void clear() {
    windowPanel.clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return windowPanel.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return windowPanel.remove(w);
  }

  public interface LassoHandler {
    /**
     * Responsible for positioning the lasso correctly. This method is fired on
     * viewport resize.
     * @param lasso the lasso
     * @param event the last resize event
     */
    void positionLasso(Widget lasso, WidgetResizeEvent event);

    /**
     * Responsible for sizing the lasso appropriately.This method is fired on
     * viewport resize.
     * @param lasso the lasso widget
     * @param event the last resize event
     */
    void resizeLasso(Widget lasso, WidgetResizeEvent event);
  }

}
