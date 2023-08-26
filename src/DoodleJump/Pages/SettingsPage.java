package DoodleJump.Pages;

import DoodleJump.Main;
import javafx.scene.Cursor;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;

public class SettingsPage extends Pane {

    private Stage PrimaryStage;
    private Pane buttons = new Pane();

    private ImageView Background3_iv = new ImageView(Images.SettingsPage);

    private ImageView X_iv = new ImageView(Images.X);
    private ImageView Volume_iv = new ImageView(Images.Von);

    private ImageView Res_iv = new ImageView();

    private ImageView Zombie_iv = new ImageView();
    private ImageView Doodle_iv = new ImageView();
    private ImageView Snow_iv = new ImageView();

    private ImageView inputMode = new ImageView();

    private ImageView fly_iv = new ImageView(Images.Fly);
    private ImageView nojump_iv = new ImageView(Images.noJump);
    private ImageView immune_iv = new ImageView(Images.Immune);

    boolean click = false;
    boolean click2 = false;

    public SettingsPage(Stage stage) {
        this.PrimaryStage = stage;
    }

    public void start() {
        // int xy = 115;
        if (Main.numRes == 2)
            Res_iv.setImage(Images.ResCustom);
        if (Main.numRes == 3)
            Res_iv.setImage(Images.ResHD);
        if (Main.numRes == 1)
            Res_iv.setImage(Images.ResFullHD);

        if (Main.numChar == 1) {
            Doodle_iv.setImage(Images.NormalDoodle2);
            Zombie_iv.setImage(Images.ZombieDoodle);
            Snow_iv.setImage(Images.SnowDoodle);
        } else if (Main.numChar == 2) {
            Doodle_iv.setImage(Images.NormalDoodle);
            Zombie_iv.setImage(Images.ZombieDoodle2);
            Snow_iv.setImage(Images.SnowDoodle);
        } else if (Main.numChar == 3) {
            Doodle_iv.setImage(Images.NormalDoodle);
            Zombie_iv.setImage(Images.ZombieDoodle);
            Snow_iv.setImage(Images.SnowDoodle2);
        }

        if (Main.Mute == false) {
            Volume_iv.setImage(Images.Von);
        } else {
            Volume_iv.setImage(Images.Voff);
        }

        if (Main.numInput == 1) {
            inputMode.setImage(Images.Keyboard);
        } else {
            inputMode.setImage(Images.Arduino);
        }

        if (Main.fly == false)
            fly_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
        else
            fly_iv.setEffect(new ColorAdjust(0, 0, 0, 0));

        if (Main.nojump == false)
            nojump_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
        else
            nojump_iv.setEffect(new ColorAdjust(0, 0, 0, 0));

        if (Main.immune == false)
            immune_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
        else
            immune_iv.setEffect(new ColorAdjust(0, 0, 0, 0));

        Res_iv.setX(1060);
        Res_iv.setY(170);
        Res_iv.setFitWidth(300);
        Res_iv.setFitHeight(80);
        Res_iv.setOnMouseEntered(e -> {
            Res_iv.setCursor(Cursor.HAND);
            Res_iv.setFitWidth(300 + 20);
            Res_iv.setFitHeight(80 + 8);
            Res_iv.setX(1060 - 10);
            Res_iv.setY(170 - 4);
        });
        Res_iv.setOnMouseExited(e -> {
            Res_iv.setFitWidth(300);
            Res_iv.setFitHeight(80);
            Res_iv.setX(1060);
            Res_iv.setY(170);
        });
        Res_iv.setOnMouseClicked(e -> {
            Main.numRes++;
            if (Main.numRes == 2) {
                Main.SelectedResolution = Main.ResolutionCustom;
                Main.SelectedOffset = Main.OffsetCustom;
                Main.Factor = 2.5;
                Res_iv.setImage(Images.ResCustom);
                FileIO.SettingsWrite("RESOLUTION: CUSTOM", 0);
                PrimaryStage.setScene(new SettingsPage(PrimaryStage).Create());
            }
            if (Main.numRes == 3) {
                Main.SelectedResolution = Main.ResolutionHD;
                Main.SelectedOffset = Main.OffsetHD;
                Main.Factor = 2;
                Res_iv.setImage(Images.ResHD);
                FileIO.SettingsWrite("RESOLUTION: HD", 0);
                PrimaryStage.setScene(new SettingsPage(PrimaryStage).Create());
            }
            if (Main.numRes == 4) {
                Main.SelectedResolution = Main.ResolutionFullHD;
                Main.SelectedOffset = Main.OffsetFullHD;
                Main.Factor = 3;
                Main.numRes = 1;
                Res_iv.setImage(Images.ResFullHD);
                FileIO.SettingsWrite("RESOLUTION: FULLHD", 0);
                PrimaryStage.setScene(new SettingsPage(PrimaryStage).Create());
            }

        });

        Doodle_iv.setX(920);
        Doodle_iv.setY(310);
        Doodle_iv.setFitWidth(100);
        Doodle_iv.setFitHeight(100);
        Doodle_iv.setOnMouseEntered(e -> {
            Doodle_iv.setCursor(Cursor.HAND);
            Doodle_iv.setFitWidth(100 + 10);
            Doodle_iv.setFitHeight(100 + 10);
            Doodle_iv.setX(920 - 5);
            Doodle_iv.setY(310 - 5);
        });
        Doodle_iv.setOnMouseExited(e -> {
            Doodle_iv.setFitWidth(100);
            Doodle_iv.setFitHeight(100);
            Doodle_iv.setX(920);
            Doodle_iv.setY(310);
        });
        Doodle_iv.setOnMouseClicked(e -> {
            Main.numChar = 1;
            Main.imagChar = Images.doodleTiles;
            Main.imagBG = Images.BGDoodle;
            Doodle_iv.setImage(Images.NormalDoodle2);
            Zombie_iv.setImage(Images.ZombieDoodle);
            Snow_iv.setImage(Images.SnowDoodle);
            FileIO.SettingsWrite("THEME: DOODLE", 1);

        });

        Zombie_iv.setX(1070);
        Zombie_iv.setY(310);
        Zombie_iv.setFitWidth(100);
        Zombie_iv.setFitHeight(100);
        Zombie_iv.setOnMouseEntered(e -> {
            Zombie_iv.setCursor(Cursor.HAND);
            Zombie_iv.setFitWidth(100 + 10);
            Zombie_iv.setFitHeight(100 + 10);
            Zombie_iv.setX(1070 - 5);
            Zombie_iv.setY(310 - 5);
        });
        Zombie_iv.setOnMouseExited(e -> {
            Zombie_iv.setFitWidth(100);
            Zombie_iv.setFitHeight(100);
            Zombie_iv.setX(1070);
            Zombie_iv.setY(310);
        });
        Zombie_iv.setOnMouseClicked(e -> {
            Main.numChar = 2;
            Main.imagChar = Images.zombieTiles;
            Main.imagBG = Images.BGZombie;
            Doodle_iv.setImage(Images.NormalDoodle);
            Zombie_iv.setImage(Images.ZombieDoodle2);
            Snow_iv.setImage(Images.SnowDoodle);
            FileIO.SettingsWrite("THEME: ZOMBIE", 1);

        });

        Snow_iv.setX(1220);
        Snow_iv.setY(310);
        Snow_iv.setFitWidth(100);
        Snow_iv.setFitHeight(100);
        Snow_iv.setOnMouseEntered(e -> {
            Snow_iv.setCursor(Cursor.HAND);
            Snow_iv.setFitWidth(100 + 10);
            Snow_iv.setFitHeight(100 + 10);
            Snow_iv.setX(1220 - 5);
            Snow_iv.setY(310 - 5);
        });
        Snow_iv.setOnMouseExited(e -> {
            Snow_iv.setFitWidth(100);
            Snow_iv.setFitHeight(100);
            Snow_iv.setX(1220);
            Snow_iv.setY(310);
        });
        Snow_iv.setOnMouseClicked(e -> {
            Main.numChar = 3;
            Main.imagChar = Images.snowTiles;
            Main.imagBG = Images.BGSnow;
            Doodle_iv.setImage(Images.NormalDoodle);
            Zombie_iv.setImage(Images.ZombieDoodle);
            Snow_iv.setImage(Images.SnowDoodle2);
            FileIO.SettingsWrite("THEME: SNOW", 1);
        });

        inputMode.setX(1085);
        inputMode.setY(600);
        inputMode.setFitWidth(280);
        inputMode.setFitHeight(80);
        inputMode.setOnMouseEntered(e -> {
            inputMode.setCursor(Cursor.HAND);
            inputMode.setFitWidth(280 + 20);
            inputMode.setFitHeight(80 + 8);
            inputMode.setX(1085 - 10);
            inputMode.setY(600 - 4);
        });
        inputMode.setOnMouseExited(e -> {
            inputMode.setFitWidth(280);
            inputMode.setFitHeight(80);
            inputMode.setX(1085);
            inputMode.setY(600);
        });
        inputMode.setOnMouseClicked(e -> {
            if (Main.numInput == 1) {
                inputMode.setImage(Images.Arduino);
                Main.numInput = 2;
                FileIO.SettingsWrite("INPUT: ARDUINO", 3);
            } else {
                inputMode.setImage(Images.Keyboard);
                Main.numInput = 1;
                FileIO.SettingsWrite("INPUT: KEYBOARD", 3);
            }
        });

        fly_iv.setX(600);
        fly_iv.setY(830);
        fly_iv.setFitWidth(200);
        fly_iv.setFitHeight(80);
        fly_iv.setOnMouseEntered(e -> {
            fly_iv.setCursor(Cursor.HAND);
            fly_iv.setFitWidth(200 + 20);
            fly_iv.setFitHeight(80 + 8);
            fly_iv.setX(600 - 10);
            fly_iv.setY(830 - 4);
        });
        fly_iv.setOnMouseExited(e -> {
            fly_iv.setFitWidth(200);
            fly_iv.setFitHeight(80);
            fly_iv.setX(600);
            fly_iv.setY(830);
        });
        fly_iv.setOnMouseClicked(e -> {
            if (Main.fly == true) {
                Main.fly = false;
                FileIO.SettingsWrite("FLY: OFF", 4);
                fly_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
            } else {
                Main.fly = true;
                FileIO.SettingsWrite("FLY: ON", 4);
                fly_iv.setEffect(new ColorAdjust(0, 0, 0, 0));
            }

        });

        nojump_iv.setX(860);
        nojump_iv.setY(830);
        nojump_iv.setFitWidth(200);
        nojump_iv.setFitHeight(80);
        nojump_iv.setOnMouseEntered(e -> {
            nojump_iv.setCursor(Cursor.HAND);
            nojump_iv.setFitWidth(200 + 20);
            nojump_iv.setFitHeight(80 + 8);
            nojump_iv.setX(860 - 10);
            nojump_iv.setY(830 - 4);
        });
        nojump_iv.setOnMouseExited(e -> {
            nojump_iv.setFitWidth(200);
            nojump_iv.setFitHeight(80);
            nojump_iv.setX(860);
            nojump_iv.setY(830);
        });
        nojump_iv.setOnMouseClicked(e -> {
            if (Main.nojump == true) {
                Main.nojump = false;
                FileIO.SettingsWrite("NOJUMP: OFF", 5);
                nojump_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
            } else {
                Main.nojump = true;
                FileIO.SettingsWrite("NOJUMP: ON", 5);
                nojump_iv.setEffect(new ColorAdjust(0, 0, 0, 0));
            }
        });

        immune_iv.setX(1120);
        immune_iv.setY(830);
        immune_iv.setFitWidth(200);
        immune_iv.setFitHeight(80);
        immune_iv.setOnMouseEntered(e -> {
            immune_iv.setCursor(Cursor.HAND);
            immune_iv.setFitWidth(200 + 20);
            immune_iv.setFitHeight(80 + 8);
            immune_iv.setX(1120 - 10);
            immune_iv.setY(830 - 4);
        });
        immune_iv.setOnMouseExited(e -> {
            immune_iv.setFitWidth(200);
            immune_iv.setFitHeight(80);
            immune_iv.setX(1120);
            immune_iv.setY(830);
        });
        immune_iv.setOnMouseClicked(e -> {
            if (Main.immune == true) {
                Main.immune = false;
                FileIO.SettingsWrite("IMMUNE: OFF", 6);
                immune_iv.setEffect(new ColorAdjust(0, 0, -0.5, 0));
            } else {
                Main.immune = true;
                FileIO.SettingsWrite("IMMUNE: ON", 6);
                immune_iv.setEffect(new ColorAdjust(0, 0, 0, 0));
            }
        });

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

        Volume_iv.setFitHeight(100);
        Volume_iv.setFitWidth(100);
        Volume_iv.setX(1050);
        Volume_iv.setY(450);
        Volume_iv.setOnMouseEntered(e -> {
            Volume_iv.setCursor(Cursor.HAND);
            Volume_iv.setFitHeight(110);
            Volume_iv.setFitWidth(110);
            Volume_iv.setX(1050 - 5);
            Volume_iv.setY(450 - 5);

        });
        Volume_iv.setOnMouseExited(e -> {
            Volume_iv.setFitHeight(100);
            Volume_iv.setFitWidth(100);
            Volume_iv.setX(1050);
            Volume_iv.setY(450);
        });
        Volume_iv.setOnMouseClicked(e -> {
            // click2 = true;
            // Voff_iv.setImage(Images.Voff3);
            if (Main.Mute == true) {
                Volume_iv.setImage(Images.Von);
                Main.Mute = false;
                FileIO.SettingsWrite("SOUND: ON", 2);
            } else {
                Volume_iv.setImage(Images.Voff);
                Main.Mute = true;
                FileIO.SettingsWrite("SOUND: OFF", 2);
            }

        });

        this.getChildren().addAll(Background3_iv, X_iv, Volume_iv, Zombie_iv, Snow_iv, Doodle_iv, Res_iv, fly_iv,
                nojump_iv,
                immune_iv, inputMode, buttons);

    }

