package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class Main extends Application {
    private final double R = 200.0;
    private final double DELTA = 0.05;
    private final double X = 250.0;
    private final double Y = 100.0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 300, Color.WHITE);
        double y;
        double prevX = X - R;
        double prevY = Y;
        double pieLength = 200;
        for (double x = 0.0; x < R; x += DELTA) {// draw quarter of circle, then 2 lines; which depends on pie length + fill

            Path path = new Path();
            y = Math.sqrt(Math.pow(R, 2) - Math.pow(x - X, 2)) + Y;

            MoveTo moveTo = new MoveTo();
            moveTo.setX(prevX);
            moveTo.setY(prevY);
            path.getElements().add(moveTo);

            LineTo lineTo = new LineTo();
            lineTo.setX(x);
            lineTo.setY(y);
            path.getElements().add(lineTo);

            prevX = x;
            prevY = y;

            path.setStrokeWidth(2);
            path.setStroke(Color.BLACK);

            root.getChildren().add(path);
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
