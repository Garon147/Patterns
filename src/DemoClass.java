

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by VKI on 07.01.2018.
 */
public class DemoClass {

    private static final double inchDivider = 2.54;

    public static void main(String[] args) {

        //Factory
        Supplier<InstrumentFactory> instrumentFactorySupplier = InstrumentFactory::new;

        instrumentFactorySupplier.get().getInstrument(InstrumentType.ELECTRIC_GUITAR.toString()).numberOfStrings(InstrumentType.ELECTRIC_GUITAR);
        instrumentFactorySupplier.get().getInstrument(InstrumentType.BASS_GUITAR.toString()).numberOfStrings(InstrumentType.BASS_GUITAR);
        instrumentFactorySupplier.get().getInstrument(InstrumentType.BALALAIKA.toString()).numberOfStrings(InstrumentType.BALALAIKA);

        //Visitor
        StringInstrumentsHolder stringInstrumentsHolder = new StringInstrumentsHolder(Arrays.asList(
                new ElectricGuitar(),
                new BassGuitar(),
                new Balalaika()));
        Visitor visitor = mensurInInchesVisitor;
        stringInstrumentsHolder.accept(visitor);
    }


    static Visitor mensurInInchesVisitor = (StringInstrument instrument) -> {

        if (instrument instanceof ElectricGuitar) {

            ElectricGuitar electricGuitar = (ElectricGuitar)instrument;
            double mensurInInches = electricGuitar.getMensurLengthInSm() / inchDivider;
            DemoClass.printMensurOfInches(ElectricGuitar.class.getName(), mensurInInches);
        } else if (instrument instanceof BassGuitar) {

            BassGuitar bassGuitar = (BassGuitar)instrument;
            double mensurInInches = bassGuitar.getMensurLengthInSm() / inchDivider;
            DemoClass.printMensurOfInches(BassGuitar.class.getName(), mensurInInches);
        } else if (instrument instanceof Balalaika) {

            Balalaika balalaika = (Balalaika) instrument;
            double mensurInInches = balalaika.getMensurLengthInSm() / inchDivider;
            DemoClass.printMensurOfInches(Balalaika.class.getName(), mensurInInches);
        } else if (instrument instanceof StringInstrumentsHolder) {

            for (StringInstrument stringInstrument: ((StringInstrumentsHolder) instrument).getStringInstruments()) {
                DemoClass.mensurInInchesVisitor.visit(stringInstrument);
            }
        } else {
            throw new IllegalArgumentException();
        }
    };


    public static void printMensurOfInches(String instrumentClassName, double mensurInInches) {

        System.out.println(instrumentClassName +
                " has mensur of " +
                String.valueOf(mensurInInches) +
                " in inches.");
    }
}
