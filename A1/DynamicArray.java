/** 
 * Dynamic Array Class
 * Methods to use a dynamic array
*/
public class DynamicArray<T> implements IndexAccessADT<T>{

    private T[] arr;
    private int size;

    /**
     * Constructor
     * @param len the length of the array
     */
    public DynamicArray(int len){
       arr = allocate(len);
       size = 0;
    }

    /**
     * Private utility to do array allocation
     */
    @SuppressWarnings("unchecked")
    private T[] allocate(int len) {
        return (T[]) new Object[len];
    }

    /**
     * Gets value at nth index 
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to find the value at
     * @return value from the index
     */
    public T get(int index){
        if(index < arr.length && index >= 0){
            return arr[index];
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Changes value at nth index 
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to replace the value at
     * @param value the replacement value
     * @return value T, old value
     */
    public T set(int index, T value){
        if(index < arr.length && index >= 0){
            T oldValue= arr[index];
            arr[index] =  value;
            return oldValue;
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Gets the length of the array 
     * @return length of the array
     */
    public int size(){
        return arr.length;
    }

    /**
     * Inserts an element an nth index and shift everything else to the right
     * ThrowsOutOfBoundsException if nth index is invalid
     * @param index the index you want to add the value at
     * @param value the new value
     */
    public void add(int index, T value){ 
        if(index < 0 || index > arr.length-1){ 
            throw new IndexOutOfBoundsException(); 
        }else if(index < size){ 
            int length = arr.length + 1; 
                DynamicArray<T> newArray = new DynamicArray<>(length); 
                for (int i = 0; i < index; i++) { 
                    newArray.set(i, arr[i]); 
                } 
                newArray.set(index, value); 
                for (int i = index +1; i < length; i++) { 
                    newArray.set(i, arr[i-1]); 
                } 
                arr = newArray.arr; 
        }else{ 
            arr[index] = value; 
            size += 1; 
    }
}


    /**
     * Deletes value at nth index and moves everything to the left
     * ThrowsOutOfBoundsException if nth index is invalid
     * @param index the index you want to remove the value at
     * @return value removed
     */
    public T remove(int index){
        if(index < 0 || index > arr.length){
            throw new IndexOutOfBoundsException();
        }else{
            int length = arr.length - 1;
            DynamicArray<T> newArray = new DynamicArray<>(length);
            for (int i = 0; i < index; i++) {
                newArray.set(i, arr[i]);
             }
            T value;
            value = arr[index];
            for (int i = index; i < length; i++) {
                newArray.set(i, arr[i+1]);
            }
            size -=1;
            arr = newArray.arr;
            return value;
        }
        
    }

    /**
     * Adds the new array to the end of the old array 
     * @param array the new array
     * @return the new array
     */
    public DynamicArray<T> append(DynamicArray<T> array){
        int length = array.size() + arr.length;
        DynamicArray<T> newArray = new DynamicArray<>(length);
        for(int i = 0; i < arr.length; i++) {
            newArray.set(i, arr[i]);
        }
        int count = 0;
        for(int i = arr.length; i < length; i++){
           newArray.set(i, array.get(count));
           count += 1;
        }
        size= size + array.size();
        return newArray;
    }

    /**
     * Inserts array at new index
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to add the array at
     * @param array the new array
     * @return new array
     */
    public DynamicArray<T> insert(int index, DynamicArray<T> array){
        if(index < 0 || index > arr.length){
            throw new IndexOutOfBoundsException();
        }else{
            int length = arr.length + array.size();
            DynamicArray<T> newArray = new DynamicArray<>(length);
            for (int i = 0; i < index; i++) {
                newArray.set(i, arr[i]);
             }
            int count = 0;
            for (int i = index; i <index + array.size(); i++) {
                newArray.set(i, array.get(count));
                count +=1;
             }
            count = index;
            for (int i = index + array.size(); i < length; i++) {
                newArray.set(i, array.get(count));
                count +=1;
            }
            size= size + array.size();
            return newArray;
        }

    }

    /**
     * Returns the elements from a specified index and after as a new `DynamicArray`
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to start copying at
     * @return new array
     */
   public DynamicArray<T> splitSuffix(int index){
        if(index < 0 || index > arr.length){
            throw new IndexOutOfBoundsException();
        }else{
            int length= 0;
            for(int i = index; i < arr.length; i++){
                length += 1; 
            }
            DynamicArray<T> newArray = new DynamicArray<>(length);
            int count = index;
            for (int i = index; i < arr.length; i++) {
                newArray.set(i, arr[count]);
                count +=1;
             }
             return newArray;
        }

   }

   /**
     * Returns the elements before a specified index as a new `DynamicArray
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param index the index you want to start copying at
     * @return new array
     */
   public DynamicArray<T> splitPrefix(int index){
        if(index < 0 || index > arr.length){
            throw new IndexOutOfBoundsException();
        }else{
            int length= 0;
            for(int i = 0; i < index; i++){
                length += 1; 
            }
            DynamicArray<T> newArray = new DynamicArray<>(length);
            for (int i = 0; i < index; i++) {
                newArray.set(i, arr[i]);
             }
             return newArray;
            }

   }

    /**
     * Deletes from index to index
     * ThrowsOutOfBoundsException if nth index does not exist
     * @param indexStart the index you want to start copying at
     * @param indexEnd the index you want to end at
     * @return new array
     */
    public DynamicArray<T> delete(int indexStart, int indexEnd){
        if(indexStart < 0 || indexEnd >= size|| indexStart > indexEnd){
            throw new IndexOutOfBoundsException();
        }else{
            int lengthDeleted = indexEnd- indexStart + 1;
            int length = size - lengthDeleted;
            DynamicArray<T> newArray = new DynamicArray<>(length);
            int count = indexStart;
            for (int i = 0; i < indexStart; i++) {
                newArray.set(i, arr[i]);
             }
            for (int i = indexEnd+1; i < size; i++) {
                newArray.set(count, arr[i]);
                count +=1;
             }
             size = length;
             return newArray;
        }
    }

    /**
     * Creates a new dynamic array that is a sublist of an array 
     * @param indexStart the index you want to start copying at
     * @param indexEnd the index you want to end at
     * @return new array
     */
    public DynamicArray<T> extract(int indexStart, int indexEnd){
        if(indexStart < 0 || indexEnd > arr.length+1 || indexEnd < 0 || indexStart > arr.length+1 || indexStart > indexEnd){
            throw new IndexOutOfBoundsException();
        }else{
            int length = indexEnd - indexStart;
            DynamicArray<T> newArray = new DynamicArray<>(length);
            int count = 0;
            for (int i = indexStart; i < indexEnd; i++) {
                newArray.set(count, arr[i]);
                count +=1;
             }
             size = length;
             return newArray;
        }
    }

    public String toString() {
        String finalString = "[";
        for (int i = 0; i <= size; i++){
            if (i == size) {
                finalString += this.get(i);
            }else {
                finalString += this.get(i) + ", ";
            }
        }
        return finalString + "]";
    }
    
}
