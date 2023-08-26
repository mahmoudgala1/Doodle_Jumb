package DoodleJump.GameLogic;

import java.util.ArrayList;
import DoodleJump.Main;

public class KeyboardListener {
    private Boolean wMove = false;
    private Boolean sMove = false;
    private Boolean aMove = false;
    private Boolean dMove = false;
    private Boolean canJump = true;
    private GamePage selectedPane;
    private Player selectedPlayer;
    private Obstacle[] selectedObstacles;
    private PowerUp[] selectedPowerUp;
    private Monster[] selectedMonsters;
    private ArrayList<Projectile> selectedProjectiles;

    KeyboardListener(GamePage gamePage, Player player, Obstacle obstacle[], PowerUp powerUp[], Monster monster[],
            ArrayList<Projectile> projectile) {
        selectedPane = gamePage;
        selectedPlayer = player;
        selectedObstacles = obstacle;
        selectedPowerUp = powerUp;
        selectedMonsters = monster;
        selectedProjectiles = projectile;
    }

    // Initilize Key Events
    public void Start() {
        selectedPane.setOnKeyPressed(PressedKey -> {
            switch (PressedKey.getCode()) {
                case W:
                    // wMove = true;

                    if (Main.fly == true)
                        selectedPlayer.setyVelocity(-18);
                    // if (canJump == true) {
                    // selectedPlayer.setyVelocity(-18);
                    // // canJump = false;
                    // }
                    break;
                case S:
                    sMove = true;
                    break;
                case A:
                    aMove = true;
                    break;
                case D:
                    dMove = true;
                    break;
                case SPACE:
                    selectedPlayer.setxVelocity(15);
                default:
            }
        });

        selectedPane.setOnKeyReleased(PressedKey -> {

            switch (PressedKey.getCode()) {
                case W:
                    wMove = false;
                    break;
                case S:
                    sMove = false;
                    break;
                case A:
                    aMove = false;
                    break;
                case D:
                    dMove = false;
                    break;
                case SPACE:
                    selectedPlayer.setxVelocity(5);
                    break;
                default:
            }
        });

        selectedPane.setOnMouseClicked(Mouse -> {
            Projectile.create(selectedPlayer, selectedProjectiles, selectedPane, Mouse.getSceneX(), Mouse.getSceneY());
        });

    }

    // Checks for conditions to move the player either in Y or in X
    public void Loop() {

        // if (wMove == true)
        // selectedPlayer.moveY(5, selectedObstacles, selectedPowerUp);
        // if (sMove == true)
        // selectedPlayer.moveY(5, selectedObstacles, selectedPowerUp);
        // System.out.println(Main.fly);

        if (aMove == true)
            selectedPlayer.moveX(-selectedPlayer.getxVelocity(), selectedObstacles);
        if (dMove == true)
            selectedPlayer.moveX(selectedPlayer.getxVelocity(), selectedObstacles);

        selectedPane.requestFocus();
    }

}
