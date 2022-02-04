import Tools.TypeSprite;
import sound.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Level {

    private int Score = 0;

    public Level() {
        MyPanel.posJ = new Point();
        MyPanel.myBullets = new ArrayList<>();
        MyPanel.myBulletsE = new ArrayList<>();
        MyPanel.myEnnemies = new ArrayList<>();
        MyPanel.imgexplosion = new ArrayList<>();
        MyPanel.myexplosiones = new ArrayList<>();
        MyPanel.imgexplosionj = new ArrayList<>();
        MyPanel.myexplosionesj = new ArrayList<>();
        ImageLoader();
        Sound.StopWave();
        SoundLoader();
        DrawEnnemy();
        MyPanel.isrunning = true;


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
            MyPanel.explosion = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/explos.jpg")));
            MyPanel.explosionj = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("image/explosionj.jpg")));
            for (int a = 0; a < 5; a++) {
                MyPanel.imgexplosion.add(MyPanel.explosion.getSubimage(a * 43, 0, 43, 50));
            }
            for (int a = 0; a < 5; a++) {
                MyPanel.imgexplosion.add(MyPanel.explosion.getSubimage(a * 43, 50, 43, 50));
            }
            for (int a = 0; a < 6; a++) {
                MyPanel.imgexplosionj.add(MyPanel.explosionj.getSubimage(a * 65, 0, 65, 70));
            }
            for (int a = 0; a < 6; a++) {
                MyPanel.imgexplosionj.add(MyPanel.explosionj.getSubimage(a * 65, 70, 65, 70));
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    private void DrawEnnemy() {
        for (int x = 1; x<=20; x++) {
            MyPanel.myEnnemies.add(new Sprite(x * 75, 10 , TypeSprite.ENNEMY1));

        }
        for (int x = 2; x<=19; x++) {
            MyPanel.myEnnemies.add(new Sprite(x * 75, 85 ,TypeSprite.ENNEMY2));

        }
        for (int x = 3; x<=18; x++) {
            MyPanel.myEnnemies.add(new Sprite(x * 75, 160 ,TypeSprite.ENNEMY1));

        }
        for (int x = 4; x<=17; x++) {
            MyPanel.myEnnemies.add(new Sprite(x * 75, 235 ,TypeSprite.ENNEMY1));

        }
        for (int x = 5; x<=16; x++) {
            MyPanel.myEnnemies.add(new Sprite(x * 75, 310 ,TypeSprite.ENNEMY2));

        }
        for (int x = 6; x<=15; x++) {
            MyPanel.myEnnemies.add(new Sprite(x * 75, 385 ,TypeSprite.ENNEMY1));

        }
    }
    private void SoundLoader() {
        MyPanel.boomW = this.getClass().getClassLoader().getResource("sound/boom.wav");
        MyPanel.laserW = this.getClass().getClassLoader().getResource("sound/laser.wav");
        MyPanel.musicW = this.getClass().getClassLoader().getResource("sound/music.wav");
    }
}
