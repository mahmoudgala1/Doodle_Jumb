package DoodleJump;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;

public class App extends Application {

    Boolean wMove = false;
    Boolean sMove = false;
    Boolean aMove = false;
    Boolean dMove = false;
    double xPos = 0;
    double yPos = 0;
    double yVelocity = 0;
    int xVelocity = 5;
    int GravityVelocity = 10;
    long time = System.currentTimeMillis();
    int FPS = 0;

    Rectangle Hitbox = new Rectangle(300, 400, 29, 29);
    Rectangle Player = new Rectangle(300, 400, 30, 30);

    Rectangle Obstacle1 = new Rectangle(600, 100, 150, 30);
    Rectangle Obstacle2 = new Rectangle(800, 300, 150, 30);
    Rectangle Obstacle3 = new Rectangle(200, 500, 150, 30);
    Rectangle Obstacle4 = new Rectangle(1000, 700, 150, 30);
    Rectangle Obstacle5 = new Rectangle(50, 600, 150, 30);
    Rectangle Obstacle6 = new Rectangle(100, 800, 150, 30);
    Rectangle Obstacle7 = new Rectangle(500, 400, 150, 30);
    Rectangle Obstacle8 = new Rectangle(1050, 200, 150, 30);

    Button startButton = new Button("Start");
    Label moveXLabel = new Label();
    Label moveYLabel = new Label();
    Label FPSLabel = new Label();
    Label stopYLabel = new Label();

    ArrayList<Rectangle> Obstacles = new ArrayList<Rectangle>();
    int[] ObstaclesDirection;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Hitbox.setFill(Color.AQUA);
        Player.setFill(Color.RED);
        //Player.setVisible(false);
        Collections.addAll(Obstacles, Obstacle1, Obstacle2, Obstacle3, Obstacle4, Obstacle5, Obstacle6, Obstacle7,Obstacle8);
        ObstaclesDirection = new int[Obstacles.size()];
        moveYLabel.setLayoutY(15);
        stopYLabel.setLayoutY(15);
        FPSLabel.setLayoutX(75);
        stopYLabel.setLayoutX(100);

        for (int i = 0; i < ObstaclesDirection.length; i++) {
            ObstaclesDirection[i] = 1;
        }

        Pane P = new Pane();
        P.getChildren().addAll(startButton, Hitbox, moveXLabel, moveYLabel, FPSLabel, stopYLabel, Obstacle1,
                Obstacle2, Obstacle3, Obstacle4, Obstacle5, Obstacle6, Obstacle7, Obstacle8, Player);
        Scene SC = new Scene(P, 1280, 800);
        startButton.setLayoutX((int) SC.getWidth() / 2);

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
                case SPACE:
                    yVelocity = -18;
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


                moveXLabel.setText("X = " + Player.getX());
                moveYLabel.setText("Y = " + Player.getY());

                if (wMove == true)
                    moveY(-xVelocity);
                if (sMove == true)
                    moveY(xVelocity);
                if (aMove == true)
                    moveX(-xVelocity);
                if (dMove == true)
                    moveX(xVelocity);
                    

                if (yVelocity < GravityVelocity)
                    yVelocity++;


                moveY((int) yVelocity);

                for (int i = 0; i < Obstacles.size(); i++) {
                    double pos = Obstacles.get(i).getX();

                    if (pos > SC.getWidth() - Obstacle1.getWidth() || pos < 0) {
                        ObstaclesDirection[i] *= -1;
                    }
                    Obstacles.get(i).setX(pos + (3 * ObstaclesDirection[i]));
                }

                if (System.currentTimeMillis() - time > 1000) {
                    FPSLabel.setText("FPS: " + FPS);
                    time = System.currentTimeMillis();
                    FPS = 0;
                }
                FPS++;
                Player.setX(Hitbox.getX());
                Player.setY(Hitbox.getY());
            }
        };

        startButton.setOnAction(e -> {
            GameLoop.start();
            P.getChildren().remove(startButton);
        });

        primaryStage.setScene(SC);
        primaryStage.show();
    }

    public void moveY(int distance) {

        for (int i = 0; i < Math.abs(distance); i++) {
            for (Rectangle Box : Obstacles) {
                if (Hitbox.getBoundsInParent().intersects(Box.getBoundsInParent())) {
                    if (Hitbox.getY() + Hitbox.getHeight() == Box.getY() && distance > 0) {
                        Hitbox.setY(yPos);
                        //yVelocity = -18;
                        return;
                    }                    
                }
            }
            yPos = Hitbox.getY();
            if (distance < 0)
                Hitbox.setY(Hitbox.getY() - 1);
            if (distance > 0)
                Hitbox.setY(Hitbox.getY() + 1);
        }
    }

    public void moveX(int distance) {

        for (int i = 0; i < Math.abs(distance); i++) {
            for (Rectangle Box : Obstacles) {
                if (Hitbox.getBoundsInParent().intersects(Box.getBoundsInParent())) {
                    if (Hitbox.getY() + Hitbox.getWidth() == Box.getY() && distance > 0) {
                        Hitbox.setX(xPos);
                        return;
                    }                   
                }
            }
            xPos = Hitbox.getX();
            if (distance < 0)
                Hitbox.setX(Hitbox.getX() - 1);
            if (distance > 0)
                Hitbox.setX(Hitbox.getX() + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
