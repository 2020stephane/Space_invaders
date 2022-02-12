import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class Game {

    public static Player joueur1;
    private static int count = 0;
    private static int time = 0;
    private static int level = 1;

    private static boolean isrunning = true;
    private static boolean endgame = false;

    public Game() throws IOException {
        joueur1 = new Player();
        level = 1;
        count = 0;
        time = 0;
        isrunning = false;
        endgame = false;
        MyPanel.musicW = this.getClass().getClassLoader().getResource("sound/music.wav");
        MyPanel.buffer = ImageIO.read(Objects.requireNonNull
                (MainClass.class.getResource("image/moon1.png")));
       Level currentlevel = new Level(Game.getLevel());

    }

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Game.count = count;
    }

    public static int getTime() {
        return time;
    }

    public static void setTime(int time) {
        Game.time = time;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        Game.level = level;
    }

    public static boolean isIsrunning() {
        return isrunning;
    }

    public static void setIsrunning(boolean isrunning) {
        Game.isrunning = isrunning;
    }

    public static boolean isEndgame() {
        return endgame;
    }

    public static void setEndgame(boolean endgame) {
        Game.endgame = endgame;
    }
}
