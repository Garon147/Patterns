

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

        //Factory + Supplier
        System.out.println("__________________FACTORY + SUPPLIER_____________________");
        Supplier<InstrumentFactory> instrumentFactorySupplier = InstrumentFactory::new;

        ElectricGuitar electricGuitar = (ElectricGuitar)instrumentFactorySupplier.get().
                getStringInstrument(InstrumentType.ELECTRIC_GUITAR.toString());
        electricGuitar.numberOfStrings(InstrumentType.ELECTRIC_GUITAR);

        BassGuitar bassGuitar = (BassGuitar)instrumentFactorySupplier.get().
                getStringInstrument(InstrumentType.BASS_GUITAR.toString());
        bassGuitar.numberOfStrings(InstrumentType.BASS_GUITAR);

        Balalaika balalaika = (Balalaika)instrumentFactorySupplier.get().
                getStringInstrument(InstrumentType.BALALAIKA.toString());
        balalaika.numberOfStrings(InstrumentType.BALALAIKA);

        //Visitor
        System.out.println("__________________VISITOR_____________________");
        StringInstrumentsHolder stringInstrumentsHolder = new StringInstrumentsHolder(Arrays.asList(
                new ElectricGuitar(),
                new BassGuitar(),
                BassGuitar.create(5, 88.9),
                BassGuitar.create(4, 86.36),
                new Balalaika()));
        Visitor visitor = mensurInInchesVisitor;
        stringInstrumentsHolder.accept(visitor);

        //Flyweight
        System.out.println("__________________FLYWEIGHT_____________________");
        Banjo firstBanjo = Banjo.create(6, true);
        Banjo secondBanjo = Banjo.create(5, false);
        Banjo thirdBanjo = Banjo.create(6, true);

        //Memento
        System.out.println("__________________MEMENTO_____________________");
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();

        originator.setState(MementoState.ON);
        System.out.println("State is " + originator.getState());
        caretaker.setMemento(originator.saveState());

        originator.setState(MementoState.OFF);
        System.out.println("State is " + originator.getState());

        originator.restoreState(caretaker.getMemento());
        System.out.println("State is " + originator.getState());
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
