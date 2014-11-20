package main.java.org.lucjross.jmtl.interval;

/**
 * Created by lucas on 11/17/2014.
 *
 * <p>The order of this is important as {@link #UNISON} should have index 0 in
 * {@link #values()}, {@link #OCTAVE} should have index 7, etc.
 *
 * <p>Intervals are defined only up to the fifteenth (two octaves) because
 * it isn't typically of any use to refer to a larger compound interval by its
 * ordinal. Operations on larger intervals should take advantage of
 * octave equivalence.
 */
public enum IntervalNumber
{

    UNISON(IntervalQualityType.PERFECT, 0),
    SECOND(IntervalQualityType.MAJOR_MINOR, 1, 2),
    THIRD(IntervalQualityType.MAJOR_MINOR, 3, 4),
    FOURTH(IntervalQualityType.PERFECT, 5),
    FIFTH(IntervalQualityType.PERFECT, 7),
    SIXTH(IntervalQualityType.MAJOR_MINOR, 8, 9),
    SEVENTH(IntervalQualityType.MAJOR_MINOR, 10, 11),
    OCTAVE(UNISON),
    NINTH(SECOND),
    TENTH(THIRD),
    ELEVENTH(FOURTH),
    TWELFTH(FIFTH),
    THIRTEENTH(SIXTH),
    FOURTEENTH(SEVENTH),
    FIFTEENTH(UNISON);

    private final IntervalQualityType intervalQualityType;

    private final int[] halfStepDistances;

    private final int octaves;

    private final IntervalNumber simpleEquivalent;

    private IntervalNumber(IntervalQualityType intervalQualityType, int... halfStepDistances)
    {
        this.intervalQualityType = intervalQualityType;
        this.halfStepDistances = halfStepDistances;
        octaves = 0;
        simpleEquivalent = this;
    }

    private IntervalNumber(IntervalNumber simpleEquivalent)
    {
        this.simpleEquivalent = simpleEquivalent;
        this.intervalQualityType = simpleEquivalent.intervalQualityType;
        this.halfStepDistances = new int[simpleEquivalent.halfStepDistances.length];
        this.octaves = ordinal() / 7;
        for (int i = 0; i < halfStepDistances.length; i++)
        {
            halfStepDistances[i] = (octaves * 12) + simpleEquivalent.halfStepDistances[i];
        }
    }

    public IntervalQualityType getIntervalQualityType()
    {
        return intervalQualityType;
    }

    /**
     * Returns whether this interval is {@link #OCTAVE} or higher. This is
     * contrary to the usual definition of a compound interval but is more
     * useful here.
     *
     * @return  true if this is a compound interval.
     */
    public boolean isCompound()
    {
        return compareTo(OCTAVE) >= 0;
    }

    public IntervalNumber getSimpleEquivalent()
    {
        return simpleEquivalent;
    }

//    public int getHalfStepsDistance(IntervalQuality intervalQuality)
//    {
//        if (intervalQuality.getIntervalQualityType() != getIntervalQualityType())
//        {
//            throw new IllegalArgumentException(intervalQuality.toString());
//        }
//
//        if (intervalQuality.compareTo(IntervalQuality.MAJOR) >= 0 )
//    }

    public int getMinorHalfStepsDistance()
    {
        if (! intervalQualityType.equals(IntervalQualityType.MAJOR_MINOR))
        {
            throw new UnsupportedOperationException();
        }

        return halfStepDistances[0];
    }

    public int getMajorHalfStepsDistance()
    {
        if (! intervalQualityType.equals(IntervalQualityType.MAJOR_MINOR))
        {
            throw new UnsupportedOperationException();
        }

        return halfStepDistances[1];
    }

    public int getPerfectHalfStepsDistance()
    {
        if (! intervalQualityType.equals(IntervalQualityType.PERFECT))
        {
            throw new UnsupportedOperationException();
        }

        return halfStepDistances[0];
    }

    public IntervalNumber inversion()
    {
        if (equals(UNISON))
        {
            return OCTAVE;
        }

        final int inversionHalfSteps;
        if (halfStepDistances[0] % 12 == 0)
        {
            inversionHalfSteps = halfStepDistances[0] - 12;
        }
        else
        {
            int distanceIndex =
                    simpleEquivalent.compareTo(FOURTH) < 0 || simpleEquivalent.compareTo(FIFTH) > 0 ?
                            1 : 0;
            inversionHalfSteps = (octaves * 12) + (12 - (halfStepDistances[distanceIndex] % 12));
        }

        final IntervalNumber[] values = values();
        for (int i = inversionHalfSteps / 12 * 7; i < values.length; i++)
        {
            if (values[i].halfStepDistances[0] >= inversionHalfSteps)
            {
                return values[i];
            }
        }
        throw new UnknownError();
    }
}
