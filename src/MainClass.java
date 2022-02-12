import java.awt.*;
import java.io.IOException;

public final class MainClass implements Runnable {

    public MainClass() {  new Thread(this).start();  }

    public static void main(String[] args) {  javax.swing.SwingUtilities.invokeLater(MainClass::new); }

    @Override
    public void run() {

        final long DELAY = 1000 / MyPanel.FPS;
        long beforeTime;
        long timeDiff;
        long sleep;
        beforeTime = System.currentTimeMillis();
        Frame frame = null;
        try {
            frame = new MyFrame();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {

            assert frame != null;
            frame.repaint();
            Game.setCount(Game.getCount() + 1);
            Game.setTime(Game.getTime() + 1);
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {  Thread.sleep(sleep);  }
            catch (InterruptedException e) {
                String msg = String.format("Thread interrupted: %s", e.getMessage());
                System.out.println( msg + "Error");
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}
