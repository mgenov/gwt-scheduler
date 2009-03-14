package gwtscheduler.client.resources.css;

import com.google.gwt.libideas.resources.client.ImmutableResourceBundle;

/**
 * Defines structural css resources.
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface CommonCssResources extends ImmutableResourceBundle {

    /**
     * This is the main stylesheet.
     * 
     * @return the main stylesheet object
     */
    @Resource("gwtscheduler/client/resources/css/structural-day-week.css")
    public DayWeekCss dayWeekCss();

    @Resource("gwtscheduler/client/resources/css/structural-month.css")
    public MonthCss monthCss();

}
