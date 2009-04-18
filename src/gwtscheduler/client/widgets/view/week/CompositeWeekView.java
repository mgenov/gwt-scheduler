package gwtscheduler.client.widgets.view.week;

import gwtscheduler.client.resources.Resources;
import gwtscheduler.client.resources.css.DayWeekCssResource;
import gwtscheduler.client.utils.Constants;
import gwtscheduler.client.widgets.view.common.AbstractCompositeDayView;
import gwtscheduler.client.widgets.view.common.AbstractDayView;

import com.google.gwt.gen2.table.override.client.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 */
public class CompositeWeekView extends AbstractCompositeDayView {

	/** static ref to css */
	protected static final DayWeekCssResource CSS = Resources.dayWeekCss();

	@Override
	protected AbstractDayView createDayView() {
		return new WeekView();
	}

	@Override
	protected Widget createTopView(int columns) {
		FlexTable g = new FlexTable();
		g.getCellFormatter().setWidth(0, 0, CSS.titleColumnWidthPx() + "px");
		g.getCellFormatter().setWidth(0, columns + 2, Constants.SCROLLBAR_WIDTH + "px");

		for (int i = 0; i < columns; i++) {
			g.setWidget(0, 1, new Label("Day" + i));
			g.getFlexCellFormatter()
					.setHorizontalAlignment(0, 1 + i, HasHorizontalAlignment.ALIGN_CENTER);
		}

		return g;
	}

	@Override
	public void setVisible(boolean visible) {
		// triggers resize
		super.setVisible(visible);
		getViewportPanel().setVisible(visible);
	}

}
