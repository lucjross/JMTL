package main.java.org.lucjross.jmtl.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by lucas on 11/18/2014.
 */
public class ClosedIntervalSequence extends LinkedList<Interval>
{
    public ClosedIntervalSequence(Interval... intervals)
    {
        this(Arrays.asList(intervals));
    }

    public ClosedIntervalSequence(List<Interval> intervals)
    {
        SortedSet<Interval> componentIntervals = new TreeSet<>();

        for (int i = intervals.size() - 1; i >= 0; i--)
        {
            int numberDistance = 0;
            int halfStepsDistance = 0;
            for (int j = 0; j <= i; j++)
            {
                numberDistance += intervals.get(j).getNumber().ordinal();
                halfStepsDistance += intervals.get(j).getHalfStepsDistance();
            }
            while (numberDistance > 6)
            {
                numberDistance -= 7;
            }
            while (halfStepsDistance > 11)
            {
                halfStepsDistance -= 12;
            }

            IntervalNumber number = IntervalNumber.values()[numberDistance];
            IntervalQualityType type = number.getIntervalQualityType();
            for (IntervalQuality q : IntervalQuality.values())
            {
                if (type == q.getIntervalQualityType())
                {
                    Interval interval = new Interval(q, number);
                    if (halfStepsDistance == interval.getHalfStepsDistance())
                    {
                        componentIntervals.add(interval);
                        break;
                    }
                }
            }
        }

        componentIntervals.remove(new Interval(IntervalQuality.PERFECT, IntervalNumber.UNISON));

        List<Interval> list = new ArrayList<>(componentIntervals);
        for (int i = list.size() - 1; i > 0; i--)
        {
            list.set(i, list.get(i).subtract(list.get(i - 1)));
        }
        addAll(list);
    }
}
