package gwtscheduler.client.widgets.view.day;

import gwtscheduler.client.widgets.view.common.AbstractDayPanel;

/**
 * View class for days.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
class DayPanel extends AbstractDayPanel {

	/**
	 * Default constructor.
	 */
	public DayPanel() {
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
