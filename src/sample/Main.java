package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300, Color.WHITE);

        Path path = new Path();
        MoveTo moveTo = new MoveTo();
        moveTo.setX(50);
        moveTo.setY(50);

        LineTo lineTo = new LineTo();
        lineTo.setX(200);
        lineTo.setY(200);

        path.getElements().add(moveTo);
        path.getElements().add(lineTo);
        path.setStrokeWidth(2);
        path.setStroke(Color.BLACK);

        root.getChildren().add(path);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
