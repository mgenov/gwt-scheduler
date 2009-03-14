package gwtscheduler.client.resources.css;

import com.google.gwt.libideas.resources.client.ImmutableResourceBundle;

/**
 * Defines structural css resources. "st" in filename stands for "Structural".
 * 
 * @author Miguel Ping
 * @version $Revision: $
 * @since 1.0
 */
public interface CommonCssResourceBundle extends ImmutableResourceBundle {

    /**
     * This is the main stylesheet.
     * 
     * @return the main stylesheet object
     */
    @Resource("gwtscheduler/client/resources/css/st-day-week.css")
    public DayWeekCssResource dayWeekCss();

    @Resource("gwtscheduler/client/resources/css/st-month.css")
    public MonthCssResource monthCss();

}
