package main.java.org.lucjross.jmtl.interval;

import java.util.Objects;

/**
 * Created by lucas on 11/14/2014.
 */
public class Interval {
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

    public Interval inversion()
    {
        final IntervalNumber inversionNumber = number.inversion();
        final IntervalQuality inversionQuality = quality.inversion();
        return new Interval(inversionQuality, inversionNumber);
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

        if (! number.equals(interval.number))
        {
            return false;
        }
        if (! quality.equals(interval.quality))
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
}
