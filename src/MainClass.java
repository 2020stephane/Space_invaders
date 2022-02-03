import java.awt.*;

public final class MainClass implements Runnable {

    public MainClass() {  new Thread(this).start();  }

    public static void main(String[] args) {  javax.swing.SwingUtilities.invokeLater(MainClass::new); }

    @Override
    public void run() {

        final long DELAY = 1000 / 60;
        long beforeTime;
        long timeDiff;
        long sleep;
        beforeTime = System.currentTimeMillis();
        Frame frame = new MyFrame();

        while (true) {

            frame.repaint();
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
