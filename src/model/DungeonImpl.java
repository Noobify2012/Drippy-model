package model;

import java.util.ArrayList;
import java.util.Random;
import random.RandomNumberGenerator;

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
  private ArrayList<Edge> potEdgeList;
  private ArrayList<Edge> leftOverEdge;
  private ArrayList<Edge> finalEdgeList;

  public DungeonImpl(boolean wraps, int rows, int columns, int interconnect, int treasure) {
    //possible case for the builder pattern for this constructor using the make dungeon method
    // to abstract it
    //Cave = cave;
    //Location = location;
    Cave[][] Gameboard = new Cave[rows][columns];
    ArrayList edgeList = new ArrayList();
    ArrayList<Edge> potEdgeList = new ArrayList();
    ArrayList<Edge> leftOverEdge = new ArrayList<>();
    ArrayList<Edge> finalEdgeList = new ArrayList<>();

    this.wraps = false;
    this.rows = rows;
    this.columns = columns;
    this.interconnect = interconnect;
    this.treasure = treasure;
    this.Gameboard = Gameboard;
    this.potEdgeList = potEdgeList;
    this.leftOverEdge = leftOverEdge;
    this.finalEdgeList = finalEdgeList;




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

    if (wraps) {
      //add wrapping edges to kruskals
    } else {
      //no wrapping edges
    }

    if (interconnect > 0 && !wraps) {
      //forumla derived by Madhira Datta
      int maxEdges = 2 * rows * columns - rows - columns;
      if (interconnect > maxEdges -  (rows  * columns - 1)) {
          throw new IllegalArgumentException("Interconnectivity too high, beyond number of edges in"
                + " graph.");
      }
    } else if (interconnect > 0 && wraps) {
      //forumla derived by Madhira Datta
      int maxEdges = 2 * rows * columns;
      if (interconnect > maxEdges) {
        throw new IllegalArgumentException("Interconnectivity too high, beyond number of edges in"
                + " graph.");
      }
    }

    //construct caves
    int index = 0;
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        ArrayList entrances = new ArrayList();
        ArrayList neighborList = new ArrayList();
        ArrayList treasureList = new ArrayList();
        Cave cave = new Cave(r, c, entrances, neighborList, treasureList, index, index);
        Gameboard[r][c] = cave;
        System.out.print("\nCurrent row = " + r + " Current Column = " + c + " Current cave "
                + cave.getIndex());
        index++;
      }
    }
    // build edges
    if (!wraps) {
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < columns; c++) {
          //case for nodes that aren't on far edge
          if (c < columns - 1 && r < rows - 1) {
            Edge edge = new Edge(Gameboard[r][c], Gameboard[r + 1][c]);
            potEdgeList.add(edge);
            System.out.print("\nadded edge 1 " + edge.toString());
            Edge edge2 = new Edge(Gameboard[r][c], Gameboard[r][c + 1]);
            potEdgeList.add(edge2);
            System.out.print("\nadded edge 2 " + edge2.toString());
            //bottom right hand corner, opposite origin
          } else if (c == columns - 1 && r == rows - 1) {
            //do nothing
            //max column, co
          } else if (c == columns - 1 && r <= rows - 1) {
            Edge edge = new Edge(Gameboard[r][c], Gameboard[r + 1][c]);
            potEdgeList.add(edge);
            System.out.print("\nadded edge 3 " + edge.toString());
          } else {
            Edge edge = new Edge(Gameboard[r][c], Gameboard[r][c + 1]);
            potEdgeList.add(edge);
            System.out.print("\nadded edge 4 " + edge.toString());
          }
        }
      }
    } else {
      //figure out wrapping logic for finding edges
      for (int r = 0; r < rows; r++) {
        for (int c = 0; c < columns; c++) {
          //case: not an edge node, add edge right, add edge down
          if (c < columns - 1 && r < rows - 1) {
            Edge edge = new Edge(Gameboard[r][c], Gameboard[r + 1][c]);
            potEdgeList.add(edge);
            System.out.print("\n0Edge added between caves:" + edge.toString());
            Edge edge2 = new Edge(Gameboard[r][c], Gameboard[r][c + 1]);
            potEdgeList.add(edge2);
            System.out.print("\n1Edge added between caves:" + edge2.toString());
            //case: bottom right edge, wrap right, wrap down
          } else if (c == columns - 1 && r == rows - 1) {
            Edge edge = new Edge(Gameboard[r][c], Gameboard[0][c]);
            potEdgeList.add(edge);
            System.out.print("\n2Edge added between caves:" + edge.toString());
            Edge edge2 = new Edge(Gameboard[r][c], Gameboard[r][0]);
            potEdgeList.add(edge2);
            System.out.print("\n3Edge added between caves:" + edge2.toString());

            //case: right edge, not bottom
          } else if (c == columns - 1 && r <= rows - 1) {
            Edge edge = new Edge(Gameboard[r][c], Gameboard[r + 1][c]);
            potEdgeList.add(edge);
            System.out.print("\n4Edge added between caves:" + edge.toString());
            Edge edge2 = new Edge(Gameboard[r][c], Gameboard[r][0]);
            potEdgeList.add(edge2);
            System.out.print("\n5Edge added between caves:" + edge2.toString());
          } else {
            Edge edge = new Edge(Gameboard[r][c], Gameboard[r][c + 1]);
            potEdgeList.add(edge);
            System.out.print("\n6Edge added between caves:" + edge.toString());
            Edge edge2 = new Edge(Gameboard[r][c], Gameboard[0][c]);
            potEdgeList.add(edge2);
            System.out.print("\n7Edge added between caves:" + edge2.toString());
          }
        }
      }
    }
    //System.out.print("\nstatus of edgeList: " + edgeList.toString());
    System.out.print("\nstatus of potential edge list: " + potEdgeList.toString());

    getDungeon();
  }



  public void getDungeon() {
    //runs Kruscals, adds interconnectivity
    runKruscals(this.getGameBoard(), this.interconnect);
    //generates a start point by index
    getStartPoint();
    //finds a viable end point
    findEndPoint();
    //finds caves for adding treasure
    //findCaves();

  }

  private void getStartPoint() {
    RandomNumberGenerator rand = new RandomNumberGenerator(0, rows * columns - 1, 0,
            1);
    System.out.print("\nMax index is: " + (rows * columns - 1));
    int startIndex = rand.getRandomNumber();
    System.out.print("\nStarting point is index: " + startIndex);
  }

  private void findEndPoint() {
    //TODO- figure this out
    //find a valid end point
  }

  private void findCaves() {
    ArrayList<Integer> caves = new ArrayList<>();
    int treasureInt = 0;
    //make list of caves, exclude tunnels
    for (int r = 0; r < Gameboard.length; r++) {
      for (int c = 0; c < Gameboard.length; c++) {
        if (Gameboard[r][c].getNeighbors().size() != 2) {
          caves.add(Gameboard[r][c].getIndex());
        }
      }
    }
    System.out.print("\nList of Caves: " + caves);
    //calculate how many caves require treasure
    if (this.treasure != 0) {
      int treasCaveNum = (int) Math.ceil((caves.size() * treasure) / 100);
      RandomNumberGenerator rand = new RandomNumberGenerator(0, caves.size() - 1, 0, 1);
      for (int t = 0; t < treasCaveNum; t++) {
        caves.get(rand.getRandomNumber());
//        Treasure a = TreasureEnum.RUBY.createTreasure();
      }
    }

  }

  private Cave findCaveByIndex(int index) {
    for (int r = 0; r < Gameboard.length; r++) {
      for (int c = 0; c < Gameboard.length; c++) {
        if (Gameboard[r][c].getIndex() == index) {
          return Gameboard[r][c];
        }
      }
    }
    throw new IllegalArgumentException("couldn't find cave index of " + index);
  }

  private Cave[][] getGameBoard() {
    return this.Gameboard;
  }

  private ArrayList<Edge> getPotEdgeList() {
    return this.potEdgeList;
  }

  private ArrayList<Edge> getLeftOverEdge() {
    return this.getLeftOverEdge();
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
  public DungeonImpl makeDungeon(boolean wraps, int rows, int columns, int interconnect,
                                 int treasure, DungeonImpl dungeon) {
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
        Cave cave = new Cave(r, c, entrances, neighborList, treasureList, index, index);
        //Gameboard[r][c] = cave;
//        if (r == 0 && c == 0) {
//          neighborList.add((int) r + 1);
//          neighborList.add((int) c + 1);
//        } else if (r == 0 && c == columns - 1) {
//          neighborList.add((int) r - 1);
//          neighborList.add((int) c + 1);
//        }
        System.out.print("\nCurrent row = " + r + " Current Column = " + c + " Current cave "
                + cave.getRow());
//        else if (c == 0
//        Cave cave = new Cave(r,c,entrances,neighborList,treasureList);
      }
    }
    return dungeon;
  }

  private void runKruscals(Cave[][] gameboard, int interconnect) {
    //start condition - every cave in own set
    RandomNumberGenerator rand = new RandomNumberGenerator(0, this.getPotEdgeList().size(), 0, 1);
    Random randGen = new Random(rand.getRandomNumber());
    boolean exitCond = false;
    ArrayList<Integer> setList = new ArrayList<>();
    for (int s = 0; s < rows * columns; s++) {
      setList.add(s);
    }
    if (setList.size() - 1 != Gameboard[rows - 1][columns - 1].getIndex()) {
      throw new IllegalArgumentException("the set list doesn't match the number of elements");
    }
    while (!exitCond) {
      // grab random edge
      int random = randGen.nextInt(this.getPotEdgeList().size());
      System.out.print("\nCurrent size of potEdgeList: " + this.getPotEdgeList().size());

      //if they are in the same set check to see if this edge has already been called,
      // if not add to left over list
      if (this.potEdgeList.get(random).compareSets()) {
        if (!this.leftOverEdge.contains(this.potEdgeList.get(random))) {
          this.leftOverEdge.add(this.potEdgeList.get(random));
        }
      } else {
        //if not in the same set
        //add edge to final set
        this.potEdgeList.get(random).addNeighbors();
        finalEdgeList.add(this.potEdgeList.get(random));

        // save set number of right cave
        int tempint = this.potEdgeList.get(random).getRightSet();
        int newSetNum = this.potEdgeList.get(random).getLeftSet();
        //remove from potential edge list
        this.potEdgeList.remove(random);
        //loop through all members of that set and set to left set value
        for (int r = 0; r < rows; r++) {
          for (int c = 0; c < columns; c++) {
            if (Gameboard[r][c].getSet() == tempint) {
              Gameboard[r][c].adjSet(newSetNum);
            }
          }
        }
        //remove setnum from setList
        if (setList.contains(tempint)) {
          int removeInt = setList.indexOf(tempint);
          setList.remove(setList.indexOf(tempint));
          System.out.print("\nneed to remove: " + tempint + " the max index is: " + setList.size());
        } else {
          System.out.print("\ncouldn't remove: " + tempint);
        }

        //check for single set
        if (setList.size() == 1 && interconnect == 0) {
          exitCond = true;
          System.out.print("\nmade it to single set");
          System.out.print("status of final edge list: " + finalEdgeList.toString());
        } else if (setList.size() == 1 && interconnect > 0) {
          //dump edges into single list
          for (int l = 0; l < this.potEdgeList.size(); l++) {
            if (!this.leftOverEdge.contains(this.potEdgeList.get(l))) {
              this.leftOverEdge.add(this.potEdgeList.get(l));
            }
          }
          for (int j = 0; j < interconnect; j++) {
            if (leftOverEdge.size() <= 0) {
                throw new IllegalStateException("Left over edge list is already empty");
            } else {
              int randomInt = randGen.nextInt(leftOverEdge.size());
              finalEdgeList.add(leftOverEdge.get(randomInt));
              leftOverEdge.get(randomInt).addNeighbors();
              leftOverEdge.remove(randomInt);
            }

          }
          exitCond = true;
          System.out.print("\nmade it to single set and added interconnectivity");
          System.out.print("\nstatus of final edge list: " + finalEdgeList.toString());
        }

      }
    }
    System.out.print("Kruskals complete");
  }
}
