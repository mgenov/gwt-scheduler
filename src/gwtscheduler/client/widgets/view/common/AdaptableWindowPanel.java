package gwtscheduler.client.widgets.view.common;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import gwtscheduler.client.widgets.common.event.HasWidgetResizeHandlers;
import gwtscheduler.client.widgets.common.event.WidgetResizeEvent;
import gwtscheduler.client.widgets.common.event.WidgetResizeHandler;

import java.util.Iterator;

/**
 * Defines a panel that occupies just about enough of the visible screen. It can
 * coexist with other hmtml elements or widgets in the same page.
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class AdaptableWindowPanel extends Composite 
implements ResizeHandler, HasWidgets, HasWidgetResizeHandlers {

  /** main container */
  @UiField
  SimplePanel scrollPanel;
  /** wrapper */
  @UiField
  AbsolutePanel container;

  /** ui binder instance */
  private static AdaptableWindowPanelUiBinder uiBinder = GWT.create(AdaptableWindowPanelUiBinder.class);

  /** ui binder interface */
  interface AdaptableWindowPanelUiBinder extends UiBinder<Widget, AdaptableWindowPanel> {
  }

  /**
   * Default constructor.
   */
  public AdaptableWindowPanel() {
    initWidget(uiBinder.createAndBindUi(this));
    container.getElement().getStyle().clearOverflow();

    Window.addResizeHandler(this);
  }

  /**
   * Resizes the main target in order to occupy all available space.
   * @param viewporWidth the available viewport width
   * @param viewportHeight the available viewport height
   */
  void doResize(int viewporWidth, int viewportHeight) {
    int maxWidth = viewporWidth;
    int maxHeight = viewportHeight;
//    int maxWidth = viewporWidth - scrollPanel.getAbsoluteLeft();
//    int maxHeight = viewportHeight - scrollPanel.getAbsoluteTop();
//    int maxWidth =  scrollPanel.getParent().getParent().getOffsetWidth() - scrollPanel.getParent().getAbsoluteLeft(); //- scrollPanel.getAbsoluteLeft();
//    int maxHeight =   scrollPanel.getParent().getParent().getOffsetWidth() -  scrollPanel.getParent().getAbsoluteTop();//viewportHeight - scrollPanel.getAbsoluteTop();
//    int maxWidth = viewporWidth - scrollPanel.getAbsoluteLeft();
//     int maxHeight = viewportHeight - scrollPanel.getAbsoluteTop();

//    maxWidth = maxWidth - Constants.SCROLLBAR_WIDTH();
    maxHeight = maxHeight - 40; // 10px for margin

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

  public void add(Widget w, int left, int top) {
    container.add(w, left, top);
  }

  @Override
  public HandlerRegistration addWidgetResizeHandler(WidgetResizeHandler handler) {
    return addHandler(handler, WidgetResizeEvent.getType());
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
//        final int[] availableSize = DOMUtils.getViewportDimensions();
//        doResize(availableSize[0], availableSize[1]);

        doResize(scrollPanel.getParent().getParent().getParent().getParent().getParent().getParent().getOffsetWidth(), scrollPanel.getParent().getParent().getParent().getParent().getParent().getParent().getOffsetHeight());
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

  @Override
  public void clear() {
    scrollPanel.clear();
  }

  @Override
  public Iterator<Widget> iterator() {
    return scrollPanel.iterator();
  }

  @Override
  public boolean remove(Widget w) {
    return scrollPanel.remove(w);
  }
}
