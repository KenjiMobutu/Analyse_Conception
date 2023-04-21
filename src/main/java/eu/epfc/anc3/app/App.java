//ANC3 2223 c01
package eu.epfc.anc3.app;


import eu.epfc.anc3.view.FermeView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application  {
    //Test changement de dossier

    @Override
    public void start(Stage stage) throws Exception {
        FermeView ferme = new FermeView(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}
