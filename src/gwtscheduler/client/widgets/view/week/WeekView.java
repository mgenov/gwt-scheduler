package gwtscheduler.client.widgets.view.week;

import gwtscheduler.client.widgets.resize.IViewportResizeHandler;
import gwtscheduler.client.widgets.view.common.AbstractDayView;

/**
 * View class for weeks.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public class WeekView extends AbstractDayView implements IViewportResizeHandler {

	/**
	 * Default constructor.
	 */
	public WeekView() {
		super();
	}

	@Override
	protected int getColumns() {
		return 7;
	}

	@Override
	protected int getRows() {
		return 48; // 24*2
	}

}
