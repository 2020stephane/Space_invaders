import Tools.TypeSprite;

import java.awt.*;

public class Enemy {

    private boolean isalive = true;
    private Point coord_enemy = new Point();
    private TypeSprite type_enemy;

    public Enemy(int x, int y, TypeSprite type) {
        this.coord_enemy.x = x;
        this.coord_enemy.y = y;
        this.type_enemy = type;
    }

    public void setCoord_enemy(Point coord_enemy) {
        this.coord_enemy = coord_enemy;
    }

    public Point getCoord_enemy() {
        return coord_enemy;
    }

    public void setType_enemy(TypeSprite type_enemy) {
        this.type_enemy = type_enemy;
    }

    public TypeSprite getType_enemy() {
        return type_enemy;
    }

    public void movSpriteR() { this.coord_enemy.x += 2; }

    public void movSpriteL() { this.coord_enemy.x -= 2; }

    public  boolean TestColision(int i) {
        boolean setsound = false;
        for (int j =MyPanel.ListBulletJ.size() - 1; j >= 0; j--) {
            if ((MyPanel.ListBulletJ.get(j).getCoordY() - MyPanel.ListEnemy.get(i).getCoord_enemy().y) < 40 &
                    (Math.abs(MyPanel.ListBulletJ.get(j).getCoordX() - MyPanel.ListEnemy.get(i).getCoord_enemy().x)) < 25) {
                MyPanel.myexplosiones.add(new Bullet(MyPanel.ListEnemy.get(i).getCoord_enemy().x,
                        MyPanel.ListEnemy.get(i).getCoord_enemy().y));
                MyPanel.ListEnemy.remove(i);
                MyPanel.ListBulletJ.remove(j);
                setsound = true;
            }
        }
        return setsound;
    }
    public void movEnemy(int i, boolean dir) {

        if (dir) {
            MyPanel.ListEnemy.get(i).movSpriteR();
        } else MyPanel.ListEnemy.get(i).movSpriteL();
        if (MyPanel.ListEnemy.get(i).getCoord_enemy().x >= 1534) {
            MyPanel.direction = false;
        }
        if (MyPanel.ListEnemy.get(i).getCoord_enemy().x <= 0) {
            MyPanel.direction = true;
        }
    }
}
