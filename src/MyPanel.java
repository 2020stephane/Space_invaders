import Tools.TypeSprite;
import sound.Sound;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MyPanel extends JPanel  {


    public static int FPS = 35;
    public static Game mygame;
    public static BufferedImage bufferbackground = null;
    public static BufferedImage vaisseauj = null;
    public static BufferedImage enemy1 = null;
    public static BufferedImage enemy2 = null;
    public static BufferedImage buffer = null;
    public static BufferedImage bufferlost = null;
    public static BufferedImage bulletj = null;
    public static BufferedImage bullete = null;
    public static BufferedImage explosionE = null;
    public static BufferedImage explosionJ = null;

    public static boolean direction = true;
    public static boolean onscreen = false;

    public static Sound mymusic;
    public static URL boomW;
    public static URL laserW;
    public static URL musicW;

    public static int screen_width;
    public static int screen_height;

    public static ArrayList<Enemy> ListEnemy = new ArrayList<>();
    public static ArrayList<Bullet> ListBulletJ = new ArrayList<>();
    public static ArrayList<Bullet> myBulletsE;
    public static ArrayList<Bullet> myexplosiones;
    public static ArrayList<BufferedImage> imgexplosion;

    public MyPanel(MyFrame frame) throws IOException {
        setDoubleBuffered(true);
        EventHandler eh = new EventHandler();
        addMouseListener(eh);
        addMouseMotionListener(eh);
        frame.addKeyListener(eh);
        mygame = new Game();
        setVisible(true);
   }


    @Override
    public void paintComponent(Graphics g)  {
        Graphics2D g2d = (Graphics2D) g;
        if (bufferbackground == null) {
            Game.setTime(0);
            screen_width = this.getWidth();
            screen_height = this.getHeight();
            bufferbackground = (BufferedImage) createImage(getWidth(),getHeight());
            g2d = bufferbackground.createGraphics();
            Level currentlevel = new Level(Game.getLevel());
            g2d.drawImage(buffer,0,0,null);
        }
        g2d.drawImage(bufferbackground,null,0,0);
        if (Game.isIsrunning() & !Game.isEndgame()) {
           try {
             render(bufferbackground.createGraphics());
           } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {e.printStackTrace(); }
        } else {
            if (Game.isEndgame() & Game.joueur1.getNbr_vie() == 0) {
                if (Game.getTime() > 60) {
                    g2d.drawImage(bufferlost, 0, 0, null);
                }
                try {
                    mygame = new Game();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                if (Game.isEndgame() & Game.joueur1.getNbr_vie() != 0) {
                    if (Game.getTime() > 60) {
                        Level currentlevel = new Level(Game.getLevel());
                    }
                }
            }
        }
    }
    public void render(Graphics2D g2d) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,getWidth(),getHeight());
        if (Game.joueur1.getCoord_joueur().x >= screen_width - 100) {
            Game.joueur1.setCoord_joueurX(screen_width - 100);
        }
        g2d.setColor(Color.WHITE);
        g2d.drawString("x = "+ screen_width,50,500);
        g2d.drawString("y = "+ screen_height,50,525);
        g2d.drawString("time = "+ (int) (Game.getTime() / FPS) ,50,550);
        g2d.drawString("vie = "+ Game.joueur1.getNbr_vie() ,50,575);
        g2d.drawString("images per seconde = "+ FPS ,50,600);

        if (Game.joueur1.isJoueurvisible()) {
            g2d.drawImage(vaisseauj, Game.joueur1.getCoord_joueur().x, screen_height - 95,null);
            Game.joueur1.TestColision();
        } else {
            g2d.drawImage(Game.joueur1.getImage_explosion((int) Game.joueur1.getIndex_image_explosion()),
                    Game.joueur1.getCoord_explosion().x,Game.joueur1.getCoord_explosion().y,null);
            Game.joueur1.UpdateExplosion();
        }
        if (ListBulletJ.size() != 0) {
            for (int i = ListBulletJ.size() -1; i >= 0; i--) {
                if (ListBulletJ.get(i).getCoordY() < 25) {
                    ListBulletJ.remove(i);
                    continue;
                }
                g2d.drawImage(bulletj, ListBulletJ.get(i).getCoordX(), ListBulletJ.get(i).getCoordY(), null);
                ListBulletJ.get(i).incBullet();
            }
        }
        if (myBulletsE.size() != 0) {
            for (int i = myBulletsE.size() - 1; i >= 0; i--) {
                if (myBulletsE.get(i).getCoordY() > (screen_height - 50)) {
                    myBulletsE.remove(i);
                    continue;
                }
                g2d.drawImage(bullete,myBulletsE.get(i).getCoordX(),myBulletsE.get(i).getCoordY(),
                        null);
                myBulletsE.get(i).setCoordY(myBulletsE.get(i).getCoordY() + 5);
            }
        }
        if (ListEnemy.size() != 0) {
            boolean dir = direction;
            for (int i = ListEnemy.size() - 1; i >= 0; i--) {

                if (ListEnemy.get(i).getType_enemy() == TypeSprite.ENNEMY1) {
                    g2d.drawImage(enemy1, ListEnemy.get(i).getCoord_enemy().x, ListEnemy.get(i).getCoord_enemy().y, null);
                } else {
                    g2d.drawImage(enemy2, ListEnemy.get(i).getCoord_enemy().x, ListEnemy.get(i).getCoord_enemy().y, null);
                }
                if (ListEnemy.get(i).TestColision(i)) {
                    new Sound(boomW);
                }
                double ran = Math.random() * 100;

                if ( ran > 30  & Game.getCount() > 60) {
                     myBulletsE.add(new Bullet(ListEnemy.get(i).getCoord_enemy().x, ListEnemy.get(i).getCoord_enemy().y));
                    Game.setCount(0);
                }
                ListEnemy.get(i).movEnemy(i,dir);
            }
            if (myexplosiones.size() != 0) {
                for (int i =myexplosiones.size() - 1; i >= 0; i--) {
                    g2d.drawImage(imgexplosion.get(( int )myexplosiones.get(i).getIndex()), myexplosiones.get(i).getCoordX(),
                            myexplosiones.get(i).getCoordY(), null);
                    myexplosiones.get(i).setIndex(myexplosiones.get(i).getIndex() + 0.5);
                    if (myexplosiones.get(i).getIndex() == 10) {
                        myexplosiones.remove(i);
                    }
                }
            }

        } else {
            Game.setEndgame(true);
            Game.setLevel(Game.getLevel() + 1);
        }

    }
}