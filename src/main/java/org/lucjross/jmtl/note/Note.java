package main.java.org.lucjross.jmtl.note;

import main.java.org.lucjross.jmtl.pitch.Pitch;

/**
 * Created by lucas on 11/14/2014.
 */
public class Note
{
    private final Pitch pitch;

    private final int octaveNumber;

    // Possibly for MIDI
    private final int noteNumber;

    public Note(Pitch pitch, int octaveNumber)
    {
        this.pitch = pitch;
        this.octaveNumber = octaveNumber;
        this.noteNumber = (octaveNumber * 12) + pitch.getHalfStepsUpFromC();
    }
}
