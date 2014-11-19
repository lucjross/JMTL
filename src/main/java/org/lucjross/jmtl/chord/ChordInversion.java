package main.java.org.lucjross.jmtl.chord;

import main.java.org.lucjross.jmtl.interval.ClosedIntervalSequence;
import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;

import java.util.Arrays;
import java.util.List;

/**
 * An enumeration of chord inversions. Root position is among them.
 */
public enum ChordInversion
{
    ROOT_POSITION(ChordMember.ROOT),
    FIRST_INVERSION(ChordMember.THIRD),
    SECOND_INVERSION(ChordMember.FIFTH),
    THIRD_INVERSION(ChordMember.SEVENTH);

    private final ChordMember bass;

    private ChordInversion(ChordMember bass)
    {
        this.bass = bass;
    }

    /**
     * Returns a {@code ChordInversion} based upon a limited, conventional
     * interpretation of a sequence of musical intervals. The specified
     * interval sequence must express the intervallic content of some pitch set
     * with at least three and no more than four pitches and therefore must have
     * size > 2. Only interval numbers are considered, which means that an
     * enharmonic intersection of two non-connected interval endpoints does not
     * result in a reduction of two intervals to a single interval. For
     * example, {@code min 6th + dim 4th} is interpreted as a third-inversion
     * seven chord, not as a single minor sixth interval with the upper
     * interval discarded.
     *
     * <p>If the interval sequence cannot be interpreted as a triad or seven
     * chord, {@code null} will be returned.
     *
     * <p>Omission of intermediate intervals from the specified sequence may
     * result in a non-null result in cases where the omission does not cause
     * ambiguity; those cases are limited to omitting the fifth in a root
     * position, first-inversion, or third-inversion seven chord, and
     * omitting the third in a root position, second-inversion, or third-
     * inversion seven chord.
     *
     * @param  intervals An interval sequence.
     * @return A {@code ChordInversion}.
     */
    public static ChordInversion forIntervals(List<Interval> intervals)
    {
        ClosedIntervalSequence c = new ClosedIntervalSequence(intervals);

        if (c.size() < 2)
        {
            // cannot be a chord
            return null;
        }

        switch (c.get(0).getNumber())
        {
            case SECOND:
                return isThirdInversion(c) ? THIRD_INVERSION : null;
            case THIRD:
                return isRootPosition(c) ? ROOT_POSITION :
                        isFirstInversion(c) ? FIRST_INVERSION :
                        isSecondInversion(c) ? SECOND_INVERSION : null;
            case FOURTH:
                return isSecondInversion(c) ? SECOND_INVERSION : null;
            case FIFTH:
                return isRootPosition(c) ? ROOT_POSITION :
                        isFirstInversion(c) ? FIRST_INVERSION : null;
            default:
                return null;
        }
    }

    /**
     * Returns a {@code ChordInversion} based upon a conventional interpretation
     * of a sequence of musical intervals.
     *
     * @param  intervals An interval sequence.
     * @return A {@code ChordInversion}.
     * @see #forIntervals(java.util.List)
     */
    public static ChordInversion forIntervals(Interval... intervals)
    {
        return forIntervals(Arrays.asList(intervals));
    }

    private static boolean isRootPosition(ClosedIntervalSequence c)
    {
        switch (c.get(0).getNumber())
        {
            case THIRD:
                switch (c.get(1).getNumber())
                {
                    case THIRD:
                        return c.size() == 2 || c.size() == 3 &&
                                c.get(2).getNumber() == IntervalNumber.THIRD;
                    case FIFTH:
                        return c.size() == 2;
                    default:
                        return false;
                }
            default:
                return false;
        }
    }

    private static boolean isFirstInversion(ClosedIntervalSequence c)
    {
        switch (c.get(0).getNumber())
        {
            case THIRD:
                switch (c.get(1).getNumber())
                {
                    case THIRD:
                        return c.size() == 3 &&
                                c.get(2).getNumber() == IntervalNumber.SECOND;
                    case FOURTH:
                        return c.size() == 2;
                    default:
                        return false;
                }
            case FIFTH:
                return c.size() == 2 &&
                        c.get(1).getNumber() == IntervalNumber.SECOND;
            default:
                return false;
        }
    }

    private static boolean isSecondInversion(ClosedIntervalSequence c)
    {
        switch (c.get(0).getNumber())
        {
            case THIRD:
                switch (c.get(1).getNumber())
                {
                    case SECOND:
                        return c.size() == 2 || c.size() == 3 &&
                                c.get(2).getNumber() == IntervalNumber.THIRD;
                    default:
                        return false;
                }
            case FOURTH:
                return c.size() == 2 &&
                        c.get(1).getNumber() == IntervalNumber.THIRD;
            default:
                return false;
        }
    }

    private static boolean isThirdInversion(ClosedIntervalSequence c)
    {
        switch (c.get(0).getNumber())
        {
            case SECOND:
                switch (c.get(1).getNumber())
                {
                    case THIRD:
                        return c.size() == 2 || c.size() == 3 &&
                                c.get(2).getNumber() == IntervalNumber.THIRD;
                    case FIFTH:
                        return c.size() == 2;
                    default:
                        return false;
                }
            default:
                return false;
        }
    }
}
