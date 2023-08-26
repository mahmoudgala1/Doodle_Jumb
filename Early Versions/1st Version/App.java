package DoodleJump;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {

    Boolean wMove = false;
    Boolean sMove = false;
    Boolean aMove = false;
    Boolean dMove = false;
    double xPos = 0;
    double yPos = 0;
    ArrayList<Rectangle> Obstacles = new ArrayList<Rectangle>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Circle circle = new Circle(300, 350, 19.9);
        Rectangle Obstacle1 = new Rectangle(400, 100, 200, 40);
        Rectangle Obstacle2 = new Rectangle(250, 400, 200, 40);
        Rectangle Obstacle3 = new Rectangle(550, 600, 200, 40);
        Rectangle Obstacle4 = new Rectangle(100, 700, 200, 40);
        Obstacles.add(Obstacle1);
        Obstacles.add(Obstacle2);
        Obstacles.add(Obstacle3);
        Obstacles.add(Obstacle4);

        Label moveXLabel = new Label();
        Label moveYLabel = new Label();
        Label stopXLabel = new Label();
        Label stopYLabel = new Label();
        moveYLabel.setLayoutY(15);
        stopYLabel.setLayoutY(15);
        stopXLabel.setLayoutX(100);
        stopYLabel.setLayoutX(100);
        Pane P = new Pane();
        P.getChildren().addAll(circle, moveXLabel, moveYLabel, stopXLabel, stopYLabel, Obstacle1 , Obstacle2 , Obstacle3 ,Obstacle4);

        Scene SC = new Scene(P, 800, 800);

        SC.setOnKeyPressed(PressedKey -> {
            switch (PressedKey.getCode()) {
                case W:
                    wMove = true;
                    break;
                case S:
                    sMove = true;
                    break;
                case A:
                    aMove = true;
                    break;
                case D:
                    dMove = true;
                    break;
                default:
            }
        });

        SC.setOnKeyReleased(PressedKey -> {

            switch (PressedKey.getCode()) {
                case W:
                    wMove = false;
                    break;
                case S:
                    sMove = false;
                    break;
                case A:
                    aMove = false;
                    break;
                case D:
                    dMove = false;
                    break;
                default:
            }
        });

        AnimationTimer GameLoop = new AnimationTimer() {

            @Override
            public void handle(long arg0) {

                xPos = circle.getCenterX();
                yPos = circle.getCenterY();
                if (wMove == true)
                    circle.setCenterY(circle.getCenterY() - 5);
                if (sMove == true)
                    circle.setCenterY(circle.getCenterY() + 5);
                if (aMove == true)
                    circle.setCenterX(circle.getCenterX() - 5);
                if (dMove == true)
                    circle.setCenterX(circle.getCenterX() + 5);

                moveXLabel.setText("X = " + xPos);
                moveYLabel.setText("Y = " + yPos);

                for (Rectangle Box : Obstacles) {
                    if (circle.getBoundsInParent().intersects(Box.getBoundsInParent())) {

                        stopXLabel.setText("X = " + circle.getCenterX());
                        stopYLabel.setText("Y = " + circle.getCenterY());
                        circle.setCenterX(xPos);
                        circle.setCenterY(yPos);
                    }

                }

            }
        };
        GameLoop.start();

        primaryStage.setScene(SC);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
