package model;

import java.util.ArrayList;

public class Cave extends AbstractLocation {
  protected Cave(int row, int column, ArrayList entrances, ArrayList neighborList,
                 ArrayList treasureList) {
    super(new Point2D(row, column), entrances, neighborList, treasureList);
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


}
