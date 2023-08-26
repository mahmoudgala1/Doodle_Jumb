package DoodleJump.Pages;

import DoodleJump.Main;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SelectPage extends Pane {

    private Stage PrimaryStage;

    private ImageView Background_iv = new ImageView(Images.Background8);
    private ImageView NewGame_iv = new ImageView(Images.NewGame);
    private ImageView Continue_iv = new ImageView(Images.Continue);
    private ImageView X_iv = new ImageView(Images.X);

    public SelectPage(Stage stage) {
        this.PrimaryStage = stage;
    }

    public void start() {
        double x = 475;
        double y = 180;

        NewGame_iv.setX(720);
        NewGame_iv.setY(600);
        NewGame_iv.setFitWidth(x);
        NewGame_iv.setFitHeight(y);
        NewGame_iv.setOnMouseEntered(e -> {
            NewGame_iv.setCursor(Cursor.HAND);
            NewGame_iv.setImage(Images.NewGame2);
            NewGame_iv.setFitWidth(x + 20);
            NewGame_iv.setFitHeight(y + 8);
            NewGame_iv.setX(720 - 10);
            NewGame_iv.setY(600 - 4);
        });
        NewGame_iv.setOnMouseExited(e -> {
            NewGame_iv.setImage(Images.NewGame);
            NewGame_iv.setFitWidth(x);
            NewGame_iv.setFitHeight(y);
            NewGame_iv.setX(720);
            NewGame_iv.setY(600);
        });
        NewGame_iv.setOnMouseClicked(e -> {
            PrimaryStage.setScene(new LoginPage(PrimaryStage).Create());
        });
        ////////////////////////////////////////////////////////////////////////////////

        Continue_iv.setX(720);
        Continue_iv.setY(300);
        Continue_iv.setFitWidth(475.32);
        Continue_iv.setFitHeight(179.78);
        Continue_iv.setOnMouseEntered(e -> {
            Continue_iv.setCursor(Cursor.HAND);
            Continue_iv.setImage(Images.Continue2);
            Continue_iv.setFitWidth(x + 20);
            Continue_iv.setFitHeight(y + 8);
            Continue_iv.setX(720 - 10);
            Continue_iv.setY(300 - 4);
        });
        Continue_iv.setOnMouseExited(e -> {
            Continue_iv.setImage(Images.Continue);
            Continue_iv.setFitWidth(x);
            Continue_iv.setFitHeight(y);
            Continue_iv.setX(720);
            Continue_iv.setY(300);
        });
        Continue_iv.setOnMouseClicked(e -> {
            PrimaryStage.setScene(new DifficultyPage(PrimaryStage).Create());
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
            PrimaryStage.setScene(new MainPage(PrimaryStage).Create());
        });
        ////////////////////////////////////////////////////////////////////////////////

        this.getChildren().addAll(Background_iv, NewGame_iv, X_iv);
        if (!(FileIO.Read("PlayerName.txt").equals(""))) {
            this.getChildren().add(Continue_iv);
        } else {
            NewGame_iv.setX(722.34);
            NewGame_iv.setY(450.11);
        }
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
