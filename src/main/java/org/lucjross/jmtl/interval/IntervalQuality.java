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

    public IntervalQuality inversion()
    {
        switch (this)
        {
            case MM_DOUBLY_DIM:
                return MM_DOUBLY_AUG;
            case MM_DIM:
                return MM_AUG;
            case MINOR:
                return MAJOR;
            case MAJOR:
                return MINOR;
            case MM_AUG:
                return MM_DIM;
            case MM_DOUBLY_AUG:
                return MM_DOUBLY_DIM;
            case P_DOUBLY_DIM:
                return P_DOUBLY_AUG;
            case P_DIM:
                return P_AUG;
            case PERFECT:
                return PERFECT;
            case P_AUG:
                return P_DIM;
            case P_DOUBLY_AUG:
                return P_DOUBLY_DIM;
            default:
                return null;
        }
    }

    public IntervalQuality lower()
    {
        switch (this)
        {
            case MM_DIM:
                return MM_DOUBLY_DIM;
            case MINOR:
                return MM_DIM;
            case MAJOR:
                return MINOR;
            case MM_AUG:
                return MAJOR;
            case MM_DOUBLY_AUG:
                return MM_AUG;
            case P_DIM:
                return P_DOUBLY_DIM;
            case PERFECT:
                return P_DIM;
            case P_AUG:
                return PERFECT;
            case P_DOUBLY_AUG:
                return P_AUG;
            default:
                return null;
        }
    }

    public IntervalQuality raise()
    {
        switch (this)
        {
            case MM_DOUBLY_DIM:
                return MM_DIM;
            case MM_DIM:
                return MINOR;
            case MINOR:
                return MAJOR;
            case MAJOR:
                return MM_AUG;
            case MM_AUG:
                return MM_DOUBLY_AUG;
            case P_DOUBLY_DIM:
                return P_DIM;
            case P_DIM:
                return PERFECT;
            case PERFECT:
                return P_AUG;
            case P_AUG:
                return P_DOUBLY_AUG;
            default:
                return null;
        }
    }
}
