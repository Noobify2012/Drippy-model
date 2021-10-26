package model;

import java.util.ArrayList;

public class Cave extends AbstractLocation {
  protected Cave(Location location, ArrayList entrances, ArrayList neighborList,
                 ArrayList treasureList) {
    super(location, entrances, neighborList, treasureList);
    if (entrances.size() == 2 && !treasureList.isEmpty()) {
      throw new IllegalStateException("Tunnels can not have treasure");
    }
  }

  @Override
  public Location getLocation() {
    return null;
  }
}
