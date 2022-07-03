package byow.Core;

import byow.TileEngine.TETile;

import java.io.IOException;

/** This is the main entry point for the program. This class simply parses
 *  the command line inputs, and lets the byow.Core.Engine class take over
 *  in either keyboard or input string mode.
 */

// main
public class Main {
    public static void main(String[] args) {
        if (args.length > 2) {
            System.out.println("Can only have two arguments - the flag and input string");
            System.exit(0);
            //  Users can provide the program a command line argument, describing how they want to generate
            //  the random world and what exploration they wish to complete.
            //  The format of the command argument should be -s inputString, where inputString is the input of interactWithInputString()
        } else if (args.length == 2 && args[0].equals("-s")) {
            // -s N2342SWASDWASD
            if (args[1] == null) {
                System.out.println("Input is null.");
                System.exit(0);
            }
            Engine engine = new Engine();
            TETile[][] worldToPrint = engine.interactWithInputString(args[1]);
            System.out.println(TETile.toString(worldToPrint));
        // DO NOT CHANGE THESE LINES YET ;)
        } else if (args.length == 2 && args[0].equals("-p")) { System.out.println("Coming soon."); }
        // DO NOT CHANGE THESE LINES YET ;)
        else {
            Engine engine = new Engine();
            engine.interactWithKeyboard();
        }
    }
}
