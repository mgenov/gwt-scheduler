package gwtscheduler.client.widgets;

import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Defines a viewport to calendar events.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class ViewportPanel extends Composite implements ResizeHandler {

	/** indicates if a resize occurred when this widget was not visible */
	private boolean resizeQueued = false;

	/** main container */
	private ScrollPanel container;
	/** the minimum size for the target */
	private final int minWidth, minHeight;
	/** cached values */
	private int lastWidth, lastHeight;
	/** widget collection for resize events */
	private List<IViewportResizeHandler> resizeHandlers;

	/**
	 * Default constructor.
	 */
	public ViewportPanel() {
		minWidth = -1;
		minHeight = -1;

		// we never show hscroll
		container = new ScrollPanel();
		container.getElement().getStyle().setProperty("overflowX", "hidden");
		initWidget(container);
		Window.addResizeHandler(this);
	}

	/**
	 * Another constructor.
	 * 
	 * @param minWidth the minimum width
	 * @param minHeight the minimum height
	 */
	public ViewportPanel(int minWidth, int minHeight) {
		this();
		minWidth = -1;
		minHeight = -1;
	}

	/**
	 * Resizes the main target in order to occupy all available space.
	 * 
	 * @param w the available width
	 * @param h the available height
	 */
	void doResize(int w, int h) {
		// if a resize occurred, we'll queue the process
		if (!isVisible() || !isAttached()) {
			resizeQueued = true;
			return;
		}

		// check if we are not resizing for the same dimensions
		if (lastWidth == w && lastHeight == h) {
			return;
		}

		// 19 is a magical number, should be scrollbar width
		// TODO this code assumes the parent goes to the right of the screen
		int magick = 19;
		int maxWidth = w - getWidget().getAbsoluteLeft() - magick;
		int maxHeight = h - getWidget().getAbsoluteTop() - magick;

		GWT.log("dims: " + maxWidth + ", " + maxHeight, null);
		GWT.log("leftTop: " + getWidget().getAbsoluteLeft() + ", " + getWidget().getAbsoluteTop(), null);
		GWT.log("off: " + getWidget().getOffsetWidth() + ", " + getWidget().getOffsetHeight(), null);
		
		GWT.log("pleftTop: " + getParent().getAbsoluteLeft() + ", " + getParent().getAbsoluteTop(), null);
		GWT.log("poff: " + getParent().getOffsetWidth() + ", " + getParent().getOffsetHeight(), null);
		GWT.log("------", null);

		maxWidth = Math.max(maxWidth, minWidth);
		maxHeight = Math.max(maxHeight, minHeight);

		if (maxWidth > 0) {
			setWidth(maxWidth + "px");
		}
		if (maxHeight > 0) {
			setHeight(maxHeight + "px");
		}
		if (maxWidth > 0 || maxHeight > 0) {
			fireResizeEvent(maxWidth, maxHeight);
		}

		lastWidth = w;
		lastHeight = h;
	}

	/**
	 * Fires the resize events.
	 * 
	 * @param w the width
	 * @param h the height
	 */
	private void fireResizeEvent(int w, int h) {
		// don't work with negative values
		if (w <= 0 || h <= 0) {
			return;
		}

		if (resizeHandlers != null) {
			ViewportResizeEvent event = new ViewportResizeEvent(w, h);
			for (IViewportResizeHandler ira : resizeHandlers) {
				ira.onViewportResize(event);
			}
		}
	}

	/**
	 * Adds a widget to this viewport.
	 * 
	 * @param w the widget
	 */
	public void add(Widget w) {
		container.add(w);
	}

	/**
	 * Adds a widget to this viewport.
	 * 
	 * @param w the widget
	 * @param handler the resize handler
	 */
	public void add(Widget w, IViewportResizeHandler handler) {
		container.add(w);
		if (resizeHandlers == null) {
			resizeHandlers = new ArrayList<IViewportResizeHandler>();
		}
		resizeHandlers.add(handler);
	}

	public void onResize(final ResizeEvent event) {
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				doResize(event.getWidth(), event.getHeight());
				resizeQueued = false;
			}
		});
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible && resizeQueued) {
			DeferredCommand.addCommand(new Command() {
				public void execute() {
					doResize(Window.getClientWidth(), Window.getClientHeight());
					resizeQueued = false;
				}
			});
		}
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				doResize(Window.getClientWidth(), Window.getClientHeight());
			}
		});
	}

}
