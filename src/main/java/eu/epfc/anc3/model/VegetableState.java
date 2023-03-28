package eu.epfc.anc3.model;
//L’interface État est une interface qui permettra de définir les méthodes communes à tous les états.
public abstract class VegetableState {
    /*
    propriétés :
    currentState : propriété qui contiendra l'état actuel de croissance du légume.
    *growthDays : propriété qui contiendra le nombre de jours nécessaires pour que le légume arrive à maturité.
    *yieldPoints : propriété qui contiendra le nombre de points gagnés en récoltant le légume.
    *rottenDays : propriété qui contiendra le nombre de jours avant que le légume ne pourrisse.
     */
    protected int growthDays;
    protected int yieldPoints;
    protected int rottenDays;
    protected int day;
    protected Vegetable vegetable;

    public VegetableState(Vegetable vegetable) {
        this.vegetable = vegetable;
    }
    /*
    Méthodes:
    next() : Cette méthode permettra de passer au stade de croissance suivant.
    nextDay() : Cette méthode permettra de passer au jour suivant.
    isRipe() : Cette méthode permettra de vérifier si le légume est arrivé à maturité.
    isRotten() : Cette méthode permettra de vérifier si le légume est pourri.
    getHarvestPoints() : Cette méthode permettra de récupérer le nombre de points gagnés en récoltant le légume.
    * */
    public abstract void nextState();
    public abstract boolean isRipe();
    public abstract boolean isRotten();
    public abstract int getHarvestPoints();
    public abstract void nextDay();

}
