package gwtscheduler.client.widgets.view.day;

import gwtscheduler.client.widgets.view.common.AbstractDayView;

/**
 * View class for days.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class DayView extends AbstractDayView {

	/**
	 * Default constructor.
	 */
	public DayView() {
		super();
	}

	@Override
	protected int getColumns() {
		return 1;
	}

	@Override
	protected int getRows() {
		return 48; // 24*2
	}

}
