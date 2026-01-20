/** 
 * Interface to explain the behavior of an array 
 * In this example T is the place holder for the type of array int, char, ect.
 * T array is the array itself
 * int index is the index at which you would like to search/replace a value
 * T value is the value at a certain index
*/

public interface IndexAccessADT<T>{

    /**
     * Gets the length of the array 
     * @param array an array
     * @return length of the array
     */
    public int length(T array);

    /**
     * Gets value at nth index 
     * Throws runtime expection if nth index does not exist
     * @param index the index you want to find the value at
     * @return value from the index
     */
    public T returnValue(int index);

    /**
     * Changes value at nth index 
     * Throws runtime expection if nth index does not exist
     * @param index the index you want to replace the value at
     * @param value the replacement value
     */
    public void changeValue(int index, T value);


    /**
     * Gets index for n value 
     * Throws runtime expection if n value does not exist
     * @param value the value you want to find the index at
     * @return index for which the value is located at
     */
    public int returnIndex(T value);

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