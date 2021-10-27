package model;

class Edge {
  private Cave cave1;
  private Cave cave2;
  private boolean active;
  private Direction directionFromCave1;
  private Direction directionFromCave2;

  Edge(Cave cave1, Cave cave2) {
    this.cave1 = cave1;
    this.cave2 = cave2;
    //TODO - Possibly Remove
    this.active = false;
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

  void setActive() {
    this.active = true;
  }

  boolean checkActive(){
    return this.active;
  }
}
