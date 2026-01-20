import java.util.ListIterator;
/**Class to time selection sort */
public class SelectionSortTimer {

    /**
   * Method to sort a list using selection sort
   * @param unsorted the unsorted list
   * @return the sorted list
   */
  public static CardPile sort(CardPile unsorted) {

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

      
    }
    // ***********************************************************

    // return the sorted result here
    return sorted;
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
