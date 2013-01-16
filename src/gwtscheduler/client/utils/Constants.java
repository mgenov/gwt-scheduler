package gwtscheduler.client.utils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.constants.TimeZoneConstants;
import org.goda.time.tz.FixedDateTimeZone;

import java.util.Date;
import java.util.TimeZone;

/**
 * Application constants.
 */
public class Constants {

  /** holds the scrollbar width */
  public static int width = 0;

  /** the scrollbar width - magic number */
  public static final int SCROLLBAR_WIDTH() {
    if (width == 0) {
      width = JSNIUtils.getScrollbarWidth();
    }
    return width;
  }

  /** z index for lasso panel */
  public static final int LASSO_ZINDEX = 1;
  /** z index for events panel */
  public static final int EVENTS_ZINDEX = 12;
  /** z index for lasso panel while a range is being selected */
  public static final int LASSO_ZINDEX_SELECTING = 13;


  public static final FixedDateTimeZone timeZone = new FixedDateTimeZone("deafault", "default", (-1) * 60000 * new Date().getTimezoneOffset(), (-1) * 60000 * new Date().getTimezoneOffset());

}
