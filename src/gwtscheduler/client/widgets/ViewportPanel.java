package gwtscheduler.client.widgets;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Defines a viewport to calendar events.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class ViewportPanel extends Composite {

    private FlowPanel container;

    /**
     * Default constructor.
     */
    public ViewportPanel() {
        container = new FlowPanel();
        DecoratorPanel decorator = new DecoratorPanel();
        decorator.setWidget(container);
        initWidget(decorator);
    }
}
