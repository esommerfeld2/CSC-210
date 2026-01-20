import java.util.ListIterator;
/**Class to time insertion sort */
public class InsertionSortTimer {
    
    /**
   * Method to sort a list using insertion sort
   * @param unsorted the unsorted list
   * @return the sorted list
   */
  public static CardPile sort(CardPile unsorted) {

    // Here is the result list you will be creating
    CardPile sorted = new CardPile();
  
    // ***********************************************************
    // Here is where you'll do the "work" of SelectionSort:
    while (unsorted.size() > 0) {
      if(sorted.isEmpty()){
        sorted.add(unsorted.remove());
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
