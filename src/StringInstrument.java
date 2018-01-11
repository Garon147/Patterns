/**
 * Created by VKI on 07.01.2018.
 */
enum InstrumentType {

    ELECTRIC_GUITAR,
    BASS_GUITAR,
    BALALAIKA
}

public interface StringInstrument {

    public void numberOfStrings(InstrumentType type);
    public void accept(Visitor visitor);
}
