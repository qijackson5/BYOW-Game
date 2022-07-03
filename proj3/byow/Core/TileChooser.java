package byow.Core;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.HashMap;

public class TileChooser {
    public static final HashMap<Integer, TETile> waterTiles = new HashMap<>();
    public static final HashMap<Integer, TETile> stoneTiles = new HashMap<>();
    public static final HashMap<Integer, TETile> grassTiles = new HashMap<>();
    public static final HashMap<Integer, TETile> fenceTiles = new HashMap<>();

    public static final HashMap<Integer, TETile> nothinggrassTiles = new HashMap<>();
    public static final ArrayList<String> floorDescriptions = new ArrayList<>();
    public static final ArrayList<String> wallDescriptions = new ArrayList<>();
    public static final ArrayList<String> nothingDescriptions = new ArrayList<>();

    public TileChooser() {
        // descriptions of tiles that are floor vs walls vs empty:
        floorDescriptions.add("grass");
        floorDescriptions.add("flower");
        floorDescriptions.add("water");
        wallDescriptions.add("fence");
        wallDescriptions.add("stone");
        nothingDescriptions.add("nothinggrass");



        // add lava tiles to lavaTiles hashmap
        waterTiles.put(0, Tileset.WATER1);
        waterTiles.put(1, Tileset.WATER2);
        waterTiles.put(2, Tileset.WATER3);
        waterTiles.put(3, Tileset.WATER4);
        waterTiles.put(4, Tileset.WATER5);

        // add stone tiles to stoneTiles hashmap
        stoneTiles.put(1, Tileset.STONE1);
        stoneTiles.put(2, Tileset.STONE2);
        stoneTiles.put(3, Tileset.STONE3);
        stoneTiles.put(4, Tileset.STONE4);
        stoneTiles.put(5, Tileset.STONE5);

        // add grass tiles to grassTiles hashmap
        grassTiles.put(0, Tileset.GRASS0);
        grassTiles.put(1, Tileset.GRASS1);
        grassTiles.put(2, Tileset.GRASS2);
        grassTiles.put(3, Tileset.GRASS3);
        grassTiles.put(4, Tileset.GRASS4);
        grassTiles.put(5, Tileset.GRASS5);
        grassTiles.put(6, Tileset.GRASS6);
        grassTiles.put(7, Tileset.GRASS7);
        grassTiles.put(8, Tileset.GRASS8);
        grassTiles.put(9, Tileset.GRASS9);
        grassTiles.put(10, Tileset.GRASS10);
        grassTiles.put(11, Tileset.GRASS11);
        grassTiles.put(12, Tileset.GRASS12);
        grassTiles.put(13, Tileset.GRASS13);
        grassTiles.put(14, Tileset.GRASS14);
        // flowers  to grassTiles hashmap
        grassTiles.put(15, Tileset.FLOWER1);
        grassTiles.put(16, Tileset.FLOWER2);
        grassTiles.put(17, Tileset.FLOWER3);
        grassTiles.put(18, Tileset.FLOWER4);
        grassTiles.put(19, Tileset.FLOWER5);
        grassTiles.put(20, Tileset.FLOWER6);
        grassTiles.put(21, Tileset.FLOWER7);
        grassTiles.put(22, Tileset.FLOWER8);

        nothinggrassTiles.put(0, Tileset.NOTHINGGRASS0);
        nothinggrassTiles.put(1, Tileset.NOTHINGGRASS1);
        nothinggrassTiles.put(2, Tileset.NOTHINGGRASS2);
        nothinggrassTiles.put(3, Tileset.NOTHINGGRASS3);
        nothinggrassTiles.put(4, Tileset.NOTHINGGRASS4);
        nothinggrassTiles.put(5, Tileset.NOTHINGGRASS5);
        nothinggrassTiles.put(6, Tileset.NOTHINGGRASS6);
        nothinggrassTiles.put(7, Tileset.NOTHINGGRASS7);
        nothinggrassTiles.put(8, Tileset.NOTHINGGRASS8);
        nothinggrassTiles.put(9, Tileset.NOTHINGGRASS9);
        nothinggrassTiles.put(10, Tileset.NOTHINGGRASS10);
        nothinggrassTiles.put(11, Tileset.NOTHINGGRASS11);
        nothinggrassTiles.put(12, Tileset.NOTHINGGRASS12);
        nothinggrassTiles.put(13, Tileset.NOTHINGGRASS13);
        nothinggrassTiles.put(14, Tileset.NOTHINGGRASS14);

        fenceTiles.put(0, Tileset.FENCEVERTICAL0);
        fenceTiles.put(1, Tileset.FENCEVERTICAL1);
        fenceTiles.put(2, Tileset.FENCEVERTICAL2);
        fenceTiles.put(3, Tileset.FENCEVERTICAL3);
        fenceTiles.put(4, Tileset.FENCEHORIZONTAL);
    }
    public static TETile chooseFloor(boolean waterIfTrue) {
        if (waterIfTrue) {
            int randomWaterNum = Engine.RANDOM.nextInt(5);
            return waterTiles.get(randomWaterNum);
        } else {
            int randomFloorNum = Engine.RANDOM.nextInt(16);
            if (randomFloorNum == 1) {
                int randomFlower = Engine.RANDOM.nextInt(8) + 15;
                return grassTiles.get(randomFlower);
            }
            return grassTiles.get(Engine.RANDOM.nextInt(15));
        }
    }
    public static TETile chooseMonster(String direction) {
        if (direction.equals("up")) {
            return Tileset.BEEBACK;
        }
        if (direction.equals("down")) {
            return Tileset.BEEFRONT;
        }
        if (direction.equals("right")) {
            return Tileset.BEERIGHT;
        } else {
            return Tileset.BEELEFT;
        }
    }

    public static TETile chooseAvatar(String direction, String waterOrGrass) {
        if (direction.equals("up")) {
            if (waterOrGrass.equals("water")) {
                return Tileset.AVATARBACKWATER;
            }
            return Tileset.AVATARBACK;
        }
        if (direction.equals("down")) {
            if (waterOrGrass.equals("water")) {
                return Tileset.AVATARFRONTWATER;
            }
            return Tileset.AVATARFRONT;
        }
        if (direction.equals("left")) {
            if (waterOrGrass.equals("water")) {
                return Tileset.AVATARLEFTWATER;
            }
            return Tileset.AVATARLEFT;
        } else {
            if (waterOrGrass.equals("water")) {
                return Tileset.AVATARRIGHTWATER;
            }
            return Tileset.AVATARRIGHT;
        }
    }
    public static TETile chooseNothing() {
        return nothinggrassTiles.get(Engine.RANDOM.nextInt(15));
    }
    public static TETile chooseWall(boolean stoneIfTrue) {
        if (stoneIfTrue) {
            return stoneTiles.get(Engine.RANDOM.nextInt(5)+ 1);
        } else {
            return fenceTiles.get(4);
        }
    }
}
