import com.sun.istack.internal.NotNull;

import java.util.List;

/**
 * Created by VKI on 11.01.2018.
 */
public class StringInstrumentsHolder implements StringInstrument {

    private List<StringInstrument> stringInstruments;

    public List<StringInstrument> getStringInstruments() {

        return stringInstruments;
    }

    public StringInstrumentsHolder(@NotNull List<StringInstrument> stringInstruments) {

        this.stringInstruments = stringInstruments;
    }

    @Override
    public void numberOfStrings(InstrumentType type) {
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
