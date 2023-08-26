package DoodleJump;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {
    Circle cr = new Circle();
    Boolean wMove = false;
    Boolean sMove = false;
    Boolean aMove = false;
    Boolean dMove = false;
    Boolean canJump = true;
    double xPos = 0;
    double yPos = 0;
    double yVelocity = 0;
    int xVelocity = 5;
    int GravityVelocity = 10;
    long time = System.currentTimeMillis();
    int FPS = 0;
    double lastPos = 0;
    Rectangle Hitbox = new Rectangle(300, 400, 29, 29);
    Rectangle Player = new Rectangle(300, 400, 30, 30);

    Button startButton = new Button("Start");
    Label moveXLabel = new Label();
    Label moveYLabel = new Label();
    Label FPSLabel = new Label();
    Label stopYLabel = new Label();

    Obstacle[] newObstacles;

    @Override
    public void start(Stage primaryStage) {

        Hitbox.setFill(Color.AQUA);
        Player.setFill(Color.RED);
        // Player.setVisible(false);

        moveYLabel.setLayoutY(15);
        stopYLabel.setLayoutY(15);
        FPSLabel.setLayoutX(75);
        stopYLabel.setLayoutX(100);

        Pane P = new Pane();
        P.getChildren().addAll( Hitbox, moveXLabel, moveYLabel, FPSLabel, stopYLabel);
        Scene SC = new Scene(P, 1280, 700);
        startButton.setLayoutX((int) SC.getWidth() / 2);
        newObstacles = new Obstacle[7];
        for (int i = 0; i < 7; i++) {

            double atX = ((int) (Math.random() * 22) * (SC.getWidth() - 180) / 22);
            double atY = 100 * i;
            newObstacles[i] = new Obstacle(atX, atY, 150, 30);

            P.getChildren().add(newObstacles[i]);
            newObstacles[i].setFill(Color.color(Math.random(), Math.random(), Math.random()));

        }
        Hitbox.setX(newObstacles[6].getX() + newObstacles[6].getWidth() / 2);
        Hitbox.setY(newObstacles[6].getY() - newObstacles[6].getHeight());
        Player.setX(newObstacles[6].getX() + newObstacles[6].getWidth() / 2);
        Player.setY(newObstacles[6].getY() - newObstacles[6].getHeight());
        P.getChildren().add(Player);
        P.getChildren().add(startButton);

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
                    if (canJump == true) {
                        yVelocity = -18;
                        canJump = false;
                    }
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
                    moveY(-15);
                if (sMove == true)
                    moveY(xVelocity);
                if (aMove == true)
                    moveX(-xVelocity);
                if (dMove == true)
                    moveX(xVelocity);

                if (yVelocity < GravityVelocity)
                    yVelocity++;

                moveY((int) yVelocity);

                for (int i = 0; i < newObstacles.length; i++) {
                    double pos = newObstacles[i].getX();
                    if (pos > SC.getWidth() - newObstacles[i].getWidth() || pos < 0) {
                        newObstacles[i].toggleDir();
                    }

                    newObstacles[i].setX(pos + (3 * newObstacles[i].getDir()));

                }

                if (Player.getY() < SC.getHeight() / 2) {
                    for (int i = 0; i <  (SC.getHeight() / 2) - Player.getY() ; i++) {
                        for (int index = 0; index < newObstacles.length; index++) {
                            newObstacles[index].setY(newObstacles[index].getY() + 1);

                        }
                        Hitbox.setY(Hitbox.getY() + 1 );
                    }
                }

                for (int i = 0; i < newObstacles.length; i++) {
                    if(newObstacles[i].getY()+newObstacles[i].getHeight() > SC.getHeight()) {
                        newObstacles[i].setY(newObstacles[i].getY() - SC.getHeight());
                        newObstacles[i].setFill(Color.color(Math.random(), Math.random(), Math.random()));
                        newObstacles[i].setX((int) (Math.random() * 22) * (SC.getWidth() - 180) / 22);
                        System.out.print("test");
                    }
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
            for (int j = 0; j < newObstacles.length; j++) {
                if (Hitbox.getBoundsInParent().intersects(newObstacles[j].getBoundsInParent())) {
                    if (Hitbox.getY() + Hitbox.getHeight() == newObstacles[j].getY() && distance > 0) {
                        Hitbox.setY(yPos);
                        canJump = true;
                        // yVelocity = -18;
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
            for (int j = 0; j < newObstacles.length; j++) {
                if (Hitbox.getBoundsInParent().intersects(newObstacles[j].getBoundsInParent())) {
                    if (Hitbox.getY() + Hitbox.getHeight() == newObstacles[j].getY() && distance > 0) {
                        Hitbox.setX(xPos);
                        canJump = true;
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
