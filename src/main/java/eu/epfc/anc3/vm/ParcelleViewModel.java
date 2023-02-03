package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.FermeFacade;

public class ParcelleViewModel {
//cell
    private final int line, col;
    private FermeFacade ferme;
    public ParcelleViewModel(int line, int col, FermeFacade ferme){
        this.col = col;
        this.line = line;
        this.ferme = ferme;
    }
}
