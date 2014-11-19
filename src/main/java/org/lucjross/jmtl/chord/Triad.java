package main.java.org.lucjross.jmtl.chord;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;
import main.java.org.lucjross.jmtl.note.Note;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lucas on 11/18/2014.
 */
public class Triad extends Chord
{
    public static final List<Interval> MAJ_INTERVALS;
    public static final List<Interval> MIN_INTERVALS;
    public static final List<Interval> DIM_INTERVALS;
    public static final List<Interval> AUG_INTERVALS;
    static
    {
        List<Interval> majorTriad = new LinkedList<>();
        majorTriad.add(new Interval(TriadQuality.MAJ.getBottomThird(), IntervalNumber.THIRD));
        majorTriad.add(new Interval(TriadQuality.MAJ.getTopThird(), IntervalNumber.THIRD));
        MAJ_INTERVALS = Collections.unmodifiableList(majorTriad);

        List<Interval> minorTriad = new LinkedList<>();
        majorTriad.add(new Interval(TriadQuality.MIN.getBottomThird(), IntervalNumber.THIRD));
        majorTriad.add(new Interval(TriadQuality.MIN.getTopThird(), IntervalNumber.THIRD));
        MIN_INTERVALS = Collections.unmodifiableList(minorTriad);

        List<Interval> dimTriad = new LinkedList<>();
        majorTriad.add(new Interval(TriadQuality.DIM.getBottomThird(), IntervalNumber.THIRD));
        majorTriad.add(new Interval(TriadQuality.DIM.getTopThird(), IntervalNumber.THIRD));
        DIM_INTERVALS = Collections.unmodifiableList(dimTriad);

        List<Interval> augTriad = new LinkedList<>();
        majorTriad.add(new Interval(TriadQuality.AUG.getBottomThird(), IntervalNumber.THIRD));
        majorTriad.add(new Interval(TriadQuality.AUG.getTopThird(), IntervalNumber.THIRD));
        AUG_INTERVALS = Collections.unmodifiableList(augTriad);
    }

    public Triad(List<Interval> intervalVector, Note bass)
    {
        super(intervalVector, bass);
    }


}
