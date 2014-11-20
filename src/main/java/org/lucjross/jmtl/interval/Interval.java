package main.java.org.lucjross.jmtl.interval;

/**
 * Created by lucas on 11/14/2014.
 */
public class Interval implements Comparable<Interval> {
    private final IntervalQuality quality;

    private final IntervalNumber number;

    private final int halfStepsDistance;

    public Interval(IntervalQuality quality, IntervalNumber number)
    {
        if (! number.getIntervalQualityType().equals(quality.getIntervalQualityType()))
        {
            throw new IllegalArgumentException();
        }

        this.quality = quality;
        this.number = number;

        final IntervalQualityType qualityType = quality.getIntervalQualityType();
        final int modifier = quality.getHalfStepsModifier();
        if (qualityType.equals(IntervalQualityType.MAJOR_MINOR))
        {
            if (quality.compareTo(IntervalQuality.MINOR) <= 0)
            {
                halfStepsDistance = number.getMinorHalfStepsDistance() + modifier;
            }
            else
            {
                halfStepsDistance = number.getMajorHalfStepsDistance() + modifier;
            }
        }
        else
        {
            halfStepsDistance = number.getPerfectHalfStepsDistance() + modifier;
        }
    }

//    /**
//     * A map of half-step distances to sets of enharmonically equivalent intervals
//     */
//    public static final Map<Integer, Set<Interval>> ENHARMONIC_EQUIVALENCIES;
//    static
//    {
//        Map<Integer, Set<Interval>> equivalencies = new HashMap<>();
//        for (Interval interval : values())
//        {
//            Set<Interval> equivalents = equivalencies.get(interval.halfStepsDistance);
//            if (equivalents == null)
//            {
//                equivalents = new HashSet<>();
//                equivalencies.put(interval.halfStepsDistance, equivalents);
//            }
//            equivalents.add(interval);
//        }
//        ENHARMONIC_EQUIVALENCIES = Collections.unmodifiableMap(equivalencies);
//    }

    public IntervalQuality getQuality()
    {
        return quality;
    }

    public IntervalNumber getNumber()
    {
        return number;
    }

    public int getHalfStepsDistance()
    {
        return halfStepsDistance;
    }

//    public Set<Interval> getEnharmonicEquivalents()
//    {
//        return ENHARMONIC_EQUIVALENCIES.get(halfStepsDistance);
//    }

    public boolean isEnharmonicallyEquivalentTo(Interval interval)
    {
        return halfStepsDistance == interval.halfStepsDistance;
    }

    public boolean isCompound()
    {
        return number.isCompound();
    }

    public Interval toSimple()
    {
        if (! isCompound())
        {
            return new Interval(quality, number);
        }

        return new Interval(quality, number.getSimpleEquivalent());
    }

    public Interval toInversion()
    {
        final IntervalNumber inversionNumber = number.inversion();
        final IntervalQuality inversionQuality = quality.toInversion();
        return new Interval(inversionQuality, inversionNumber);
    }

    @Override
    public int compareTo(Interval o)
    {
        int comparison = number.compareTo(o.number);
        if (comparison == 0)
        {
            comparison = quality.compareTo(o.quality);
        }
        return comparison;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Interval interval = (Interval) o;

        if (number != interval.number)
        {
            return false;
        }
        if (quality != interval.quality)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (quality != null ? quality.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "Interval{" +
                "quality=" + quality +
                ", number=" + number +
                '}';
    }

    public Interval add(Interval a)
    {
        int numberSum = number.ordinal() + a.number.ordinal();
        if (numberSum > IntervalNumber.values().length - 1)
        {
            throw new IllegalArgumentException(a.toString());
        }
        int halfStepsSum = halfStepsDistance + a.halfStepsDistance;

        IntervalNumber intervalNumber = IntervalNumber.values()[numberSum];
        for (IntervalQuality q : IntervalQuality.values())
        {
            if (intervalNumber.getIntervalQualityType() == q.getIntervalQualityType())
            {
                Interval interval = new Interval(q, intervalNumber);
                if (halfStepsSum == interval.getHalfStepsDistance())
                {
                    return interval;
                }
            }
        }

        throw new IllegalArgumentException(a.toString());
    }

    public Interval subtract(Interval a)
    {
        int numberDiff = number.ordinal() - a.number.ordinal();
        if (numberDiff < 0)
        {
            throw new IllegalArgumentException(a.toString());
        }
        int halfStepsDiff = halfStepsDistance - a.halfStepsDistance;

        IntervalNumber intervalNumber = IntervalNumber.values()[numberDiff];
        for (IntervalQuality q : IntervalQuality.values())
        {
            if (intervalNumber.getIntervalQualityType() == q.getIntervalQualityType())
            {
                Interval interval = new Interval(q, intervalNumber);
                if (halfStepsDiff == interval.getHalfStepsDistance())
                {
                    return interval;
                }
            }
        }

        throw new IllegalArgumentException(a.toString());
    }
}
