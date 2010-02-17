package gwtscheduler.client.widgets.view.common.lasso;

import gwtscheduler.client.interfaces.LassoStrategy;
import gwtscheduler.client.interfaces.LassoSubject;
import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawEvent;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawHandler;
import gwtscheduler.client.interfaces.uievents.resize.HasWidgetResizeHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.view.common.AdaptableWindowPanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Lasso panel that extends the redrawable panel in order to support lasso-style
 * selection.
 * @author malp
 */
//TODO migrate to MVP
public abstract class LassoAwarePanel extends Composite implements HasWidgetResizeHandlers, HasWidgetRedrawHandlers {
  /** widget impl */
  @UiField
  VerticalPanel impl;
  /** adaptable viewport */
  @UiField
  AdaptableWindowPanel windowPanel;
  /** the lasso widget itself */
  @UiField
  LassoPanel lasso;

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
        DeferredCommand.addCommand(new Command() {
          @Override
          public void execute() {
            //delegates to this widget (self)
            delegateEvent(LassoAwarePanel.this, event); //fires a resize 
            delegateEvent(LassoAwarePanel.this, new WidgetRedrawEvent()); //fires a redraw
          }
        });
      }
    });

    addWidgetResizeHandler(new WidgetResizeHandler() {
      @Override
      public void onResize(WidgetResizeEvent event) {
        positionLasso(lasso, event);
        resizeLasso(lasso, event);
      }
    });
    //don't know why but the UiBinder doesn't assume this
    lasso.getElement().getStyle().setPosition(Position.ABSOLUTE);
    lasso.getElement().getStyle().setZIndex(Constants.LASSO_ZINDEX);
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
   * Indicates if y-axis overflow is to be visible or hidden
   * @return <code>true</code> to show a scroll bar, <code>false</code> to hide
   */
  protected boolean isOverflowY() {
    return true;
  }

  /**
   * Adds a wiget to this panel.
   * @param w the widget
   */
  protected void add(Widget w) {
    impl.add(w);
  }

  /**
   * Adds a wiget to this panel.
   * @param w the widget
   * @param beforeIndex the index
   */
  protected void insert(Widget w, int beforeIndex) {
    impl.insert(w, beforeIndex);
  }

  /**
   * Adds a widget to the window panel.
   * @param w the widget to add
   */
  protected void addToWindow(Widget w) {
    windowPanel.add(w);
  }

  /**
   * Adds an absolutely positioned widget to the window panel
   * @param widget the widget
   * @param left left
   * @param top top
   */
  protected void addToWindow(Widget widget, int left, int top) {
    windowPanel.add(widget, left, top);
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

  /**
   * Responsible for positioning the lasso correctly. This method is fired on
   * viewport resize.
   * @param lasso the lasso
   * @param event the last resize event
   */
  protected abstract void positionLasso(Widget lasso, WidgetResizeEvent event);

  /**
   * Responsible for sizing the lasso appropriately.This method is fired on
   * viewport resize.
   * @param lasso the lasso widget
   * @param event the last resize event
   */
  protected abstract void resizeLasso(Widget lasso, WidgetResizeEvent event);

}
