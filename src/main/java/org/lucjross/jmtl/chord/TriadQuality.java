package main.java.org.lucjross.jmtl.chord;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.interval.IntervalQuality;

import java.util.List;

/**
 * Created by lucas on 11/18/2014.
 */
public enum TriadQuality
{
    MAJ(IntervalQuality.MAJOR, IntervalQuality.MINOR),
    MIN(IntervalQuality.MINOR, IntervalQuality.MAJOR),
    DIM(IntervalQuality.MINOR, IntervalQuality.MINOR),
    AUG(IntervalQuality.MAJOR, IntervalQuality.MAJOR);

    private final IntervalQuality bottomThird;

    private final IntervalQuality topThird;

    private TriadQuality(IntervalQuality bottomThird, IntervalQuality topThird)
    {
        this.bottomThird = bottomThird;
        this.topThird = topThird;
    }

    public IntervalQuality getBottomThird()
    {
        return bottomThird;
    }

    public IntervalQuality getTopThird()
    {
        return topThird;
    }
}
