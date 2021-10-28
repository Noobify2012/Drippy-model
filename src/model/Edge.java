package model;

class Edge {
  private Cave cave1;
  private Cave cave2;

  private Direction directionFromCave1;
  private Direction directionFromCave2;

  Edge(Cave cave1, Cave cave2) {
    this.cave1 = cave1;
    this.cave2 = cave2;
    //TODO - Possibly Remove
    this.directionFromCave1 = directionFromCave1;
    this.directionFromCave2 = directionFromCave2;
  }

  Direction getDirection(Cave cave) {
    if (cave == cave1) {
      return directionFromCave1;
    } else if (cave == cave2) {
      return directionFromCave2;
    } else {
      throw new IllegalArgumentException(cave + "is not associated with this edge");
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


  @Override
  public String toString() {
    String returnString = cave1.getIndex() + "<========>" + cave2.getIndex();
    return returnString;
  }
}
