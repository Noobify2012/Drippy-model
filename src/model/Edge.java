package model;

class Edge {
  private Cave cave1;
  private Cave cave2;

  private Direction directionToCave1;
  private Direction directionToCave2;

  Edge(Cave cave1, Cave cave2) {
    this.cave1 = cave1;
    this.cave2 = cave2;
    this.directionToCave1 = directionToCave1;
    this.directionToCave2 = directionToCave2;

    if (cave1.getRow() - cave2.getRow() == 0 && cave1.getColumn() - cave2.getColumn() == -1) {
      directionToCave2 = Direction.EAST;
      directionToCave1 = Direction.WEST;
    } else if (cave1.getRow() - cave2.getRow() == 0 && cave1.getColumn() - cave2.getColumn() == 1) {
      directionToCave2 = Direction.WEST;
      directionToCave1 = Direction.EAST;
    } else if (cave1.getRow() - cave2.getRow() == -1 && cave1.getColumn()
            - cave2.getColumn() == 0) {
      directionToCave2 = Direction.SOUTH;
      directionToCave1 = Direction.NORTH;
    } else {
      directionToCave2 = Direction.NORTH;
      directionToCave1 = Direction.SOUTH;
    }
  }

  public Direction getDirection(Cave cave1, Cave cave2) {
    if (cave1 == this.cave1 && cave2 == this.cave2) {
      return directionToCave2;
    } else if (cave1 == this.cave2 && cave1 == this.cave1) {
      return directionToCave1;
    } else {
      throw new IllegalArgumentException(cave1 + " or " + cave2 + "is not associated with this edge");
    }
  }

  boolean compareSets() {
    if (this.cave1.getSet() == this.cave2.getSet()) {
      return true;
    } else {
      return false;
    }
  }

  int getLeftSet() {
    return this.cave1.getSet();
  }

  int getRightSet() {
    return this.cave2.getSet();
  }

  int getLeftIndex() {
    return this.cave1.getIndex();
  }

  int getRightIndex() {
    return this.cave2.getIndex();
  }

  void addNeighbors() {
    this.cave1.addNeighbor(this.getRightIndex());
    this.cave2.addNeighbor(this.getLeftIndex());
  }

  Direction getDirectionToCave1() {
    return this.directionToCave1;
  }

  Direction getDirectionToCave2() {
    return this.directionToCave2;
  }


  @Override
  public String toString() {
    String returnString = cave1.getIndex() + "<========>" + cave2.getIndex();
    return returnString;
  }
}
