package main.java.org.lucjross.jmtl.chord;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.note.Note;

import java.util.List;

/**
 * Created by lucas on 11/14/2014.
 */
public abstract class Chord
{
    protected final List<Interval> intervals;

    protected final Note bass;

    protected Chord(List<Interval> intervals, Note bass)
    {
        this.intervals = intervals;
        this.bass = bass;
    }
}
