package driver;

import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;

public class Driver {

  public static void main(String[] args) {
    Player player = new PlayerImpl();
    DungeonImpl test = new DungeonImpl(false, 5, 10, 0, 20, player);
    //System.out.print("\n" + player.getPlayerStatus());
    //int testInt = test.getPlayerStartPoint();

  }

  public static void printHelper(String printString) {
    System.out.println(printString);
  }
}
