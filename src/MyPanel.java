import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class MyPanel extends JPanel  {

    public static BufferedImage bufferbackground = null;
    public static BufferedImage vaisseauj = null;
    public static BufferedImage enemy1 = null;
    public static BufferedImage enemy2 = null;
    public static BufferedImage buffer = null;
    public static BufferedImage bulletj = null;
    public static BufferedImage explosion = null;
    public static BufferedImage ex1 = null;
    public static boolean direction = true;
    public static Point posJ;
    public static int screen_width;
    public static int screen_height;

    public static boolean onscreen = false;
    public static ArrayList<Sprite> myBullets;
    public static ArrayList<Sprite> myEnnemies;
    public static ArrayList<Sprite> myexplosiones;
    public static ArrayList<BufferedImage> imgexplosion;



    public MyPanel(MyFrame frame) {
        setDoubleBuffered(true);
        setVisible(true);
        posJ = new Point();
        myBullets = new ArrayList<>();
        myEnnemies = new ArrayList<>();
        imgexplosion = new ArrayList<>();
        myexplosiones = new ArrayList<>();
        Level currentlevel = new Level();
        EventHandler eh = new EventHandler();
        addMouseListener(eh);
        addMouseMotionListener(eh);
        frame.addKeyListener(eh);
   }


    @Override
    public void paintComponent(Graphics g)  {
        Graphics2D g2d = (Graphics2D) g;
        if (bufferbackground == null) {

            for (int a = 0; a < 5; a++) {
                imgexplosion.add(explosion.getSubimage(a * 43, 0, 43, 50));
            }
            for (int a = 0; a < 5; a++) {
                imgexplosion.add(explosion.getSubimage(a * 43, 50, 43, 50));
            }
            AlphaComposite ac = AlphaComposite.Clear;
            g2d.setComposite(ac);
            screen_width = this.getWidth();
            screen_height = this.getHeight();
            bufferbackground = (BufferedImage) createImage(getWidth(),getHeight());
            g2d = bufferbackground.createGraphics();
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,getWidth(),getHeight());
        }
        g2d.drawImage(bufferbackground,null,0,0);

        try {
            render(bufferbackground.createGraphics());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void render(Graphics2D g2d) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,getWidth(),getHeight());
        if (posJ.x >= screen_width - 100) {
            posJ.x = screen_width - 100;
        }
        //g2d.drawImage(buffer,0,0,null);
        g2d.setColor(Color.WHITE);
        g2d.drawString("x = "+ screen_width,50,500);
        g2d.drawString("y = "+ screen_height,50,525);
        g2d.drawString("myennemies.size = "+ myEnnemies.size(),50,550);
        g2d.drawImage(vaisseauj, posJ.x, screen_height - 95,null);


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
        if (myEnnemies.size() != 0) {
            for (int i = 0; i < myEnnemies.size() ; i++) {
                if (myEnnemies.get(i).getSpriteType() == TypeSprite.ENNEMY1) {
                    g2d.drawImage(enemy1, myEnnemies.get(i).getSpriteX(),
                            myEnnemies.get(i).getSpriteY(), null);
                } else {
                    g2d.drawImage(enemy2, myEnnemies.get(i).getSpriteX(),
                        myEnnemies.get(i).getSpriteY(), null);
                }
                if (Sprite.TestColision(i)) {
                    new Sound("res/boom.wav");
                }

                myEnnemies.get(i).movSprite(direction);

            }
            if (myexplosiones.size() != 0) {
                for (int i = 0; i < myexplosiones.size() ; i++) {
                    g2d.drawImage(imgexplosion.get(myexplosiones.get(i).getIndex()), myexplosiones.get(i).getSpriteX(),
                            myexplosiones.get(i).getSpriteY(), null);
                    myexplosiones.get(i).setIndex(myexplosiones.get(i).getIndex() + 1);
                    if (myexplosiones.get(i).getIndex() == 10) {
                        myexplosiones.remove(i);
                    }
                }

            }
            if (myEnnemies.get(myEnnemies.size() - 1).getSpriteX() > 1534) {
                   direction = false;
                }
            if (myEnnemies.get(0).getSpriteX() < 0) {
                direction = true;
                }
        }
        //repaint();
    }
}