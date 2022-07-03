package byow.Core;

import byow.TileEngine.TETile;

public class Character {
    //
    public TETile tile;
    public Position currentPosition;
    public World worldObject;
    public TETile tileUnderCharacter;


    // tile is this character's tile representation
    // currentPosition is set to spawnPosition in the constructor
    // worldObject is the world this character belongs to
    public Character(World world, TETile t, Position currPos) {
        currentPosition = currPos;
        worldObject = world;
        tileUnderCharacter =  world.tileInPosition(currentPosition);
        tile = t;
    }

    // ALL METHODS BELOW THIS ONLY APPLY TO MONSTERS

//    public void move(Position curr, Position to) {
//
//    }
}


