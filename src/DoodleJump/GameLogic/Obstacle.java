
package DoodleJump.GameLogic;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import DoodleJump.Pages.Images;

public class Obstacle extends ImageView {
     private int movementDir = 1;
     private Boolean landOn = false;
     private Boolean canMove = false;
     private Boolean activated = true;
     private Boolean isOccupied = false;
     private Boolean Died = false;

     static private Point2D PictureDimensions = new Point2D(115, 30);
     static public double Width = 80;
     static public double Height = 22;

     private int index = 0;
     static private int num = 0;
     static private Image obstacleTiles = Images.obstacleTiles;

     Obstacle(double X, double Y, int index, Pane gamePane) {
          super(obstacleTiles);
          gamePane.getChildren().add(this);
          this.setViewport(new Rectangle2D(0, 0, PictureDimensions.getX(), PictureDimensions.getY()));
          this.setX(X);
          this.setY(Y);
          this.setFitWidth(Width);
          this.setFitHeight(Height);
          this.index = index;
          if (Math.random() > 0.95) {
               this.setMove(true);
               this.setViewport(new Rectangle2D(0, 30, PictureDimensions.getX(), PictureDimensions.getY()));
          }
     }

     // Call this static method to initialize any array containing Obstacle objects.
     public static void initialize(Obstacle newObstacles[], GamePage gamePane) {
          newObstacles[0] = new Obstacle(GamePage.LeftBorder + 250, 1000, 0, gamePane);
          for (int i = 1; i < newObstacles.length; i++) {
               newObstacles[i] = new Obstacle(Obstacle.xRandom(), 1000 - (50 * i), i, gamePane);
          }
     }

     // This method moves obstacles that are marked for moving from side to side
     public void swing() {
          if (canMove == true && activated == true) {
               double pos = this.getX();
               if (pos > GamePage.RightBorder - Obstacle.Width || pos < GamePage.LeftBorder) {
                    this.toggleDir();
               }
               this.setX(pos + (2 * movementDir));
          }
     }

     // This method is called when the obstacle is under the screen so it can be used
     // again by teleporting it up the screen.
     public void teleportUP(Player Doodle, PowerUp newPowerUps[], Monster newMonsters[]) {
          if (this.getY() + Obstacle.Height > GamePage.GameScreenHeight && activated == true) {
               this.setY(this.getY() - GamePage.GameScreenHeight);
               this.setX(Obstacle.xRandom());

               double probablity = Math.random();

               for (int i = 0; i < newPowerUps.length; i++) {
                    if (newPowerUps[i].getObstacleIndex() == index) {
                         newPowerUps[i].radnomActivation();
                         newPowerUps[i].boundTo(this);

                    }
               }

               for (int i = 0; i < newMonsters.length; i++) {
                    if (newMonsters[i].getObstacleIndex() == index) {
                         newMonsters[i].radnomActivation();
                         newMonsters[i].boundTo(this);

                    }
               }

               if (probablity > 0.5) {
                    this.toggleDir();
               }
               if (probablity > 0.95) {
                    this.setMove(true);
               } else {
                    this.setMove(false);
               }
               if (Doodle.getScore() > 2000 && index % 2 == 1 && probablity > 0.7) {
                    this.Deactivate();
                    // num++;
                    // System.out.println(num);
                    this.setViewport(new Rectangle2D(0, 60, PictureDimensions.getX(), PictureDimensions.getY()));
               }

               // if (Doodle.getScore() > 3000 && index % 4 == 0 && probablity > 0.7) {
               // this.Deactivate();
               // this.setViewport(new Rectangle2D(0, 90, PictureDimensions.getX(),
               // PictureDimensions.getY()));
               // }
          }
     }

     //This method returns a random X value for the obstacle
     static public double xRandom() {
          return ((int) (Math.random() * 100) * (GamePage.GameScreenWidth - Obstacle.Width) / 100)
                    + GamePage.LeftBorder;
          // return 900;
     }

     //This method sets if the obstacle can swing or not
     public void setMove(Boolean i) {
          if (i == false)
               this.setViewport(new Rectangle2D(0, 0, PictureDimensions.getX(), PictureDimensions.getY()));
          if (i == true)
               this.setViewport(new Rectangle2D(0, 30, PictureDimensions.getX(), PictureDimensions.getY()));
          this.canMove = i;
     }

     public int getIndex() {
          return index;
     }

     public void toggleDir() {
          if (this.movementDir == 1)
               this.movementDir = -1;
          else
               this.movementDir = 1;
     }

     public Boolean getActivated() {
          return activated;
     }

     public void setActivated(Boolean activated) {
          this.activated = activated;
     }

     public void Deactivate() {
          this.activated = false;
     }

     public int getDir() {
          return this.movementDir;
     }

     public void landOn() {
          this.landOn = true;
     }

     public Boolean getMove() {
          return this.canMove;
     }

     public void setOccupied(Boolean isOccupied) {
          this.isOccupied = isOccupied;
     }

     public Boolean getDied() {
          return Died;
     }

     public void setDied(Boolean died) {
          Died = died;
     }

}
