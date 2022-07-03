package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
public class Room {
    // static Hashset to store all the points occupied

    public final int room_width;
    public final int room_height;
    public final int floor_width;
    public final int floor_height;
    public final int initial_height;
    public final int initial_width;
    public final int initial_height_floor;
    public final int initial_width_floor;
    public final int lavaOrStoneFloor;
    public final Position midpoint;
    Random RANDOM = Engine.RANDOM;

    public static Room firstRoom;

    // world refers to the world object in which Room will be created
    public Room() {
        // lava or stone:
        lavaOrStoneFloor = RANDOM.nextInt(5) + 1;
        // wide or long room. 0 is long, 1 is wide.
        int wideorlong = RANDOM.nextInt(2);
        if (wideorlong == 0) {
            room_width = RANDOM.nextInt(6) + 10;
            room_height = RANDOM.nextInt(6) + 3;
        } else {
            room_width = RANDOM.nextInt(6) + 3;
            room_height = RANDOM.nextInt(6) + 10;
        }
        floor_width = room_width - 2;
        floor_height = room_height - 2;

        initial_height = RANDOM.nextInt(World.HEIGHT - room_height - 1);
        initial_width = RANDOM.nextInt(World.WIDTH - room_width - 1);
        initial_height_floor = initial_height + 1;
        initial_width_floor = initial_width + 1;

        int xMiddle = initial_width + (room_width/2);
        int yMiddle = initial_height + (room_height/2);
        Position m = new Position(xMiddle, yMiddle);
        midpoint = m;
    }

    // creates a room and fill it with walls
    public void makeWalls(World worldObject, int initial_width, int initial_height, boolean stoneIfTrue) {
        for (int x = initial_width; x < initial_width + room_width + 1; x += 1) {
            for (int y = initial_height; y < initial_height + room_height + 1; y += 1) {
                worldObject.world[x][y] = TileChooser.chooseWall(stoneIfTrue);
                Position point = new Position(x, y);
                worldObject.OCCUPIED.add(point);
            }
        }
    }

    // creates the floors in the rooms that are made
    public void makeFloors(World worldObject, int initial_width_floor, int initial_height_floor, boolean waterIfTrue) {
        for (int x = initial_width_floor; x < initial_width_floor + floor_width + 1; x += 1) {
            for (int y = initial_height_floor; y < initial_height_floor + floor_height + 1; y += 1) {
                worldObject.world[x][y] = TileChooser.chooseFloor(waterIfTrue);
            }
        }
    }

    // returns true if the room overlaps with the current existing rooms
    public boolean overlaps(World worldObject, int initial_width, int initial_height){
        for (int x = initial_width; x < initial_width + room_width + 1; x += 1) {
            for (int y = initial_height; y < initial_height + room_height + 1; y += 1) {
                Position point = new Position(x, y);
                if (worldObject.OCCUPIED.contains(point)) {
                    return true;
                }
            }
        }
        return false;
    }

    // draw the walls for a vertical hallway
    public void drawVWalls(World worldObject, Position r1midpoint, int y) {
        if (TileChooser.nothingDescriptions.contains(worldObject.world[r1midpoint.getX()-1][y].description())) {
            worldObject.world[r1midpoint.getX()-1][y] = TileChooser.chooseWall(false);
        }
        if (TileChooser.nothingDescriptions.contains(worldObject.world[r1midpoint.getX()+1][y].description())) {
            worldObject.world[r1midpoint.getX()+1][y] = TileChooser.chooseWall(false);
        }
    }

    // draw the walls for a horizontal hallway
    public void drawHWalls(World worldObject, Position r2midpoint, int x) {
        if (TileChooser.nothingDescriptions.contains(worldObject.world[x][r2midpoint.getY() + 1].description())) {
            worldObject.world[x][r2midpoint.getY() + 1] = TileChooser.chooseWall(false);
        }
        if (TileChooser.nothingDescriptions.contains(worldObject.world[x][r2midpoint.getY() - 1].description())) {
            worldObject.world[x][r2midpoint.getY() - 1] = TileChooser.chooseWall(false);
        }
    }

