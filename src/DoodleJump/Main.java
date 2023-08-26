package DoodleJump;

import DoodleJump.GameLogic.ArduinoListener;
import DoodleJump.Pages.Images;
import DoodleJump.Pages.MainPage;
import DoodleJump.Pages.SettingsPage;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

        public final static Point2D ResolutionFullHD = new Point2D(1920, 1080);
        public final static Point2D ResolutionHD = new Point2D(1280, 720);
        public final static Point2D ResolutionCustom = new Point2D(1600, 900);

        public final static Point2D OffsetFullHD = new Point2D(0, 0);
        public final static Point2D OffsetHD = new Point2D(-213, -119);
        public final static Point2D OffsetCustom = new Point2D(-133, -75);

        public static Point2D SelectedResolution = ResolutionFullHD;
        public static Point2D SelectedOffset = OffsetFullHD;
        public static double Factor = 3;

        public static int numRes = 1;
        public static int numChar = 1;
        public static Image imagChar;
        public static Image imagBG;
        public static int numInput = 1;

        public static Boolean Mute = false;

        public static Boolean fly = false;
        public static Boolean nojump = false;
        public static Boolean immune = false;

        public static int numMonDifficulty = 2;
        public static int numPowDifficulty = 6;

        public static String PathToFont = "file:" + System.getProperty("user.dir") + "\\src\\Files\\";
        public static String PathToResources = System.getProperty("user.dir") + "\\src\\Sounds\\";

        @Override
        public void start(Stage stage) {

                // imagChar = Images.doodleTiles;
                // imagBG = Images.BGDoodle;
        
                SettingsPage.initialize();
                ArduinoListener.start();

                Images images = new Images();
                
                stage.setScene(new MainPage(stage).Create());
                stage.setOnCloseRequest(e -> {
                        System.exit(0);
                });
                //stage.setFullScreen(true);
                stage.setResizable(false);
                stage.setTitle("Doodle Jumb");
                stage.show();
        }

        public static void main(String[] args) {
                launch(args);
        }

}
