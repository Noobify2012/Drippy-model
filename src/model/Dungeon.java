package model;

import java.util.ArrayList;

/**This represents a dungeon which is where the player navigates from a start point to an end point.
 *
 */
public interface Dungeon {

  int getGameBoardRows();

  int getGameBoardCols();

  void getDungeon();

  ArrayList<Edge> getFinalEdgeList();

  ArrayList<Integer> getFinalPath();

  Cave[][] getGameBoard();

}
