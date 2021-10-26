package model;

import java.util.ArrayList;

public abstract class AbstractLocation implements Location {
  private Location location;
  private ArrayList entrances;
  private ArrayList neighborList;
  private ArrayList treasureList;

  protected AbstractLocation(Location location, ArrayList entrances, ArrayList neighborList,
                             ArrayList treasureList) {
    this.location = location;
    this.entrances = entrances;
    this.neighborList = neighborList;
    this.treasureList = treasureList;
  }

  private void setLocation() {
  }
}
