package main.java.org.lucjross.jmtl.chord;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lucas on 11/18/2014.
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

//    public static ChordInversion forIntervals(List<Interval> intervals)
//    {
//        if (intervals.size() < 2 || intervals.size() > 3)
//        {
//            throw new IllegalArgumentException(intervals.toString());
//        }
//
//        List<IntervalNumber> numbers = new ArrayList<>(intervals.size());
//        for (Interval i : intervals)
//        {
//            numbers.add(i.getNumber().getSimpleEquivalent());
//        }
//
//
//
//
//
//
//        boolean mustBeRoot = false;
//        boolean mustBeFirst = false;
//        boolean mustBeSecond = false;
//        boolean mustBeThird = false;
//
//        if (numbers.contains(IntervalNumber.SECOND))
//        {
//            mustBeThird = true;
//        }
//
//        if (numbers.contains(IntervalNumber.SEVENTH))
//        {
//            mustBeRoot = true;
//        }
//
//
//
//        switch (numbers.get(0))
//        {
//            case SECOND:
//                mustBeThird = true;
//                break;
//            case THIRD:
//                switch (numbers.get(1))
//                {
//                    case THIRD:
//                        return ROOT_POSITION;
//                    case FOURTH:
//                        return FIRST_INVERSION;
//                    default:
//                        return null;
//                }
//            case FOURTH:
//                switch (top)
//                {
//                    case THIRD:
//                        return SECOND_INVERSION;
//                    default:
//                        return null;
//                }
//            case FIFTH:
//            case SIXTH:
//            default:
//                return null;
//        }
//    }
}
