package DoodleJump;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    Rectangle Hitbox = new Rectangle(275, 950, 49, 49);
    ImageView Player = new ImageView(new Image("DoodleJump/doodleR.png"));
    ImageView BackGround = new ImageView(new Image("DoodleJump/bg.png"));

    Button startButton = new Button("Start");
    Label moveXLabel = new Label();
    Label moveYLabel = new Label();
    Label FPSLabel = new Label();
    Label stopYLabel = new Label();

    Obstacle[] newObstacles;

    @Override
    public void start(Stage primaryStage) {

        Hitbox.setFill(Color.AQUA);
        //Player.setFill(Color.RED);
        Hitbox.setVisible(false);

        moveYLabel.setLayoutY(15);
        stopYLabel.setLayoutY(15);
        FPSLabel.setLayoutX(75);
        stopYLabel.setLayoutX(100);
        Player.setFitHeight(50);
        Player.setFitWidth(50);
        Player.setX(275);
        Player.setY(950);

        Pane P = new Pane();
        P.getChildren().addAll(BackGround, Hitbox, moveXLabel, moveYLabel, FPSLabel, stopYLabel);
        Scene SC = new Scene(P, 600, 1080);
        startButton.setLayoutX((int) SC.getWidth() / 2);
        newObstacles = new Obstacle[21];
        newObstacles[0] = new Obstacle(250, 1000, 58, 15);
        
        P.getChildren().add(newObstacles[0]);
        for (int i = 1; i < newObstacles.length; i++) {
            // double atX = 0;
            // double atX = (int) (Math.random() * 22) * newObstacles[i-1].getX() ;
            double atX = ((int) (Math.random() * 22) * (SC.getWidth() - 50) / 22);
            double atY = 50 * i;

            // if(probablity > 0.5){

            // atX = (newObstacles[i-1].getX() + newObstacles[i-1].getWidth()) +
            // 200;//(int)((Math.random()/10) * 100);
            // if(atX > 600)
            // atX = 600 - newObstacles[0].getWidth() - (int)((Math.random()/10) * 200) ;
            // }else {
            // atX = (newObstacles[i-1].getX() + newObstacles[i-1].getWidth()) -
            // 200;//(int)((Math.random()/10) * 100);
            // if(atX < 0)
            // atX = 0 + (int)((Math.random()/10) * 200);
            // }

            newObstacles[i] = new Obstacle(atX, 1000 - atY, 58, 15);
            P.getChildren().add(newObstacles[i]);
            

        
            double probablity = Math.random();
            if (probablity > 0.9) {
                newObstacles[i].setMove(true);
                newObstacles[i].setImage(new Image("DoodleJump/obs2.png"));
            }

        }
        // newObstacles[20].setX(250);
        // newObstacles[20].setY(1000);
        // Hitbox.setX(newObstacles[newObstacles.length-1].getX() +
        // newObstacles[newObstacles.length-1].getWidth() / 2);
        // Hitbox.setY(newObstacles[newObstacles.length-1].getY() - Hitbox.getHeight());
        // Player.setX(newObstacles[newObstacles.length-1].getX() +
        // newObstacles[newObstacles.length-1].getWidth() / 2);
        // Player.setY(newObstacles[newObstacles.length-1].getY() - Player.getHeight());
        P.getChildren().add(Player);
        P.getChildren().add(startButton);

        SC.setOnKeyPressed(PressedKey -> {
            switch (PressedKey.getCode()) {
                case W:
                    //Move = true;
                     if (canJump == true) {
                     yVelocity = -18;
                     // canJump = false;
                     }
                    break;
                case S:
                    sMove = true;
                    break;
                case A:
                    aMove = true;
                    Player.setImage(new Image("DoodleJump/doodleL.png"));
                    break;
                case D:
                    dMove = true;
                    Player.setImage(new Image("DoodleJump/doodleR.png"));
                    break;
                case SPACE:
                    
                    xVelocity = 15;
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
                case SPACE:
                    xVelocity = 5;
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
                    if (newObstacles[i].getMove() == true) {
                        double pos = newObstacles[i].getX();
                        if (pos > SC.getWidth() - newObstacles[i].getFitWidth() || pos < 0) {
                            newObstacles[i].toggleDir();
                        }
                        newObstacles[i].setX(pos + (3 * newObstacles[i].getDir()));
                    }
                }

                if (Player.getY() <  (SC.getHeight() / 1.7)) {
                    for (int i = 0; i <  (SC.getHeight() / 1.7) - Player.getY(); i++) {
                        for (int index = 0; index < newObstacles.length; index++) {
                            newObstacles[index].setY(newObstacles[index].getY() + 0.05);
                        }
                        Hitbox.setY(Hitbox.getY() + 0.05);
                    }

                    for (int index = 0; index < newObstacles.length; index++) {
                        newObstacles[index].setY(Math.ceil(newObstacles[index].getY()) );
                    }
                    Hitbox.setY(Math.ceil( Hitbox.getY()));
                }

                for (int i = 0; i < newObstacles.length; i++) {
                    if (newObstacles[i].getY() + newObstacles[i].getFitHeight() > SC.getHeight()) {
                        newObstacles[i].setY(newObstacles[i].getY() - SC.getHeight());
                        
                        newObstacles[i].setX((int) (Math.random() * 22) * (SC.getWidth() - 50) / 22);
                        double probablity = Math.random();
                        if (probablity > 0.5) {
                            newObstacles[i].toggleDir();
                        }
                        if (probablity > 0.9) {
                            newObstacles[i].setMove(true);
                            newObstacles[i].setImage(new Image("DoodleJump/obs2.png"));
                        } else {
                            newObstacles[i].setMove(false);
                            newObstacles[i].setImage(new Image("DoodleJump/obs.png"));
                        }
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
                        // canJump = true;
                        yVelocity = -20;
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
                        // canJump = true;
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
