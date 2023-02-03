package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.TerrainViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static eu.epfc.anc3.view.FermeView.GRID_WIDTH;
import static eu.epfc.anc3.view.FermeView.PADDING;

public class TerrainView extends GridPane {
    //grid
    public TerrainView(TerrainViewModel terrainViewModel, DoubleProperty fermeWidthProperty){

        setGridLinesVisible(false);
        setPadding(new Insets(PADDING));
        // Cellules de même taille
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100.0 / GRID_WIDTH);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / GRID_WIDTH);
        DoubleBinding cellWidthProperty = fermeWidthProperty.subtract(PADDING * 2).divide(GRID_WIDTH);

        for (int i = 0; i < GRID_WIDTH; ++i) {
            getColumnConstraints().add(columnConstraints);
            getRowConstraints().add(rowConstraints);
        }

        // Remplissage de la grille
        for (int i = 0; i < GRID_WIDTH; ++i) {
            for (int j = 0; j < GRID_WIDTH; ++j) {
                ParcelleView parcelleView = new ParcelleView(terrainViewModel.getParcelleViewModel(i, j), cellWidthProperty);
                add(parcelleView, j, i); // lignes/colonnes inversées dans gridpane
            }
        }
    }
}
