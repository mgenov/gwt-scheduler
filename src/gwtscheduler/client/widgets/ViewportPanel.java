package gwtscheduler.client.widgets;

import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.resize.ViewportResizeEvent;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
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

        container = new ScrollPanel();
        DecoratorPanel decorator = new DecoratorPanel();
        decorator.setWidget(container);
        initWidget(decorator);

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
        // we'll have to use a little offset/margin because of borders
        // TODO use css resources to link the border width and this 'margin'
        int maxWidth = w - container.getAbsoluteLeft() - 10;
        int maxHeight = h - container.getAbsoluteTop() - 10;
        maxWidth = Math.max(maxWidth, minWidth);
        maxHeight = Math.max(maxHeight, minHeight);

        if (maxWidth > 0) {
            container.setWidth(maxWidth + "px");
        }
        if (maxHeight > 0) {
            container.setHeight(maxHeight + "px");
        }

        if (resizeHandlers != null) {
            ViewportResizeEvent event = new ViewportResizeEvent(maxWidth, maxHeight);
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

    public void onResize(ResizeEvent event) {
        doResize(event.getWidth(), event.getHeight());
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
