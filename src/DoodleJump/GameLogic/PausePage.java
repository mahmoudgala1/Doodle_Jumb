package DoodleJump.GameLogic;

import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import DoodleJump.Main;
import DoodleJump.Pages.*;

public class PausePage {

    private static ImageView X_iv = new ImageView(Images.X);
    private static ImageView PlayAgain_iv = new ImageView(Images.PlayAgain);
    private static ImageView Main_iv = new ImageView(Images.Main);
    private static ImageView Resume_iv = new ImageView(Images.Resume);
    private static ImageView PausePage = new ImageView(Images.PausePage);
    private static Font doodleFont = Font.loadFont(Main.PathToFont + "ShortStack-Regular.ttf", 50);

    //
    // A class for the pause pane
    //

    public static void Pause(Stage stage, Scene scene, Player Doodle, AnimationTimer GameLoop, GamePage gamePage) {

        Pane pause = new Pane();
        Text score = new Text(1150, 375, (int) Doodle.getScore() + "");
        score.setFill(Color.BLACK);
        score.setFont(doodleFont);

        ////////////////////////////////////////////////////////////////////////////////

        PlayAgain_iv.setX(570);
        PlayAgain_iv.setY(730);
        PlayAgain_iv.setFitWidth(365);
        PlayAgain_iv.setFitHeight(138);
        PlayAgain_iv.setOnMouseEntered(e -> {
            PlayAgain_iv.setImage(Images.PlayAgain2);
            PlayAgain_iv.setFitWidth(365 + 20);
            PlayAgain_iv.setFitHeight(138 + 8);
            PlayAgain_iv.setX(570 - 10);
            PlayAgain_iv.setY(730 - 4);
            PlayAgain_iv.setCursor(Cursor.HAND);
        });
        PlayAgain_iv.setOnMouseExited(e -> {
            PlayAgain_iv.setImage(Images.PlayAgain);
            PlayAgain_iv.setFitWidth(365);
            PlayAgain_iv.setFitHeight(138);
            PlayAgain_iv.setX(570);
            PlayAgain_iv.setY(730);
        });
        PlayAgain_iv.setOnMouseClicked(e -> {
            stage.setScene(new DifficultyPage(stage).Create());
        });

        ////////////////////////////////////////////////////////////////////////////////

        Main_iv.setX(970);
        Main_iv.setY(730);
        Main_iv.setFitWidth(365);
        Main_iv.setFitHeight(138);
        Main_iv.setOnMouseEntered(e -> {
            Main_iv.setCursor(Cursor.HAND);
            Main_iv.setImage(Images.Main2);
            Main_iv.setFitWidth(365 + 20);
            Main_iv.setFitHeight(138 + 8);
            Main_iv.setX(970 - 10);
            Main_iv.setY(730 - 4);
        });
        Main_iv.setOnMouseExited(e -> {
            Main_iv.setImage(Images.Main);
            Main_iv.setFitWidth(365);
            Main_iv.setFitHeight(138);
            Main_iv.setX(970);
            Main_iv.setY(730);
        });
        Main_iv.setOnMouseClicked(e -> {
            stage.setScene(new MainPage(stage).Create());
        });

        ////////////////////////////////////////////////////////////////////////////////

        Resume_iv.setX(760);
        Resume_iv.setY(520);
        Resume_iv.setFitWidth(402);
        Resume_iv.setFitHeight(152);
        Resume_iv.setOnMouseEntered(e -> {
            Resume_iv.setCursor(Cursor.HAND);
            Resume_iv.setImage(Images.Resume2);
            Resume_iv.setFitWidth(402 + 20);
            Resume_iv.setFitHeight(152 + 8);
            Resume_iv.setX(760 - 10);
            Resume_iv.setY(520 - 4);
        });
        Resume_iv.setOnMouseExited(e -> {
            Resume_iv.setImage(Images.Resume);
            Resume_iv.setFitWidth(402.2);
            Resume_iv.setFitHeight(152.12);
            Resume_iv.setX(760);
            Resume_iv.setY(520);
        });
        Resume_iv.setOnMouseClicked(e -> {
            // stage.setScene(scene);
            gamePage.getChildren().remove(pause);
            gamePage.setPauseActive(false);
            GameLoop.start();
        });

        ////////////////////////////////////////////////////////////////////////////////

        X_iv.setX(1380);
        X_iv.setY(80);
        X_iv.setFitWidth(40);
        X_iv.setFitHeight(40);
        X_iv.setOnMouseEntered(e -> {
            X_iv.setImage(Images.X2);
            X_iv.setCursor(Cursor.HAND);
        });
        X_iv.setOnMouseExited(e -> {
            X_iv.setImage(Images.X);
        });
        X_iv.setOnMouseClicked(e -> {
            gamePage.getChildren().remove(pause);
            gamePage.setPauseActive(false);
            GameLoop.start();
        });

        ////////////////////////////////////////////////////////////////////////////////

        pause.getChildren().addAll(PausePage, X_iv, PlayAgain_iv, Main_iv, Resume_iv, score);
        gamePage.getChildren().addAll(pause);
    }
}
