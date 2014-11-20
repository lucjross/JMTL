package main.java.org.lucjross.jmtl.interval;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by lucas on 11/14/2014.
 */
public enum IntervalQuality
{
    // Major/minor intervals are ordered by size
    MM_DOUBLY_DIM(IntervalQualityType.MAJOR_MINOR, -2),
    MM_DIM(IntervalQualityType.MAJOR_MINOR, -1),
    MINOR(IntervalQualityType.MAJOR_MINOR, 0),
    MAJOR(IntervalQualityType.MAJOR_MINOR, 0),
    MM_AUG(IntervalQualityType.MAJOR_MINOR, 1),
    MM_DOUBLY_AUG(IntervalQualityType.MAJOR_MINOR, 2),

    // Perfect intervals are also ordered by size
    P_DOUBLY_DIM(IntervalQualityType.PERFECT, -2),
    P_DIM(IntervalQualityType.PERFECT, -1),
    PERFECT(IntervalQualityType.PERFECT, 0),
    P_AUG(IntervalQualityType.PERFECT, 1),
    P_DOUBLY_AUG(IntervalQualityType.PERFECT, 2);

    public static final List<IntervalQuality> MM_QUALITIES = Collections.unmodifiableList(
            Arrays.asList(MM_DOUBLY_DIM, MM_DIM, MINOR, MAJOR, MM_AUG, MM_DOUBLY_AUG));

    public static final List<IntervalQuality> P_QUALITIES = Collections.unmodifiableList(
            Arrays.asList(P_DOUBLY_DIM, P_DIM, PERFECT, P_AUG, P_DOUBLY_AUG));

    private final IntervalQualityType intervalQualityType;

    private final int halfStepsModifier;

    private IntervalQuality(IntervalQualityType intervalQualityType, int halfStepsModifier)
    {
        this.intervalQualityType = intervalQualityType;
        this.halfStepsModifier = halfStepsModifier;
    }

    public IntervalQualityType getIntervalQualityType()
    {
        return intervalQualityType;
    }

    public int getHalfStepsModifier()
    {
        return halfStepsModifier;
    }

    public IntervalQuality toInversion()
    {
        if (compareTo(MM_DOUBLY_AUG) <= 0)
        {
            return MM_QUALITIES.get(
                    MM_QUALITIES.size() - 1 - MM_QUALITIES.indexOf(this));
        }
        else
        {
            return P_QUALITIES.get(
                    P_QUALITIES.size() - 1 - P_QUALITIES.indexOf(this));
        }
    }

    public IntervalQuality lower()
    {
        if (compareTo(MM_DOUBLY_AUG) <= 0)
        {
            int mmIndex = MM_QUALITIES.indexOf(this);
            if (mmIndex == 0)
            {
                throw new IllegalStateException();
            }
            return MM_QUALITIES.get(mmIndex - 1);
        }
        else
        {
            int pIndex = P_QUALITIES.indexOf(this);
            if (pIndex == 0)
            {
                throw new IllegalStateException();
            }
            return P_QUALITIES.get(pIndex - 1);
        }
    }

    public IntervalQuality raise()
    {
        if (compareTo(MM_DOUBLY_AUG) <= 0)
        {
            int mmIndex = MM_QUALITIES.indexOf(this);
            if (mmIndex == MM_QUALITIES.size() - 1)
            {
                throw new IllegalStateException();
            }
            return MM_QUALITIES.get(mmIndex + 1);
        }
        else
        {
            int pIndex = P_QUALITIES.indexOf(this);
            if (pIndex == P_QUALITIES.size() - 1)
            {
                throw new IllegalStateException();
            }
            return P_QUALITIES.get(pIndex + 1);
        }
    }
}
