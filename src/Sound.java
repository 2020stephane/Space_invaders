import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    public Sound(String sound) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        URL boom = this.getClass().getClassLoader().getResource(sound);
        AudioInputStream audioIn = null;

        audioIn = AudioSystem.getAudioInputStream(boom);
        Clip clip = null;
        clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
}
