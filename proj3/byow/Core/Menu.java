package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

// shows the menu screen
public class Menu {
    private static final String title = "61Bee: We Don't Bite";
    private static final String title2 = "We Sting!";
    private static final String newGame = "New Game (N)";
    private static final String loadGame = "Load Game (L)";
    private static final String quit = "Quit (Q)";
    private static final int HEIGHT = World.HEIGHT;
    private static final int WIDTH = World.WIDTH;
    public Engine engine;

    public String engineSeed;
    public Menu() {
        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    public static void drawTitle(String title) {
        StdDraw.setPenColor(Color.black);

        Font font = new Font("", Font.BOLD, 50);
        StdDraw.setFont(font);

        StdDraw.text((double) WIDTH/2, (double) HEIGHT*.80, title);

        StdDraw.show();
    }

    public static void drawBody() {
        Font font = new Font("Arial", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.black);
        StdDraw.text((double) WIDTH/2, (double) HEIGHT*0.6, newGame);
//        StdDraw.setPenColor(Color.CYAN);
        StdDraw.text((double) WIDTH/2, (double) HEIGHT*0.5, loadGame);
//        StdDraw.setPenColor(Color.GREEN);
        StdDraw.text((double) WIDTH/2, (double) HEIGHT*0.4, quit);

        StdDraw.show();

    }

    public static void drawBackground() {
        StdDraw.picture((double) WIDTH*0.5, (double) HEIGHT*0.5, "farm.png");

        StdDraw.picture((double) WIDTH*0.20, (double) HEIGHT*0.4, "bee2.png");
        StdDraw.picture((double) WIDTH*0.85, (double) HEIGHT*0.8, "bee2.png");
        StdDraw.picture((double) WIDTH*0.7, (double) HEIGHT*0.3, "bee2.png");
        StdDraw.show();
    }

    public static void drawBackground2() {
        StdDraw.picture((double) WIDTH * 0.5, (double) HEIGHT * 0.5, "farm.png");
        StdDraw.picture((double) WIDTH*0.31, (double) HEIGHT/8, "bee.png");
        StdDraw.picture((double) WIDTH*0.69, (double) HEIGHT/8, "bee.png");
        drawTitle(title2);
        StdDraw.show();
    }

        public static void drawFrame(String s) {
        StdDraw.clear();
        drawBackground2();
        StdDraw.setPenColor(Color.black);
        Font font = new Font("Arial", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.text((double) WIDTH/2, (double) HEIGHT*0.7, s);
        StdDraw.show();
    }

    public static void drawMenu() {
        Menu menu = new Menu();
        drawBackground();
        drawTitle(title);
        drawBody();
    }

    public void updateEngineSeed() {
        String on_screen = "Enter Seed: ";
        drawFrame(on_screen);
        String seed = "";
        while (true) {
            while (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'S' || key == 's') {
                    engineSeed = "N" + seed + "S";
                    updateEngineSeedHelper(engineSeed);
                    return;
                }
                on_screen += key;
                seed += key;
                drawFrame(on_screen);
            }

        }
    }

    public void updateEngineSeedHelper(String engineSeed) {
        Engine.SEED = Long.parseLong(engineSeed.substring(1, engineSeed.length()-1));
    }
    public void startMenu() {
        drawMenu();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'N' || key == 'n') {
                    // update the seed in Engine
                    updateEngineSeed();
                    return;
                } else if (key == 'L' || key == 'l') {
                    // load the game
                    engine = new Engine();
                    Game loadedGame = Utils.readObject(Engine.savedGame, Game.class);
                    engine.replayGame(loadedGame);
                    engine.playManually();
                    return;
                } else if (key == 'Q' || key == 'q') {
                    System.exit(0);
                }
            }
        }
    }
}
