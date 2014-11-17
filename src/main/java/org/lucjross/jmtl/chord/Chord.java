package main.java.org.lucjross.jmtl.chord;

import main.java.org.lucjross.jmtl.interval.IntervalVector;
import main.java.org.lucjross.jmtl.note.Note;
import main.java.org.lucjross.jmtl.pitch.PitchSet;

/**
 * Created by lucas on 11/14/2014.
 */
public class Chord
{
    private final IntervalVector intervalVector;

    private final PitchSet pitchSet;

    private final Note root;

    public Chord(IntervalVector intervalVector, Note root)
    {
        this.intervalVector = intervalVector;
        this.root = root;

        this.pitchSet = null;
    }

    public Chord(PitchSet pitchSet, Note root)
    {
        this.pitchSet = pitchSet;
        this.root = root;

        this.intervalVector = null;
    }
}
