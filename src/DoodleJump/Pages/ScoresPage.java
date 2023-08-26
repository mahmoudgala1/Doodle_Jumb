package DoodleJump.Pages;

import DoodleJump.Main;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScoresPage extends Pane {

    private Stage PrimaryStage;
    private ImageView Background5_iv = new ImageView(Images.Background5);
    private ImageView X_iv = new ImageView(Images.X);
    private Text topscore = new Text(1050, 475, FileIO.Read("TopScore.txt"));
    private Text playerName = new Text(650, 475, FileIO.Read("PlayerName.txt"));

    public ScoresPage(Stage stage) {
        this.PrimaryStage = stage;
    }

    public void start() {

        topscore.setFill(Color.BLACK);
        topscore.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 50));
        playerName.setFill(Color.BLACK);
        playerName.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 50));

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
            PrimaryStage.setScene(new MainPage(PrimaryStage).Create());
        });
        this.getChildren().addAll(Background5_iv, topscore, X_iv, playerName);
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