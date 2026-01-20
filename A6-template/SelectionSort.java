import java.util.Collections;
import java.util.ListIterator;

/**
 * Selection sorts a deck of cards
 */
public class SelectionSort {
  /**
   * Method to sort a list using selection sort
   * @param unsorted the unsorted list
   * @param record keeps track of frames
   * @return the sorted list
   */
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    
    // register the starting configuration with the recorder
    record.add(unsorted);

    // Here is the result list you will be creating
    CardPile sorted = new CardPile();
  
    // ***********************************************************
    // Here is where you'll do the "work" of SelectionSort:
    while (unsorted.size() > 0) {
      //Scan `unsorted` for the smallest remaining element.
      ListIterator<Card> pos = unsorted.listIterator();
      int i = 0;
      int smallest;
      smallest = i;
        while (pos.hasNext()) {
          Card currentCard = unsorted.get(i);
            if (currentCard.compareTo(unsorted.get(smallest)) > 0) {
              //current card is bigger
            } else {
              // current card is smaller
              smallest=  i;
            }
          i++;
          pos.next();
        }
      // move one card between piles CHECK THAT THIS APPENDS
      sorted.add(unsorted.remove(smallest));

      // register the new state with the recorder:
      record.next(); // tell it this is a new step
      record.add(sorted); // the allegedly sorted pile
      record.add(unsorted); // the unsorted pile
    }
    // ***********************************************************

    // return the sorted result here
    return sorted;
  }

  /** Starts the program running */
  public static void main(String args[]) {

    // set up a class to record and display the sorting results
    SortRecorder recorder = new SortRecorder();

    // set up the deck of cards
    Card.loadImages(recorder);
    CardPile cards = new CardPile(Card.newDeck(true), 2, 2);

    // for debugging purposes, uncomment this to
    // work with a smaller number of cards:
    // cards = cards.split(cards.get(39));

    // mix up the cards
    Collections.shuffle(cards);

    // in your program, this would be a call to a real sorting algorithm
    cards = SelectionSort.sort(cards, recorder);

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: Selection Sort");
  }
}