package DoodleJump.GameLogic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import DoodleJump.Pages.Audio;
import DoodleJump.Pages.Images;

public class PowerUp extends ImageView {

    static final public int SPRING = 1;
    static final public int HAT = 2;
    static final public int TRAMPOLINE = 3;
    static final public int JETPACK = 4;

    private double probablityActivated = 0.7;
    private int Type = 0;
    private Boolean activated = false;
    private int obstacleIndex = 0;
    private ImageView occuipedAnimation = new ImageView();

    static private Image Spring = Images.Spring;
    static private Image Hat = Images.Hat;
    static private Image Trampoline = Images.Trampoline;
    static private Image JetPack = Images.JetPack;

    private Audio springSound = new Audio("spring.mp3");
    private Audio trampolineSound = new Audio("trampoline.mp3");
    private Audio jetpackSound = new Audio("jetpack.mp3");
    private Audio hatSound = new Audio("hat.mp3");

    PowerUp(GamePage gamePane) {
        super();
        gamePane.getChildren().add(this);
        occuipedAnimation.setVisible(false);
    }

    // Call this static method to initialize a PowerUp array containing PowerUpAnimation objects.
    public static void initializeAnimations(PowerUp newPowerUps[], GamePage gamePane) {
        for (int i = 0; i < newPowerUps.length; i++) {
            gamePane.getChildren().add(newPowerUps[i].occuipedAnimation);
        }
    }
    // Call this static method to initialize any array containing PowerUp objects.
    public static void initialize(PowerUp newPowerUps[], Obstacle newObstacles[], GamePage gamePane) {
        for (int i = 0; i < newPowerUps.length; i++) {
            newPowerUps[i] = new PowerUp(gamePane);

        }
        for (int i = 0; i < newPowerUps.length; i++) {
            newPowerUps[i].Activate(newPowerUps, newObstacles);
        }
    }

    // This method sets up every PowerUp Object 
    public void Activate(PowerUp newPowerUps[], Obstacle newObstacles[]) {
        int nowObstacleIndex;
        do {
            nowObstacleIndex = (int) (newObstacles.length * Math.random());
        } while (CheckDuplicates(newPowerUps, nowObstacleIndex));

        this.radnomActivation();
        this.obstacleIndex = nowObstacleIndex;
        newObstacles[nowObstacleIndex].setOccupied(true);
        this.boundTo(newObstacles[nowObstacleIndex]);
    }

    // This method is called for a random chance of activation of the PowerUp.
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
    private Boolean CheckDuplicates(PowerUp newPowerUps[], int nowObstacleIndex) {
        for (int i = 0; i < newPowerUps.length; i++) {
            if (nowObstacleIndex == newPowerUps[i].getObstacleIndex()) {
                return true;
            }
        }
        return false;
    }

    
    
