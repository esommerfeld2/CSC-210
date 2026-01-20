  /** Nested class to keep track of nodes */
  public class NodeSL<T> {
      /** The data at the node */
      private T data;
      
      /** Link to the next node */
      private NodeSL<T> next;

      /** A constructor
       * @param data the data for the node
       * @param next the next node
       */
      NodeSL(T data, NodeSL<T> next) {
          this.data = data;
          this.next = next;
      }

      /** Gets data from a node
       * @return data field */
      public T getData() {
        return data;
      }

      /** Sets the data of a node
       * @param d new data vaue */
      public void setData(T d) {
        this.data = d;
      }

      /** Gets the next node
       * @return next node 
       */
      public NodeSL<T> getNext() {
        return next;
      }

      /** Sets the next node
       * @param next new next node 
       */
      public void setNext(NodeSL<T> next) {
        this.next = next;
      }
    }
