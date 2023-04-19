package eu.epfc.anc3.model;

public class Memento {
    private Ferme ferme;
    private Farmer farmer;
    private String backup;

    public Memento( Farmer farmer,Ferme ferme){
        this.farmer = farmer;
        this.ferme = ferme;

    }
    public void restore(){
        ferme.loadGame();
    }

}
