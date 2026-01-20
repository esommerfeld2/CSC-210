import java.util.Collections;
import java.util.ListIterator;

/**
 * Insertion sorts a deck of cards
 */
public class InsertionSort {
  /**
   * Method to sort a list using insertion sort
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
      if(sorted.isEmpty()){
        sorted.add(unsorted.remove());
        // register the new state with the recorder:
        record.next(); // tell it this is a new step
        record.add(sorted); // the allegedly sorted pile
        record.add(unsorted); // the unsorted pile
        continue;
      }
      ListIterator<Card> pos = sorted.listIterator();
      int i = 0;
      Card currentCard = unsorted.remove();
      boolean inserted = false;
      while (pos.hasNext()) {
        if (currentCard.compareTo(sorted.get(i)) > 0) {
          //current card is bigger than sorted i
        }else {
          // current card is smaller
          sorted.insertBefore(currentCard, sorted.get(i));
          inserted = true;
          break;
        }
        i++;
        pos.next();
      }
      if(!inserted){
        sorted.add(currentCard);
      }

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
    cards = InsertionSort.sort(cards, recorder);

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: Insertion Sort");
  }
}
