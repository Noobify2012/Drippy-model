package model;

import java.lang.reflect.Array;

/**
 * The implementation of the Dungeon interface.
 */
public class DungeonImpl implements Dungeon {
  //private final Object Location;
  private boolean wraps;
  private int rows;
  private int columns;
  private int interconnect;
  private int treasure;
  //private Array[][] Gameboard;

  DungeonImpl(Object location, boolean wraps, int rows, int columns, int interconnect,
              int treasure) {
    //Location = location;
    this.wraps = wraps;
    this.rows = rows;
    this.columns = columns;
    this.interconnect = interconnect;
    this.treasure = treasure;
    //Location[rows][columns] = Gameboard;

    if (rows < 1 || columns < 1) {
      throw new IllegalArgumentException("Rows or Columns cannot be less than 1.");
    } else if (rows == 1 && columns < 6 || rows < 6 && columns == 1) {
      throw new IllegalArgumentException("You must have at least 6 rows or columns if the other "
              + "is 1.");
    } else if (rows == 2 && columns < 3 || rows < 3 && columns == 2) {
      throw new IllegalArgumentException("You must have at least 6 nodes in the graph.");
    }

    if (treasure < 20) {
      throw new IllegalArgumentException("You must have at least 20% treasure.");
    }

    if (interconnect < 0) {
      throw new IllegalArgumentException("The interconnectivity cannot be less than 0");
    }

  }

  /**
   * This creates a dungeon that requires the specification of whether the dungeon should wrap or
   * not. How many rows and columns there should be specified as integers. The degree of
   * interconnectivity(default is 0) or how many paths between nodes should there be. An
   * interconnectivity of 0 means that there is exactly 1 path between all nodes. Each degree above
   * that is an additional edge/connection added to the map. Finally, what percentage of caves
   * should have treasure in it. The default is 20%. Caves are defined as having 1, 3, or 4
   * entrances. Tunnels only have 2 entrances and do not have treasure.
   *
   * @param wraps        A boolean which determines if a dungeon wraps its edges around to the other
   *                     side.
   * @param rows         The number of rows in the dungeon as an integer.
   * @param columns      The number of columns in the dungeon as an integer.
   * @param interconnect The level of interconnectivity expressed as an integer. Default is 0.
   * @param treasure     The percentage of caves with treasure expressed as an integer. Default is
   *                     20.
   * @return The dungeon built to specification represented as a 2 dimensional array.
   */
  @Override
  public Dungeon makeDungeon(boolean wraps, int rows, int columns, int interconnect, int treasure) {
    return null;
  }
}
