//ANC3 2223 c01
package eu.epfc.anc3.app;

import eu.epfc.anc3.model.Carrot;
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
//        Carrot carrotState1 = new Carrot();
//        carrotState1.nextState();
//        System.out.println(carrotState1.getCurrentState());
//        carrotState1.nextState();
//        System.out.println(carrotState1.getCurrentState()); carrotState1.nextState();
//        System.out.println(carrotState1.getCurrentState()); carrotState1.nextState();
//        System.out.println(carrotState1.getCurrentState());
//
//        System.out.println(carrotState1.getType());
//        Carrot.CarrotState2 carrotState2 = new Carrot().new CarrotState2(new Carrot());
//        carrotState2.nextState();
//        Carrot.CarrotState3 carrotState3 = new Carrot().new CarrotState3(new Carrot());
//        carrotState3.nextState();
//        Carrot.CarrotState4 carrotState4 = new Carrot().new CarrotState4(new Carrot());
//        carrotState4.nextState();
    }
}
