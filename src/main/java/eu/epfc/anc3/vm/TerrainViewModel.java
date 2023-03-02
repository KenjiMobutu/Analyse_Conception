package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.FermeFacade;

public class TerrainViewModel {
    //grid
    private final FermeFacade ferme;

    public TerrainViewModel(FermeFacade ferme){
        this.ferme = ferme;
    }
    public ParcelleViewModel getParcelleViewModel(int line, int col) {
        return new ParcelleViewModel(line, col, ferme);
    }

}
