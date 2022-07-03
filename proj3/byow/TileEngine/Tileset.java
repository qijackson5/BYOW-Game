package byow.TileEngine;

import byow.Core.Room;
import java.util.Random;
import java.awt.Color;
import java.io.File;
import java.nio.file.Path;
import java.util.HashSet;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 *
 *
 *     /**
 *      * Full constructor for TETile objects.
 *      * @param character The character displayed on the screen.
 *      * @param textColor The color of the character itself.
 *      * @param backgroundColor The color drawn behind the character.
 *      * @param description The description of the tile, shown in the GUI on hovering over the tile.
 *      * @param filepath Full path to image to be used for this tile. Must be correct size (16x16)
 *
 *      public TETile(char character,Color textColor,Color backgroundColor,String description, String filepath)
 */
public class Tileset {
    public static final TETile AVATARFRONT = new TETile('1', Color.white, Color.black, "you", "farmerman_front.png");
    public static final TETile AVATARBACK = new TETile('2', Color.white, Color.black, "you", "farmerman_back.png");
    public static final TETile AVATARRIGHT = new TETile('3', Color.white, Color.black, "you", "farmerman_right.png");
    public static final TETile AVATARLEFT = new TETile('4', Color.white, Color.black, "you", "farmerman_left.png");
    public static final TETile AVATARFRONTWATER = new TETile('5', Color.white, Color.black, "you", "farmerman_front_water.png");
    public static final TETile AVATARBACKWATER = new TETile('6', Color.white, Color.black, "you", "farmerman_back_water.png");
    public static final TETile AVATARRIGHTWATER = new TETile('7', Color.white, Color.black, "you", "farmerman_right_water.png");
    public static final TETile AVATARLEFTWATER = new TETile('8', Color.white, Color.black, "you", "farmerman_left_water.png");
    public static final TETile TOMATO = new TETile('|', new Color(215, 0, 139), new Color(255, 25, 0), "tomato", "tomatotile.png");
    public static final TETile BEEFRONT = new TETile('9', Color.magenta, Color.pink, "bee", "bee_front.png");
    public static final TETile BEEBACK = new TETile('0', Color.magenta, Color.pink, "bee", "bee_back.png");
    public static final TETile BEERIGHT = new TETile('-', Color.magenta, Color.pink, "bee", "bee_right.png");
    public static final TETile BEELEFT = new TETile('+', Color.magenta, Color.pink, "bee", "bee_left.png");




    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,"wall");

    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"', Color.black, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");
    // LAVA TILES:
    public static final TETile WATER1 = new TETile('a', new Color(215, 0, 139), new Color(255, 25, 0), "water", "water1.png");
    public static final TETile WATER2 = new TETile('b', new Color(215, 0, 139), new Color(255, 25, 0), "water", "water2.png");
    public static final TETile WATER3 = new TETile('c', new Color(215, 0, 139), new Color(255, 25, 0), "water", "water3.png");
    public static final TETile WATER4 = new TETile('d', new Color(215, 0, 139), new Color(255, 25, 0), "water", "water4.png");
    public static final TETile WATER5 = new TETile('e', new Color(215, 0, 139), new Color(255, 25, 0), "water", "water5.png");
    // STONE TILES:
    public static final TETile STONE1 = new TETile('f', new Color(39, 88, 59), new Color(139, 193, 206), "stone", "stonetile1.png");
    public static final TETile STONE2 = new TETile('g', new Color(39, 88, 59), new Color(139, 193, 206), "stone", "stonetile2.png");
    public static final TETile STONE3 = new TETile('h', new Color(39, 88, 59), new Color(139, 193, 206), "stone", "stonetile3.png");
    public static final TETile STONE4 = new TETile('i', new Color(39, 88, 59), new Color(139, 193, 206), "stone", "stonetile4.png");
    public static final TETile STONE5 = new TETile('j', new Color(39, 88, 59), new Color(139, 193, 206), "stone", "stonetile5.png");

    // GRASS TILES:
    public static final TETile GRASS0 = new TETile('k', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass0.png");
    public static final TETile GRASS1 = new TETile('l', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass1.png");
    public static final TETile GRASS2 = new TETile('m', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass2.png");
    public static final TETile GRASS3 = new TETile('n', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass3.png");
    public static final TETile GRASS4 = new TETile('o', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass4.png");
    public static final TETile GRASS5 = new TETile('p', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass5.png");
    public static final TETile GRASS6 = new TETile('q', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass6.png");
    public static final TETile GRASS7 = new TETile('r', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass7.png");
    public static final TETile GRASS8 = new TETile('s', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass8.png");
    public static final TETile GRASS9 = new TETile('t', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass9.png");
    public static final TETile GRASS10 = new TETile('u', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass10.png");
    public static final TETile GRASS11 = new TETile('v', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass11.png");
    public static final TETile GRASS12 = new TETile('w', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass12.png");
    public static final TETile GRASS13 = new TETile('x', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass13.png");
    public static final TETile GRASS14 = new TETile('y', new Color(39, 88, 59), new Color(139, 193, 206), "grass", "grass14.png");

    // FLOWERS ON GRASS TILES
    public static final TETile FLOWER1 = new TETile('z', new Color(150, 200, 200), new Color(150, 200, 200), "flower", "grassflower1.png");
    public static final TETile FLOWER2 = new TETile('A', new Color(150, 200, 200), new Color(150, 200, 200), "flower", "grassflower2.png");
    public static final TETile FLOWER3 = new TETile('B', new Color(150, 200, 200), new Color(150, 200, 200), "flower", "grassflower3.png");
    public static final TETile FLOWER4 = new TETile('C', new Color(150, 200, 200), new Color(150, 200, 200), "flower", "grassflower4.png");
    public static final TETile FLOWER5 = new TETile('D', new Color(150, 200, 200), new Color(150, 200, 200), "flower", "grassflower5.png");
    public static final TETile FLOWER6 = new TETile('E', new Color(150, 200, 200), new Color(150, 200, 200), "flower", "grassflower6.png");
    public static final TETile FLOWER7 = new TETile('F', new Color(150, 200, 200), new Color(150, 200, 200), "flower", "grassflower7.png");
    public static final TETile FLOWER8 = new TETile('G', new Color(150, 200, 200), new Color(150, 200, 200), "flower", "grassflower8.png");

    // GRASS NOTHING TILES
    public static final TETile NOTHINGGRASS0 = new TETile('{', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass0.png");
    public static final TETile NOTHINGGRASS1 = new TETile('}', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass1.png");
    public static final TETile NOTHINGGRASS2 = new TETile('[', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass2.png");
    public static final TETile NOTHINGGRASS3 = new TETile(']', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass3.png");
    public static final TETile NOTHINGGRASS4 = new TETile('H', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass4.png");
    public static final TETile NOTHINGGRASS5 = new TETile('I', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass5.png");
    public static final TETile NOTHINGGRASS6 = new TETile('J', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass6.png");
    public static final TETile NOTHINGGRASS7 = new TETile('K', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass7.png");
    public static final TETile NOTHINGGRASS8 = new TETile('L', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass8.png");
    public static final TETile NOTHINGGRASS9 = new TETile('M', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass9.png");
    public static final TETile NOTHINGGRASS10 = new TETile('N', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass10.png");
    public static final TETile NOTHINGGRASS11 = new TETile('O', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass11.png");
    public static final TETile NOTHINGGRASS12 = new TETile('P', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass12.png");
    public static final TETile NOTHINGGRASS13 = new TETile('Q', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass13.png");
    public static final TETile NOTHINGGRASS14 = new TETile('R', new Color(39, 88, 59), new Color(139, 193, 206), "nothinggrass", "grass14.png");


    // FENCE HORIZONTAL
    public static final TETile FENCEHORIZONTAL = new TETile('S', new Color(238, 238, 180),new Color(238, 238, 180), "fence", "fence_horizontal.png");
    public static final TETile FENCEVERTICAL0 = new TETile('T', new Color(238, 238, 180),new Color(238, 238, 180), "fence", "fence_vertical0.png");
    public static final TETile FENCEVERTICAL1 = new TETile('U', new Color(238, 238, 180),new Color(238, 238, 180), "fence", "fence_vertical1.png");
    public static final TETile FENCEVERTICAL2 = new TETile('V', new Color(238, 238, 180),new Color(238, 238, 180), "fence", "fence_vertical2.png");
    public static final TETile FENCEVERTICAL3 = new TETile('W', new Color(238, 238, 180),new Color(238, 238, 180), "fence", "fence_vertical3.png");

}


