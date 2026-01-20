/**Class to time quick sort */
public class QuickSortTimer {
      /**
   * Sort method for quick sort
   * @param unsorted the unsorted list
   * @return the sorted list
   */
  public static CardPile sort(CardPile unsorted) {

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
  
    // This will hold the assembled result
    CardPile result = new CardPile();
    
    // ***********************************************************
    // Here is where you'll do the remaining work of Quicksort:
    // Make recursive calls on the partitions
    // Assemble the sorted results into a single pile
    CardPile returned = new CardPile();
    returned = sort(smaller);
    if(!returned.isEmpty()){
      result.append(returned);
    }
    result.add(pivot);
    returned = sort(bigger);
    if(!returned.isEmpty()){
      result.append(returned);
    }
    // ***********************************************************
    // return the sorted result here
    return result;
  }

  /** Starts the program running */
  public static void main(String args[]) {
    
    if (args.length<1) {
      System.err.println("Please specify how many cards to sort!");
    } else {
      Card[] deck = Card.newDeck(true);
      CardPile cards = new CardPile();
      
      for (int i = 0; i<Integer.parseInt(args[0]); i++ ) {
        cards.add(deck[(int)(52*Math.random())]);
      }

      sort(cards);
      
    }
  }
}
