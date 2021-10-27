package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * The implementation of the Dungeon interface.
 */
public class DungeonImpl implements Dungeon {
  //private final Object Cave;
  //private final Object Location;
  private boolean wraps;
  private int rows;
  private int columns;
  private int interconnect;
  private int treasure;
  private Cave[][] Gameboard;

  public DungeonImpl(boolean wraps, int rows, int columns, int interconnect, int treasure) {
    //possible case for the builder pattern for this constructor using the make dungeon method
    // to abstract it
    //Cave = cave;
    //Location = location;
    this.wraps = false;
    this.rows = rows;
    this.columns = columns;
    this.interconnect = 0;
    this.treasure = 20;
    Cave[][] Gameboard = new Cave[rows][columns];
    ArrayList edgeList = new ArrayList();
    ArrayList potEdgeList = new ArrayList();



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

    if(wraps) {
      //add wrapping edges to kruskals
    } else {
      //no wrapping edges
    }

    int index = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        ArrayList entrances = new ArrayList();
        ArrayList neighborList = new ArrayList();
        ArrayList treasureList = new ArrayList();
        Cave cave = new Cave(r,c,entrances, neighborList, treasureList, index);

        Gameboard[r][c] = cave;
        if (!wraps) {
          if (c < columns - 1 && r < rows - 1) {
            edgeList.add((c + 1)+ "," + (r));
            System.out.print("\ncurrent index being added: " + index + " current column being added: "+ (c + 1)+"," + (r));
            edgeList.add((c)+","+(r + 1));
            System.out.print("\ncurrent index being added: " + index + " current row being added: "+ (c) +","+(r + 1));

          } else if (c == columns -1 && r == rows - 1) {
            //do nothing
          } else if (c == columns - 1 && r <= rows - 1) {
            edgeList.add( (c)+","+(r + 1));
            System.out.print("\ncurrent index being added1: " + index + " current row being added: "+ (r + 1));

          } else {
            edgeList.add( (c+1)+","+(r));
            System.out.print("\ncurrent index being added2: " + index + " current column being added: "+ (c + 1));
          }
        } else {
          if (c != columns - 1 && r != rows - 1) {
            edgeList.add(c + 1);
            edgeList.add(r + 1);
          } else if (c == columns - 1 && r != rows - 1) {
            edgeList.add(r + 1);
            edgeList.add(0);
          } else if (c != columns - 1 && r == rows - 1) {
            edgeList.add(c + 1);
            edgeList.add(0);
          }
        }
        System.out.print("\nCurrent row = " + r + " Current Column = " + c + " Current cave " + cave.getRow());
        index++;
      }
    }
    if (!wraps) {
      for (int r = 0; r < Gameboard.length; r++) {
        for (int c = 0; c < Gameboard.length; c++) {
          if (c < columns - 1 && r < rows - 1) {
            Edge edge = new Edge(Gameboard[c][r], Gameboard[c + 1][r]);
            potEdgeList.add(edge);
            Edge edge2 = new Edge(Gameboard[c][r], Gameboard[c][r + 1]);
            potEdgeList.add(edge2);
          } else if (c == columns - 1 && r == rows - 1) {
            //do nothing
          } else if (c == columns - 1 && r <= rows - 1) {
            Edge edge = new Edge(Gameboard[c][r], Gameboard[c][r + 1]);
            potEdgeList.add(edge);
          } else {
            Edge edge = new Edge(Gameboard[c][r], Gameboard[c + 1][r]);
            potEdgeList.add(edge);
          }
        }
      }
    } else {
      //figure out wrapping logic for finding edges
    }
    System.out.print("\nstatus of edgeList: " + edgeList.toString());
    System.out.print("\nstatus of potential edge list: " + potEdgeList);
  }

//  private ArrayList<Edge> addEdgesNonWrap() {
//    ArrayList<Edge> edgeList = new ArrayList<>();
//    for (int r = 0; r < Gameboard.length; r++) {
//      for (int c = 0; c < Gameboard.length; c++) {
//        if (c < columns - 1 && r < rows - 1) {
//          Edge edge = new Edge(Gameboard[c][r], Gameboard[c + 1][r]);
//          edgeList.add(edge);
//          Edge edge2 = new Edge(Gameboard[c][r], Gameboard[c][r + 1]);
//          edgeList.add(edge2);
//        } else if (c == columns -1 && r == rows - 1) {
//          //do nothing
//        } else if (c == columns - 1 && r <= rows - 1) {
//          Edge edge = new Edge(Gameboard[c][r], Gameboard[c][r + 1]);
//          edgeList.add(edge);
//        } else {
//          Edge edge = new Edge(Gameboard[c][r], Gameboard[c + 1][r]);
//          edgeList.add(edge);
//        }
//      }
//    }
//    return edgeList;
//  }



  private void makeGameBoard() {

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
  public DungeonImpl makeDungeon(boolean wraps, int rows, int columns, int interconnect, int treasure, DungeonImpl dungeon) {
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

    ArrayList edgeList = new ArrayList();
    int index = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        ArrayList entrances = new ArrayList();
        ArrayList neighborList = new ArrayList();
        ArrayList treasureList = new ArrayList();
        Cave cave = new Cave(r,c,entrances, neighborList, treasureList, index);
        //Gameboard[r][c] = cave;
//        if (r == 0 && c == 0) {
//          neighborList.add((int) r + 1);
//          neighborList.add((int) c + 1);
//        } else if (r == 0 && c == columns - 1) {
//          neighborList.add((int) r - 1);
//          neighborList.add((int) c + 1);
//        }
        System.out.print("\nCurrent row = " + r + " Current Column = " + c + " Current cave " + cave.getRow());
//        else if (c == 0
//        Cave cave = new Cave(r,c,entrances,neighborList,treasureList);
      }
    }
    return dungeon;
  }

  private void runKruscals(){

  }
}
