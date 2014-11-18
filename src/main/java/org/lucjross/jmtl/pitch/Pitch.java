package main.java.org.lucjross.jmtl.pitch;

import main.java.org.lucjross.jmtl.interval.Interval;
import main.java.org.lucjross.jmtl.interval.IntervalNumber;

import java.util.*;

/**
 * Created by lucas on 11/14/2014.
 */
public class Pitch {
    public static final Pitch C = new Pitch('C', 0);
    public static final Pitch D = new Pitch('D', 2);
    public static final Pitch E = new Pitch('E', 4);
    public static final Pitch F = new Pitch('F', 5);
    public static final Pitch G = new Pitch('G', 7);
    public static final Pitch A = new Pitch('A', 9);
    public static final Pitch B = new Pitch('B', 11);

    public static final List<Pitch> PITCHES =
            Collections.unmodifiableList(Arrays.asList(C, D, E, F, G, A, B));

    public static final Map<Integer, Set<Pitch>> ENHARMONIC_EQUIVALENCIES;
    static
    {
        Map<Integer, Set<Pitch>> equivalencies = new HashMap<>(12);
        for (Pitch p : PITCHES)
        {
            for (Accidental a : Accidental.values())
            {
                final Pitch altered = p.alter(a);
                Set<Pitch> equivalents = equivalencies.get(altered.halfStepsUpFromC);
                if (equivalents == null)
                {
                    equivalents = new HashSet<>();
                    equivalencies.put(altered.halfStepsUpFromC, equivalents);
                }
                equivalents.add(altered);
            }
        }
        ENHARMONIC_EQUIVALENCIES = Collections.unmodifiableMap(equivalencies);
    }

    private final char letter;

    private final int halfStepsUpFromC;

    private final Pitch basePitch;

    private final Accidental accidental;

    private Pitch(char letter, int halfStepsUpFromC)
    {
        this.letter = letter;
        this.halfStepsUpFromC = halfStepsUpFromC;
        basePitch = this;
        accidental = Accidental.NATURAL;
    }

    private Pitch(Pitch basePitch, Accidental accidental)
    {
        if (! PITCHES.contains(basePitch))
        {
            throw new IllegalArgumentException(basePitch.toString());
        }

        letter = basePitch.letter;
        int halfStepsUpFromC = basePitch.halfStepsUpFromC + accidental.getModifier();
        if (halfStepsUpFromC < 0)
        {
            this.halfStepsUpFromC = halfStepsUpFromC + 12;
        }
        else if (halfStepsUpFromC > 11)
        {
            this.halfStepsUpFromC = halfStepsUpFromC - 12;
        }
        else
        {
            this.halfStepsUpFromC = halfStepsUpFromC;
        }

        this.basePitch = basePitch;
        this.accidental = accidental;
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

        Pitch pitch = (Pitch) o;

        if (letter != pitch.letter)
        {
            return false;
        }
        if (accidental != pitch.accidental)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) letter;
        result = 31 * result + (accidental != null ? accidental.hashCode() : 0);
        return result;
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

    public Accidental getAccidental()
    {
        return accidental;
    }

    public Set<Pitch> getEnharmonicEquivalents()
    {
        return ENHARMONIC_EQUIVALENCIES.get(halfStepsUpFromC);
    }

    public Pitch alter(Accidental accidental)
    {
        return new Pitch(basePitch, accidental);
    }

    public Pitch raiseBy(Interval interval)
    {
        // The number of scale degrees to raise by.
        // E.g., for the interval of a second, steps == 1; for a seventh, steps == 6.
        int steps = interval.getNumber().ordinal();

        // Move to the upper base pitch.
        Pitch raisedBasePitch = basePitch;
        for (int i = 0; i < steps; i++)
        {
            raisedBasePitch = raisedBasePitch.nextBasePitch();
        }

        // Get the correct pitch based upon the new half step distance from C.
        int raisedHalfStepsUpFromC = halfStepsUpFromC + interval.getHalfStepsDistance();
        while (raisedHalfStepsUpFromC > 11)
        {
            raisedHalfStepsUpFromC -= 12;
        }
        Set<Pitch> enharmonicEquivalents = ENHARMONIC_EQUIVALENCIES.get(raisedHalfStepsUpFromC);
        for (Pitch p : enharmonicEquivalents)
        {
            if (p.basePitch.equals(raisedBasePitch))
            {
                return p;
            }
        }
        throw new IllegalArgumentException(interval.toString());
    }

    public Pitch lowerBy(Interval interval)
    {
        // The number of scale degrees to lower by.
        // E.g., for the interval of a second, steps == 1; for a seventh, steps == 6.
        int steps = interval.getNumber().ordinal();

        // Move to the lower base pitch.
        Pitch loweredBasePitch = basePitch;
        for (int i = 0; i < steps; i++)
        {
            loweredBasePitch = loweredBasePitch.previousBasePitch();
        }

        // Get the correct pitch based upon the new half step distance from C.
        int loweredHalfStepsUpFromC = halfStepsUpFromC - interval.getHalfStepsDistance();
        while (loweredHalfStepsUpFromC < 0)
        {
            loweredHalfStepsUpFromC += 12;
        }
        Set<Pitch> enharmonicEquivalents = ENHARMONIC_EQUIVALENCIES.get(loweredHalfStepsUpFromC);
        for (Pitch p : enharmonicEquivalents)
        {
            if (p.basePitch.equals(loweredBasePitch))
            {
                return p;
            }
        }
        throw new IllegalArgumentException(interval.toString());
    }

    public Pitch nextBasePitch()
    {
        int index = PITCHES.indexOf(basePitch) + 1;
        if (index == PITCHES.size())
        {
            index = 0;
        }
        return PITCHES.get(index);
    }

    public Pitch previousBasePitch()
    {
        int index = PITCHES.indexOf(basePitch) - 1;
        if (index < 0)
        {
            index = PITCHES.size() - 1;
        }
        return PITCHES.get(index);
    }

    @Override
    public String toString()
    {
        return "Pitch{" +
                "letter=" + letter +
                ", accidental=" + accidental +
                '}';
    }
}
