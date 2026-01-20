import java.util.Collections;
/**A method to sort cards using quick sort */
public class Quicksort {
  
  /**
   * Sort method for quick sort
   * @param unsorted the unsorted list
   * @param record the record
   * @return the sorted list
   */
  public static CardPile sort(CardPile unsorted, SortRecorder record) {

    // ***********************************************************
    // Here is where you'll check the stop condition and return
    // if it is satisfied.
    if(unsorted.size() <= 1){
      return unsorted;
    }
    // ***********************************************************
    
    // Here are the two partitions you will be creating
    CardPile smaller = new CardPile();
    CardPile bigger = new CardPile();

    // ***********************************************************
    // Here is where you'll do the partition part of Quicksort:
    //Choose a pivot
    Card pivot = unsorted.removeFirst(); 
    //Partition the unsorted list into two piles
    while(!unsorted.isEmpty()){
      Card currentCard = unsorted.removeFirst();
      if (currentCard.compareTo(pivot) < 0) {
          // current card is smaller
          smaller.add(currentCard);
        }else {
          //current card is bigger than pivot
          bigger.add(currentCard);
        }
    }
  
    // ***********************************************************
    // register the partitions with the recorder
    record.add(smaller);
    record.add(pivot);
    record.add(bigger);
    record.next();
  
    // This will hold the assembled result
    CardPile result = new CardPile();
    
    // ***********************************************************
    // Here is where you'll do the remaining work of Quicksort:
    // Make recursive calls on the partitions
    // Assemble the sorted results into a single pile
    CardPile returned = new CardPile();
    returned = sort(smaller, record);
    if(!returned.isEmpty()){
      result.append(returned);
    }
    result.add(pivot);
    returned = sort(bigger, record);
    if(!returned.isEmpty()){
      result.append(returned);
    }
    // ***********************************************************

    // record the sorted result
    record.add(result);
    record.next();
    
    // return the sorted result here
    return result;
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
    cards = Quicksort.sort(cards, recorder); 

    // We can print out the (un)sorted result:
    System.out.println(cards);

    // make window appear showing the record
    recorder.display("Card Sort Demo: Quick Sort");
  }
}
