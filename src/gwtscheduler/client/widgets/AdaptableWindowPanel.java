package gwtscheduler.client.widgets;

import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeEvent;
import gwtscheduler.client.interfaces.uievents.resize.WidgetResizeHandler;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.utils.DOMUtils;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Defines a panel that occupies just about enough of the visible screen. It can
 * coexist with other hmtml elements or widgets in the same page.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class AdaptableWindowPanel extends Composite implements ResizeHandler {

  /** main container */
  private ScrollPanel scrollPanel;
  /** wrapper */
  private Panel container;

  /** the minimum size for the target */
  private final int minWidth, minHeight;

  /**
   * Default constructor.
   */
  public AdaptableWindowPanel() {
    this(-1, -1);
  }

  /**
   * Another constructor.
   * @param minWidth the minimum width
   * @param minHeight the minimum height
   */
  public AdaptableWindowPanel(int minWidth, int minHeight) {
    this.minWidth = minWidth;
    this.minHeight = minHeight;

    // we never show hscroll
    scrollPanel = new ScrollPanel();
    initWidget(scrollPanel);

    getElement().getStyle().setProperty("overflowX", "hidden");
    getElement().getStyle().setProperty("position", "relative");

    container = new FlowPanel();
    container.setSize("100%", "100%");
    scrollPanel.add(container);

    Window.addResizeHandler(this);
  }

  /**
   * Resizes the main target in order to occupy all available space.
   * @param viewporWidth the available viewport width
   * @param viewportHeight the available viewport height
   */
  void doResize(int viewporWidth, int viewportHeight) {
    int maxWidth = viewporWidth - getWidget().getAbsoluteLeft();
    int maxHeight = viewportHeight - getWidget().getAbsoluteTop();

    maxWidth = Math.max(maxWidth, minWidth) - Constants.SCROLLBAR_WIDTH;
    maxHeight = Math.max(maxHeight, minHeight) - 10; // 10px for margin

    if (maxWidth > 0) {
      setWidth(maxWidth + "px");
    }
    if (maxHeight > 0) {
      setHeight(maxHeight + "px");
    }
    if (maxWidth > 0 || maxHeight > 0) {
      fireResizeEvent(maxWidth, maxHeight);
    }
  }

  /**
   * Fires the resize events.
   * @param w the width
   * @param h the height
   */
  private void fireResizeEvent(int w, int h) {
    // don't work with negative values
    if (w <= 0 || h <= 0) {
      return;
    }

    WidgetResizeEvent event = new WidgetResizeEvent(w, h);
    fireEvent(event);
  }

  /**
   * Adds a widget to this viewport.
   * @param w the widget
   */
  public void add(Widget w) {
    container.add(w);
  }

  /**
   * Adds a widget to this viewport.
   * @param w the widget
   * @param handler the resize handler
   */
  public void add(Widget w, WidgetResizeHandler handler) {
    add(w);
    addHandler(handler, WidgetResizeEvent.getType());
  }

  public void onResize(final ResizeEvent event) {
    doDeferredResize();
  }

  /**
   * Handles resize through a deferred command.
   */
  public void doDeferredResize() {
    DeferredCommand.addCommand(new Command() {
      public void execute() {
        final int[] availableSize = DOMUtils.getViewportDimensions();
        doResize(availableSize[0], availableSize[1]);
      }
    });
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    doDeferredResize();
  }

  @Override
  protected void onAttach() {
    super.onAttach();
    doDeferredResize();
  }

}
