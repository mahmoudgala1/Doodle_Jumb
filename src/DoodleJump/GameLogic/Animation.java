package DoodleJump.GameLogic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animation {

    private Boolean reverse = false;
    private int i = 0;
    private Timeline animate;
    // ArrayList<Animation> newAnimations = new ArrayList<Animation>();

    // This class is used to animate pictures moving depending on the tile size in the image

    // Marked for removal //

    Animation(ImageView image, double width, double height, int time, int numOfTiles, int cycleCount, int offset) {
        i = offset;
        animate = new Timeline(new KeyFrame(Duration.millis(time), e -> {
            image.setViewport(new Rectangle2D(i * width, 0, width, height));
            if (reverse == false) {
                i++;
                if (i == numOfTiles - 1)
                    reverse = true;
            } else {
                i--;
                if (i == 0)
                    reverse = false;
            }
        }));

        if (cycleCount == 0) {
            animate.setCycleCount(Timeline.INDEFINITE);
        } else {
            animate.setCycleCount(cycleCount);
        }
        animate.play();
    }
}
