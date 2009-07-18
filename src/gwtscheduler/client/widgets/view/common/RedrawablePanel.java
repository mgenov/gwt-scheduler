package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawEvent;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawHandler;
import gwtscheduler.client.interfaces.uievents.resize.HasWidgetResizeHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Abstract class for calendars with resize and redraw events.
 * @author malp
 */
public abstract class RedrawablePanel extends Composite implements HasWidgetResizeHandlers, HasWidgetRedrawHandlers {

  /** widget impl */
  private VerticalPanel impl;
  /** adaptable viewport */
  private AdaptableWindowPanel windowPanel;

  /**
   * Default constructor.
   */
  public RedrawablePanel() {
    impl = new VerticalPanel();
    initWidget(impl);

    windowPanel = new AdaptableWindowPanel();
    windowPanel.getElement().getStyle().setProperty("position", "relative");

    // we'll "delegate" the resize to the window panel
    // but the viewport panel will retrieve the available size
    //this is only fired the first time the panel is loaded
    addWidgetResizeHandler(new WidgetResizeHandler() {
      public void onResize(WidgetResizeEvent event) {
        windowPanel.doDeferredResize();
      }
    });
    windowPanel.addWidgetResizeHandler(new WidgetResizeHandler() {
      @Override
      public void onResize(final WidgetResizeEvent event) {
        DeferredCommand.addCommand(new Command() {
          @Override
          public void execute() {
            delegateEvent(RedrawablePanel.this, event);
            delegateEvent(RedrawablePanel.this, new WidgetRedrawEvent());
          }
        });
      }
    });
    add(windowPanel);
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
}
