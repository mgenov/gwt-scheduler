package gwtscheduler.client.widgets.view.common;

import gwtscheduler.client.interfaces.uievents.redraw.HasWidgetRedrawHandlers;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawEvent;
import gwtscheduler.client.interfaces.uievents.redraw.WidgetRedrawHandler;
import gwtscheduler.client.interfaces.uievents.resize.HasWidgetResizeHandlers;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.widgets.AdaptableWindowPanel;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Abstract class for calendars with resize and redraw events.
 * @author malp
 */
public abstract class RedrawableCalendar extends Composite implements HasWidgetResizeHandlers, HasWidgetRedrawHandlers {

  /** widget impl */
  private VerticalPanel impl;
  /** adaptable viewport */
  private AdaptableWindowPanel windowPanel;

  /**
   * Default constructor.
   */
  public RedrawableCalendar() {
    super();
    impl = new VerticalPanel();
    initWidget(impl);

    windowPanel = new AdaptableWindowPanel();
    
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
      public void onResize(WidgetResizeEvent event) {
        DeferredCommand.addCommand(new Command() {
          @Override
          public void execute() {
            delegateEvent(RedrawableCalendar.this, new WidgetRedrawEvent());
          }
        });
      }
    });
  }

  /**
   * Gets the main panel.
   * @return the main panel
   */
  protected Panel getContainer() {
    return impl;
  }

  /**
   * Gets the window panel.
   * @return the window panel
   */
  protected AdaptableWindowPanel getWindowPanel() {
    return windowPanel;
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
