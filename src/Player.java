import sound.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player {

    private boolean joueurvisible;
    private Point coord_joueur;
    private Point coord_explosion;
    private int nbr_vie;
    private double index_image_explosion;
    private  BufferedImage[] image_explosion;
    //private static ArrayList<Sprite> myBullets;

    public Player() {

        this.joueurvisible = true;
        this.coord_joueur = new Point(MyPanel.screen_width / 2, MyPanel.screen_height - 100);
        this.nbr_vie = 3;
        this.index_image_explosion = 0;
        this.image_explosion = new BufferedImage[12];
        this.coord_explosion = new Point();
    }
    public Point getCoord_joueur() {
        return this.coord_joueur;
    }
    public void setCoord_joueur(Point pt) {
        this.coord_joueur = pt;
    }
    public void setCoord_joueurX(int x) {
        this.coord_joueur.x = x;
    }
    public void setCoord_joueurY(int y) {
        this.coord_joueur.y = y;
    }

    public void setNbr_vie(int nbr_vie) {
        this.nbr_vie = nbr_vie;
    }

    public int getNbr_vie() {
        return nbr_vie;
    }

    public void setJoueurvisible(boolean joueurvisible) {
        this.joueurvisible = joueurvisible;
    }

    public boolean isJoueurvisible() {
        return joueurvisible;
    }

    public BufferedImage getImage_explosion(int i) {
        return this.image_explosion[i];
    }
    public  void setImage_explosion(int i, BufferedImage img) {
        this.image_explosion[i] = img;
    }

    public void setIndex_image_explosion(double index_image_explosion) {
        this.index_image_explosion = index_image_explosion;
    }

    public double getIndex_image_explosion() {
        return index_image_explosion;
    }

    public Point getCoord_explosion() {
        return coord_explosion;
    }

    public void setCoord_explosion(Point coord_explosion) {
        this.coord_explosion = new Point(coord_explosion.x, coord_explosion.y);
    }

    public void TestColision() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        for (int j = 0; j < MyPanel.myBulletsE.size(); j++) {
            if ((  MyPanel.myBulletsE.get(j).getSpriteY() ) > MyPanel.screen_height - 100 &
                    (Math.abs(MyPanel.myBulletsE.get(j).getSpriteX() - coord_joueur.x)) < 50) {
                setCoord_explosion(new Point(MyPanel.myBulletsE.get(j).getSpriteX(), MyPanel.myBulletsE.get(j).getSpriteY()));
                MyPanel.myBulletsE.remove(j);
                new Sound(MyPanel.boomW);
                MyPanel.joueur1.setNbr_vie(MyPanel.joueur1.getNbr_vie() - 1);
                if (MyPanel.joueur1.getNbr_vie() == 0) {
                    MyPanel.endgame = true;
                }
                MyPanel.joueur1.setJoueurvisible(false);
                break;
            }
        }

    }
    public void UpdateExplosion() {

            MyPanel.joueur1.setIndex_image_explosion( (MyPanel.joueur1.getIndex_image_explosion() + 0.5));
            if (MyPanel.joueur1.getIndex_image_explosion() == 11) {
                MyPanel.joueur1.setJoueurvisible(true);
                MyPanel.joueur1.setIndex_image_explosion(0);
            }
    }
}
