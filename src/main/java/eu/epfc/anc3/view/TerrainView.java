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
        setGridLinesVisible(true);
        setPadding(new Insets(10));
        // Cellules de même taille
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100.0 / FERME_WIDTH);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / FERME_WIDTH);
        DoubleBinding cellWidthProperty = fermeWidthProperty.subtract(PADDING * 2).divide(FERME_WIDTH);

        for (int i = 0; i < FERME_WIDTH; ++i) {
            getColumnConstraints().add(columnConstraints);
            getRowConstraints().add(rowConstraints);
        }

        // Remplissage de la grille
        for (int i = 0; i < FERME_WIDTH; ++i) {
            for (int j = 0; j < FERME_WIDTH; ++j) {
                ParcelleView parcelleView = new  ParcelleView(terrainViewModel.getParcelleViewModel(i, j), cellWidthProperty);
                add(parcelleView, j, i); // lignes/colonnes inversées dans gridpane
            }
        }
    }
}
