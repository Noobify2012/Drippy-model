package driver;

import java.util.Scanner;

import javax.swing.*;

import model.Dungeon;
import model.DungeonImpl;
import model.Player;
import model.PlayerImpl;

public class Driver {

  public static void main(String[] args) {
    boolean startCond = false;
    boolean wraps = false;
    int rows = 0;
    int columns = 0;
    int interconnect = 0;
    int treasPer = 0;
    while (!startCond) {

      String welcomeString = "Welcome to the dungeon please enter true or false if you would like "
              + "the dungeon to wrap, \nthe number of rows you would like as an integer, the number of"
              +" columns as an integer, \nthe level of interconnectedness you would like as an integer,"
              + " \nand the percentage of caves that you would like to have treasure." +
              "\nbelow is an example \ntrue 10 10 0 10";

      Driver.printHelper(welcomeString);

      Scanner in = new Scanner(System.in);
      String input = in.nextLine();


      String inputChunks[] = new String[5];
      inputChunks = input.split(" ");

//      for (String s : inputChunks) {
//        Driver.printHelper(s);
//      }

//      for (int s = 0; s < 5; s++) {
//        Driver.printHelper("input chunks position " + s + " is " + inputChunks[s]);
//      }


      boolean wrapsBool = false;
      if (inputChunks[0].equalsIgnoreCase("false")
              || inputChunks[0].equalsIgnoreCase("true")) {
        if (inputChunks[0].equalsIgnoreCase("false")) {
          wraps = false;
        } else {
          wraps = true;
        }
        //Driver.printHelper(inputChunks[0]);
        //Driver.printHelper("Status of intputChunks" + inputChunks);
        wrapsBool = true;
      }

      boolean rowBool = false;
      if (Integer.parseInt(inputChunks[1]) > 0) {
        rowBool = true;
        rows = Integer.parseInt(inputChunks[1]);
      }

      boolean colBool = false;
      if (Integer.parseInt(inputChunks[2]) > 0) {
        colBool = true;
        rows = Integer.parseInt(inputChunks[2]);
      }

      boolean intBool = false;
      if (Integer.parseInt(inputChunks[3]) >= 0) {
        intBool = true;
        interconnect = Integer.parseInt(inputChunks[3]);
      }

      boolean treasBool = false;
      if (Integer.parseInt(inputChunks[4]) >= 0 && Integer.parseInt(inputChunks[4]) <= 100) {
        treasBool = true;
        interconnect = Integer.parseInt(inputChunks[4]);
      }

      if (wrapsBool == true && rowBool == true && colBool == true && intBool == true
              && treasBool == true) {
        startCond = true;
      }

    }




    Player player = new PlayerImpl();
    DungeonImpl test = new DungeonImpl(wraps, 5, 10, 0, 20, player);
    //System.out.print("\n" + player.getPlayerStatus());
    //int testInt = test.getPlayerStartPoint();

  }

  public static void printHelper(String printString) {
    System.out.println(printString);
  }
}
