package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class Engine implements Serializable {
    public static TETile[][] storage;
    public static long SEED;
    public World worldObject;
    public KeyMovements keymovements;
    public static String keyPresses;
    public static Random RANDOM;
    public static String input;
    public static Game game;
    public Menu menu;
    public static boolean won = false;
    /* Feel free to change the width and height. */

    public static int livesLeft = 3;

    // for the menu
    private static final String TITLE = "61Bee: We Don't Bite";
    private static final String TITLE2 = "We Sting!";
    private static final String newGame = "New Game (N)";
    private static final String loadGame = "Load Game (L)";
    private static final String quit = "Quit (Q)";
    private static final int HEIGHT = World.HEIGHT;
    private static final int WIDTH = World.WIDTH;
    ///
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File savedGame = Utils.join(CWD, "game.txt");

    public Engine() {
        keyPresses = "";
        // if saved game does not exist
        try {
            savedGame.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void gameWon() {
//        StdDraw.picture(World.WIDTH*0.5, World.HEIGHT*0.5, "beeSting.jpeg");
//        StdDraw.picture(World.WIDTH*0.5, World.HEIGHT*0.3, "warning.png");;

        StdDraw.setPenColor(Color.black);
        Font font = new Font("Arial", Font.BOLD, 35);
        StdDraw.setFont(font);

        StdDraw.text((double) WIDTH / 2, (double) HEIGHT * 0.7, "YOU WON!");
        StdDraw.show();
        StdDraw.pause(1500);
        won = true;
    }
    public void stringInputSeed(String input) {
        game = null;
        String newInput;
        if (input.charAt(0) == 'l' || input.charAt(0) == 'L') {
            game = Utils.readObject(savedGame, Game.class);
            if (game.KeyPresses.length() < 1) {
                SEED = game.SEED;
                keyPresses = game.KeyPresses + input.substring(1, input.length());
            } else if (game.KeyPresses.charAt(game.KeyPresses.length() - 1) == 'q' ||
                    game.KeyPresses.charAt(game.KeyPresses.length() - 1) == 'Q') {
                if (game.KeyPresses.charAt(game.KeyPresses.length() - 2) == ':') {
                    game.KeyPresses = game.KeyPresses.substring(0, game.KeyPresses.length() - 2);
                    keyPresses = game.KeyPresses;
                    newInput = 'N' + SEED + game.KeyPresses + input.substring(1, input.length() - 1);
                    SEED = game.SEED;
                    keyPresses = game.KeyPresses + input.substring(1, input.length() - 1);
                }
            }
        } else if (input.charAt(0) == 'n' || input.charAt(0) == 'N') {
            String inputLower = input.toLowerCase();
            int endingSeedIndex = inputLower.indexOf("s") + 1;
            int endingKeyIndex = inputLower.length();
            String SeedInput = inputLower.substring(1, endingSeedIndex);
            String newKeyPresses = inputLower.substring(endingSeedIndex, endingKeyIndex);
            SEED = Long.parseLong(SeedInput.substring(1, SeedInput.length() - 1));
            keyPresses = newKeyPresses;
            game = new Game(SEED, keyPresses);
        }
        initEngine(false);
    }


    public void initEngine(boolean render) {
        RANDOM = new Random(SEED);
        if (render) {
            worldObject = new World("keyboard");
        } else {
            worldObject = new World();
        }
        keymovements = new KeyMovements(worldObject);
    }


    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() { //
        menu = new Menu();
        menu.startMenu();
        initEngine(true);
        playManually();
    }
    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        stringInputSeed(input);
        playWithString(keyPresses);
        Game game = new Game(SEED, keyPresses);
        inputGameAsString(game);
//        System.out.println(Arrays.toString(worldObject.world));
        return worldObject.world;
    }

    public void playManually() { //lets just submit it, we have 3 mins
        int steps = 0;
        boolean ready_to_exit = false;
        Font font = new Font("Arial", Font.BOLD, 35);
        StdDraw.setFont(font);

        Position startingMousePos = new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY());

        while (livesLeft > 0) {
            if (won) {
                break;
            }
            worldObject.renderWorld();
            Position currMousePos = new Position((int) StdDraw.mouseX(), (int) StdDraw.mouseY());

            String startingMouseDesc = worldObject.tileInPosition(startingMousePos).description();
            String currMouseDesc = worldObject.tileInPosition(currMousePos).description();

            if (!startingMouseDesc.equals(Tileset.NOTHING.description())) {
                StdDraw.text(WIDTH / 2, HEIGHT * 0.98, currMouseDesc);
                StdDraw.show();
                StdDraw.pause(5);
            }


            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (steps % 5 == 0 && worldObject.MONSTERS.size() < 100) {
                    worldObject.spawnMonster();
                }
                if (ready_to_exit) {
                    if (key == 'Q' || key == 'q') {
                        saveGame();
                        System.exit(0);
                    }
                }
                if (key == ':') {
                    ready_to_exit = true;
                    continue;
                }
                keymovements.nextCharacterTile(worldObject.mainCharacter, key);
                keyPresses += key;
                if (!worldObject.MONSTERS.isEmpty()) {
                    int randomMonster = RANDOM.nextInt(worldObject.MONSTERS.size());
                    Character monster = worldObject.MONSTERS.get(randomMonster);
                    keymovements.nextCharacterTile(monster, key);
                    keymovements.nextCharacterTile(monster, key);
                    keymovements.nextCharacterTile(monster, key);
                }
                    worldObject.updateState();
                    worldObject.renderWorld();
                    steps += 1;
                    ready_to_exit = false;
                }
            }
        if (won) {
            StdDraw.clear(Color.white);
            StdDraw.picture((double) WIDTH * 0.5, HEIGHT * 0.5, "happybee.png");
            StdDraw.text(WIDTH / 2, HEIGHT * 0.85, "You're Awesome! Good Job!");
            StdDraw.show();
        } else {
                StdDraw.clear();
                StdDraw.picture((double) WIDTH * 0.5, HEIGHT * 0.5, "BeeScary.jpeg");
                StdDraw.picture((double) WIDTH * 0.5, HEIGHT * 0.5, "gameOver.png");
                StdDraw.show();
        }
    }

    public void replayGame(Game g) {
        game = g;
        SEED = game.SEED;
        initEngine(false);
        int steps = 0;
        boolean ready_to_exit = false;
        if (game.KeyPresses.length() == 0) {
            worldObject.updateState();
            worldObject.renderWorld();
        }
        for (int i = 0; i <= game.KeyPresses.length() - 1; i++) {
            char key = game.KeyPresses.charAt(i);
                if (steps%5 == 0 && worldObject.MONSTERS.size() < 3) {
                    worldObject.spawnMonster();
                }
                if (ready_to_exit) {
                    if (key == 'Q' || key == 'q') {
                        saveGame();
                        return;
                    }
                }
                if (key == ':') {
                    ready_to_exit = true;
                    continue;
                }
                keymovements.nextCharacterTile(worldObject.mainCharacter, key);
                keyPresses += key;
                if (!worldObject.MONSTERS.isEmpty()) {
                    int randomMonster = RANDOM.nextInt(worldObject.MONSTERS.size());
                    Character monster = worldObject.MONSTERS.get(randomMonster);
                    keymovements.nextCharacterTile(monster, key);
                    keymovements.nextCharacterTile(monster, key);
                    keymovements.nextCharacterTile(monster, key);
                }
                worldObject.updateState();
                worldObject.renderWorld();
                steps += 1;
                ready_to_exit = false;
            }
        }
    public void inputGameAsString(Game g) {
        game = g;
        SEED = game.SEED;
        initEngine(false);
        int steps = 0;
        boolean ready_to_exit = false;
        for (int i = 0; i <= game.KeyPresses.length() - 2; i++) {
            char key = game.KeyPresses.charAt(i);
            if (steps%5 == 0 && worldObject.MONSTERS.size() < 3) {
                worldObject.spawnMonster();
            }
            if (ready_to_exit) {
                if (key == 'Q' || key == 'q') {
                    saveGame();
                    return;
                }
            }
            if (key == ':') {
                ready_to_exit = true;
                continue;
            }
            keymovements.nextCharacterTile(worldObject.mainCharacter, key);
            keyPresses += key;
            if (!worldObject.MONSTERS.isEmpty()) {
                int randomMonster = RANDOM.nextInt(worldObject.MONSTERS.size());
                Character monster = worldObject.MONSTERS.get(randomMonster);
                keymovements.nextCharacterTile(monster, key);
                keymovements.nextCharacterTile(monster, key);
                keymovements.nextCharacterTile(monster, key);
            }
            worldObject.updateState();
            steps += 1;
            ready_to_exit = false;
        }
    }
    public void playWithString(String input) {
        int steps = 0;
        boolean ready_to_exit = false;
        for (int i = 0; i < input.length(); i+= 1) {
            char key = input.charAt(i);
            if (steps%5 == 0 && worldObject.MONSTERS.size() < 20) {
                worldObject.spawnMonster();
            }
            if (ready_to_exit) {
                if (key == 'Q' || key == 'q') {
                    saveGame();
                    return;
                }
            }
            if (key == ':') {
                ready_to_exit = true;
                continue;
            }
            keymovements.nextCharacterTile(worldObject.mainCharacter, key);
            keyPresses += key;
            if (!worldObject.MONSTERS.isEmpty()) {
                int randomMonster = RANDOM.nextInt(worldObject.MONSTERS.size());
                Character monster = worldObject.MONSTERS.get(randomMonster);
                keymovements.nextCharacterTile(monster, key);
                keymovements.nextCharacterTile(monster, key);
                keymovements.nextCharacterTile(monster, key);
            }
            worldObject.updateState();
            steps += 1;
            ready_to_exit = false;
        }
    }

    @Override
    public String toString(){
        return Arrays.deepToString(storage);
    }


    public static void saveGame() {
        // save seed and keyPresses
        game = new Game(SEED, keyPresses);
        Utils.writeObject(savedGame, game);
    }

}
