package DoodleJump.Pages;

import DoodleJump.Main;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginPage extends Pane {

    private Stage PrimaryStage;
    private ImageView Background_iv = new ImageView(Images.Background7);
    private ImageView Next_iv = new ImageView(Images.Next);
    private ImageView X_iv = new ImageView(Images.X);
    TextField Name = new TextField();
    TextField Age = new TextField();
    //ChoiceDialog b = new ChoiceDialog();

    public LoginPage(Stage stage) {
        this.PrimaryStage = stage;
    }

    public void start() {

        Next_iv.setX(780);
        Next_iv.setY(790);
        Next_iv.setFitWidth(365);
        Next_iv.setFitHeight(140);
        Next_iv.setOnMouseEntered(e -> {
            Next_iv.setCursor(Cursor.HAND);
            Next_iv.setImage(Images.Next2);
            Next_iv.setFitWidth(365 + 20);
            Next_iv.setFitHeight(140 + 8);
            Next_iv.setX(780 - 10);
            Next_iv.setY(790 - 4);
        });
        Next_iv.setOnMouseExited(e -> {
            Next_iv.setFitWidth(365);
            Next_iv.setFitHeight(140);
            Next_iv.setX(780);
            Next_iv.setY(790);
            Next_iv.setImage(Images.Next);

        });
        Next_iv.setOnMouseClicked(e -> {
            if (!(Name.getText().equals("") && Age.getText().equals(""))) {
                FileIO.Write(Name.getText(), "PlayerName.txt");
                FileIO.Write("0", "Score.txt");
                FileIO.Write("0", "TopScore.txt");
                PrimaryStage.setScene(new DifficultyPage(PrimaryStage).Create());
            }
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

        Name.setPromptText("Ente Your Name");
        Name.setLayoutX(880);
        Name.setLayoutY(445);
        Name.setPrefSize(450, 40);

        ////////////////////////////////////////////////////////////////////////////////

        Age.setPromptText("Ente Your Age");
        Age.setLayoutX(880);
        Age.setLayoutY(650);
        Age.setPrefSize(250, 40);

        ////////////////////////////////////////////////////////////////////////////////
        this.getChildren().addAll(Background_iv, Next_iv, X_iv, Name, Age);

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
