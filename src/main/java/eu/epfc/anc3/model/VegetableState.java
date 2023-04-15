package eu.epfc.anc3.model;

public abstract class VegetableState {
    /*
    propriétés :
    growthDays : propriété qui contiendra le nombre de jours nécessaires pour que le légume arrive à maturité.
    daysToNextState : propriété qui contiendra le nombre de jours nécessaires pour passer à l'état suivant.
     */
    //faire un state pour chaque légume + grass
    protected int growthDays ; // Nombre de jours nécessaires pour que le légume arrive à maturité
    protected int daysToNextState; // Nombre de jours nécessaires pour passer à l'état suivant

    protected Vegetable vegetable; // Légume auquel cet état est associé
    // Constructeur
    public VegetableState(Vegetable vegetable, int growthDays, int daysToNextState) {
        this.vegetable = vegetable;
        this.growthDays = growthDays;
        this.daysToNextState = daysToNextState;
    }
    public VegetableState(Vegetable vegetable) {
        this.vegetable = vegetable;
    }

    // Méthode abstraite pour passer à l'état suivant
    public abstract ParcelleValue nextState();

    // Méthode abstraite pour obtenir le nombre de points gagnés en récoltant le légume
    public abstract int getHarvestPoints();

    // Méthode abstraite pour faire avancer la croissance du légume
    public abstract void nextDay();

    @Override
    public String toString() {
        return "VegetableState{" +
                "growthDays=" + growthDays +
                ", daysToNextState=" + daysToNextState +
               // ", day=" + day +
                ", vegetable=" + vegetable +
                '}';
    }
    // Méthode pour récupérer le type de légume associé à cet état
    public abstract ParcelleValue getType();

    public abstract void nextDayWithGrass();

    public abstract int stateProperty();

    public abstract int getDaysBeforeRotting();
}