    public static void initialize() {

        if (FileIO.SettingsRead(0).equals("RESOLUTION: FULLHD")) {
            Main.SelectedResolution = Main.ResolutionFullHD;
            Main.SelectedOffset = Main.OffsetFullHD;
            Main.Factor = 3;
            Main.numRes = 1;
        } else if (FileIO.SettingsRead(0).equals("RESOLUTION: HD")) {
            Main.SelectedResolution = Main.ResolutionHD;
            Main.SelectedOffset = Main.OffsetHD;
            Main.Factor = 2;
            Main.numRes = 3;
        } else {
            Main.SelectedResolution = Main.ResolutionCustom;
            Main.SelectedOffset = Main.OffsetCustom;
            Main.Factor = 2.5;
            Main.numRes = 2;
        }

        if (FileIO.SettingsRead(1).equals("THEME: DOODLE")) {
            Main.numChar = 1;
            Main.imagChar = Images.doodleTiles;
            Main.imagBG = Images.BGDoodle;
        } else if (FileIO.SettingsRead(1).equals("THEME: ZOMBIE")) {
            Main.numChar = 2;
            Main.imagChar = Images.zombieTiles;
            Main.imagBG = Images.BGZombie;
        } else {
            Main.numChar = 3;
            Main.imagChar = Images.snowTiles;
            Main.imagBG = Images.BGSnow;
        }

        if (FileIO.SettingsRead(2).equals("SOUND: ON"))
            Main.Mute = false;
        else
            Main.Mute = true;

        if (FileIO.SettingsRead(3).equals("INPUT: KEYBOARD"))
            Main.numInput = 1;
        else
            Main.numInput = 2;

        if (FileIO.SettingsRead(4).equals("FLY: OFF"))
            Main.fly = false;
        else
            Main.fly = true;

        if (FileIO.SettingsRead(5).equals("NOJUMP: OFF"))
            Main.nojump = false;
        else
            Main.nojump = true;

        if (FileIO.SettingsRead(6).equals("IMMUNE: OFF"))
            Main.immune = false;
        else
            Main.immune = false;
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
