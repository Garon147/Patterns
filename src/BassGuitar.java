import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * Created by VKI on 07.01.2018.
 */
public class BassGuitar extends SimpleStringInstrument {

    private boolean isFourStrings;
    private static final WeakHashMap<BassGuitar, WeakReference<BassGuitar>> flyweightData =
            new WeakHashMap<>();

    public BassGuitar() {

        create(4, 86.36);
    }

    private BassGuitar(int numberOfStrings, double mensurLength) {

        this.numberOfStrings = numberOfStrings;
        this.mensurLength = mensurLength;

        isFourStrings = (numberOfStrings == 4) ? true : false;
    }

    public static BassGuitar create(int numberOfStrings, double mensurLength) {

        BassGuitar bassGuitar = new BassGuitar(numberOfStrings, mensurLength);
        if (!flyweightData.containsKey(bassGuitar)) {
            flyweightData.put(bassGuitar, new WeakReference<>(bassGuitar));
        }

        return flyweightData.get(bassGuitar).get();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof BassGuitar) {

            if (obj == this) {

                return true;
            }

            BassGuitar otherBass = (BassGuitar) obj;

            if (    otherBass.numberOfStrings == numberOfStrings &&
                    Math.abs(otherBass.mensurLength - mensurLength) <= 0.0001 &&
                    otherBass.isFourStrings == isFourStrings) {

                return true;
            }
        }

        return false;
    }
}
