package model;

enum Direction {
  NORTH (-1,0),
  SOUTH (1,0),
  EAST (0,1),
  WEST (0,-1);

  private final int xAdj;
  private final int yAdj;

  Direction(int xAdj, int yAdj) {
    this.xAdj = xAdj;
    this.yAdj = yAdj;
  }
}
