package model;

public enum TreasureEnum {
  RUBY {
    @Override
    Treasure createTreasure() {
      return new RUBY();
    }
  },
  DIAMOND {
    @Override
    Treasure createTreasure() {
      return new DIAMOND();
    }
  },
  SAPPHIRE {
    @Override
    Treasure createTreasure() {
      return new SAPPHIRE();
    }
  },
  ;
  abstract Treasure createTreasure();
}
