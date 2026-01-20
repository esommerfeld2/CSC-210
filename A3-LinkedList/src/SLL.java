/**
 * Class to implement a singly linked list
 *
 * @author Ella Sommerfeld
 * @version Spring 2024
 */

 public class SLL<T> implements Phase1SLL<T>, Phase2SLL<T>, Phase4SLL<T>{

    private NodeSL<T> head;
    private NodeSL<T> tail;


  /**
  * Constructor
  */
  public SLL(){
    head = null;

  }

  /**
  * Constructor
  @param list a list you want to make a deep copy of
  */
  public SLL(SLL<T> list){
    if(list.head == null){
        this.head = this.tail = null;
    }else{
    NodeSL<T> pos = list.head;
    head = new NodeSL<T>(pos.getData(),null);
    this.tail = this.head;
    for(pos = list.head.getNext(); pos != null; pos = pos.getNext()) {
        NodeSL<T> add = new NodeSL<T>(pos.getData(),null);
        tail.setNext(add);
        this.tail = tail.getNext();
        }
    }
  }
 

   /** 
   *  Accessor for head node
   *  @return the head node
   */
  public NodeSL<T> getHead(){
    return this.head;
  }
  
  /** 
   *  Accessor for tail node
   *  @return the tail node
   */
  public NodeSL<T> getTail(){
    if(this.head == null){
        return this.head;
    }
    return this.tail;
  }

  /** 
   *  Determines whether a list is empty
   *  @return T/F is the list empty?
   */
  public boolean isEmpty(){
    if(this.head == null){
        return true;
    }
    return false;
  }

  /** 
   *  Inserts the given item at the head of the list
   *  @param v item to insert 
   */
  public void addFirst(T v){
    NodeSL<T> addition;
    if(this.head == null){
       addition = new  NodeSL<>(v, null);
       this.tail= addition;
    }else{
        addition = new NodeSL<>(v, this.head);
    }
    this.head = addition;
  }
  
  /** Converts to a string representation */
  public String toString(){
    String finalString;
    if(this.head == null){
        return "[]";
    }
    finalString = "[";
    for(NodeSL<T> n = this.head; n != null; n =n.getNext()) {
        finalString += n.getData();
        if(n != this.tail){
            finalString += ", ";
        }
    }
    finalString += "]";
    return finalString;
  }

  /** 
   *  Inserts the given item at the tail of the list
   *  @param v item to insert 
   */
  public void addLast(T v){
    if(this.head == null){
        addFirst(v);
    }else{
        NodeSL<T> addition;
        addition = new  NodeSL<>(v, null);
        this.tail.setNext(addition);
        this.tail= addition;
    }
  }

  /**
   *  Inserts the given item after the specified node
   *  @param here node to insert after
   *  @param v item to insert 
   */
  public void addAfter(NodeSL<T> here, T v){
        Boolean worked = false;
        NodeSL<T> currentItem;
        currentItem = this.head;
        while(currentItem != null){
            if(currentItem == here){
                NodeSL<T> addition;
                addition = new  NodeSL<>(v, here.getNext());
                here.setNext(addition);
                if(here == this.tail){
                    this.tail = addition;
                }
                worked = true;
                break;
            }
            currentItem = currentItem.getNext();
        }
        if(!worked){
            throw new MissingElementException();
        }
  }

  /** 
   *  Removes the given item from the head of the list
   *  @return v item removed
   */
  public T removeFirst(){
    if(this.head == null){
        throw new MissingElementException();
    }
    NodeSL<T> removedItem = this.head;
    this.head= this.head.getNext();
    return removedItem.getData();
  }

  /** 
   *  Removes the given item from the tail of the list
   *  @return item removed
   */
  public T removeLast(){
    if(this.head == null){
        throw new MissingElementException();
    }else if (this.head == this.tail){
        NodeSL<T> removedItem = this.tail;
        this.head= null;
        this.tail= null;
        return removedItem.getData();
    }else{
        NodeSL<T> removedItem = this.tail;
        NodeSL<T> currentItem;
        currentItem = this.head;
        while(currentItem.getNext() != tail){
            currentItem = currentItem.getNext();
        }
       currentItem.setNext(null);
       this.tail = currentItem;
       return removedItem.getData();       
    }
    
  }

  /** 
   *  Removes the node after the given position
   *  @param here marks position to remove after
   *  @return item removed
   */
  public T removeAfter(NodeSL<T> here){
    if(this.head == null){
        throw new MissingElementException();
    }
    if(head == null){
        throw new MissingElementException();
    }
    if(here == null){
        return removeFirst();
    }
    if(here == tail){
        return removeLast();
    }else{
    NodeSL<T> currentItem;
    currentItem = this.head;
    while(currentItem != here){
        currentItem = currentItem.getNext();
    }
    NodeSL<T> removed;
    removed = here.getNext();
    here.setNext(here.getNext().getNext());
    return removed.getData();
        }
    }

  /**
   *  Returns a count of the number of elements in the list
   *  @return current number of nodes
   */
  public int size(){
    int count = 0;
    if(head == null){
        return 0;
    }else{
    NodeSL<T> currentItem;
    currentItem = this.head;
    while(currentItem != null){
            count ++;
            currentItem = currentItem.getNext();
    }
    return count;
    }
  }

   /** 
   *  Makes a copy of elements from the original list
   *  @param here  starting point of copy
   *  @param n  number of items to copy
   *  @return the copied list
   */
  public SLL<T> subseqByCopy(NodeSL<T> here, int n){
    SLL<T> newList = new SLL<>();
    NodeSL<T>currentItem = here;
    for(int i = 0; i< n; i++){
        if(currentItem == null){
            throw new MissingElementException();
        }
        newList.addLast(currentItem.getData());
        currentItem= currentItem.getNext();
    }
    return newList;


  }

  /**
   *  Places copy of the provided list into this after the specified node.
   *  @param list  the list to splice in a copy of
   *  @param afterHere  marks the position in this where the new list should go
   */
  public void spliceByCopy(SLL<T> list, NodeSL<T> afterHere){
    if(this == list){
        throw new SelfInsertException();
    }
    if(list.head == null){
        return;
    }else if(afterHere == null){
        SLL<T> copy = new SLL<T>(list);
        copy.tail.setNext(this.head);
        this.head = copy.head;
    }else{
        boolean afterHereExists = false;
        for(NodeSL<T> k = this.head; k != null; k = k.getNext()) {
            if(k== afterHere){
                afterHereExists = true;
                break;
            }
        }
        if(afterHereExists){
            SLL<T> copy = new SLL<T>(list);
            NodeSL<T> afafterHere = afterHere.getNext();
            NodeSL<T> pos = copy.head;
            afterHere.setNext(pos);
            copy.tail.setNext(afafterHere);
        if(afterHere.getNext() == null){
            this.tail= copy.tail;
        }
        }else{
             throw new MissingElementException();
        }
    }
  }

  /** 
   *  Extracts a subsequence of nodes and returns them as a new list
   *  @param afterHere  marks the node just before the extraction
   *  @param toHere  marks the node where the extraction ends
   *  @return  the new list
   */
  public SLL<T> subseqByTransfer(NodeSL<T> afterHere, NodeSL<T> toHere){
    if(afterHere == null){
        SLL<T> newList = new SLL<T>();
        newList.head = this.head;
        newList.tail= toHere;
        this.head= toHere.getNext();
        newList.tail.setNext(null);
        return newList;
    }else{
        SLL<T> newList = new SLL<T>();
        newList.head = afterHere.getNext();
        newList.tail= toHere;
        afterHere.setNext(toHere.getNext());
        newList.tail.setNext(null);
        return newList;
    }
  }

  /** 
   *  Takes the provided list and inserts its elements into this
   *  after the specified node.  The inserted list ends up empty.
   *  @param list  the list to splice in.  Becomes empty after the call
   *  @param afterHere  Marks the place where the new elements are inserted
   */
  public void spliceByTransfer(SLL<T> list, NodeSL<T> afterHere){
    if(this == list){
        throw new SelfInsertException();
    }
    if(afterHere == null){
        list.tail.setNext(this.head);
        this.head = list.head;
        list.head= null;
    }else{
        list.tail.setNext(afterHere.getNext());
        afterHere.setNext(list.head);
        list.head = null;
    }
  }
/** 
   *  Running tests via printing
   * @param args for the print statement
   */
public static void main(String[] args) {
    SLL<String> list = new SLL<>();
    SLL<String> otherList = new SLL<>();
    list.addFirst("F");
    list.addFirst("E");
    otherList.addFirst("D");
    otherList.addFirst("C");
    list.addFirst("B");
    list.addFirst("A");
    System.out.println(list);
    list.spliceByTransfer(otherList, null);
    System.out.println(list);
  }
 
 }