package main.java.org.lucjross.jmtl.pitch;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;

import java.util.*;

/**
 * Created by lucas on 11/14/2014.
 */
public class Pitch implements Comparable<Pitch> {
//    C(0),
//        C_FLAT(11, C),
//        C_SHARP(1, C),
//        C_DOUBLESHARP(2, C),
//    D(2),
//        D_DOUBLEFLAT(0, D),
//        D_FLAT(1, D),
//        D_SHARP(3, D),
//        D_DOUBLESHARP(4, D),
//    E(4),
//        E_DOUBLEFLAT(2, E),
//        E_FLAT(3, E),
//        E_SHARP(5, E),
//    F(5),
//        F_FLAT(4, F),
//        F_SHARP(6, F),
//        F_DOUBLESHARP(7, F),
//    G(7),
//        G_DOUBLEFLAT(5, G),
//        G_FLAT(6, G),
//        G_SHARP(8, G),
//        G_DOUBLESHARP(9, G),
//    A(9),
//        A_DOUBLEFLAT(7, A),
//        A_FLAT(8, A),
//        A_SHARP(10, A),
//        A_DOUBLESHARP(11, A),
//    B(11),
//        B_DOUBLEFLAT(9, B),
//        B_FLAT(10, B),
//        B_SHARP(0, B);

    public static final Pitch C = new Pitch('C', 0);
    public static final Pitch D = new Pitch('D', 2);
    public static final Pitch E = new Pitch('E', 4);
    public static final Pitch F = new Pitch('F', 5);
    public static final Pitch G = new Pitch('G', 7);
    public static final Pitch A = new Pitch('A', 9);
    public static final Pitch B = new Pitch('B', 11);

    public static final List<Pitch> PITCHES =
            Collections.unmodifiableList(Arrays.asList(C, D, E, F, G, A, B));
//    static
//    {
//        List<Pitch> pitchList = new CircularArrayList<>(7);
//        pitchList.addAll(Arrays.asList(C, D, E, F, G, A, B));
//        PITCHES = Collections.unmodifiableList(pitchList);
//    }

    public static final Map<Integer, Set<Pitch>> ENHARMONIC_EQUIVALENCIES;
    static
    {
        Map<Integer, Set<Pitch>> equivalencies = new HashMap<>(12);
        for (Pitch p : PITCHES)
        {
            for (int i = -2; i <= 2; i++)
            {
                Pitch altered = p.alter(i);
                Set<Pitch> equivalents = equivalencies.get(altered.halfStepsUpFromC);
                if (equivalents == null)
                {
                    equivalents = new HashSet<>();
                    equivalencies.put(p.halfStepsUpFromC, equivalents);
                }
                equivalents.add(p);
            }
        }
        ENHARMONIC_EQUIVALENCIES = Collections.unmodifiableMap(equivalencies);
    }

    private final char letter;

    private final int halfStepsUpFromC;

    private final Pitch basePitch;

    private Pitch(char letter, int halfStepsUpFromC)
    {
        this.letter = letter;
        this.halfStepsUpFromC = halfStepsUpFromC;
        basePitch = this;
    }

    private Pitch(int halfStepsUpFromC, Pitch basePitch)
    {
        letter = basePitch.letter;
        this.halfStepsUpFromC = halfStepsUpFromC;
        this.basePitch = basePitch;
    }

    @Override
    public int compareTo(Pitch o)
    {
        return halfStepsUpFromC - o.halfStepsUpFromC;
    }

    public char getLetter()
    {
        return letter;
    }

    public int getHalfStepsUpFromC()
    {
        return halfStepsUpFromC;
    }

    public Pitch getBasePitch()
    {
        return basePitch;
    }

    public Set<Pitch> getEnharmonicEquivalents()
    {
        return ENHARMONIC_EQUIVALENCIES.get(halfStepsUpFromC);
    }

    public Pitch doubleFlat()
    {
        int halfStepsUpFromC = basePitch.halfStepsUpFromC - 2;
        if (halfStepsUpFromC < 0)
        {
            halfStepsUpFromC += 12;
        }
        return new Pitch(halfStepsUpFromC, basePitch);
    }

    public Pitch flat()
    {
        int halfStepsUpFromC = basePitch.halfStepsUpFromC - 1;
        if (halfStepsUpFromC < 0)
        {
            halfStepsUpFromC += 12;
        }
        return new Pitch(halfStepsUpFromC, basePitch);
    }

    public Pitch natural()
    {
        return new Pitch(basePitch.halfStepsUpFromC, basePitch);
    }

    public Pitch sharp()
    {
        int halfStepsUpFromC = basePitch.halfStepsUpFromC + 1;
        if (halfStepsUpFromC > 11)
        {
            halfStepsUpFromC -= 12;
        }
        return new Pitch(halfStepsUpFromC, basePitch);
    }

    public Pitch doubleSharp()
    {
        int halfStepsUpFromC = basePitch.halfStepsUpFromC + 2;
        if (halfStepsUpFromC > 11)
        {
            halfStepsUpFromC -= 12;
        }
        return new Pitch(halfStepsUpFromC, basePitch);
    }

    public Pitch alter(int halfSteps)
    {
        if (halfSteps < -2 || halfSteps > 2)
        {
            throw new IllegalArgumentException("halfSteps=" + halfSteps);
        }

        switch (halfSteps)
        {
            case -2:
                return doubleFlat();
            case -1:
                return flat();
            case 0:
                return natural();
            case 1:
                return sharp();
            default:
                return doubleSharp();
        }
    }

    public Pitch add(Interval interval)
    {
        return null;
    }

    public Pitch subtract(Interval interval)
    {
        return null;
    }

//    public Interval intervalTo(Pitch upper)
//    {
//        Pitch p = this;
//        int steps = 0;
//        while (p != upper.basePitch)
//        {
//            p = p.nextBasePitch();
//            steps++;
//        }
//        final IntervalNumber number = IntervalNumber.values()[steps];
//
//
//    }

    public Pitch nextBasePitch()
    {
        int index = PITCHES.indexOf(this) + 1;
        if (index == PITCHES.size())
        {
            index = 0;
        }
        return PITCHES.get(index);
    }
}
