/**
 * Created by VKI on 11.01.2018.
 */
public abstract class SimpleStringInstrument implements StringInstrument {

    protected int numberOfStrings;
    protected double mensurLength;

    @Override
    public void numberOfStrings(InstrumentType type) {

        System.out.println(type.toString() + " has " + String.valueOf(numberOfStrings) + " strings ");
    }

    @Override
    public void accept(Visitor visitor) {

        visitor.visit(this);
    }

    public double getMensurLengthInSm() {

        return mensurLength;
    }



}
