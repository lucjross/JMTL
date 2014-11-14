package main.java.org.lucjross.jmtl.interval;

import java.util.*;

/**
 * Created by lucas on 11/14/2014.
 */
public enum Interval {
    UNISON(IntervalQuality.PERFECT, 0),
    DIM_UNISON(IntervalQuality.DIM, UNISON),
    AUG_UNISON(IntervalQuality.AUG, UNISON),
    MINOR_SECOND(IntervalQuality.MINOR, 1),
    MAJOR_SECOND(IntervalQuality.MAJOR, 2),
    DIM_SECOND(IntervalQuality.DIM, MINOR_SECOND),
    AUG_SECOND(IntervalQuality.AUG, MAJOR_SECOND),
    MINOR_THIRD(IntervalQuality.MINOR, 3),
    MAJOR_THIRD(IntervalQuality.MAJOR, 4),
    DIM_THIRD(IntervalQuality.DIM, MINOR_THIRD),
    AUG_THIRD(IntervalQuality.AUG, MAJOR_THIRD),
    FOURTH(IntervalQuality.PERFECT, 5),
    DIM_FOURTH(IntervalQuality.DIM, FOURTH),
    AUG_FOURTH(IntervalQuality.AUG, FOURTH),
    FIFTH(IntervalQuality.PERFECT, 7),
    DIM_FIFTH(IntervalQuality.DIM, FIFTH),
    AUG_FIFTH(IntervalQuality.AUG, FIFTH),
    MINOR_SIXTH(IntervalQuality.MINOR, 8),
    MAJOR_SIXTH(IntervalQuality.MAJOR, 9),
    DIM_SIXTH(IntervalQuality.DIM, MINOR_SIXTH),
    AUG_SIXTH(IntervalQuality.AUG, MAJOR_SIXTH),
    MINOR_SEVENTH(IntervalQuality.MINOR, 10),
    MAJOR_SEVENTH(IntervalQuality.MAJOR, 11),
    DIM_SEVENTH(IntervalQuality.DIM, MINOR_SEVENTH),
    AUG_SEVENTH(IntervalQuality.AUG, MAJOR_SEVENTH),
    OCTAVE(IntervalQuality.PERFECT, 12),
    DIM_OCTAVE(IntervalQuality.DIM, OCTAVE),
    AUG_OCTAVE(IntervalQuality.AUG, OCTAVE),
    MINOR_NINTH(IntervalQuality.MINOR, 13),
    MAJOR_NINTH(IntervalQuality.MAJOR, 14),
    DIM_NINTH(IntervalQuality.DIM, MINOR_NINTH),
    AUG_NINTH(IntervalQuality.AUG, MAJOR_NINTH),
    MINOR_TENTH(IntervalQuality.MINOR, 15),
    MAJOR_TENTH(IntervalQuality.MAJOR, 16),
    DIM_TENTH(IntervalQuality.DIM, MINOR_TENTH),
    AUG_TENTH(IntervalQuality.AUG, MAJOR_TENTH),
    // ...
    ;


    public static final Map<Integer, Set<Interval>> ENHARMONIC_EQUIVALENCIES;
    static
    {
        Map<Integer, Set<Interval>> equivalencies = new TreeMap<>();

        for (Interval interval : values())
        {
            Set<Interval> equivalents = equivalencies.get(interval.halfStepsDistance);
            if (equivalents == null)
            {
                equivalents = new HashSet<>();
                equivalencies.put(interval.halfStepsDistance, equivalents);
            }
            equivalents.add(interval);
        }

        ENHARMONIC_EQUIVALENCIES = Collections.unmodifiableMap(equivalencies);
    }

    private final IntervalQuality quality;

    private final int halfStepsDistance;

    private final Interval baseInterval;

    private Interval(IntervalQuality quality, Interval baseInterval)
    {
        this.quality = quality;
        this.halfStepsDistance = baseInterval.halfStepsDistance + quality.getHalfStepsModifier();
        this.baseInterval = baseInterval;
    }

    private Interval(IntervalQuality quality, int halfStepsDistance)
    {
        this.quality = quality;
        this.halfStepsDistance = halfStepsDistance;
        this.baseInterval = this;
    }

    public IntervalQuality getQuality()
    {
        return quality;
    }

    public Set<Interval> getEnharmonicEquivalents()
    {
        return ENHARMONIC_EQUIVALENCIES.get(halfStepsDistance);
    }

    public boolean isCompound()
    {
        return this.compareTo(OCTAVE) > 0;
    }

    public Interval toSimple()
    {
        if (! isCompound())
        {
            return this;
        }

        Interval interval = this;
        int halfStepsDistance = this.halfStepsDistance;
        while (interval.isCompound())
        {
            halfStepsDistance -= OCTAVE.halfStepsDistance;
            Set<Interval> equivalents = ENHARMONIC_EQUIVALENCIES.get(halfStepsDistance);
            System.out.println(equivalents);
            Iterator<Interval> iterator = equivalents.iterator();
            Interval equivalent;
            do
            {
                equivalent = iterator.next();
            }
            while (! equivalent.getQuality().equals(getQuality()));
            interval = equivalent;
        }
        return interval;
    }
}
