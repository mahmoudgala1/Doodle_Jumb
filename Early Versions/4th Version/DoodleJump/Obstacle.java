
package DoodleJump;

import javafx.scene.image.ImageView;
//import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

public class Obstacle extends ImageView {
     private int movementDir = 1;
     private Boolean landOn = false;
     private Boolean canMove = false;
     

     Obstacle(double X, double Y, int width, int height){
          super(new Image("DoodleJump/obs.png"));
          this.setX(X);
          this.setY(Y);
          this.setFitWidth(width);
          this.setFitHeight(height);
     }
     

     public void toggleDir() {
          if (this.movementDir == 1)
               this.movementDir = -1;
          else
               this.movementDir = 1;
     }

     public int getDir(){
          return this.movementDir;
     }

     public void landOn(){
          this.landOn = true;
     }

     public void setMove(Boolean i) {
          this.canMove = i;
     }

     public Boolean getMove(){
          return this.canMove;
     }

}
