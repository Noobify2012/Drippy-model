package model;

import java.util.ArrayList;

public class Cave extends AbstractLocation {
  private int index;
  private int set;
  protected Cave(int row, int column, ArrayList entrances, ArrayList neighborList,
                 ArrayList treasureList, int index, int set) {
    super(new Point2D(row, column), entrances, neighborList, treasureList);
    this.index = index;
    this.set = set;
    if (entrances.size() == 2 && !treasureList.isEmpty()) {
      throw new IllegalStateException("Tunnels can not have treasure");
    }
  }


//  protected ArrayList getEntrances() {
//    return null;
//  }

  protected int getRow() {
    return location.getRow();
  }

  protected int getColumn() {
    return location.getColumn();
  }

  protected int getIndex() {
    return this.index;
  }

  protected int getSet() {
    return this.set;
  }

  protected void adjSet(int set) {
    this.set = set;
  }



}
