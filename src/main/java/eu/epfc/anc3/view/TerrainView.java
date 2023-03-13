package eu.epfc.anc3.view;


import eu.epfc.anc3.vm.TerrainViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static eu.epfc.anc3.view.FermeView.*;

public class TerrainView extends GridPane {
    //grid
    public TerrainView (TerrainViewModel terrainViewModel, DoubleProperty fermeWidthProperty){
        setPadding(new Insets(10));
        // Cellules de même taille
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100.0 / FERME_HEIGHT);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / FERME_WIDTH);
        DoubleBinding cellWidthProperty = fermeWidthProperty.subtract(PADDING * 2).divide(FERME_WIDTH);

        // Remplissage de la grille
        for (int i = 0; i < FERME_HEIGHT; ++i) {
            getRowConstraints().add(rowConstraints);
            for (int j = 0; j < FERME_WIDTH; ++j) {
                getColumnConstraints().add(columnConstraints);
                ParcelleView parcelleView = new ParcelleView(terrainViewModel.getParcelleViewModel(i, j), cellWidthProperty);
                add(parcelleView, j, i);
            }
        }
    }

}
