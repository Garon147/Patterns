/**
 * Created by VKI on 15.01.2018.
 */

enum MementoState {

    NONE,
    ON,
    OFF
}

public class Memento {

    private final MementoState state;

    public Memento(MementoState state) {

        this.state = state;
    }

    public MementoState getState() {

        return this.state;
    }
}



