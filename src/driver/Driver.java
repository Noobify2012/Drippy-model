package driver;

import model.Dungeon;
import model.DungeonImpl;

public class Driver {

  public static void main(String[] args) {
    System.out.print("This is my main method");
    DungeonImpl test = new DungeonImpl(false, 3, 3, 0, 20);
    test.getDungeon();
//    DungeonImpl test2 = new DungeonImpl(false, 4,4,0,20);
//    test2.getDungeon();

  }
}
