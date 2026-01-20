/**
 * Implements binary search trees.
 *
 * @author YOUR_NAME_HERE
 * @version Fall 2025
 */
public class BST<E extends Comparable<E>> extends BinaryTree<E> implements BST_Ops<E> {

    /** This constructor creates a leaf node 
     * @param data the data for the leaf node
    */
    public BST(E data) {
        super(data);
    }

    /** This constructor creates a deep copy of the entire tree structure 
     * @param tree the tree to copy
     */ 
    public BST(BinaryTree<E> tree) {
        super(tree);
    }

    /** Override inherited manipulator to accept only BST 
     * @param left the new left side
    */
    public void setLeft(BinaryTree<E> left) {
        if ((left==null)||(left instanceof BST<E>)) {
            super.setLeft(left);
        } else {
            throw new UnsupportedOperationException("Only BST children allowed");
        }
    }

    /** Override inherited manipulator to accept only BST 
     * @param right the new right side
    */
    public void setRight(BinaryTree<E> right) {
        if ((right==null)||(right instanceof BST<E>)) {
            super.setRight(right);
        } else {
            throw new UnsupportedOperationException("Only BST children allowed");
        }
    }

    /**
     *  Returns the node of the given element, or null if not found
     *
     *  @param query The element to search
     *  @return the node of the given element, or null if not found
     */
    public BST<E> lookup(E data){
        E currentData = this.getData();
        int compare = currentData.compareTo(data);
        if(compare == 0){
            return this;
        }else if(compare > 0 ){
            if(this.getLeft() == null){
                return null;
            }
            return ((BST<E>) this.getLeft()).lookup(data);
        }else if(compare < 0 ){
            if(this.getRight() == null){
                return null;
            }
            return ((BST<E>) this.getRight()).lookup(data);
        }else{
            //fail
            return null;
        }
    }

    /**
     *  Inserts a new node into the tree
     *
     *  @param data The element to insert
     */
    public void insert(E data){
        E currentData = this.getData();
        int compare = currentData.compareTo(data);
        if(compare == 0){
            //data is already present
        }else if(compare > 0 ){
            if(this.getLeft() == null){
                BST<E> dataTree = new BST<>(data);
                this.setLeft(dataTree);
            }else{
                ((BST<E>) this.getLeft()).insert(data);
            }
        }else{
            if(this.getRight() == null){
                BST<E> dataTree = new BST<>(data);
                this.setRight(dataTree);
            }else{
                ((BST<E>) this.getRight()).insert(data);
            }
        }
    }

