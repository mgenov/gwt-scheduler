package gwtscheduler.client.widgets.resize;

import org.cobogw.gwt.user.client.ui.RoundedPanel;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

/**
 * Delegates events to childs.
 * 
 * @author malp
 */
public class DelegatingRoundedPanel extends RoundedPanel {

	public DelegatingRoundedPanel() {
		super();
	}

	public DelegatingRoundedPanel(int corners, int cornerHeight) {
		super(corners, cornerHeight);
	}

	public DelegatingRoundedPanel(int corners) {
		super(corners);
	}

	public DelegatingRoundedPanel(Widget w, int corners, int cornerHeight) {
		super(w, corners, cornerHeight);
	}

	public DelegatingRoundedPanel(Widget w, int corners) {
		super(w, corners);
	}

	public DelegatingRoundedPanel(Widget w) {
		super(w);
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		getWidget().fireEvent(event);
	}

}
