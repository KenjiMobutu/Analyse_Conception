package eu.epfc.anc3.model;

 class Memento {
    private Ferme ferme;
    private int score, jour;

     public Memento() {

     }

    public Memento(Ferme ferme, int score, int jour) {
        this.ferme = ferme;
        this.score = score;
        this.jour = jour;
    }

     public Ferme getFerme() {
         return ferme;
     }

     public int getScore() {
         return score;
     }

     public int getJour() {
         return jour;
     }
 }
