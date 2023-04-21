package eu.epfc.anc3.model;

public class CareTaker {

    private final Memento memento = new Memento();

    public void addMemento(Memento memento) {
       memento = memento;
    }

    public Memento getMemento() {
        return memento;
    }
}
