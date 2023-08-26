package DoodleJump.GameLogic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import DoodleJump.Pages.Images;

public class Monster extends ImageView {

    static final public int FLY = 1;
    static final public int LONGFLY = 2;
    static final public int PP = 3;
    static final public int OP = 4;

    static final public Point2D FLYpos = new Point2D(25, 67);
    static final public Point2D WORMpos = new Point2D(0, 0);
    static final public Point2D PPpos = new Point2D(0, 0);
    static final public Point2D OPpos = new Point2D(0, 0);
    private Timeline wiggleAni;
    private Timeline animate;

    private Boolean reverse = false;
    private int AnimationIndex = 0;

    private int width;
    private int height;
    private int numOfTiles;
    private int xPos;
    private int yPos;

    private double probablityActivated = 0.5;
    private int Type = 0;
    private Boolean activated = false;
    private int obstacleIndex = 0;

    public Monster(Pane gamePane) {
        gamePane.getChildren().add(this);
    }

    // Call this static method to initialize any array containing monster objects.

    public static void initialize(Monster newMonsters[], PowerUp newPowerUps[], Obstacle newObstacles[],
            GamePage gamePane) {
        for (int i = 0; i < newMonsters.length; i++) {
            newMonsters[i] = new Monster(gamePane);

        }
        for (int i = 0; i < newMonsters.length; i++) {
            newMonsters[i].Activate(newMonsters, newPowerUps, newObstacles);
        }
    }

    // This method sets up every monster Object
    public void Activate(Monster newMonsters[], PowerUp newPowerUps[], Obstacle newObstacles[]) {
        int nowObstacleIndex;
        do {
            nowObstacleIndex = (int) (newObstacles.length * Math.random());
        } while (CheckDuplicates(newMonsters, newPowerUps, nowObstacleIndex));

        // System.out.println(nowObstacleIndex);
        // this.radnomActivation();
        this.obstacleIndex = nowObstacleIndex;
        this.setVisible(false);
        Loop(newObstacles[nowObstacleIndex]);
        newObstacles[nowObstacleIndex].setOccupied(true);
        this.boundTo(newObstacles[nowObstacleIndex]);
    }

    // This method is called for a random chance of activation of the monster.
    public void radnomActivation() {
        double probablity = Math.random();

        if (probablity > 1 - probablityActivated) {
            this.setVisible(true);
            this.activated = true;

        } else {
            this.setVisible(false);
            this.activated = false;
        }

    }

    // This method checks if there is already an object linked to the obstacle in
    // question, if there it is, then it finds another obstacle.
    private Boolean CheckDuplicates(Monster newMonsters[], PowerUp newPowerUps[], int nowObstacleIndex) {
        for (int i = 0; i < newPowerUps.length; i++) {
            if (nowObstacleIndex == newPowerUps[i].getObstacleIndex()) {
                return true;
            }
        }
        for (int i = 0; i < newMonsters.length; i++) {
            if (nowObstacleIndex == newMonsters[i].getObstacleIndex()) {
                return true;
            }
        }
        if (nowObstacleIndex % 2 == 1) {
            return true;
        }
        return false;
    }

    // This method is called when the monster is under the screen so it can be used
    // again by teleporting it up the screen.
    public void teleportUP(Player Doodle, Obstacle newObstacles[]) {
        if (this.getY() + this.getFitHeight() < 0) {
            this.radnomActivation();
            this.boundTo(newObstacles[this.obstacleIndex]);
        }
    }

    // This method choses the type of monster randomly and calls the setType method
    public void boundTo(Obstacle myObstacle) {

        double probablity = Math.random();
        // System.out.println(probablity);
        if (probablity > 0.5) {
            this.setType(FLY, myObstacle);
        } else if (probablity > 0.10) {
            this.setType(LONGFLY, myObstacle);
        } else if (probablity > 0.02) {
            this.setType(PP, myObstacle);
        } else if (probablity > 0) {
            this.setType(OP, myObstacle);
        }

    }

    // This method sets up the properties for the selected type sent.
    public void setType(int type, Obstacle myObstacle) {
        switch (type) {
            case FLY:
                this.Type = type;
                this.setImage(Images.monster1Tiles);
                this.setViewport(new Rectangle2D(0, 0, 156, 89));
                this.setFitWidth(140);
                this.setFitHeight(80);
                this.width = 156;
                this.height = 89;
                this.numOfTiles = 5;
                this.xPos = 30;
                this.yPos = 75;
                this.AnimationIndex = 0;
                this.reverse = false;
                this.xProperty().bind(myObstacle.xProperty().subtract(xPos));
                this.yProperty().bind(myObstacle.yProperty().subtract(yPos));

                // animate.setDelay(Duration.millis(this.time));
                break;
            case LONGFLY:
                this.Type = type;
                this.setImage(Images.monster2Tiles);
                this.setViewport(new Rectangle2D(0, 0, 134, 175));
                this.setFitWidth(120);
                this.setFitHeight(145);
                this.width = 134;
                this.height = 175;
                this.numOfTiles = 5;
                this.xPos = 23;
                this.yPos = 140;
                this.AnimationIndex = 0;
                this.reverse = false;
                ;
                this.xProperty().bind(myObstacle.xProperty().subtract(xPos));
                this.yProperty().bind(myObstacle.yProperty().subtract(yPos));
                break;

        }

    }

    // This method is used to animate the monsters pictures.
    private void Loop(Obstacle myObstacle) {
        wiggleAni = new Timeline(new KeyFrame(Duration.millis(25), e -> {
            this.xProperty().bind(myObstacle.xProperty().subtract(xPos + (int) (Math.random() * 3)));
            this.yProperty().bind(myObstacle.yProperty().subtract(yPos + (int) (Math.random() * 3)));

        }));
        wiggleAni.setCycleCount(Timeline.INDEFINITE);
        wiggleAni.play();

        animate = new Timeline(new KeyFrame(Duration.millis(25), e -> {
            this.setViewport(new Rectangle2D(AnimationIndex * width, 0, width, height));
            if (reverse == false) {
                AnimationIndex++;
                if (AnimationIndex == numOfTiles - 1)
                    reverse = true;
            } else {
                AnimationIndex--;
                if (AnimationIndex == 0)
                    reverse = false;
            }
        }));
        animate.setCycleCount(Timeline.INDEFINITE);
        animate.play();
    }

    public Boolean getStatus() {
        return activated;
    }

    public void Deactivate() {
        this.setVisible(false);
        this.activated = false;
    }

    public int getObstacleIndex() {
        return obstacleIndex;
    }

    public void setObstacleIndex(int obstacleIndex) {
        this.obstacleIndex = obstacleIndex;
    }
}
