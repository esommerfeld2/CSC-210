import java.util.ArrayDeque;
import java.util.Collections;
/**
 * Merge sorts a deck of cards
 */
public class MergeSort {
  
  /**
   * Method to sort a list using merge sort
   * @param unsorted the unsorted list
   * @param record keeps track of frames
   * @return the sorted list
   */
  public static CardPile sort(CardPile unsorted, SortRecorder record) {
    
    ArrayDeque<CardPile> queue = new ArrayDeque<CardPile>();
  
    // ***********************************************************
    // Here is where you'll do the "work" of MergeSort:
    while (unsorted.size() > 0) {
      CardPile newPile = new CardPile();
      newPile.add(unsorted.remove());
      queue.add(newPile);
    }

    while(queue.size() > 1){
      CardPile pileA = queue.remove();
      CardPile pileB = queue.remove();
      CardPile pileFinal = merge(pileA, pileB);
      queue.add(pileFinal);
      record.next();        // tell it this is a new step
      for (CardPile pile: queue) { // add all piles
        record.add(pile);
      }
    }
   
    // ***********************************************************

    // return the sorted result here
    return queue.remove();
  }

  /**
   * Method to merge two card piles
   * @param a the first pile to merge
   * @param b the second pile to merge
   * @return the merged Card Pile
   */

  public static CardPile merge(CardPile a, CardPile b){
    CardPile pileFinal = new CardPile();
    while(!a.isEmpty() || !b.isEmpty()){
      if(a.isEmpty()){
        pileFinal.append(b);
        break;
      }
      if(b.isEmpty()){
        pileFinal.append(a);
        break;
      }
      Card elementA1 = a.get(0);
      Card elementB1 = b.get(0);
      if (elementA1.compareTo(elementB1) > 0) {
        //a is bigger than b
        pileFinal.add(b.remove());
      }else {
        // b is bigger than a
        pileFinal.add(a.remove());
      }
    }
    return pileFinal;
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
    cards = MergeSort.sort(cards, recorder);

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: Merge Sort");
  }
}
