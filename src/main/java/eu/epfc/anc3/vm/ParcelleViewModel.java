package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.FermeFacade;
import eu.epfc.anc3.model.ParcelleValue;
import javafx.beans.property.ReadOnlyObjectProperty;

public class ParcelleViewModel {
//cell
    private final int line, col;
    private FermeFacade ferme;
    public ParcelleViewModel(int line, int col, FermeFacade ferme){
        this.col = col;
        this.line = line;
        this.ferme = ferme;
    }
//    public ReadOnlyObjectProperty<ParcelleValue> gameStatusProperty(){return ferme.fermeStatusProperty();}


    public ReadOnlyObjectProperty<ParcelleValue> valueProperty(){return ferme.valueProperty(line,col);}


    public void play() {
        ferme.play(line, col);
    }

}
