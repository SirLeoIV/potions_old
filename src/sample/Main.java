package sample;

import math.Point2D;
import math.TwoVectors;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        TwoVectors vectors = new TwoVectors(new Point2D(8,5 ), new Point2D(8,9 ), new Point2D(9,7 ), new Point2D(11,7 ));
        TwoVectors vectors2 = new TwoVectors();
        System.out.println(vectors.calculateCollision());
        System.out.println(vectors2.calculateCollision());
        primaryStage.setTitle("Game");

        Scene scene = new MyScene(500, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
