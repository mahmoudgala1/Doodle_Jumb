
package DoodleJump;

import javafx.scene.shape.Rectangle;

public class Obstacle extends Rectangle {
     private int movementDir = 1;
     private Boolean landOn = false;

     Obstacle(double X, double Y, double width, double height){
          super(X,Y,width,height);
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

}
