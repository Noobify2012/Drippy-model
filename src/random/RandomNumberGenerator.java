package random;

import java.util.ArrayList;

public class RandomNumberGenerator {
  private int min;
  private int max;
  private int seed;
  private int listSize;


  public RandomNumberGenerator(int min, int max, int seed, int listSize) {
    this.min = min;
    this.max = max;
    this.seed = seed;
    this.listSize = listSize;

    if (listSize <= 0) {
      throw new IllegalArgumentException("Cannot have a list of less than 1.");
    } else if (min >= max) {
      throw new IllegalArgumentException("The minimum value cannot be equal to or greater than the"
              + " maximum value");
    } else if (listSize == 1) {
      getRandomNumber(min, max, seed);
    } else if (listSize > 1) {
      getRandomList(min, max, seed, listSize);
    }
  }

  int getRandomNumber(int min, int max, int seed) {
    return 0;
  }

  ArrayList getRandomList(int min, int max, int seed, int listSize) {
    ArrayList numList = new ArrayList();
    for (int i = 0; i < listSize; i++) {
      //generate random number
      //add to list
    }
    return numList;
  }
}
