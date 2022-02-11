import Tools.TypeSprite;
import sound.Sound;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class MyPanel extends JPanel  {


    public static int FPS = 35;
    public static BufferedImage bufferbackground = null;
    public static BufferedImage vaisseauj = null;
    public static BufferedImage enemy1 = null;
    public static BufferedImage enemy2 = null;
    public static BufferedImage buffer = null;
    public static BufferedImage bulletj = null;
    public static BufferedImage bullete = null;
    public static BufferedImage explosion = null;
    public static BufferedImage explosionj = null;
    public static BufferedImage ex1 = null;

    public static boolean direction = true;
    public static boolean isrunning = false;
    public static boolean endgame = false;

    public static boolean onscreen = false;

    public static Sound mymusic;
    public static URL boomW;
    public static URL laserW;
    public static URL musicW;


    public static int screen_width;
    public static int screen_height;
    public static int count = 0;
    public static int time = 0;

    public static int level = 1;

    public static Player joueur1 = new Player();

    public static ArrayList<Sprite> myBullets;
    public static ArrayList<Sprite> myEnnemies;
    public static ArrayList<Sprite> myBulletsE;
    public static ArrayList<Sprite> myexplosiones;
    public static ArrayList<Sprite> myexplosionesj;
    public static ArrayList<BufferedImage> imgexplosion;





    public MyPanel(MyFrame frame) throws IOException {
        setDoubleBuffered(true);
        EventHandler eh = new EventHandler();
        addMouseListener(eh);
        addMouseMotionListener(eh);
        frame.addKeyListener(eh);
        musicW = this.getClass().getClassLoader().getResource("sound/music.wav");
        buffer = ImageIO.read(Objects.requireNonNull
                (MainClass.class.getResource("image/moon1.png")));
        setVisible(true);

   }


    @Override
    public void paintComponent(Graphics g)  {
        Graphics2D g2d = (Graphics2D) g;
        if (bufferbackground == null) {
            time = 0;
           screen_width = this.getWidth();
            screen_height = this.getHeight();
            bufferbackground = (BufferedImage) createImage(getWidth(),getHeight());
            g2d = bufferbackground.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,getWidth(),getHeight());
        }
        g2d.drawImage(bufferbackground,null,0,0);
        if (isrunning & !endgame ) {
           try {
             render(bufferbackground.createGraphics());
           } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {e.printStackTrace(); }
        } else  {
            g2d.drawImage(buffer,0,0,null);
            try {
                 mymusic = new Sound(musicW);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) { e.printStackTrace(); }
        if (time > 60) {  Level currentlevel = new Level(level); }
        }
    }
    public void render(Graphics2D g2d) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,getWidth(),getHeight());
        if (joueur1.getCoord_joueur().x >= screen_width - 100) {
            joueur1.setCoord_joueurX(screen_width - 100);
        }

        //g2d.drawImage(buffer,0,0,null);
        g2d.setColor(Color.WHITE);
        g2d.drawString("x = "+ screen_width,50,500);
        g2d.drawString("y = "+ screen_height,50,525);
        g2d.drawString("time = "+ (int) (time / FPS) ,50,550);
        g2d.drawString("vie = "+ joueur1.getNbr_vie() ,50,575);
        g2d.drawString("images per seconde = "+ FPS ,50,600);
        if (joueur1.isJoueurvisible()) {
            g2d.drawImage(vaisseauj, joueur1.getCoord_joueur().x, screen_height - 95,null);
        }

        if (myBullets.size() != 0) {
            for (int i = 0; i < myBullets.size(); i++) {
                if (myBullets.get(i).getSpriteY() < 25) {
                    myBullets.remove(i);
                    continue;
                }
                g2d.drawImage(bulletj,myBullets.get(i).getSpriteX(),myBullets.get(i).getSpriteY(),
                        null);
                myBullets.get(i).setSpriteY(myBullets.get(i).getSpriteY() - 5);
            }
        }
        if (myBulletsE.size() != 0) {
            for (int i = 0; i < myBulletsE.size(); i++) {
                if (myBulletsE.get(i).getSpriteY() > (screen_height - 50)) {
                    myBulletsE.remove(i);
                    continue;
                }
                g2d.drawImage(bullete,myBulletsE.get(i).getSpriteX(),myBulletsE.get(i).getSpriteY(),
                        null);
                myBulletsE.get(i).setSpriteY(myBulletsE.get(i).getSpriteY() + 5);
            }
        }
        if (myEnnemies.size() != 0) {
            boolean dir = direction;
            for (int i = 0; i < myEnnemies.size() ; i++) {

                if (myEnnemies.get(i).getSpriteType() == TypeSprite.ENNEMY1) {
                    g2d.drawImage(enemy1, myEnnemies.get(i).getSpriteX(),
                            myEnnemies.get(i).getSpriteY(), null);
                } else {
                    g2d.drawImage(enemy2, myEnnemies.get(i).getSpriteX(),
                        myEnnemies.get(i).getSpriteY(), null);
                }
                if (Sprite.TestColision(i)) {
                    new Sound(boomW);
                }
                double ran = Math.random() * 100;

                if ( ran > 30 & myEnnemies.get(i).getSpriteShot() & count > 60) {
                     myBulletsE.add(new Sprite(myEnnemies.get(i).getSpriteX(), myEnnemies.get(i).getSpriteY(), TypeSprite.BULLET));
                    myEnnemies.get(i).setSpriteShot(false);
                    count = 0;
                }
                if (dir) { myEnnemies.get(i).movSpriteR();
                } else myEnnemies.get(i).movSpriteL();

                if (myEnnemies.get(i).getSpriteX() > 1534) {
                    direction = false;
                }
                if (myEnnemies.get(i).getSpriteX() < 0) {
                    direction = true;
                }
            }
            if (myexplosiones.size() != 0) {
                for (int i = 0; i < myexplosiones.size() ; i++) {
                    g2d.drawImage(imgexplosion.get(( int )myexplosiones.get(i).getIndex()), myexplosiones.get(i).getSpriteX(),
                            myexplosiones.get(i).getSpriteY(), null);
                    myexplosiones.get(i).setIndex(myexplosiones.get(i).getIndex() + 0.5);
                    if (myexplosiones.get(i).getIndex() == 10) {
                        myexplosiones.remove(i);
                    }
                }

            }

        } else {
            endgame = true;
            level +=1;
        }
        if (joueur1.TestColision()) {
            new Sound(boomW);
            joueur1.setNbr_vie(joueur1.getNbr_vie() - 1);
            if (joueur1.getNbr_vie() == 0) {
                endgame = true;
                bufferbackground = null;
            }
            joueur1.setJoueurvisible(false);
        }
        joueur1.AfficheExplosion(g2d);
    }
}