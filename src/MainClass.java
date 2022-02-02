import java.awt.*;

public final class MainClass {

    private Frame frame;

    public MainClass() {

        frame = new MyFrame();

    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(MainClass::new);
    }
}