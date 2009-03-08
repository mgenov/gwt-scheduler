package gwtscheduler.client.widgets;

import gwtscheduler.client.utils.JSNIUtils;
import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        if (!isVisible()) {
            resizeQueued = true;
            return;
        }

        // we'll have to use a little offset/margin because of borders
        // TODO use css resources to link the border width and this 'margin'
        
        // 30 is a magical number..
        int magick = 30;
        int maxWidth = w - container.getAbsoluteLeft() - magick;
        int maxHeight = h - container.getAbsoluteTop() - magick;
        maxWidth = Math.max(maxWidth, minWidth);
        maxHeight = Math.max(maxHeight, minHeight);

        if (maxWidth > 0) {
            container.setWidth(maxWidth + "px");
        }
        if (maxHeight > 0) {
            container.setHeight(maxHeight + "px");
        }

        int offset = maybeGetScrollbarOffset();
        if (offset > 0) {
            fireResizeEvent(maxWidth - offset, maxHeight);
        } else {
            fireResizeEvent(maxWidth, maxHeight);
        }
    }

    /**
     * Fires the resize events.
     * 
     * @param w the width
     * @param h the height
     */
    private void fireResizeEvent(int w, int h) {
        if (resizeHandlers != null) {
            ViewportResizeEvent event = new ViewportResizeEvent(w, h);
            for (IViewportResizeHandler ira : resizeHandlers) {
                ira.onViewportResize(event);
            }
        }
    }

    /**
     * Returns the scrollbar width, if the scrollbar is visible. Returns <code>0</code> if no scrollbar is visible.
     * Applies only to vertical scrollbar.
     * 
     * @return the scrollbar width, or 0
     */
    private int maybeGetScrollbarOffset() {
        // if there's a widget taller than the container, a vertical scroll bar will appear
        // this will cause the available width to reduce
        int height = container.getOffsetHeight();
        for (Iterator<Widget> it = container.iterator(); it.hasNext();) {
            Widget w = it.next();
            if (w.getOffsetHeight() >= height + 30) {
                return JSNIUtils.getScrollBarWidth();
            }
        }
        return 0;
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

    public void onResize(ResizeEvent event) {
        doResize(event.getWidth(), event.getHeight());
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (visible && resizeQueued) {
            doResize(Window.getClientWidth(), Window.getClientHeight());
            resizeQueued = false;
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
