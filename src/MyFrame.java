import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyFrame extends JFrame {

    public MyFrame() throws IOException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SPACE INVADERS");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int height = dim.height;
        int width = dim.width;
        this.setLocation(0, 0);
        this.setSize(width, height);
        MyPanel mypanel = new MyPanel(this);
        add(mypanel,BorderLayout.CENTER);
        setVisible(true);
    }

}
