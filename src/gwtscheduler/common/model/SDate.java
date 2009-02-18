package gwtscheduler.common.model;

import gwtscheduler.common.event.TimeUnit;
import gwtscheduler.common.event.IDate;

import java.util.Calendar;
import java.util.Date;

/**
 * Simple date class.
 */
public class SDate implements IDate, Comparable<IDate> {

    /**
     * Internal date field.
     */
    private Date delegate;

    /**
     * Default constructor.
     */
    public SDate() {
        delegate = new Date();
    }

    public Object delegate() {
        return delegate;
    }

    public IDate addDays(int amount) {
        return this;
    }

    public IDate addMonths(int amount) {
        return this;
    }

    public IDate addYears(int amount) {
        return this;
    }

    public int diff(IDate otherDate, TimeUnit unit) {
        return 0;
    }

    public int diff(IDate otherDate) {
        return 0;
    }

    public int compareTo(IDate o) {
        return delegate.compareTo((Date) o.delegate());
    }

    /**
     * Truncates this date instance.
     * 
     * @param field the field to truncate
     * @return this instance
     */
    protected IDate truncate(int field) {
        // TODO implement me
        boolean done = false;
        if (MILLISECOND == field) {
            done = true;
        }
        if (SECOND == field) {
            done = true;
        }
        if (MINUTE == field) {
            done = true;
        }
        return this;
    }

}
