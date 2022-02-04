import Tools.TypeSprite;

public class Sprite {

    private int x;
    private int y;
    private TypeSprite sprite = null;
    private double index = 0;
    private boolean isshoot = true;


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

    Sprite(int x, int y, TypeSprite t, boolean shot) {
        this.x = x;
        this.y = y;
        sprite = t;
        this.isshoot = shot;
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

    public double getIndex() {

        return this.index;
    }

    public void setIndex(double index) {

        this.index = index;
    }

    public TypeSprite getSpriteType() {

        return this.sprite;
    }
    public boolean getSpriteShot() {
        return isshoot;
    }
    public void setSpriteShot(boolean shot) {
        this.isshoot = shot;
    }

    public void movSpriteR() { this.x += 2; }
    public void movSpriteL() { this.x -= 2; }

    public static boolean TestColision(int i) {
        boolean setsound = false;
        for (int j = 0; j < MyPanel.myBullets.size(); j++) {
            if ((MyPanel.myBullets.get(j).getSpriteY() - MyPanel.myEnnemies.get(i).getSpriteY()) < 40 &
                    (Math.abs(MyPanel.myBullets.get(j).getSpriteX() - MyPanel.myEnnemies.get(i).getSpriteX())) < 25) {
                MyPanel.myexplosiones.add(new Sprite(MyPanel.myEnnemies.get(i).getSpriteX(),
                        MyPanel.myEnnemies.get(i).getSpriteY(), TypeSprite.EXPLOSION));
                MyPanel.myEnnemies.remove(i);
                MyPanel.myBullets.remove(j);
                setsound = true;
            }
        }
        return setsound;
    }
    public static boolean TestColisionJ() {
        boolean setsound = false;
        for (int j = 0; j < MyPanel.myBulletsE.size(); j++) {
            if ((  MyPanel.myBulletsE.get(j).getSpriteY() ) > MyPanel.screen_height - 95 &
                    (Math.abs(MyPanel.myBulletsE.get(j).getSpriteX() - MyPanel.posJ.x)) < 50) {
                MyPanel.myexplosionesj.add(new Sprite(MyPanel.posJ.x,
                        MyPanel.myBulletsE.get(j).getSpriteY(), TypeSprite.EXPLOSION));
                MyPanel.myBulletsE.remove(j);

                setsound = true;
            }
        }
        return setsound;
    }

}
