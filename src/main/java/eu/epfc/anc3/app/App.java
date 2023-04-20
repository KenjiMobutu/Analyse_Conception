//ANC3 2223 c01
package eu.epfc.anc3.app;

import eu.epfc.anc3.view.FermeView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application  {

    @Override
    public void start(Stage stage) throws Exception {
        FermeView ferme = new FermeView(stage);
    }

    public static void main(String[] args) {

        launch();
    }
}
