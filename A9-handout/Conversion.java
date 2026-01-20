/**
 * Class to implement tree conversions
 *
 * @author YOUR_NAME_HERE
 * @version Fall 2025
 */
public class Conversion {
  /** Converts a sorted array to a balanced BST */
  public static <T extends Comparable<T>> BST<T> arrayToBST(T[] arr) {
    return recursiveArrayToBST(arr, 0, arr.length);
  }


/**
 * Recursive Method to implement arrayToBST
 * @param arr to be converted
 * @param low index, included
 * @param high index, excluded
 * @return created BST
 */
  public static <T extends Comparable<T>> BST<T> recursiveArrayToBST(T[] arr, int low, int high) {
    if(low >= high){
      return null;
    }else if(high- low == 1){
      return new BST<>(arr[low]);
    }else{
      int pivot = (low+ high)/2;
      BST<T> tree = new BST<>(arr[pivot]);
      tree.setLeft(recursiveArrayToBST(arr, low, pivot));
      tree.setRight(recursiveArrayToBST(arr, pivot+1, high));
      return tree;
    }
  }

  /** Convert BinaryTree to DLL */
  public static <S extends Comparable<S>> DLL<S> binaryTreeToDLL(BinaryTree<S> t) {
    if(t== null){
      return null;   
    }else{
      DLL<S> leftList= binaryTreeToDLL(t.getLeft()); //head
      DLL<S> rightList=binaryTreeToDLL(t.getRight()); //tail

      //T set left
      if(leftList != null){
        t.setLeft(leftList.getTail());
        leftList.getTail().setRight(t);
      }else{
        t.setLeft(null);
      }

      //Set Right (and link it together)
      if(rightList != null){
        t.setRight(rightList.getHead());
        rightList.getHead().setLeft(t);
      }else{
        t.setRight(null);
      }

      //Head
      BinaryTree<S> head;
      if(leftList != null){
        head = leftList.getHead();
      }else{
        head = t;
      }

      //Tail
      BinaryTree<S> tail;
      if(rightList != null){
        tail = rightList.getTail();
      }else{
        tail = t;
      }
      
      DLL<S> finalDLL = new DLL<>(head, tail);
      return finalDLL;
    }
    
  }
}
