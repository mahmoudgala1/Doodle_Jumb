package DoodleJump.Pages;

import DoodleJump.Main;
import javafx.animation.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameOverPage extends Pane {

    private Stage PrimaryStage;
    private ImageView Background6_iv = new ImageView(Images.Background6);
    private ImageView Character_iv = new ImageView(Images.Character);
    private ImageView PlayAgain_iv = new ImageView(Images.PlayAgain);
    private ImageView Main_iv = new ImageView(Images.Main);

    private Text score = new Text(1425, 850, FileIO.Read("Score.txt"));
    private ImageView Monster = new ImageView(Images.monster1Tiles);

    private int i;
    private Boolean reverse = false;
    private double yVelocity = 0;
    private double lastYPostion = 0;

    public GameOverPage(Stage stage) {
        this.PrimaryStage = stage;
    }

    public void start() {
        double x = 340;
        double y = 130;

        if (Integer.parseInt(FileIO.Read("TopScore.txt")) < Integer.parseInt(FileIO.Read("Score.txt"))) {
            FileIO.Write(FileIO.Read("Score.txt"), "TopScore.txt");
        }
        score.setFill(Color.BLACK);
        score.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 50));

        PlayAgain_iv.setX(940);
        PlayAgain_iv.setY(500);
        PlayAgain_iv.setFitHeight(y);
        PlayAgain_iv.setFitWidth(x);
        PlayAgain_iv.setOnMouseEntered(e -> {
            PlayAgain_iv.setImage(Images.PlayAgain2);
            PlayAgain_iv.setCursor(Cursor.HAND);
            PlayAgain_iv.setFitWidth(x + 20);
            PlayAgain_iv.setFitHeight(y + 8);
            PlayAgain_iv.setX(940 - 10);
            PlayAgain_iv.setY(500 - 4);
        });
        PlayAgain_iv.setOnMouseExited(e -> {
            PlayAgain_iv.setImage(Images.PlayAgain);
            PlayAgain_iv.setFitHeight(y);
            PlayAgain_iv.setFitWidth(x);
            PlayAgain_iv.setX(940);
            PlayAgain_iv.setY(500);
        });
        PlayAgain_iv.setOnMouseClicked(e -> {
            PrimaryStage.setScene(new DifficultyPage(PrimaryStage).Create());
        });

        ////////////////////////////////////////////////////////////////////////////////
        Main_iv.setX(1430);
        Main_iv.setY(500);
        Main_iv.setFitHeight(y);
        Main_iv.setFitWidth(x);
        Main_iv.setOnMouseEntered(e -> {
            Main_iv.setImage(Images.Main2);
            Main_iv.setCursor(Cursor.HAND);
            Main_iv.setFitWidth(x + 20);
            Main_iv.setFitHeight(y + 8);
            Main_iv.setX(1430 - 10);
            Main_iv.setY(500 - 4);
        });
        Main_iv.setOnMouseExited(e -> {
            Main_iv.setImage(Images.Main);
            Main_iv.setFitHeight(y);
            Main_iv.setFitWidth(x);
            Main_iv.setX(1430);
            Main_iv.setY(500);
        });
        Main_iv.setOnMouseClicked(e -> {
            PrimaryStage.setScene(new MainPage(PrimaryStage).Create());
        });

        ////////////////////////////////////////////////////////////////////////////////
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

        this.getChildren().addAll(Background6_iv, Character_iv, Monster, PlayAgain_iv, Main_iv, score);

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
