import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sprite {

    private int x;
    private int y;
    private TypeSprite sprite = null;
    private int index = 0;

    /**
     * constructeurs
     */
    Sprite() {
    }

    Sprite(int x, int y, TypeSprite t) {
        this.x = x;
        this.y = y;
        sprite = t;

    }

    /**
     * methodes
     */
    public int getSpriteX() {

        return this.x;
    }

    public int getSpriteY() {

        return this.y;
    }

    public void setSpriteX(int x) {

        this.x = x;
    }

    public void setSpriteY(int y) {

        this.y = y;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public TypeSprite getSpriteType() {
        return this.sprite;
    }

    public void movSprite(boolean dir) {
        if (dir) {
            this.x += 5;
        } else this.x -= 5;
    }

    public static boolean TestColision(int i) {
        boolean ex = false;
        for (int j = 0; j < MyPanel.myBullets.size(); j++) {
            if ((MyPanel.myBullets.get(j).getSpriteY() - MyPanel.myEnnemies.get(i).getSpriteY()) < 40 &
                    (Math.abs(MyPanel.myBullets.get(j).getSpriteX() - MyPanel.myEnnemies.get(i).getSpriteX())) < 25) {
                MyPanel.myexplosiones.add(new Sprite(MyPanel.myEnnemies.get(i).getSpriteX(),
                        MyPanel.myEnnemies.get(i).getSpriteY(), TypeSprite.EXPLOSION));
                MyPanel.myEnnemies.remove(i);
                MyPanel.myBullets.remove(j);
                ex = true;
            }
        }
        return ex;
    }

}
