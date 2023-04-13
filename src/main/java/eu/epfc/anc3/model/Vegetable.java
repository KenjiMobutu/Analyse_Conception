package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class Vegetable {
    private VegetableState currentState;
    private boolean hasGrass;
    private IntegerProperty growthDays = new SimpleIntegerProperty(0);
    private int yieldPoints;
    private int rottenDays;
    private int daysSincePlanted;
    private Position pos;
    private boolean isFertilized;
    private boolean isPlantedOnGrass;
    private boolean isHarvested;
    private boolean isRotten;

    public Vegetable(boolean containGrass, int line, int col) {
        this.hasGrass = containGrass;
        this.pos = new Position(line,col);
        System.out.println(containGrass + "<----- CONTAINS GRASS ???????????");
        this.isFertilized = false;
    }
    public Vegetable(int growthDays, int yieldPoints, int rottenDays) {
        this.growthDays.set(growthDays);
        this.yieldPoints = yieldPoints;
        this.rottenDays = rottenDays;
        this.daysSincePlanted = 0;
        this.isFertilized = false;
        this.isPlantedOnGrass = false;
        this.isHarvested = false;
        this.isRotten = false;
        //this.currentState = new SeedState(this);
    }
    public void setState(VegetableState state) {
        this.currentState = state;
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
        currentState.nextState();
    }


    public int getHarvestPoints() {
        return currentState.getHarvestPoints();
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
        return currentState;
    }

    public void setCurrentState(VegetableState currentState) {
        this.currentState = currentState;
    }

    public IntegerProperty getGrowthDays() {
        return growthDays;
    }

    public int getYieldPoints() {
        return yieldPoints;
    }

    public int getRottenDays() {
        return rottenDays;
    }


}

