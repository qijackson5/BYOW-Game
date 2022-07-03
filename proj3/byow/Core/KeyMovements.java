package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

public class KeyMovements {
    public World worldObject;
    public KeyMovements(World world) {
        worldObject = world;
    }

    public void nextCharacterTile(Character character, char key) {
        if (key == 'A' || key == 'a') {
            moveTile(character,"left");
        }
        else if (key == 'W' || key == 'w') {
            moveTile(character,"up");
        }
        else if (key == 'S' || key == 's') {
            moveTile(character,"down");
        }
        else if (key == 'D' || key == 'd') {
            moveTile(character,"right");
        }
    }

    public void moveTile(Character character, String direction) {
        Position newPosition = nextPosition(character, direction);
        if (isOut(newPosition)) { // if new pos does not exist
            return;
        } else if (character.tile.description().equals("bee")) {
            moveCharToNewPos(character, newPosition, direction);
        }
        else if (character.tile.description().equals("you")) { // check if position is a floor
            if (posIsBee(newPosition)) {
                Engine.livesLeft -= 1;
                if (Engine.livesLeft != 0) {
                    worldObject.drawFrame("You have " + Engine.livesLeft + " lives left. Please be careful!");
                }
            }
            if (posIsFloor(newPosition)) {
                moveCharToNewPos(character, newPosition, direction);
            }
            if (posIsTomato(newPosition)) {
                Engine.gameWon();
            }
        }
    }

    public boolean posIsTomato(Position newPosition) {
        return (worldObject.tileInPosition(newPosition).description().equals("tomato"));
    }

    /** ALL METHODS BELOW THIS ARE HELPERS did u seee when i run into the bee i die. right now the game just quits but
     * we can show a game over screen later. alsooooooo every game starts with a water room, and if the bees go into the WTF
     * water they die!
     *
     *
     *
     */

    // returns the next position of where the tile would go given the direction
    public Position nextPosition(Character character, String direction) {
        Position characterPosition = character.currentPosition;
        Position newPosition = null;
        if (direction.equals("left")) {
            newPosition = new Position(characterPosition.getX() - 1, characterPosition.getY());
        }
        if (direction.equals("right")) {
            newPosition = new Position(characterPosition.getX() + 1, characterPosition.getY());
        }
        if (direction.equals("up")) {
            newPosition = new Position(characterPosition.getX(), characterPosition.getY() + 1);
        }
        if (direction.equals("down")) {
            newPosition = new Position(characterPosition.getX(), characterPosition.getY() - 1);
        }
        return newPosition;
    }

    // returns true if the new position is a wall
    public boolean posIsWall(Position newPosition) {
        return TileChooser.wallDescriptions.contains(worldObject.tileInPosition(newPosition).description());
    }

    // returns true if the new position is a floor
    public boolean posIsFloor(Position newPosition) {
        return TileChooser.floorDescriptions.contains(worldObject.tileInPosition(newPosition).description());
    }

    public boolean posIsBee(Position newPosition) {
        for (Character monster: worldObject.MONSTERS) {
            if (monster.currentPosition.equals(newPosition)) {
                return true;
            }
        }
        return false;
    }
    // returns true if the new position is a nothing tile
    public boolean posIsNothing(Position newPosition) {
        return TileChooser.nothingDescriptions.contains(worldObject.tileInPosition(newPosition).description());
    }

    // return true if the new position is out of bounds
    public boolean isOut(Position newPosition) {
        return (newPosition.getX() >= World.WIDTH) || (newPosition.getY() >= World.HEIGHT) ||
                (newPosition.getX() < 0)  || (newPosition.getY() < 0);
    }

    // move character, update world omg u go to the gym thats so much work
    public void moveCharToNewPos(Character character, Position newPosition, String direction) {
        character.tile = checkForNewCharTile(character, newPosition, direction);
        worldObject.placeTile(character.tileUnderCharacter, character.currentPosition);
        character.tileUnderCharacter = worldObject.tileInPosition(newPosition);
        worldObject.placeTile(character.tile, newPosition);
        character.currentPosition = newPosition;
    }

    // checks if need to update character tile
    public TETile checkForNewCharTile(Character character, Position newPosition, String direction) {
        if (character.tile.description().equals("bee")) {
            return TileChooser.chooseMonster(direction);
        } else {
            if (worldObject.tileInPosition(newPosition).description().equals("water")) {
                return TileChooser.chooseAvatar(direction, "water");
            }
            return TileChooser.chooseAvatar(direction, "grass");
        }
    }
}
