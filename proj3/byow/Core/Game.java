package byow.Core;
import java.io.Serializable;

public class Game implements Serializable {
    public long SEED;
    String KeyPresses;
    Game(long seed, String kp) {
        SEED = seed;
        KeyPresses = kp;
    }
}
