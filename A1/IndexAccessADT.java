/** 
 * Interface to explain the behavior of an array 
 * In this example T is the place holder for the type of array int, char, ect.
 * T array is the array itself
 * int index is the index at which you would like to search/replace a value
 * T value is the value at a certain index
*/

public interface IndexAccessADT<T>{

    /**
     * Gets value at nth index 
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to find the value at
     * @return value from the index
     */
    public T get(int index);

    /**
     * Changes value at nth index 
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to replace the value at
     * @param value the replacement value
     * @return value T, old value
     */
    public T set(int index, T value);

    /**
     * Gets the length of the array 
     * @return length of the array
     */
    public int size();

    /**
     * Inserts an element an nth index and shift everything else to the right
     * ThrowsOutOfBoundsException if nth index is invalid
     * @param index the index you want to add the value at
     * @param value the new value
     */
    public void add(int index, T value);

    /**
     * Deletes value at nth index and moves everything to the left
     * ThrowsOutOfBoundsException if nth index is invalid
     * @param index the index you want to remove the value at
     * @return value removed
     */
    public T remove(int index);

    /**
     * Adds the new array to the end of the old array 
     * @param array the new array
     * @return new dynmaic array
     */
    public DynamicArray<T> append(DynamicArray<T> array);

    /**
     * Inserts array at new index
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to add the array at
     * @param array the new array
     * @return new array
     */
    public DynamicArray<T> insert(int index, DynamicArray<T> array);

    /**
     * Returns the elements from a specified index and after as a new `DynamicArray`
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to start copying at
     * @return new array
     */
   public DynamicArray<T> splitSuffix(int index);

   /**
     * Returns the elements before a specified index as a new `DynamicArray
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to end copying at
     * @return new array
     */
   public DynamicArray<T> splitPrefix(int index);

    /**
     * Deletes from index to index
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param indexStart the index you want to start copying at
     * @param indexEnd the index you want to end at
     * @return new array
     */
    public DynamicArray<T> delete(int indexStart, int indexEnd);

    /**
     * Creates a new dynamic array that is a sublist of an array 
     * @param indexStart the index you want to start copying at
     * @param indexEnd the index you want to end at
     * @return new array
     */
    public DynamicArray<T> extract(int indexStart, int indexEnd);
}


/*Tests I would conduct: 
First I would create an array int arr[] = {1,2,3} (this array will be used in all the following tests)
Then I would assertEquals that length(arr[]) is 3
For the next test I would assertEquals that returnValue(1) is equal to 2
And that returnValue(5) gives a runtime error that there is no index 5
Next I would run changeValue(2,4) and then assertEquals that returnValue(2) is equal to 4
And would run changeValue(5,6) to hopefully get a runtime error that there is no index 5
Finally I would assertEquals that returnIndex(1) is equal to 0.
And run returnIndex(6) to get a runtime error that there is no value 6 in the array.
*/