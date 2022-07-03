package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TERenderer.*;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;


public class World {
    public HashSet<Position> OCCUPIED;
    public ArrayList<Room> ROOMS;
    public ArrayList<Character> MONSTERS;

    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;
    public TETile tomato;
    public int numberOfRooms;
    public TETile[][] world;
    public TETile backgroundTile;
    public Character mainCharacter;
    public TERenderer ter = new TERenderer();;
    public final TileChooser tileChooser = new TileChooser();
    public Room waterRoom;
    public World(String keyboard) {
        ROOMS = new ArrayList<>();
        OCCUPIED = new HashSet<>();
        MONSTERS = new ArrayList<>();
        world = new TETile[WIDTH][HEIGHT];
        tomato = Tileset.TOMATO;
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                backgroundTile = TileChooser.chooseNothing();
                placeTile(backgroundTile, new Position(x, y));
            }
        }
        createRooms();
        createHallways();
        placeTile(mainCharacter.tile, mainCharacter.currentPosition);
        ter.initialize(WIDTH, HEIGHT);
        renderWorld();
        placeTomato();
        // place avatar on the map
        //placeTile(mainCharacter.tile, mainCharacter.currentPosition, false);

        // are they in the right order?????
    }
    public World() {
        ROOMS = new ArrayList<>();
        OCCUPIED = new HashSet<>();
        MONSTERS = new ArrayList<>();
        world = new TETile[WIDTH][HEIGHT];
        tomato = Tileset.TOMATO;
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                backgroundTile = TileChooser.chooseNothing();
                placeTile(backgroundTile, new Position(x, y));
            }
        }
        createRooms();
        createHallways();
        placeTile(mainCharacter.tile, mainCharacter.currentPosition);
        placeTomato();
        // place avatar on the map
        //placeTile(mainCharacter.tile, mainCharacter.currentPosition, false);

        // are they in the right order?????
    }

    public void placeTomato() {
        for (Room room: ROOMS) {
            if (tileInPosition(room.midpoint).description().equals("grass")) {
                placeTile(tomato, room.midpoint);
                return;
            }
        }
    }

    public void createRooms() {
        Room.generateRooms(this);
    }

    public void createHallways() {
        Room.connectAllHallways(this);
    }

    public void renderWorld() {
        ter.renderFrame(world);
    }

    // returns tile in given position
    public TETile tileInPosition(Position position) {
        return world[position.getX()][position.getY()];
    }

    // places tile in given position
    public void placeTile(TETile tile, Position position) {
        world[position.getX()][position.getY()] = tile;
    }

    public void spawnMonster() {
        Character monster = new Character(this, TileChooser.chooseMonster("down"),
                ROOMS.get(Engine.RANDOM.nextInt(ROOMS.size())).midpoint);
        if (monster.currentPosition != Room.firstRoom.midpoint) {
            MONSTERS.add(monster);
            placeTile(monster.tile, monster.currentPosition);
        }
    }
    public void updateState() {
        ArrayList<Character> monstersToRemove = new ArrayList<>();
        for (Character monster: MONSTERS) {
            if (mainCharacter.currentPosition.equals(monster.currentPosition)
            || monster.currentPosition.equals(mainCharacter.currentPosition)) {
                Engine.livesLeft -= 1;
                if (Engine.livesLeft != 0) {
                    StdDraw.clear();
                    drawFrame("You have " + Engine.livesLeft + " lives left. Please be careful!");
                }
            }
            if (monster.tileUnderCharacter.description().equals("water")) {
                monstersToRemove.add(monster);
            }
        }
        for (Character monsters: monstersToRemove) {
            placeTile(TileChooser.chooseFloor(true), monsters.currentPosition);
            MONSTERS.remove(monsters);
        }
    }

    public void drawFrame(String s) {
        //StdDraw.picture(World.WIDTH*0.5, World.HEIGHT*0.5, "beeSting.jpeg");
        //StdDraw.picture(World.WIDTH*0.5, World.HEIGHT*0.3, "warning.png");;

        StdDraw.setPenColor(Color.black);
        Font font = new Font("Arial", Font.BOLD, 35);
        StdDraw.setFont(font);

        StdDraw.text((double) WIDTH/2, (double) HEIGHT*0.7, s);
        StdDraw.show();
        StdDraw.pause(1500);
    }
}
