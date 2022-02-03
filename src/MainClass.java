import javax.swing.*;
import java.awt.*;

public final class MainClass implements Runnable {

    private static Frame frame;

    public MainClass() {
        //frame = new MyFrame();
        new Thread(this).start();  }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(MainClass::new);

        //t1.start();

    }

    @Override
    public void run() {

        final int DELAY = 25;
        long beforeTime, timeDiff, sleep;
        boolean isrunning = true;
        beforeTime = System.currentTimeMillis();
        frame = new MyFrame();
         while (isrunning) {

                frame.repaint();

                timeDiff = System.currentTimeMillis() - beforeTime;
                sleep = DELAY - timeDiff;
               if (sleep < 0) {
                    sleep = 2;
                }
               try { Thread.sleep(sleep);
               }
               catch (InterruptedException e) {

                    String msg = String.format("Thread interrupted: %s", e.getMessage());

                    System.out.println( msg + "Error");
                }

                beforeTime = System.currentTimeMillis();
         }
    }

}
