import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Level {

    private int Score = 0;

    public Level() {
        ImageLoader();
        DrawEnnemy();

    }
    private void ImageLoader() {
        try {
            MyPanel.buffer = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("images/moon1.png")));
            MyPanel.vaisseauj = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("res/vaisseauj.jpg")));
            MyPanel.enemy1 = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("res/enemy1.jpg")));
            MyPanel.enemy2 = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("res/enemy2.jpg")));
            MyPanel.bulletj = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("res/bullet1.jpg")));

            MyPanel.explosion = ImageIO.read(Objects.requireNonNull
                    (MainClass.class.getResource("res/explos.jpg")));

        } catch ( IOException e ) {
            e.printStackTrace();
        }
    }
    private void DrawEnnemy() {
        for (int x = 1; x<=20; x++) {
            MyPanel.myEnnemies.add(new Sprite(x * 75, 10 ,TypeSprite.ENNEMY1));

        }
        for (int x = 1; x<=20; x++) {
            MyPanel.myEnnemies.add(new Sprite(x * 75, 85 ,TypeSprite.ENNEMY2));

        }
        for (int x = 1; x<=20; x++) {
            MyPanel.myEnnemies.add(new Sprite(x * 75, 165 ,TypeSprite.ENNEMY1));

        }
    }
}
