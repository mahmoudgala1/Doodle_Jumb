package DoodleJump.Pages;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import DoodleJump.Main;

public class MainPage extends Pane {

    private Stage PrimaryStage;
    private Pane buttons = new Pane();

    private ImageView Background_iv = new ImageView(Images.Background4);
    private ImageView Character_iv = new ImageView(Images.Character);
    private ImageView Play_iv = new ImageView(Images.Play);
    private ImageView Settings_iv = new ImageView(Images.Settings);
    private ImageView Scores_iv = new ImageView(Images.Scores);
    private ImageView Exit_iv = new ImageView(Images.Exit);
    private ImageView Logo_iv = new ImageView(Images.Logo);
    private ImageView Monster = new ImageView(Images.monster1Tiles);
    private ImageView Credits_iv = new ImageView(Images.Credits);
    private int i;
    private Boolean reverse = false;
    private double yVelocity = 0;
    private double lastYPostion = 0;

    private Audio soundGame = new Audio("mystery.wav");

    public MainPage(Stage stage) {
        this.PrimaryStage = stage;
    }

    public void start() {
        double x = 300;
        double y = 110;
        soundGame.play();
        Character_iv.setX(490);
        Character_iv.setY(100);
        Character_iv.setFitWidth(110);
        Character_iv.setFitHeight(110);

        AnimationTimer jumping = new AnimationTimer() {

            @Override
            public void handle(long arg0) {

                for (int i = 0; i < Math.abs(yVelocity); i++) {
                    if (Character_iv.getY() + Character_iv.getFitHeight() > 950) {
                        Character_iv.setY(lastYPostion);
                        yVelocity = -20;
                        return;
                    }
                    lastYPostion = Character_iv.getY();
                    if (yVelocity < 0) {
                        Character_iv.setY(Character_iv.getY() - 1);
                    }
                    if (yVelocity > 0) {
                        Character_iv.setY(Character_iv.getY() + 1);
                    }
                }

                if (yVelocity < 12)
                    yVelocity++;

            }
        };
        jumping.start();
        ////////////////////////////////////////////////////////////////////////////////

        Logo_iv.setFitHeight(800);
        Logo_iv.setFitWidth(800);
        Logo_iv.setX(120);
        Logo_iv.setY(0);

        ////////////////////////////////////////////////////////////////////////////////
        Play_iv.setFitHeight(y);
        Play_iv.setFitWidth(x);
        Play_iv.setX(1250);
        Play_iv.setY(150);
        Play_iv.setOnMouseEntered(e -> {
            Play_iv.setImage(Images.Play2);
            Play_iv.setCursor(Cursor.HAND);
            Play_iv.setFitWidth(x + 20);
            Play_iv.setFitHeight(y + 8);
            Play_iv.setX(1250 - 10);
            Play_iv.setY(150 - 4);
        });
        Play_iv.setOnMouseExited(e -> {
            Play_iv.setImage(Images.Play);
            Play_iv.setFitWidth(x);
            Play_iv.setFitHeight(y);
            Play_iv.setX(1250);
            Play_iv.setY(150);
        });
        Play_iv.setOnMouseClicked(e -> {
            soundGame.stop();
            PrimaryStage.setScene(new SelectPage(PrimaryStage).Create());
        });
        ////////////////////////////////////////////////////////////////////////////////

        Settings_iv.setFitHeight(y);
        Settings_iv.setFitWidth(x);
        Settings_iv.setX(1350);
        Settings_iv.setY(310);
        Settings_iv.setOnMouseEntered(e -> {
            Settings_iv.setImage(Images.Settings2);
            Settings_iv.setCursor(Cursor.HAND);
            Settings_iv.setFitWidth(x + 20);
            Settings_iv.setFitHeight(y + 8);
            Settings_iv.setX(1350 - 10);
            Settings_iv.setY(310 - 4);
        });
        Settings_iv.setOnMouseExited(e -> {
            Settings_iv.setImage(Images.Settings);
            Settings_iv.setFitWidth(x);
            Settings_iv.setFitHeight(y);
            Settings_iv.setX(1350);
            Settings_iv.setY(310);
        });
        Settings_iv.setOnMouseClicked(e -> {
            soundGame.stop();
            PrimaryStage.setScene(new SettingsPage(PrimaryStage).Create());
        });
        ////////////////////////////////////////////////////////////////////////////////
        Scores_iv.setFitHeight(y);
        Scores_iv.setFitWidth(x);
        Scores_iv.setX(1250);
        Scores_iv.setY(470);
        Scores_iv.setOnMouseEntered(e -> {
            Scores_iv.setImage(Images.Scores2);
            Scores_iv.setCursor(Cursor.HAND);
            Scores_iv.setFitWidth(x + 20);
            Scores_iv.setFitHeight(y + 8);
            Scores_iv.setX(1250 - 10);
            Scores_iv.setY(470 - 4);
        });
        Scores_iv.setOnMouseExited(e -> {
            Scores_iv.setImage(Images.Scores);
            Scores_iv.setFitWidth(x);
            Scores_iv.setFitHeight(y);
            Scores_iv.setX(1250);
            Scores_iv.setY(470);
        });
        Scores_iv.setOnMouseClicked(e -> {
            soundGame.stop();
            PrimaryStage.setScene(new ScoresPage(PrimaryStage).Create());
        });
        ////////////////////////////////////////////////////////////////////////////////
        Credits_iv.setFitHeight(y);
        Credits_iv.setFitWidth(x);
        Credits_iv.setX(1350);
        Credits_iv.setY(630);
        Credits_iv.setOnMouseEntered(e -> {
            Credits_iv.setImage(Images.Credits2);
            Credits_iv.setCursor(Cursor.HAND);
            Credits_iv.setFitWidth(x + 20);
            Credits_iv.setFitHeight(y + 8);
            Credits_iv.setX(1350 - 10);
            Credits_iv.setY(630 - 4);
        });
        Credits_iv.setOnMouseExited(e -> {
            Credits_iv.setImage(Images.Credits);
            Credits_iv.setFitWidth(x);
            Credits_iv.setFitHeight(y);
            Credits_iv.setX(1350);
            Credits_iv.setY(630);
        });
        Credits_iv.setOnMouseClicked(e -> {
            soundGame.stop();
            PrimaryStage.setScene(new CreditsPage(PrimaryStage).Create());
        });
        ////////////////////////////////////////////////////////////////////////////////

        Exit_iv.setFitHeight(y);
        Exit_iv.setFitWidth(x);
        Exit_iv.setX(1250);
        Exit_iv.setY(790);
        Exit_iv.setOnMouseEntered(e -> {
            Exit_iv.setImage(Images.Exit2);
            Exit_iv.setCursor(Cursor.HAND);
            Exit_iv.setFitWidth(x + 20);
            Exit_iv.setFitHeight(y + 8);
            Exit_iv.setX(1250 - 10);
            Exit_iv.setY(790 - 4);
        });
        Exit_iv.setOnMouseExited(e -> {
            Exit_iv.setImage(Images.Exit);
            Exit_iv.setFitWidth(x);
            Exit_iv.setFitHeight(y);
            Exit_iv.setX(1250);
            Exit_iv.setY(790);
        });
        Exit_iv.setOnMouseClicked(e -> {
            System.exit(0);
            PrimaryStage.close();
        });

        ////////////////////////////////////////////////////////////////////////////////
        Monster.setFitHeight(90);
        Monster.setFitWidth(160);
        Monster.setX(800);
        Monster.setY(110);
        Monster.setRotate(10);
        Timeline animate = new Timeline(new KeyFrame(Duration.millis(50), e -> {

            Monster.setViewport(new Rectangle2D(i * 156, 0, 156, 89));
            if (reverse == false) {
                i++;
                if (i == 4)
                    reverse = true;
            } else {
                i--;
                if (i == 0)
                    reverse = false;
            }

        }));
        animate.setCycleCount(Timeline.INDEFINITE);
        animate.play();

        ////////////////////////////////////////////////////////////////////////////////

        buttons.getChildren().addAll(Play_iv, Settings_iv, Scores_iv, Exit_iv, Character_iv, Credits_iv);
        this.getChildren().addAll(Background_iv, Monster, buttons, Logo_iv);
    }

    public Scene Create() {
        this.setLayoutX(Main.SelectedOffset.getX());
        this.setLayoutY(Main.SelectedOffset.getY());
        this.setScaleX(Main.Factor / 3);
        this.setScaleY(Main.Factor / 3);
        start();
        return new Scene(this, Main.SelectedResolution.getX(), Main.SelectedResolution.getY());
    }
}
