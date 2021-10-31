package driver;

import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;

public class Driver {

  public static void main(String[] args) {
    System.out.print("This is my main method");
    Player player = new PlayerImpl();
    DungeonImpl test = new DungeonImpl(true, 4, 3, 0, 20, player);
    System.out.print("\n" + player.getPlayerStatus());

    int testInt = test.getPlayerStartPoint();
    //test.getDungeon();
//    DungeonImpl test2 = new DungeonImpl(false, 4,4,0,20);
//    test2.getDungeon();

  }
}