    // This method choses the type of monster randomly and calls the setType method
    public void boundTo(Obstacle myObstacle) {

        double probablity = Math.random();
        if (probablity > 0.20) {
            this.setType(SPRING, myObstacle);
        } else if (probablity > 0.10) {
            this.setType(TRAMPOLINE, myObstacle);
        } else if (probablity > 0.02) {
            this.setType(HAT, myObstacle);
        } else if (probablity > 0) {
            this.setType(JETPACK, myObstacle);
        }

    }
    // This method sets up the properties for the selected type sent.
    public void setType(int type, Obstacle myObstacle) {
        switch (type) {
            case SPRING:
                this.Type = type;
                this.setImage(Spring);
                this.setViewport(new Rectangle2D(0, 0, 34, 23));
                this.setFitWidth(23);
                this.setFitHeight(15);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() - 1));
                this.xProperty().bind(myObstacle.xProperty().add((int) (Math.random() * 40) + 6));
                break;
            case HAT:
                this.Type = type;
                this.setImage(Hat);
                this.setViewport(new Rectangle2D(0, 0, 60, 38));
                this.setFitWidth(50);
                this.setFitHeight(35);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() - 1));
                this.xProperty().bind(myObstacle.xProperty().add(14));
                break;
            case TRAMPOLINE:
                this.Type = type;
                this.setImage(Trampoline);
                this.setViewport(new Rectangle2D(0, 0, 71, 34));
                this.setFitWidth(60);
                this.setFitHeight(26);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() - 3));
                this.xProperty().bind(myObstacle.xProperty().add(11));
                break;
            case JETPACK:
                this.Type = type;
                this.setImage(JetPack);
                this.setViewport(new Rectangle2D(0, 0, 48, 74));
                this.setFitWidth(36);
                this.setFitHeight(58);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() - 2));
                this.xProperty().bind(myObstacle.xProperty().add(22));
                break;
        }

    } 

    // This method is called when the monster is under the screen so it can be used
    // again by teleporting it up the screen.
    public void teleportUP(Player Doodle, Obstacle newObstacles[]) {
        if (this.getY() + this.getFitHeight() < 0) {
            this.radnomActivation();
            this.boundTo(newObstacles[this.obstacleIndex]);
        }
    }

    //This method is called when the player collides with a PowerUp, So it starts its exectution
    public void Execute(Player Doodle, Obstacle newObstacles[], int distance, double lastYPostion, double jumpHeight) {
        if (this.activated == false) {
            return;
        }
        switch (this.getType()) {

            case PowerUp.SPRING:
                if (Doodle.Hitbox.getY() + Doodle.Hitbox.getHeight() == this.getY() && distance > 0) {
                    Doodle.setyVelocity(jumpHeight - 10);
                    Doodle.Hitbox.setY(lastYPostion - 15);
                    this.setViewport(new Rectangle2D(0, 23, 34, 55));
                    this.setFitHeight(35);
                    this.yProperty().bind(newObstacles[obstacleIndex].yProperty().subtract(this.getFitHeight() - 2));

                    new Timeline(new KeyFrame(Duration.millis(500), e -> {
                        this.setViewport(new Rectangle2D(0, 0, 34, 23));
                        this.setFitHeight(15);
                        this.yProperty()
                                .bind(newObstacles[obstacleIndex].yProperty().subtract(this.getFitHeight() - 1));
                    })).play();
                    springSound.play();  

                }

                break;

            case PowerUp.HAT:

                occuipedAnimation.setImage(Images.HatAnimation);
                occuipedAnimation.setViewport(new Rectangle2D(0, 0, 64, 64));
                occuipedAnimation.setFitHeight(65);
                occuipedAnimation.setFitWidth(65);
                occuipedAnimation.xProperty().bind(Doodle.xProperty().add(13));
                occuipedAnimation.yProperty().bind(Doodle.yProperty().subtract(40));
                Doodle.moveX(Player.LEFT, newObstacles);
                Doodle.setCanFlip(false);
                new Animation(occuipedAnimation, 64, 64, 20, 3, 0, 0);

                Timeline hatFly = new Timeline(new KeyFrame(Duration.millis(20), e -> {
                    occuipedAnimation.setVisible(true);
                    Doodle.setyVelocity(jumpHeight);
                    Doodle.setHasSomething(true);
                }));
                hatFly.setOnFinished(e -> {
                    Doodle.setHasSomething(false);
                    occuipedAnimation.setVisible(false);
                    Doodle.setCanFlip(true);
                });
                hatFly.setCycleCount(120);
                hatFly.play();
                hatSound.play();

                this.setVisible(false);
                this.activated = false;
                break;

            case PowerUp.TRAMPOLINE:
                if (Doodle.Hitbox.getY() + Doodle.Hitbox.getHeight() == this.getY() && distance > 0) {
                    Doodle.setyVelocity(jumpHeight - 20);
                    Doodle.Hitbox.setY(lastYPostion - 40);
                    Timeline flip = new Timeline(new KeyFrame(Duration.millis(4), e -> {
                        Doodle.setCanFlip(false);
                        Doodle.setRotate(Doodle.getRotate() + 2);

                    }));
                    flip.setOnFinished(e -> {
                        Doodle.setRotate(0);
                        Doodle.setCanFlip(true);
                    });
                    new Animation(this, 71, 34, 70, 3, 4, 1);
                    flip.setCycleCount(180);
                    flip.play();
                    trampolineSound.play();

                }
                break;

            case PowerUp.JETPACK:
                occuipedAnimation.setImage(Images.JetpackAnimation);
                occuipedAnimation.setViewport(new Rectangle2D(0, 0, 64, 128));
                occuipedAnimation.setFitHeight(110);
                occuipedAnimation.setFitWidth(55);
                occuipedAnimation.xProperty().bind(Doodle.xProperty().add(53));
                occuipedAnimation.yProperty().bind(Doodle.yProperty().subtract(0));
                Doodle.moveX(Player.LEFT, newObstacles);
                Doodle.setCanFlip(false);
                new Animation(occuipedAnimation, 64, 128, 40, 3, 0, 0);

                Timeline jetFly = new Timeline(new KeyFrame(Duration.millis(20), e -> {
                    occuipedAnimation.setVisible(true);
                    Doodle.setyVelocity(jumpHeight - 5);
                    Doodle.setHasSomething(true);

                }));
                jetFly.setOnFinished(e -> {
                    Doodle.setHasSomething(false);
                    occuipedAnimation.setVisible(false);
                    Doodle.setCanFlip(true);
                });
                jetFly.setCycleCount(220);
                jetFly.play();
                jetpackSound.play();
                this.setVisible(false);
                this.activated = false;
                break;

        }

    }

    

    public void setPostion(double X, double Y) {
        this.setX(X);
        this.setY(Y);
    }

    public int getType() {
        return Type;
    }

    public Boolean getStatus() {
        return activated;
    }

    public int getObstacleIndex() {
        return obstacleIndex;
    }

    public PowerUp getPowerUp(int index){
        if(this.getObstacleIndex() == index){
            return this;
        }
        return null;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

}
