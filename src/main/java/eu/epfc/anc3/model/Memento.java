package eu.epfc.anc3.model;

public class Memento {

    public Memento (){}

    public Memento(Terrain terrain, Farmer farmer, FermeStatus fermeStatus){

    }


    private Terrain terrain;
    private int score, jour;

    public Memento(Terrain terrain, int score, int jour) {
        this.terrain = terrain;
        this.score = score;
        this.jour = jour;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public int getScore() {
        return score;
    }

    public int getJour() {
        return jour;
    }


}
