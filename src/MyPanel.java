import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class MyPanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener {

    public static BufferedImage bufferbackground = null;
    public static BufferedImage vaisseauj = null;
    public static BufferedImage enemy1 = null;
    public static BufferedImage enemy2 = null;
    public static BufferedImage buffer = null;
    public static BufferedImage bulletj = null;
    public static BufferedImage explosion = null;
    public static BufferedImage ex1 = null;
    public static boolean direction = true;
    private static Point posJ;
    private static int screen_width;
    private static int screen_height;

    private boolean onscreen = false;
    public static ArrayList<Sprite> myBullets;
    public static ArrayList<Sprite> myEnnemies;
    public static ArrayList<Sprite> myexplosiones;
    public static ArrayList<BufferedImage> myexplosion;



    public MyPanel(MyFrame frame) {
        setDoubleBuffered(true);
        setVisible(true);
        posJ = new Point();
        myBullets = new ArrayList<>();
        myEnnemies = new ArrayList<>();
        myexplosion = new ArrayList<>();
        myexplosiones = new ArrayList<>();
        Level currentlevel = new Level();
        addMouseListener(this);
        addMouseMotionListener(this);
        frame.addKeyListener(this);


   }


    @Override
    public void paintComponent(Graphics g)  {
        Graphics2D g2d = (Graphics2D) g;
        if (bufferbackground == null) {

            for (int a = 0; a < 5; a++) {
                myexplosion.add(explosion.getSubimage(a * 43, 0, 43, 50));
            }
            for (int a = 0; a < 5; a++) {
                myexplosion.add(explosion.getSubimage(a * 43, 50, 43, 50));
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

        render(bufferbackground.createGraphics());
   }
    public void render(Graphics2D g2d){
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
                    URL url = this.getClass().getClassLoader().getResource("res/boom.wav");
                    AudioInputStream audioIn = null;
                    try {
                        assert url != null;
                        audioIn = AudioSystem.getAudioInputStream(url);
                    } catch (UnsupportedAudioFileException | IOException ex) {
                        ex.printStackTrace();
                    }
                    // Get a sound clip resource.
                    Clip clip = null;
                    try {
                        clip = AudioSystem.getClip();
                    } catch (LineUnavailableException ex) {
                        ex.printStackTrace();
                    }
                    // Open audio clip and load samples from the audio input stream.
                    try {
                        assert clip != null;
                        clip.open(audioIn);
                    } catch (LineUnavailableException | IOException ex) {
                        ex.printStackTrace();
                    }
                    clip.start();
                }

                myEnnemies.get(i).movSprite(direction);

            }
            if (myexplosiones.size() != 0) {
                for (int i = 0; i < myexplosiones.size() ; i++) {
                    g2d.drawImage(myexplosion.get(myexplosiones.get(i).getIndex()), myexplosiones.get(i).getSpriteX(),
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

    @Override
    public void mouseClicked(MouseEvent e) {
            myBullets.add(new Sprite(posJ.x,screen_height - 120, TypeSprite.BULLET));
    }

    @Override
    public void mousePressed(MouseEvent e) {  }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { onscreen = true;  }

    @Override
    public void mouseExited(MouseEvent e) { onscreen = false;  }

    @Override
    public void mouseDragged(MouseEvent e) {   }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (onscreen) {
            posJ = e.getPoint();
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'x') {
            posJ.x = posJ.x - 5;
        }
        if (e.getKeyChar() == 'n') {
            posJ.x = posJ.x + 5;
        }
        if (e.getKeyChar() == ' ') {
            myBullets.add(new Sprite(posJ.x + 50,screen_height - 120, TypeSprite.BULLET));
            URL url = this.getClass().getClassLoader().getResource("res/laser.wav");
            AudioInputStream audioIn = null;
            try {
                audioIn = AudioSystem.getAudioInputStream(url);
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            // Get a sound clip resource.
            Clip clip = null;
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
            // Open audio clip and load samples from the audio input stream.
            try {
                clip.open(audioIn);
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            clip.start();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            posJ.x = posJ.x + 15;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            posJ.x = posJ.x - 15;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            myBullets.add(new Sprite(posJ.x,screen_height - 120, TypeSprite.BULLET));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}