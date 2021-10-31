package model;

//idea for this came from https://connected2know.com/programming/java-factory-pattern/

/**
 * The implementation of treasure with a treasure enum built in and also implements a factory
 * method.
 */
public class TreasureImpl {
  enum TreasureType {
    RUBY, DIAMOND, SAPPHIRE
  }

  static class Ruby implements Treasure {
    @Override
    public Treasure getTreasure() {
      return new Ruby();
    }
  }

  static class Diamond implements Treasure {
    @Override
    public Treasure getTreasure() {
      return new Diamond();
    }
  }

  static class Sapphire implements Treasure {
    @Override
    public Treasure getTreasure() {
      return new Sapphire();
    }
  }

  static class TreasureFactory {
    public static Treasure getTreasureFromEnum(TreasureType treasureType) {

      Treasure treasure = null;

      if (TreasureType.RUBY.equals(treasureType)) {
        treasure = new Ruby();
      } else if (TreasureType.DIAMOND.equals(treasureType)) {
        treasure = new Diamond();
      } else if (TreasureType.SAPPHIRE.equals(treasureType)) {
        treasure = new Sapphire();
      }
      return treasure;
    }
  }

}
