package eu.epfc.anc3.model;

public class Memento {

    public Memento (){}

    Terrain terrain; Farmer farmer; FermeStatus fermeStatus; Ferme ferme;
    int score; int nbJour;
    public Memento(Terrain terrain, Farmer farmer, FermeStatus fermeStatus, Ferme ferme, int nbJour, int score){

        this.terrain = terrain; this.farmer = farmer; this.fermeStatus = fermeStatus;this.ferme = ferme; this.nbJour = nbJour; this.score = score;
    }

    public Memento(Terrain terrain, Farmer farmer, FermeStatus fermeStatus){

    }



    public Memento(Terrain terrain, int score, int jour) {
        this.terrain = terrain;
        this.score = score;
        this.nbJour = jour;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public int getScore() {
        return score;
    }

    public int getJour() {
        return nbJour;
    }


}
