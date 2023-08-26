package DoodleJump.GameLogic;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import DoodleJump.Main;
import DoodleJump.Pages.Audio;
import DoodleJump.Pages.Images;

public class Projectile extends ImageView {

    private static ArrayList<Projectile> removeProjectiles = new ArrayList<Projectile>();
    private static Image ProjectileImage = Images.Projectile;
    private double Xspeed = 0;
    private double Yspeed = 0;
    private double Angle = 0;
    private static Audio laserClip = new Audio("lazer.wav");
    private static Audio kill1Clip = new Audio("kill2.wav");
    private static Audio kill2Clip = new Audio("kill1.wav");

    Projectile() {
        super(ProjectileImage);
        this.setVisible(false);
        this.setFitHeight(15);
        this.setFitWidth(15);
    }

    //This Method moves every projectile its assigned x and y speeds
    public static void Loop(ArrayList<Projectile> newProjectiles, Monster newMonsters[]) {
        for (Projectile pro : newProjectiles) {
            for (int i = 0; i < 2; i++) {
                pro.setX(pro.getX() + pro.getXspeed());
                pro.setY(pro.getY() - pro.getYspeed());
                pro.setRotate(pro.getRotate() + 13);
                for (Monster mon : newMonsters) {
                    if (pro.getBoundsInParent().intersects(mon.getBoundsInParent())
                            && mon.getStatus() == true) {
                        removeProjectiles.add(pro);
                        pro.setVisible(false);
                        mon.Deactivate();
                        if (Math.random() > 0.5)
                            kill1Clip.play();
                        else
                            kill2Clip.play();
                    }
                }
                if (pro.getX() > GamePage.PlayerRightBorder || pro.getX() < GamePage.PlayerLeftBorder - 50
                        || pro.getY() > 1080
                        || pro.getY() < -20) {
                    removeProjectiles.add(pro);
                    pro.setVisible(false);
                }
            }
        }
        newProjectiles.removeAll(removeProjectiles);
        removeProjectiles.removeAll(removeProjectiles);
    }
    
    //This Method Creates a projectile when the user clicks on the screen.
    public static void create(Player Doodle, ArrayList<Projectile> newProjectiles, GamePage gamePane, double X,
            double Y) {
        if (Y > Doodle.Hitbox.getY() * Main.Factor / 3 || X > GamePage.PlayerRightBorder * Main.Factor / 3
                || X < GamePage.PlayerLeftBorder * Main.Factor / 3 || Doodle.getHasSomething() == true || gamePane.getPauseActive() == true) {
            return;
        }
        Projectile nowProjectile = new Projectile();
        gamePane.getChildren().add(nowProjectile);
        newProjectiles.add(nowProjectile);

        double tempX = X - (Doodle.Hitbox.getX() + 30) * Main.Factor / 3;
        double tempY = (Doodle.Hitbox.getY() + 10) * Main.Factor / 3 - Y;
        nowProjectile.Angle = Math.atan2(tempX, tempY);
        nowProjectile.Xspeed = Math.sin(nowProjectile.Angle) * 10;
        nowProjectile.Yspeed = Math.cos(nowProjectile.Angle) * 10;
        nowProjectile.setX(Doodle.Hitbox.getX() + 17);
        nowProjectile.setY(Doodle.Hitbox.getY() + 20);
        nowProjectile.setVisible(true);
        Doodle.shoot(nowProjectile.Angle);
        laserClip.play();
        gamePane.toAbove();

        // System.out.println("screen X: " + X);
        // System.out.println("screen Y: " + Y);
        // System.out.println("doodle X: " + Doodle.Hitbox.getX()* 2.5/3);
        // System.out.println("doodle Y: " + Doodle.Hitbox.getY()* 2.5/3);
        // System.out.println("traingle X: " + tempX);
        // System.out.println("traingle Y: " + tempY);
        // System.out.println("Angle: " + nowProjectile.Angle);
        // System.out.println("Speed X: " + nowProjectile.Xspeed);
        // System.out.println("Speed Y: " + nowProjectile.Yspeed);
        // System.out.println(newProjectiles.size());
        // System.out.println();

    }

    public double getXspeed() {
        return Xspeed;
    }

    public double getYspeed() {
        return Yspeed;
    }

}
