package gwtscheduler.client.widgets.view.day;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.view.common.AbstractCompositeDayView;
import gwtscheduler.client.widgets.view.common.AbstractDayView;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * Composite class for day views.
 */
public class CompositeDayView extends AbstractCompositeDayView {

	/** static ref to css */
	protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

	@Override
	protected AbstractDayView createDayView() {
		return new DayView();
	}

	@Override
	protected Widget createTopView(final int columns) {
		FlexTable table = new FlexTable();
		table.addStyleName(CSS.genericContainer());
		table.setWidth("100%");

		table.getCellFormatter().setWidth(0, 0, CSS.titleColumnWidthPx() + "px");
		table.getCellFormatter().setWidth(0, 2, Constants.SCROLLBAR_WIDTH + "px");

		table.setWidget(0, 1, new Label("Today"));
		table.getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);

		return table;
	}

}
