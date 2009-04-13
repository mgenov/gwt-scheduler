package gwtscheduler.client.widgets.nav;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;

import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.user.client.ui.DecoratedTabPanel;

/**
 * Main navigation panel.
 * 
 * @author malp
 * 
 */
public class DateViewsTabPanel extends DecoratedTabPanel implements BeforeSelectionHandler<Integer> {

	/** static ref to css */
	protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

	public DateViewsTabPanel() {
		super();
		addBeforeSelectionHandler(this);
	}

	public void onBeforeSelection(BeforeSelectionEvent<Integer> event) {
		// this fires the resize
		// handler
	}

	//
	// @Override
	// public void add(Widget w, Widget tabWidget) {
	// super.add(createWrapper(w), tabWidget);
	// }
	//
	// @Override
	// public void add(Widget w, String tabText) {
	// super.add(createWrapper(w), tabText);
	// }
	//
	// /**
	// * Creates a rounded panel wrapper for the widget.
	// *
	// * @param child the widget to wrap
	// * @return the child wrapped in a rounded panel
	// */
	// protected Widget createWrapper(Widget child) {
	// FlowPanel fp = new FlowPanel();
	// fp.addStyleName(CSS.genericContainer());
	// fp.add(child);
	//
	// RoundedPanel rp = new RoundedPanel(RoundedPanel.ALL, 6);
	// rp.setCornerColor("#E8EEF7");
	// rp.add(fp);
	//
	// return rp;
	// }
	//
	// @Override
	// public void add(Widget w, String tabText, boolean asHTML) {
	// super.add(createWrapper(w), tabText, asHTML);
	// }
	//
	// @Override
	// public void add(Widget w) {
	// super.add(createWrapper(w));
	// }

}
