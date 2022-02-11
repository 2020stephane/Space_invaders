import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game {

    public static Player joueur1;
    public static ArrayList<Enemy> ListEnemy;
    private int index_image_explosion;
    private BufferedImage[] image_explosion;

    public Game() {
        joueur1 = new Player();
        ListEnemy = new ArrayList<>();
    }
}
