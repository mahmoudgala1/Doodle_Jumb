package DoodleJump.GameLogic;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import DoodleJump.Main;
import DoodleJump.Pages.*;

public class GamePage extends Pane {

    private Stage PrimaryStage;
    private Scene thisScene;

    public static double GameScreenHeightOffset = 215;

    public static double GameScreenWidth = 750 - 80;
    public static double GameScreenHeight = 1080 + GameScreenHeightOffset;
    public static double LeftBorder = 635 - 50 + 40;
    public static double RightBorder = 1285 + 50 - 40;
    public static double PlayerLeftBorder = 635 - 50;
    public static double PlayerRightBorder = 1285 + 50;

    private long time = System.currentTimeMillis();
    private int FPS = 0;
    private Boolean Status = true;
    private Boolean pauseActive = false;

    private AnimationTimer GameLoop;
    private Player Doodle = new Player();
    private Obstacle[] newObstacles = new Obstacle[26];
    private PowerUp[] newPowerUps = new PowerUp[Main.numPowDifficulty];
    private Monster[] newMonsters = new Monster[Main.numMonDifficulty];
    private ArrayList<Projectile> newProjectiles = new ArrayList<Projectile>();
    private ImageView BackGround = new ImageView(Main.imagBG);
    private ImageView BackGround2 = new ImageView(Main.imagBG);
    private ImageView BackBackGround = new ImageView(Images.BackBackGround);
    private ImageView Pause_iv = new ImageView(Images.Pause);

    private Label FPSLabel = new Label();
    private Label ScoreLabel = new Label();
    private Font doodleFont = Font.loadFont(Main.PathToFont + "ShortStack-Regular.ttf", 60);

    private Text score = new Text(1425, 850, "");

    private Audio soundFall = new Audio("fall.mp3");

    private KeyboardListener keyboardListener = new KeyboardListener(this, Doodle, newObstacles, newPowerUps,
            newMonsters, newProjectiles);

    public GamePage(Point2D Resolution, Stage stage) {
        super();
        this.setWidth(Resolution.getX());
        this.setHeight(Resolution.getY());
        this.PrimaryStage = stage;
        // newPowerUps = new PowerUp[numofpow];
        // newMonsters = new Monster[numofmon];
    }

    // Initialize game primary pane

    public void start() {

        FileIO.Write((int) Doodle.getScore() + "", "Score.txt");

        ScoreLabel.setLayoutX(50);
        ScoreLabel.setLayoutY(950);
        ScoreLabel.setFont(doodleFont);
        ScoreLabel.setTextFill(Color.BURLYWOOD);
        ScoreLabel.setEffect(new DropShadow(10, Color.BLACK));
        FPSLabel.setLayoutX(75);
        BackGround.setX(635 - 50);
        BackGround2.setX(635 - 50);
        BackGround2.setY(-(GameScreenHeight + 500));

        this.getChildren().addAll(BackGround, BackGround2, Doodle.Hitbox);
        Obstacle.initialize(newObstacles, this);
        PowerUp.initialize(newPowerUps, newObstacles, this);
        Monster.initialize(newMonsters, newPowerUps, newObstacles, this);
        this.getChildren().addAll(Doodle.Nozzle, Doodle);
        PowerUp.initializeAnimations(newPowerUps, this);
        this.getChildren().addAll(BackBackGround, FPSLabel, ScoreLabel, Pause_iv);

        keyboardListener.Start();
        ArduinoListener.Xspeed = 0;

        GameLoop = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                if (Status == true) {
                    ScoreLabel.setText("Score: " + (int) Doodle.getScore());
                    // ScoreLabel.setText("Score: " + ArduinoListener.Xspeed);

                    Doodle.gravityCycle(newObstacles, newPowerUps, newMonsters);
                    Doodle.screenScroll(newObstacles, BackGround, BackGround2);

                    for (int i = 0; i < newObstacles.length; i++) {
                        newObstacles[i].swing();
                        newObstacles[i].teleportUP(Doodle, newPowerUps, newMonsters);
                    }

                    if (BackGround.getY() > GameScreenHeight) {
                        BackGround.setY(BackGround.getY() - 1 * (GameScreenHeight));
                        BackGround2.setY(BackGround2.getY() - 2 * (GameScreenHeight));
                    }

                    if (ArduinoListener.Status == true && Main.numInput == 2) {
                        Doodle.moveX(ArduinoListener.Xspeed, newObstacles);
                    } else {
                        keyboardListener.Loop();
                    }

                    Projectile.Loop(newProjectiles, newMonsters);
                    FPSCounter();
                    LoseCheck();

                }
            }
        };

        GameLoop.start();

        Pause_iv.setX(480);
        Pause_iv.setY(20);
        Pause_iv.setFitWidth(75);
        Pause_iv.setFitHeight(75);
        Pause_iv.setOnMouseEntered(e -> {
            Pause_iv.setImage(Images.Pause2);
            Pause_iv.setFitWidth(85);
            Pause_iv.setFitHeight(85);
            Pause_iv.setX(480 - 5);
            Pause_iv.setY(20 - 5);
            Pause_iv.setCursor(Cursor.HAND);
        });
        Pause_iv.setOnMouseExited(e -> {
            Pause_iv.setImage(Images.Pause);
            Pause_iv.setFitWidth(75);
            Pause_iv.setFitHeight(75);
            Pause_iv.setX(480);
            Pause_iv.setY(20);

        });
        Pause_iv.setOnMouseClicked(e -> {
            PausePage.Pause(PrimaryStage, thisScene, Doodle, GameLoop, this);
            this.pauseActive = true;
            GameLoop.stop();
        });

    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    // An FPS Counter
    public void FPSCounter() {
        if (System.currentTimeMillis() - time > 1000) {
            FPSLabel.setText("FPS: " + FPS);
            time = System.currentTimeMillis();
            FPS = 0;
        }
        FPS++;
    }

    // Checking for conditions of losing. if true activate the method lose.
    public void LoseCheck() {
        if (Doodle.getY() > GameScreenHeight - GameScreenHeightOffset) {
            Lose();
        }
        for (int i = 0; i < newMonsters.length; i++) {
            if (Doodle.Hitbox.getBoundsInParent().intersects(newMonsters[i].getBoundsInParent())
                    && newMonsters[i].getStatus() == true && Doodle.getHasSomething() == false
                    && Main.immune == false) {
                Lose();
            }
        }
    }

    // Turn Off the game loop and bring up the gameover pane.
    private void Lose() {
        Status = false;
        score = new Text(1425, 850, FileIO.Read("Score.txt"));
        FileIO.Write((int) Doodle.getScore() + "", "Score.txt");
        soundFall.play();
        PrimaryStage.setScene(new GameOverPage(PrimaryStage).Create());
    }

    // Calling this method creates the pane with the correct resolution
    public Scene Create() {
        thisScene = new Scene(this, Main.SelectedResolution.getX(), Main.SelectedResolution.getY());
        this.setLayoutX(Main.SelectedOffset.getX());
        this.setLayoutY(Main.SelectedOffset.getY());
        this.setScaleX(Main.Factor / 3);
        this.setScaleY(Main.Factor / 3);
        start();
        return thisScene;
    }

    // Called every time a new object is added to the pane so these elements can be
    // on top
    public void toAbove() {

        BackBackGround.toFront();
        ScoreLabel.toFront();
        Pause_iv.toFront();
    }

    public Boolean getPauseActive() {
        return pauseActive;
    }

    public void setPauseActive(Boolean pauseActive) {
        this.pauseActive = pauseActive;
    }

}
