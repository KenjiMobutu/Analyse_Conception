//ANC3 2223 c01
package eu.epfc.anc3.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application  {

    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new StackPane();
        Label label = new Label("Hello !");
        label.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, FontPosture.ITALIC, 60));

        root.getChildren().add(label);
        Scene scene = new Scene(root, 640, 480);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Projet ANC3 2223 c01");
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    public static void main(String[] args) {
        launch();
    }

}