    // draw a path from room1midpointY to intersectingY
    public void drawVerticalHallway(World worldObject, Position r1midpoint, Position intersect) {
        if (intersect.getY() < r1midpoint.getY()) { // go down
            for (int y = r1midpoint.getY(); y >= intersect.getY(); y -= 1) {
                if (TileChooser.wallDescriptions.contains(worldObject.world[r1midpoint.getX()][y].description()) ||
                        TileChooser.nothingDescriptions.contains(worldObject.world[r1midpoint.getX()][y].description())) {
                    worldObject.world[r1midpoint.getX()][y] = TileChooser.chooseFloor(false);
                }
                drawVWalls(worldObject, r1midpoint, y);
                if (y == intersect.getY()) {
                    drawVWalls(worldObject, r1midpoint, y-1);
                }
            }
        } else if (intersect.getY() > r1midpoint.getY()) { // go up
            for (int y = r1midpoint.getY(); y <= intersect.getY(); y += 1) {
                if (TileChooser.wallDescriptions.contains(worldObject.world[r1midpoint.getX()][y].description()) ||
                        TileChooser.nothingDescriptions.contains(worldObject.world[r1midpoint.getX()][y].description())) {
                    worldObject.world[r1midpoint.getX()][y] = TileChooser.chooseFloor(false);
                }
                drawVWalls(worldObject, r1midpoint, y);
                if (y == intersect.getY()) {
                    drawVWalls(worldObject, r1midpoint, y+1);
                }
            }
        }
    }

    // draw a path horizontally from intersectingX to room2X
    public void drawHorizontalHallway(World worldObject, Position r2midpoint, Position intersect) {
        if (intersect.getX() < r2midpoint.getX()) { // go left from the room

            for (int x = r2midpoint.getX(); x >= intersect.getX() ; x -= 1) {
                if (TileChooser.wallDescriptions.contains(worldObject.world[x][intersect.getY()].description()) ||
                        TileChooser.nothingDescriptions.contains(worldObject.world[x][intersect.getY()].description())) {
                    worldObject.world[x][intersect.getY()] = TileChooser.chooseFloor(false);
                }
                drawHWalls(worldObject, r2midpoint, x);
                if (x == intersect.getX()) {
                    drawHWalls(worldObject, r2midpoint, x-1);
                }
            }
        } else if (intersect.getX() > r2midpoint.getX()) { // go right from the room
            for (int x = r2midpoint.getX(); x <= intersect.getX() ; x += 1) {
                if (TileChooser.wallDescriptions.contains(worldObject.world[x][intersect.getY()].description()) ||
                        TileChooser.nothingDescriptions.contains(worldObject.world[x][intersect.getY()].description())) {
                    worldObject.world[x][intersect.getY()] = TileChooser.chooseFloor(false);
                }
                drawHWalls(worldObject, r2midpoint, x);
                if (x == intersect.getX()) {
                    drawHWalls(worldObject, r2midpoint, x+1);
                }
            }
        }
    }


    public void addRoom(World worldObject, boolean firstroom) {
        // create the room and fill it with walls
        makeWalls(worldObject, initial_width, initial_height, firstroom);

        // make the middle floors
        makeFloors(worldObject, initial_width_floor, initial_height_floor, firstroom);
    }

    // create hallway between two rooms
    public void connectRooms(World worldObject, Room r1, Room r2) {
        Position r1Midpoint = r1.midpoint;
        Position r2Midpoint = r2.midpoint;
        Position intersectingPoint = new Position(r1Midpoint.getX(), r2Midpoint.getY());

        // 1. draw a path from room1midpointY to intersectingPoint y (vertical)
        drawVerticalHallway(worldObject, r1Midpoint, intersectingPoint);
        // 2. draw from intersectingPointX to room2x (horizontal)
        drawHorizontalHallway(worldObject, r2Midpoint, intersectingPoint);
    }

    public static void connectAllHallways(World worldObject) {
        for (int i = 1; i < worldObject.ROOMS.size() ; i += 1) {
            worldObject.ROOMS.get(0).connectRooms(worldObject, worldObject.ROOMS.get(i-1), worldObject.ROOMS.get(i));
        }
    } // can i see your room, is it diff from mine

    public static void generateRooms(World worldObject) {
        int currNumRooms = 0;
        int triesDone = 0;
        worldObject.OCCUPIED = new HashSet<Position>();
        worldObject.ROOMS = new ArrayList<Room>();
        worldObject.numberOfRooms = Engine.RANDOM.nextInt(16) + 5;
        // add first room
        firstRoom = new Room();
        firstRoom.addRoom(worldObject, true);
        worldObject.mainCharacter = new Character(worldObject, Tileset.AVATARFRONTWATER, firstRoom.midpoint);
        worldObject.ROOMS.add(firstRoom);
        worldObject.waterRoom = firstRoom;
        while (currNumRooms < worldObject.numberOfRooms) {
            Room testRoom = new Room();
            // only add the room if it does NOT overlap
            if (!testRoom.overlaps(worldObject, testRoom.initial_width, testRoom.initial_height)) {
                testRoom.addRoom(worldObject, false);
                worldObject.ROOMS.add(testRoom);
                currNumRooms += 1;
            }
            if (triesDone == 1000) {
                break;
            }
            triesDone += 1;
        }
    }
}
