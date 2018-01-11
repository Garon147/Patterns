import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by VKI on 07.01.2018.
 */
public class InstrumentFactory {

    final static Map<String, Supplier<StringInstrument>> map = new HashMap<>();

    static {

        map.put(InstrumentType.ELECTRIC_GUITAR.toString(), ElectricGuitar::new);
        map.put(InstrumentType.BASS_GUITAR.toString(), BassGuitar::new);
        map.put(InstrumentType.BALALAIKA.toString(), Balalaika::new);
    }

    public StringInstrument getInstrument(String type) {

        Supplier<StringInstrument> instrumentSupplier = map.get(type);

        if (instrumentSupplier != null) {
            return instrumentSupplier.get();
        }
        throw new IllegalArgumentException("No such instrument " + type);
    }
}
