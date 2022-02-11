import sound.Sound;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.*;
import java.io.IOException;

public class EventHandler implements MouseMotionListener, MouseListener, KeyListener {

    public EventHandler() {}

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == ' ') {
            MyPanel.myBullets.add(new Sprite(  MyPanel.joueur1.getCoord_joueur().x + 50,
                    MyPanel.screen_height - 120));
            try {
                new Sound(MyPanel.laserW);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { MyPanel.onscreen = true; }

    @Override
    public void mouseExited(MouseEvent e) { MyPanel.onscreen = false;  }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (MyPanel.onscreen & MyPanel.isrunning) {
            MyPanel.joueur1.setCoord_joueur( e.getPoint());
        }
    }
    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {  }

    @Override
    public void mouseClicked(MouseEvent e) {  }

    @Override
    public void mousePressed(MouseEvent e) {  }

    @Override
    public void mouseReleased(MouseEvent e) {  }

    @Override
    public void mouseDragged(MouseEvent e) {   }
}
