/** 
 * Interface to explain the behavior of a sequence
 * In this example T is the place holder for the type of sequence
 * T abc is the sequence itself
 * T value is a value within 
 * int index is the index at which you would like to search/replace a value
 * T def is another sequence
*/

interface SequenceADT<T> {

    /**
     * Gets the length of the sequence
     * @param abc a sequence
     * @return length of the sequence
     */
    public int length(T abc);

    /**
     * Checks if a value is a part of a sequence
     * @param value for which you want to check if it is in the sequence
     * @return boolean 
     */
    public boolean checkValue(T value);

    /**
     * Gets value at nth index 
     * Throws runtime expection if index does not exist
     * Throws runtime expection if nth index does not exist
     * @param index the index you want to find the value at
     * @return value the value at that index
     */
    public T returnValue(int index);

    /**
     * Changes value at nth index 
     * Throws runtime expection if value can not be changed due to type
     * Throws runtime expection if index does not exist
     * Throws runtime expection if nth index does not exist
     * @param index the index you want to replace the value at
     * @param value the replacement value
     */
    public void changeValue(int index, T value);

    /**
     * Gets index for n value 
     * Throws runtime expection if index does not exist
     * Throws runtime expection if n value does not exist
     * @param value the value you want to find the index of
     * @return index that the value is at
     */
    public int returnIndex(T value);

    /**
     * Combines two sequences
     * @param abc a sequence
     * @param def another sequence
     */
    public void combine(T abc, T def);

    /**
     * Checks if two sequences are equal
     * @param abc a sequence
     * @param def anotehr sequence
     * @return boolean
     */
    public boolean isEqual(T abc, T def);
    
}