    //TEST USING TESTS, AND THEN FIX EVERYTHING ONCE FIXED YOUR DONE WITH THIS METHOD!!!
    /**
     *  Deletes the specified element from the tree
     *  Returns the modified tree because the root node 
     *  may have changed
     *  
     *  @param evictee The element to delete
     *  @return tree as modified
     */
    public BST<E> deleteWithCopyLeft(E evictee){
        BST<E> parent = this;
        BST<E> leftHighest = this;
        BST<E> current = this.lookup(evictee);
        if(current == null){
            return this;
        }
        if(current.isLeaf()){
            while(parent != null){
                //STOPS
                if(parent.getLeft()== current){
                    parent.setLeft(null);
                    break;
                }else if(parent.getRight() == current ){
                    parent.setRight(null);
                    break;
                }else if(parent== current){
                    return null;
                }

                //Move pointer forward
                E currentData = parent.getData();
                int compare = currentData.compareTo(evictee);
                if(compare > 0 ){
                    parent= (BST<E>) parent.getLeft();
                }else if(compare < 0 ){
                    parent= (BST<E>) parent.getRight();
                }
            }
        }else{
            //degree 1
            if(current.getRight()== null || current.getLeft()== null){
                while(parent != null){
                    //STOPS
                    if(parent.getLeft()== current){
                        if(current.getRight() == null){
                            parent.setLeft(parent.getLeft().getLeft());
                        }else{
                            parent.setLeft(parent.getLeft().getRight());
                        }
                        break;

                    }else if(parent.getRight() == current ){
                        if(current.getRight()== null){
                            parent.setRight(parent.getRight().getLeft());
                        }else{
                            parent.setRight(parent.getRight().getRight());
                        }
                        break;

                    }else if(parent == current){
                        if(current.getRight() == null){
                            parent.setData(parent.getLeft().getData());
                            parent.setLeft(null);
                        }else{
                            parent.setData(parent.getRight().getData());
                            parent.setRight(null);
                        }
                        break;
                    }

                    //Move pointer forward
                    E currentData = parent.getData();
                    int compare = currentData.compareTo(evictee);
                    if(compare > 0 ){
                        parent= (BST<E>) parent.getLeft();
                    }else if(compare < 0 ){
                        parent= (BST<E>) parent.getRight();
                    }
                }
            }else{

            //degree 2

            while(parent != null){
                    //STOPS
                    if(parent == current){
                        leftHighest = parent;
                        leftHighest= (BST<E>) leftHighest.getLeft();
                        BST<E> leftHighestParent= parent;
                        boolean wentRight = false;
                        int count= 0;
                        while(leftHighest.getRight() != null){
                            leftHighest= (BST<E>) leftHighest.getRight();
                            wentRight = true;
                            count ++;
                        }
                        //Set leftHighestParent
                        if(wentRight){
                            leftHighestParent = (BST<E>) leftHighestParent.getLeft();
                            for(int i = 1; i < count; i ++){
                                leftHighestParent = (BST<E>) leftHighestParent.getRight();
                            }
                        }

                        //Set new data at current
                        parent.setData(leftHighest.getData());

                        //Delete leftHighest
                        if(wentRight){
                            if(leftHighest.getLeft() != null){
                                leftHighestParent.setRight(leftHighest.getLeft());
                            }else{
                                leftHighestParent.setRight(null);
                            }
                        }else{
                            if(leftHighest.getLeft() != null){
                                leftHighestParent.setLeft(leftHighest.getLeft());
                            }else{
                               leftHighestParent.setLeft(null);
                            }
                        }
                        break;
                        
                    }

                    //Move pointer forward
                    E currentData = parent.getData();
                    int compare = currentData.compareTo(evictee);
                    if(compare > 0 ){
                        parent= (BST<E>) parent.getLeft();
                    }else if(compare < 0 ){
                        parent= (BST<E>) parent.getRight();
                    }
                }
            }
        }

        return this;
    }

    /**
     *  Apply left rotation
     *  Returns the modified tree because the root node 
     *  may have changed
     *
     *  @return tree as modified
     */
    public BST<E> rotateLeft(){
        //Setting up variables
        BST<E> newRoot = this;
        BST<E> root = this;
        BST<E> right = this;
        BST<E> newRootsLeft = this;

        //If null, can't rotate
        if(root.getRight()== null){
            return this;
        }

        //Setting up new parts of the tree
        newRoot = (BST<E>) root.getRight();
        right = (BST<E>) newRoot.getRight();
        newRootsLeft = (BST<E>) newRoot.getLeft();

        root.setRight(newRootsLeft);
        newRoot.setLeft(root);
        newRoot.setRight(right);

        return newRoot;
    }

    /**
     *  Apply right rotation
     *  Returns the modified tree because the root node 
     *  may have changed
     *
     *  @return tree as modified
     */
    public BST<E> rotateRight(){
        //Setting up variables
        BST<E> newRoot = this;
        BST<E> root = this;
        BST<E> right = this;
        BST<E> left = this;

        //If null, can't rotate
        if(root.getLeft()== null){
            return this;
        }

        //Setting up new parts of the tree
        newRoot = (BST<E>) root.getLeft();
        right = (BST<E>) newRoot.getRight();
        left = (BST<E>) newRoot.getLeft();

        root.setLeft(right);
        newRoot.setRight(root);
        newRoot.setLeft(left);

        return newRoot;
    }

}
