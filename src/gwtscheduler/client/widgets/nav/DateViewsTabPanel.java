package gwtscheduler.client.widgets.nav;

import gwtscheduler.client.interfaces.events.WidgetResizeEvent;
import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.DOMUtils;
import gwtscheduler.client.widgets.resize.DelegatingRoundedPanel;

import org.cobogw.gwt.user.client.ui.RoundedPanel;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Main navigation panel.
 * 
 * @author malp
 * 
 */
public class DateViewsTabPanel extends DecoratedTabPanel implements BeforeSelectionHandler<Integer> {

	/** static ref to css */
	protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

	/**
	 * Default constructor.
	 */
	public DateViewsTabPanel() {
		super();
		addBeforeSelectionHandler(this);
	}

	public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
		// this fires the resize
		Widget w = getWidget(event.getItem());
		w.fireEvent(new WidgetResizeEvent(DOMUtils.getViewportDimensions()));
		// w.setVisible(true); // triggers resize event
	}

	 @Override
	public void add(Widget w, Widget tabWidget) {
		super.add(createWrapper(w), tabWidget);
	}

	@Override
	public void add(Widget w, String tabText) {
		super.add(createWrapper(w), tabText);
	}

	/**
	 * Creates a rounded panel wrapper for the widget.
	 * 
	 * @param child the widget to wrap
	 * @return the child wrapped in a rounded panel
	 */
	protected Widget createWrapper(Widget child) {
		DelegatingRoundedPanel rp = new DelegatingRoundedPanel(RoundedPanel.ALL, 4);
		rp.setCornerColor("#E8EEF7");
		rp.add(child);
		return rp;
	}

	@Override
	public void add(Widget w, String tabText, boolean asHTML) {
		super.add(createWrapper(w), tabText, asHTML);
	}

	@Override
	public void add(Widget w) {
		super.add(createWrapper(w));
	}

}
