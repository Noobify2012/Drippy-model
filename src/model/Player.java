package model;

import java.util.ArrayList;

/**
 * Represents a player and all of the associated actions.
 */
public interface Player {

  /**
   * The player moves north.
   */
  void moveNorth();

  /**
   * The player moves south.
   */
  void moveSouth();

  /**
   * The player moves east.
   */
  void moveEast(int index, ArrayList<Direction> directions,
                ArrayList<Treasure> curTreasure);

  /**
   * The player moves west.
   */
  void moveWest();

  /**This builds and returns the player's status which includes, the index of the cave they are
   * currently in, the treasure the player has collected so far, the moves the player can make based
   * on their current location, and the treasure in the cave they are currently in.
   *
   * @return the string containing all the pertinent player and cave information.
   */
  void getPlayerStatus();

  /**This updates the players location based on the index of the start point, the treasure in the
   * cave that the player enters, and the directions the player can go from that start location.
   *
   * @param caveIndex the index of the cave that the player enters
   * @param treasureInCave the treasure in the cave where the player enters the dungeon.
   * @param possibleDirection the directions the player can go from the start point.
   */
  void enterDungeon(int caveIndex, ArrayList<Treasure> treasureInCave,
                    ArrayList<Direction> possibleDirection);
}
