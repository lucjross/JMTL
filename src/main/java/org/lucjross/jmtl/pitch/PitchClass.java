package main.java.org.lucjross.jmtl.pitch;

import main.java.org.lucjross.jmtl.interval.Interval;

import java.util.*;

/**
 * Created by lucas on 11/14/2014.
 */
public class PitchClass
{
    public static final PitchClass C = new PitchClass('C', 0);
    public static final PitchClass D = new PitchClass('D', 2);
    public static final PitchClass E = new PitchClass('E', 4);
    public static final PitchClass F = new PitchClass('F', 5);
    public static final PitchClass G = new PitchClass('G', 7);
    public static final PitchClass A = new PitchClass('A', 9);
    public static final PitchClass B = new PitchClass('B', 11);

    public static final List<PitchClass> PITCH_CLASSES =
            Collections.unmodifiableList(Arrays.asList(C, D, E, F, G, A, B));

    public static final Map<Integer, Set<PitchClass>> ENHARMONIC_EQUIVALENCIES;
    static
    {
        Map<Integer, Set<PitchClass>> equivalencies = new HashMap<>(12);
        for (PitchClass p : PITCH_CLASSES)
        {
            for (Accidental a : Accidental.values())
            {
                final PitchClass altered = p.alter(a);
                Set<PitchClass> equivalents = equivalencies.get(altered.halfStepsUpFromC);
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

    private final PitchClass basePitchClass;

    private final Accidental accidental;

    private PitchClass(char letter, int halfStepsUpFromC)
    {
        this.letter = letter;
        this.halfStepsUpFromC = halfStepsUpFromC;
        basePitchClass = this;
        accidental = Accidental.NATURAL;
    }

    private PitchClass(PitchClass basePitchClass, Accidental accidental)
    {
        if (! PITCH_CLASSES.contains(basePitchClass))
        {
            throw new IllegalArgumentException(basePitchClass.toString());
        }

        letter = basePitchClass.letter;
        int halfStepsUpFromC = basePitchClass.halfStepsUpFromC + accidental.getModifier();
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

        this.basePitchClass = basePitchClass;
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

        PitchClass pitchClass = (PitchClass) o;

        if (letter != pitchClass.letter)
        {
            return false;
        }
        if (accidental != pitchClass.accidental)
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

    public PitchClass getBasePitchClass()
    {
        return basePitchClass;
    }

    public Accidental getAccidental()
    {
        return accidental;
    }

    public Set<PitchClass> getEnharmonicEquivalents()
    {
        return ENHARMONIC_EQUIVALENCIES.get(halfStepsUpFromC);
    }

    public PitchClass alter(Accidental accidental)
    {
        return new PitchClass(basePitchClass, accidental);
    }

    public PitchClass raiseBy(Interval interval)
    {
        // The number of scale degrees to raise by.
        // E.g., for the interval of a second, steps == 1; for a seventh, steps == 6.
        int steps = interval.getNumber().ordinal();

        // Move to the upper base pitch.
        PitchClass raisedBasePitch = basePitchClass;
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
        Set<PitchClass> enharmonicEquivalents = ENHARMONIC_EQUIVALENCIES.get(raisedHalfStepsUpFromC);
        for (PitchClass p : enharmonicEquivalents)
        {
            if (p.basePitchClass.equals(raisedBasePitch))
            {
                return p;
            }
        }
        throw new IllegalArgumentException(interval.toString());
    }

    public PitchClass lowerBy(Interval interval)
    {
        // The number of scale degrees to lower by.
        // E.g., for the interval of a second, steps == 1; for a seventh, steps == 6.
        int steps = interval.getNumber().ordinal();

        // Move to the lower base pitch.
        PitchClass loweredBasePitchClass = basePitchClass;
        for (int i = 0; i < steps; i++)
        {
            loweredBasePitchClass = loweredBasePitchClass.previousBasePitch();
        }

        // Get the correct pitch based upon the new half step distance from C.
        int loweredHalfStepsUpFromC = halfStepsUpFromC - interval.getHalfStepsDistance();
        while (loweredHalfStepsUpFromC < 0)
        {
            loweredHalfStepsUpFromC += 12;
        }
        Set<PitchClass> enharmonicEquivalents = ENHARMONIC_EQUIVALENCIES.get(loweredHalfStepsUpFromC);
        for (PitchClass p : enharmonicEquivalents)
        {
            if (p.basePitchClass.equals(loweredBasePitchClass))
            {
                return p;
            }
        }
        throw new IllegalArgumentException(interval.toString());
    }

    public PitchClass nextBasePitch()
    {
        int index = PITCH_CLASSES.indexOf(basePitchClass) + 1;
        if (index == PITCH_CLASSES.size())
        {
            index = 0;
        }
        return PITCH_CLASSES.get(index);
    }

    public PitchClass previousBasePitch()
    {
        int index = PITCH_CLASSES.indexOf(basePitchClass) - 1;
        if (index < 0)
        {
            index = PITCH_CLASSES.size() - 1;
        }
        return PITCH_CLASSES.get(index);
    }

    @Override
    public String toString()
    {
        return "PitchClass{" +
                "letter=" + letter +
                ", accidental=" + accidental +
                '}';
    }
}
