package gwtscheduler.common.util;

/**
 * @author Miroslav Genov (mgenov@gmail.com)
 */

import java.util.Date;

@SuppressWarnings({"deprecation"})
public class DateHelper {
    /**
     * number of days per month
     */
    private static final int[] DAYS_PER_MONTH = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    /**
     * weekedn days (Sunday and Saturday)
     */
    public static final int[] WEEK_END_DAYS = {0, 6};

    /**
     * a date to operate with
     */
    private Date date;
    /**
     * the first day of week (usually 0 - Sunday)
     */
    private int firstDayOfWeek = 1;

    /**
     * Creates an instance of this class.
     *
     * @param date is a date to operate with.
     */
    public DateHelper(Date date) {
        this.date = date;
        this.firstDayOfWeek = 1;
    }

    /**
     * Creates an instance of this class.
     *
     * @param year    is a year number.
     * @param month   is a month number (from 0 to 11).
     * @param day     is a day number.
     * @param hours   is a hours number (from 0 to 23).
     * @param minutes is a minutes number.
     * @param seconds is a seconds number.
     */
    public DateHelper(int year, int month, int day, int hours, int minutes, int seconds) {
        this(new Date(year - 1900, month, day, hours, minutes, seconds));
    }

    /**
     * This method gets a week day number.<p>
     * Usually a week starts from Sunday (0), but in some localizations it can start from Monday. In this case
     * all numbers will be shifted, i.e. Monday (0), Tuesday (1),..., Sunday (6).
     *
     * @return a number of week day.
     */
    public int getDayOfWeek() {
        int day = date.getDay() - firstDayOfWeek;
        if (day < 0)
            day = 7 + day;
        return day;
    }

    /**
     * This method gets a day in the month.
     *
     * @return a day number.
     */
    public int getDay() {
        return date.getDate();
    }

    /**
     * This method gets a month in the year starting from 0.
     *
     * @return a month number.
     */
    public int getMonth() {
        return date.getMonth();
    }

    /**
     * This method gets the year.
     *
     * @return the year.
     */
    public int getYear() {
        return 1900 + date.getYear();
    }

    /**
     * This method gets a hours number from 0 to 23.
     *
     * @return a hours number.
     */
    public int getHours() {
        return date.getHours();
    }

    /**
     * This method gets minutes number.
     *
     * @return a number of minutes.
     */
    public int getMinutes() {
        return date.getMinutes();
    }

    /**
     * This method gets seconds number.
     *
     * @return a seconds number.
     */
    public int getSeconds() {
        return date.getSeconds();
    }

    /**
     * This method gets a number of days in the month.
     *
     * @return a number of days.
     */
    public int getNumberOfDaysInMonth() {
        if ((getMonth() == 1) && ((getYear() % 400 == 0) ||
                ((getYear() % 100 != 0) && (getYear() % 4 == 0))))
            return 29;
        else
            return DAYS_PER_MONTH[getMonth()];
    }


    /**
     * This method cuts time data from the date.
     *
     * @return the resulting date.
     */
    public Date trim() {
        return new Date(date.getYear(), date.getMonth(), date.getDate());
    }

    /**
     * This method adds days to the date.
     *
     * @param days a number of days to add.
     * @return the resulting date.
     */
    public Date addDays(int days) {
        return new Date(date.getYear(), date.getMonth(), date.getDate() + days, date.getHours(), date.getMinutes(), date.getSeconds());
    }

    /**
     * This method adds months to the date.
     *
     * @param months a number of months to add.
     * @return the resulting date.
     */
    public Date addMonths(int months) {
        return new Date(date.getYear(), date.getMonth() + months, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
    }

    /**
     * This method adds years to the date.
     *
     * @param years a number of years to add.
     * @return the resulting date.
     */
    public Date addYears(int years) {
        return new Date(date.getYear() + years, date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
    }

    /**
     * This method gets a week number starting from the first of January (the first week).
     *
     * @return a week number.
     */
    public int getWeekNumber() {
        DateHelper dateHelper = new DateHelper(new Date(date.getYear(), 0, 1));
        Date firstDay = dateHelper.getFirstDayOfWeek();
        int days = getDaysFrom(firstDay);
        return days / 7 + 1;
    }

    /**
     * This method returns the first date of the week.
     *
     * @return the first date of the week.
     */
    public Date getFirstDayOfWeek() {
        return new Date(date.getYear(), date.getMonth(), date.getDate() - getDayOfWeek());
    }

    /**
     * This method returns the last date of the week.
     *
     * @return the last date of the week.
     */
    public Date getLastDayOfWeek() {
        return new Date(date.getYear(), date.getMonth(), date.getDate() + 6 - getDayOfWeek());
    }

    /**
     * This method gets a number of days starting from the specified date.
     *
     * @param startDate is a start date.
     * @return a number of days.
     */
    public int getDaysFrom(Date startDate) {
        return (int) Math.round((double) (trim().getTime() - new DateHelper(startDate).trim().getTime()) / 86400000);
    }

    /**
     * This method returns the first date of the month.
     *
     * @return the first date of the month.
     */
    public Date getFirstDayOfMonth() {
        return new Date(date.getYear(), date.getMonth(), 1);
    }

    /**
     * This method returns the last date of the month.
     *
     * @return the last date of the month.
     */
    public Date getLastDayOfMonth() {
        return new Date(date.getYear(), date.getMonth(), new DateHelper(date).getNumberOfDaysInMonth());
    }

    /**
     * This method defines whether the specified week day is a weekend.
     *
     * @param weekDay is a week day number.
     * @return <code>true</code> if the day is weekend.
     */
    public static boolean isWeekEndDay(int weekDay) {
        for (int holidayWeekDay : WEEK_END_DAYS) {
            if (holidayWeekDay == weekDay)
                return true;
        }
        return false;
    }

    /**
     * Checks whether the date is weekend.
     *
     * @return the result of check.
     */
    public boolean isWeekEndDay() {
        int day = new DateHelper(date).getDayOfWeek() + firstDayOfWeek;
        if (day > 6)
            day = day - 7;
        return isWeekEndDay(day);
    }

    /**
     * Returns the date.
     *
     * @return a date value.
     */
    public Date getDate() {
        return date;
    }
}
