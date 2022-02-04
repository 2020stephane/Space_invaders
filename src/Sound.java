import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class Sound {

    private static Clip clip = null;

    public Sound(URL sound) throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        //URL boom = this.getClass().getClassLoader().getResource(sound);
        AudioInputStream audioIn = null;

        audioIn = AudioSystem.getAudioInputStream(sound);
        //Clip clip = null;
        clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();
    }
    public static void StopWave() {

        clip.stop();
        clip.flush();
        clip.close();
    }
}
