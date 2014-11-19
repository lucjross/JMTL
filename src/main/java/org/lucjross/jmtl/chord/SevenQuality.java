package main.java.org.lucjross.jmtl.chord;

import main.java.org.lucjross.jmtl.interval.IntervalQuality;

/**
 * Created by lucas on 11/18/2014.
 */
public enum SevenQuality
{
    MAJ_MAJ(TriadQuality.MAJ, IntervalQuality.MAJOR),
    MAJ_MIN(TriadQuality.MAJ, IntervalQuality.MINOR),
    MIN_MIN(TriadQuality.MIN, IntervalQuality.MINOR),
    HALF_DIM(TriadQuality.DIM, IntervalQuality.MAJOR),
    FULLY_DIM(TriadQuality.DIM, IntervalQuality.MINOR);

    private final TriadQuality triadQuality;

    private final IntervalQuality topThird;

    private SevenQuality(TriadQuality triadQuality, IntervalQuality topThird)
    {
        this.triadQuality = triadQuality;
        this.topThird = topThird;
    }

    public TriadQuality getTriadQuality()
    {
        return triadQuality;
    }

    public IntervalQuality getTopThird()
    {
        return topThird;
    }
}
