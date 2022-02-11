import Tools.TypeSprite;
import sound.Sound;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Level {


    public Level(int level) {

        MyPanel.myBullets = new ArrayList<>();
        MyPanel.myBulletsE = new ArrayList<>();
        MyPanel.myEnnemies = new ArrayList<>();
        MyPanel.imgexplosion = new ArrayList<>();
        MyPanel.myexplosiones = new ArrayList<>();



        ImageLoader();
        Sound.StopWave();
        SoundLoader();
        DrawEnnemy(level);
        MyPanel.isrunning = true;
        MyPanel.endgame = false;


    }
    private void ImageLoader() {
        try {
            MyPanel.buffer = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/moon1.png")));
            MyPanel.vaisseauj = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/vaisseauj.jpg")));
            MyPanel.enemy1 = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/enemy1.jpg")));
            MyPanel.enemy2 = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/enemy2.jpg")));
            MyPanel.bulletj = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/bullet1.jpg")));
            MyPanel.bullete = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/bulletennemy.png")));
            MyPanel.explosionE = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/explos.jpg")));
            MyPanel.explosionJ = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/explosionj.jpg")));
            for (int a = 0; a < 5; a++) {

                MyPanel.imgexplosion.add(MyPanel.explosionE.getSubimage(a * 43, 0, 43, 50));
            }
            for (int a = 0; a < 5; a++) {
                MyPanel.imgexplosion.add(MyPanel.explosionE.getSubimage(a * 43, 50, 43, 50));
            }

            for (int col = 0; col < 6; col ++) {
                MyPanel.joueur1.setImage_explosion(col, MyPanel.explosionJ.getSubimage(
                           col * 65, 0, 65, 70));
                }
            for (int col = 6, indice = 0; col < 12 | indice < 6; col ++, indice ++) {
                MyPanel.joueur1.setImage_explosion(col, MyPanel.explosionJ.getSubimage(
                        indice * 65, 70, 65, 70));
            }

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    private void DrawEnnemy(int level) {

            for (int x = 1; x <= 20; x++) {
                MyPanel.myEnnemies.add(new Sprite(x * 75, 10, TypeSprite.ENNEMY1));

            }
            for (int x = 2; x <= 19; x++) {
                MyPanel.myEnnemies.add(new Sprite(x * 75, 85, TypeSprite.ENNEMY2));

            }
            for (int x = 3; x <= 18; x++) {
                MyPanel.myEnnemies.add(new Sprite(x * 75, 160, TypeSprite.ENNEMY1));

            }
            for (int x = 4; x <= 17; x++) {
                MyPanel.myEnnemies.add(new Sprite(x * 75, 235, TypeSprite.ENNEMY1));

            }
            for (int x = 5; x <= 16; x++) {
                MyPanel.myEnnemies.add(new Sprite(x * 75, 310, TypeSprite.ENNEMY2));

            }
            for (int x = 6; x <= 15; x++) {
                MyPanel.myEnnemies.add(new Sprite(x * 75, 385, TypeSprite.ENNEMY1));

            }
         if (level == 2) {
             for (int x = 1; x <= 20; x++) {
                 MyPanel.myEnnemies.add(new Sprite(x * 75, 450, TypeSprite.ENNEMY1));

             }
             for (int x = 2; x <= 19; x++) {
                 MyPanel.myEnnemies.add(new Sprite(x * 75, 525, TypeSprite.ENNEMY2));

             }
             for (int x = 3; x <= 18; x++) {
                 MyPanel.myEnnemies.add(new Sprite(x * 75, 600, TypeSprite.ENNEMY1));

             }
         }
    }
    private void SoundLoader() {
        MyPanel.boomW = this.getClass().getClassLoader().getResource("sound/boom.wav");
        MyPanel.laserW = this.getClass().getClassLoader().getResource("sound/laser.wav");
        MyPanel.musicW = this.getClass().getClassLoader().getResource("sound/music.wav");
    }
}
