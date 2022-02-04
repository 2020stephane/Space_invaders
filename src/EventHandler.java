import javax.sound.sampled.*;
import java.awt.event.*;
import java.io.IOException;

public class EventHandler implements MouseMotionListener, MouseListener, KeyListener {

    public EventHandler() {}

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == ' ') {
            MyPanel.myBullets.add(new Sprite(MyPanel.posJ.x + 50, MyPanel.screen_height - 120, TypeSprite.BULLET));
            try {
                new Sound(MyPanel.laserW);
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            MyPanel.posJ.x = MyPanel.posJ.x + 15;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            MyPanel.posJ.x = MyPanel.posJ.x - 15;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            MyPanel.myBullets.add(new Sprite(MyPanel.posJ.x, MyPanel.screen_height - 120, TypeSprite.BULLET));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {  }

    @Override
    public void mouseClicked(MouseEvent e) {
        MyPanel.myBullets.add(new Sprite(MyPanel.posJ.x, MyPanel.screen_height - 120, TypeSprite.BULLET));
    }

    @Override
    public void mousePressed(MouseEvent e) {  }

    @Override
    public void mouseReleased(MouseEvent e) {  }

    @Override
    public void mouseEntered(MouseEvent e) { MyPanel.onscreen = true; }

    @Override
    public void mouseExited(MouseEvent e) { MyPanel.onscreen = false;  }

    @Override
    public void mouseDragged(MouseEvent e) {   }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (MyPanel.onscreen & MyPanel.isrunning) {
            MyPanel.posJ = e.getPoint();
        }
    }
}
