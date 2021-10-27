package model;

/**This represents a dungeon which is where the player navigates from a start point to an end point.
 *
 */
public interface Dungeon {


  /**This creates a dungeon that requires the specification of whether the dungeon should wrap or
   * not. How many rows and columns there should be specified as integers. The degree of
   * interconnectivity(default is 0) or how many paths between nodes should there be. An
   * interconnectivity of 0 means that there is exactly 1 path between all nodes. Each degree above
   * that is an additional edge/connection added to the map. Finally, what percentage of caves
   * should have treasure in it. The default is 20%. Caves are defined as having 1, 3, or 4
   * entrances. Tunnels only have 2 entrances and do not have treasure.
   *
   * @param wraps A boolean which determines if a dungeon wraps its edges around to the other side.
   * @param rows The number of rows in the dungeon as an integer.
   * @param columns The number of columns in the dungeon as an integer.
   * @param interconnect The level of interconnectivity expressed as an integer. Default is 0.
   * @param treasure The percentage of caves with treasure expressed as an integer. Default is 20.
   * @return The dungeon built to specification represented as a 2 dimensional array.
   */
  DungeonImpl makeDungeon(boolean wraps, int rows, int columns, int interconnect,
                      int treasure, DungeonImpl dungeon);
}
