import java.util.ArrayDeque;

/**Class to time merge sort */
public class MergeSortTimer {
     /**
   * Method to sort a list using merge sort
   * @param unsorted the unsorted list
   * @return the sorted list
   */
  public static CardPile sort(CardPile unsorted) {
    
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
