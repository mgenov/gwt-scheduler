package gwtscheduler.common.model;

import gwtscheduler.common.calendar.ITimePeriod;

public class TimePeriod implements ITimePeriod {

    protected long milis = 0;

    public TimePeriod() {
    }

    /**
     * @param milis
     */
    public TimePeriod(long milis) {
        this.milis = milis;
    }

    public int hours() {
        return (int) (milis / (1000 * 3600));
    }

    public int days() {
        return (int) (milis / (1000 * 3600 * 24));
    }

    public int months() {
        // this is not quite correct...
        return (int) (milis / (1000 * 3600 * 24 * 31));
    }
}
