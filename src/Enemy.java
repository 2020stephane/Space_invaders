import Tools.TypeSprite;

import java.awt.*;

public class Enemy {

    private boolean isalive;
    private Point coord_enemy;
   private TypeSprite type_enemy;


    public Enemy(TypeSprite type, Point pt) {
        this.type_enemy = type;
        this.coord_enemy = pt;
        this.isalive = true;

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
}
