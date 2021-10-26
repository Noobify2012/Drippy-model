package model;


public class Point2D {
  private int row;
  private int column;

  /**
   * Construct a 2d point with the given coordinates.
   *
   * @param row the x-coordinate of this point
   * @param column the y-coordinate of this point
   */
  public Point2D(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public int getRow() {
    return row;
  }

  /**
   * Return the y-coordinate of this point.
   *
   * @return y-coordinate of this point
   */
  public int getColumn() {
    return column;
  }
}