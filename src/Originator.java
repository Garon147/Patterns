/**
 * Created by VKI on 15.01.2018.
 */
public class Originator {

    private MementoState state;

    public void setState(MementoState state) {

        this.state = state;
    }

    public String getState() {

        return this.state.toString();
    }

    public Memento saveState() {

        return new Memento(state);
    }

    public void restoreState(Memento memento) {

        this.state = memento.getState();
    }
}
