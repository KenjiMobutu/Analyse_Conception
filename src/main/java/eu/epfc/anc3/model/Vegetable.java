package eu.epfc.anc3.model;
//context
public class Vegetable {
    private VegetableState vegetableState;
    private boolean hasGrass;
    private int growthDays;
    private int yieldPoints;
    private int rottenDays;
    private int daysSincePlanted;
    private boolean isFertilized;
    private boolean isPlantedOnGrass;
    private boolean isHarvested;
    private boolean isRotten;
    private int nextDay;
    public Vegetable() {
        //this.vegetableState = vegetableState; //new State1(this); //etat initial
    }

    /*public Vegetable(int growthDays, int yieldPoints, int rottenDays) {
        this.growthDays = growthDays;
        this.yieldPoints = yieldPoints;
        this.rottenDays = rottenDays;
        this.daysSincePlanted = 0;
        this.isFertilized = false;
        this.isPlantedOnGrass = false;
        this.isHarvested = false;
        this.isRotten = false;
        //this.currentState = new SeedlingState(this);
    }

    public Vegetable(VegetableState vegetableState, boolean hasGrass, int growthDays, int yieldPoints, int rottenDays, int daysSincePlanted, boolean isFertilized, boolean isPlantedOnGrass, boolean isHarvested, boolean isRotten, int nextDay) {
        this.vegetableState = vegetableState;
        this.hasGrass = hasGrass;
        this.growthDays = growthDays;
        this.yieldPoints = yieldPoints;
        this.rottenDays = rottenDays;
        this.daysSincePlanted = daysSincePlanted;
        this.isFertilized = isFertilized;
        this.isPlantedOnGrass = isPlantedOnGrass;
        this.isHarvested = isHarvested;
        this.isRotten = isRotten;
        this.nextDay = nextDay;
    }*/


    public void setState(VegetableState vegetableState) {
        this.vegetableState = vegetableState;
    }

    public boolean hasGrass() {
        return hasGrass;
    }

    public void setHasGrass(boolean hasGrass) {
        this.hasGrass = hasGrass;
    }
    public boolean isFertilized() {
        return isFertilized;
    }

    public void setFertilized(boolean fertilized) {
        isFertilized = fertilized;
    }

    public void nextState() {
        vegetableState.nextState();
    }
    public void nextDay() {
        daysSincePlanted++;
        if (daysSincePlanted > growthDays) {
            nextState();
        }
    }
    public boolean isRipe() {
        return vegetableState.isRipe();
    }

    public boolean isRotten() {
        return vegetableState.isRotten();
    }

    public int getHarvestPoints() {
        return vegetableState.getHarvestPoints();
    }

    public int getDaysSincePlanted() {
        return daysSincePlanted;
    }

    public void setDaysSincePlanted(int daysSincePlanted) {
        this.daysSincePlanted = daysSincePlanted;
    }

    public boolean isPlantedOnGrass() {
        return isPlantedOnGrass;
    }

    public void setPlantedOnGrass(boolean plantedOnGrass) {
        isPlantedOnGrass = plantedOnGrass;
    }

    public boolean isHarvested() {
        return isHarvested;
    }

    public void setHarvested(boolean harvested) {
        isHarvested = harvested;
    }

    public void setRotten(boolean rotten) {
        isRotten = rotten;
    }

    public VegetableState getCurrentState() {
        return vegetableState;
    }

    public void setCurrentState(VegetableState vegetableState) {
        this.vegetableState = vegetableState;
    }

    public int getGrowthDays() {
        return growthDays;
    }

    public int getYieldPoints() {
        return yieldPoints;
    }

    public int getRottenDays() {
        return rottenDays;
    }


}

