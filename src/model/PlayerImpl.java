package model;

import java.util.ArrayList;

/**
 * The implementation of the player. This player when created has a location and treasure list that
 * will be filled as they move from cave to cave.
 */
public class PlayerImpl implements Player {
  private Location playerLocation;
  private ArrayList treasureList;

  public PlayerImpl(Location playerLocation, ArrayList treasureList) {
    this.playerLocation = playerLocation;
    this.treasureList = treasureList;
  }

  /**
   * The player moves north.
   */
  @Override
  public void moveNorth() {

  }

  /**
   * The player moves south.
   */
  @Override
  public void moveSouth() {

  }

  /**
   * The player moves east.
   */
  @Override
  public void moveEast() {

  }

  /**
   * The player moves west.
   */
  @Override
  public void moveWest() {

  }
}
