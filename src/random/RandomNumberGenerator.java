package random;

import java.util.ArrayList;

/**
 * A class for generating random numbers and list of random numbers.
 */
public class RandomNumberGenerator {
  private int min;
  private int max;
  private int seed;
  private int listSize;


  /**A random number generator constructor that is called to help pick random numbers based on the
   * needs of different methods. A seed can be used to help predict the sequence of numbers but is
   * strictly for testing purposes.
   *
   * @param min An integer which represents the minimum value required for generating random
   *            numbers.
   * @param max An integer which represents the maximum value required for generating random
   *            numbers.
   * @param seed A seed value for helping produce consistent and repeatable numbers for testing.
   * @param listSize A list of integers which may be required for certain operations.
   */
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
      getRandomNumber();
    } else if (listSize > 1) {
      getRandomList(min, max, seed, listSize);
    }
  }

  /**Used to generate a single random number. Takes in a min and max from the constructor above.
   *
   * @return An integer between the minimum and maximum inclusively.
   */
  public int getRandomNumber() {
    if (this.seed == 0) {
      int returnInt = ((int) (Math.random() * ((this.max - this.min) + 1)) + this.min);
      return returnInt;
    } else {
      int returnInt = 0;
      return returnInt;
    }
  }

  /**Used to generate a list of psudo random integers.
   *
   * @param min the minimum integer value inclusive of the range of numbers to be selected.
   * @param max the maximum integer value inclusive of the range of numbers to be selected.
   * @param seed A seed value for helping produce consistent and repeatable numbers for testing.
   * @param listSize A list of integers which may be required for certain operations.
   * @return An array list of integers.
   */
  public ArrayList<Integer> getRandomList(int min, int max, int seed, int listSize) {
    ArrayList<Integer> numList = new ArrayList();
    for (int i = 0; i < listSize; i++) {
      //generate random number
      //add to list
    }
    return numList;
  }
}
