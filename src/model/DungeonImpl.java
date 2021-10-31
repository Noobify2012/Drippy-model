package model;

import java.util.ArrayList;
import java.util.Random;
import random.RandomNumberGenerator;

/**
 * The implementation of the Dungeon interface.
 */
public class DungeonImpl implements Dungeon {
  private boolean wraps;
  private int rows;
  private int columns;
  private int interconnect;
  private int treasure;
  private Cave[][] Gameboard;
  private ArrayList<Edge> potEdgeList;
  private ArrayList<Edge> leftOverEdge;
  private ArrayList<Edge> finalEdgeList;
  private int startPoint;
  private Player player;

  /**This creates a dungeon that requires the specification of whether the dungeon should wrap or
   * not. How many rows and columns there should be specified as integers. The degree of
   * interconnectivity(default is 0) or how many paths between nodes should there be. An
   * interconnectivity of 0 means that there is exactly 1 path between all nodes. Each degree above
   * that is an additional edge/connection added to the map. Finally, what percentage of caves
   * should have treasure in it. The default is 20%. Caves are defined as having 1, 3, or 4
   * entrances. Tunnels only have 2 entrances and do not have treasure.
   Params:
   wraps – A boolean which determines if a dungeon wraps its edges around to the other side.
   rows – The number of rows in the dungeon as an integer.
   columns – The number of columns in the dungeon as an integer.
   interconnect – The level of interconnectivity expressed as an integer. Default is 0.
   treasure – The percentage of caves with treasure expressed as an integer. Default is 20.
   Returns:
   The dungeon built to specification represented as a 2 dimensional array.**/
  public DungeonImpl(boolean wraps, int rows, int columns, int interconnect, int treasure, Player player) {
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
    this.startPoint = 0;
    this.player = player;




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



  void getDungeon() {
    //runs Kruscals, adds interconnectivity
    runKruscals();
    //generates a start point by index
    this.startPoint = getStartPoint(getCavesByIndex());
    //finds a viable end point
    int endPoint = findEndPoint(this.startPoint);
    //finds caves and adds Treasure
    findCaves(getCavesByIndex());

    setUpPlayer();

  }

  private void setUpPlayer() {
    //place the player in the dungeon at the cave index
    player.enterDungeon(this.startPoint, findCaveByIndex(this.startPoint).getTreasureList(),
            getPossibleDirection(this.startPoint));

  }

  private ArrayList<Direction> getPossibleDirection(int index) {
    ArrayList<Direction> tempArray = new ArrayList<>();
    for (int i = 0; i < finalEdgeList.size(); i++) {
      if (finalEdgeList.get(i).getLeftIndex() == index) {
        if (!tempArray.contains(finalEdgeList.get(i).getDirectionToCave2())) {
          tempArray.add(finalEdgeList.get(i).getDirectionToCave2());
        }
      } else if (finalEdgeList.get(i).getRightIndex() == index) {
        if (!tempArray.contains(finalEdgeList.get(i).getDirectionToCave1())) {
          tempArray.add(finalEdgeList.get(i).getDirectionToCave1());
        }
      }
    }
    return tempArray;
  }

  private int getStartPoint(ArrayList<Integer> caves) {

    RandomNumberGenerator rand = new RandomNumberGenerator(0, caves.size() - 1, 0,
            1);
    System.out.print("\nMax index is: " + (rows * columns - 1));
    int startIndex = caves.get(rand.getRandomNumber());
    System.out.print("\nStarting point is index: " + startIndex);
    return startIndex;
  }

  private int findEndPoint(int startIndex) {
    ArrayList<Integer> nonViable = new ArrayList<>();
    ArrayList<Integer> viable = new ArrayList<>();
    ArrayList<Integer> allCaves = getCavesByIndex();
    ArrayList<Integer> listToCheck = new ArrayList<>();
    int endPoint = 0;

    //add start index to list of things to check and those that can't be an end point
    nonViable.add(startIndex);
    listToCheck.add(startIndex);
    //check for incomplete graph
    if (findCaveByIndex(startIndex).getNeighbors().size() != 0) {
//      for (int z = 0; z < 3; z++) {
      //loop throught the list of neighbors of the start index
      for (int i = 0; i < findCaveByIndex(startIndex).getNeighbors().size(); i++) {
        //check that we already haven't seen the current node
        if (!(nonViable.contains(findCaveByIndex(startIndex).getNeighbors().get(i)))) {
          System.out.print("\nAdding this index to nonviable and list to check: "
                  + (int) findCaveByIndex(startIndex).getNeighbors().get(i));
          //add current neighbor node to list of indexes to check and nonviable
          nonViable.add((int) findCaveByIndex(startIndex).getNeighbors().get(i));
          listToCheck.add((int) findCaveByIndex(startIndex).getNeighbors().get(i));
          System.out.print("\nNon-viable indexes: " + nonViable.toString());
          System.out.print("\nList to Check indexes: " + listToCheck.toString());

        }
      }
      //remove node we just checked from list of nodes to check
      System.out.print("\nNon-viable indexes after 1 iteration: " + nonViable.toString());
      listToCheck.remove(0);
      System.out.print("\nList to check indexes after removing first item: "
              + listToCheck.toString());
      //loop 3 times
      for (int y = 0; y < 3; y++) {
        //list size at time of iteration, not to keep looping through more stuff
        int temp = listToCheck.size();
        for (int c = 0; c < temp; c++) {
          //grab next value in list to check
          int tempInt = listToCheck.get(c);
          ArrayList<Integer> tempList = findCaveByIndex(tempInt).getNeighbors();
          //checks to see if next element down has children
          if (tempList.size() > 1) {
            //if it does have children add them to the lists
            for (int a = 0; a < tempList.size(); a++) {
              if (!(nonViable.contains((int) findCaveByIndex(tempInt).getNeighbors().get(a)))) {
                nonViable.add((int) findCaveByIndex(tempInt).getNeighbors().get(a));
                listToCheck.add((int) findCaveByIndex(tempInt).getNeighbors().get(a));
              }
            }
          }
          //add current neighbor node to list of indexes to check and nonviable
          System.out.print("\nNon-viable indexes after add: " + nonViable.toString());
          System.out.print("\nList to Check indexes after add: " + listToCheck.toString());
        }
        for (int r = 0; r < temp - 1; r++) {
          //TODO - gets index out of bounds, why?
          System.out.print("Trying to remove index: " + r + " from list to check: " + listToCheck);
          listToCheck.remove(r);
        }
        System.out.print("\nNon-viable indexes after remove: " + nonViable.toString());
        System.out.print("\nList to Check indexes after remove: " + listToCheck.toString());
      }

      System.out.print("\nNon-viable indexes after final: " + nonViable.toString());
      System.out.print("\nList to Check indexes after final: " + listToCheck.toString());
    } else {
      throw new IllegalStateException("Start Node has no neighbors.");
    }
    //compare list of non-viable vs total list
    if (nonViable.size() == (rows * columns)) {
      throw new IllegalStateException("There are no viable end points, try increasing the size of "
              + "your dungeon");
    } else {
      for (int t = 0; t < allCaves.size(); t++) {
        if (!nonViable.contains(allCaves.get(t))) {
          viable.add(allCaves.get(t));
          System.out.print("\nViable end points: " + viable.toString());
        }
      }
      if (viable.size() != 1) {
        RandomNumberGenerator rand = new RandomNumberGenerator(0, viable.size() - 1 , 0, 1);
        int endRand = rand.getRandomNumber();
        endPoint = viable.get(endRand);
      } else {
        endPoint = viable.get(0);
      }
      System.out.print("\nThe end point is: " + endPoint);
    }
    return endPoint;
  }

  private void findCaves(ArrayList<Integer> caves) {
    int treasureInt = 0;
    //make list of caves, exclude tunnels
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (Gameboard[r][c].getNeighbors().size() != 2
                && !caves.contains(Gameboard[r][c].getIndex())) {
          caves.add(Gameboard[r][c].getIndex());
        }
      }
    }
    System.out.print("\nList of Caves: " + caves);
    //calculate how many caves require treasure
    if (this.treasure != 0) {
      int treasCaveNum = (int) Math.ceil((caves.size() * treasure) / 100);
      if (treasCaveNum == 0) {
        treasCaveNum++;
      }
      System.out.print("\nNumber of caves that need treasure: " + treasCaveNum);
      RandomNumberGenerator rand = new RandomNumberGenerator(0, caves.size() - 1,
              0, 1);
      RandomNumberGenerator rand2 = new RandomNumberGenerator(0, 2, 0, 1);
      TreasureImpl.TreasureFactory treasureFactory = new TreasureImpl.TreasureFactory();
      for (int t = 0; t < treasCaveNum; t++) {
        int treasureRand = rand2.getRandomNumber();
        if (treasureRand == 0 ) {
          for(int r = 0; r <= treasureRand + 1; r++) {
            findCaveByIndex(caves.get(rand.getRandomNumber())).addTreasure(TreasureImpl
                    .TreasureFactory.getTreasureFromEnum(TreasureImpl.TreasureType.RUBY));
          }
        } else if (treasureRand == 1 ) {
          for (int r = 0; r <= treasureRand + 1; r++) {
            findCaveByIndex(caves.get(rand.getRandomNumber())).addTreasure(TreasureImpl
                    .TreasureFactory.getTreasureFromEnum(TreasureImpl.TreasureType.DIAMOND));
          }
        } else {
          for (int r = 0; r <= treasureRand + 1; r++) {
            findCaveByIndex(caves.get(rand.getRandomNumber())).addTreasure(TreasureImpl
                    .TreasureFactory.getTreasureFromEnum(TreasureImpl.TreasureType.SAPPHIRE));
          }
        }


//        Treasure a = TreasureEnum.RUBY.createTreasure();
      }
    }

  }

  private Cave findCaveByIndex(int index) {
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
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

  private ArrayList<Integer> getCavesByIndex() {
    ArrayList<Integer> caves = new ArrayList<>();
    //make list of caves, exclude tunnels
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < columns; c++) {
        if (Gameboard[r][c].getNeighbors().size() != 2) {
          caves.add(Gameboard[r][c].getIndex());
        }
      }
    }
    return caves;
  }


  private void runKruscals() {
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

  @Override
  public int getPlayerStartPoint() {
    return this.startPoint;
  }
}
