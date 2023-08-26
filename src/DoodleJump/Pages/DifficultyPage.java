package DoodleJump.Pages;

////////////////////////////////////////////////////////////////////////////////
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import DoodleJump.Main;
import DoodleJump.GameLogic.GamePage;
////////////////////////////////////////////////////////////////////////////////

public class DifficultyPage extends Pane {

    private Stage PrimaryStage;
    private Pane buttons = new Pane();

    private ImageView Background2_iv = new ImageView(Images.Background2);
    private ImageView Easy_iv = new ImageView(Images.Easy);
    private ImageView Medium_iv = new ImageView(Images.Medium);
    private ImageView Hard_iv = new ImageView(Images.Hard);
    private ImageView X_iv = new ImageView(Images.X);

    public DifficultyPage(Stage stage) {
        this.PrimaryStage = stage;
    }

    public void start() {
        double x = 400;
        double y = 150;
        ////////////////////////////////////////////////////////////////////////////////

        Easy_iv.setX(750);
        Easy_iv.setY(360);
        Easy_iv.setFitWidth(x);
        Easy_iv.setFitHeight(y);
        Easy_iv.setOnMouseEntered(e -> {
            Easy_iv.setImage(Images.Easy2);
            Easy_iv.setCursor(Cursor.HAND);
            Easy_iv.setFitWidth(x + 20);
            Easy_iv.setFitHeight(y + 8);
            Easy_iv.setX(750 - 10);
            Easy_iv.setY(360 - 4);
        });
        Easy_iv.setOnMouseExited(e -> {
            Easy_iv.setImage(Images.Easy);
            Easy_iv.setFitWidth(x);
            Easy_iv.setFitHeight(y);
            Easy_iv.setX(750);
            Easy_iv.setY(360);
        });

        Easy_iv.setOnMouseClicked(e -> {
            Main.numMonDifficulty = 2;
            Main.numPowDifficulty = 6;
            PrimaryStage.setScene(new GamePage(Main.ResolutionCustom, PrimaryStage).Create());
        });
        ////////////////////////////////////////////////////////////////////////////////

        Medium_iv.setX(750);
        Medium_iv.setY(560);
        Medium_iv.setFitWidth(x);
        Medium_iv.setFitHeight(y);
        Medium_iv.setOnMouseEntered(e -> {
            Medium_iv.setImage(Images.Medium2);
            Medium_iv.setCursor(Cursor.HAND);
            Medium_iv.setFitWidth(x + 20);
            Medium_iv.setFitHeight(y + 8);
            Medium_iv.setX(750 - 10);
            Medium_iv.setY(560 - 4);
        });
        Medium_iv.setOnMouseExited(e -> {
            Medium_iv.setImage(Images.Medium);
            Medium_iv.setFitWidth(x);
            Medium_iv.setFitHeight(y);
            Medium_iv.setX(750);
            Medium_iv.setY(560);
        });

        Medium_iv.setOnMouseClicked(e -> {
            Main.numMonDifficulty = 3;
            Main.numPowDifficulty = 4;
            PrimaryStage.setScene(new GamePage(Main.ResolutionCustom, PrimaryStage).Create());

        });
        ////////////////////////////////////////////////////////////////////////////////

        Hard_iv.setX(750);
        Hard_iv.setY(760);
        Hard_iv.setFitWidth(x);
        Hard_iv.setFitHeight(y);
        Hard_iv.setOnMouseEntered(e -> {
            Hard_iv.setImage(Images.Hard2);
            Hard_iv.setCursor(Cursor.HAND);
            Hard_iv.setFitWidth(x + 20);
            Hard_iv.setFitHeight(y + 8);
            Hard_iv.setX(750 - 10);
            Hard_iv.setY(760 - 4);
        });
        Hard_iv.setOnMouseExited(e -> {
            Hard_iv.setImage(Images.Hard);
            Hard_iv.setFitWidth(x);
            Hard_iv.setFitHeight(y);
            Hard_iv.setX(750);
            Hard_iv.setY(760);
        });

        Hard_iv.setOnMouseClicked(e -> {
            Main.numMonDifficulty = 3;
            Main.numPowDifficulty = 2;
            PrimaryStage.setScene(new GamePage(Main.ResolutionCustom, PrimaryStage).Create());
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

        buttons.getChildren().addAll(Easy_iv, Medium_iv, Hard_iv);
        this.getChildren().addAll(Background2_iv, X_iv, buttons);
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
