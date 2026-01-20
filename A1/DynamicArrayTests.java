import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicArrayTests {

    private DynamicArray<Character> a1;
    private DynamicArray<Character> a2;
    private DynamicArray<Character> empty;
    private DynamicArray<Character> s;

    /**
     * Initializes DynamicArray<Character> instances to be used for testing.
     * Re-initializes before each test.
     * This ensures that tests do not interfere with one another.
     */
    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    /**
     * Puts the characters of a string into an array structure
     */
    public DynamicArray<Character> stringToArray(String s) {
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.add(i, s.charAt(i));
        }
        return result;
    }

    /**
     * Compares the sizes of a DynamicArray<Character> and a string
     */
    public void compareSize(DynamicArray<Character> arr, String s){
        assertEquals("["+s+"] Array lengths are equal", arr.size(), s.length());
    }

    /**
     * Compares each element in a DynamicArray<Character>
     * against those in a string.
     */
    public void compareToString(DynamicArray<Character> arr, String s) {
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("["+s+"] Elements are equal at index " + i, arr.get(i).charValue(), s.charAt(i));
        }
    }

    // ~*~*~*~*~ Append Tests Below ~*~*~*~*~

    /**
     * Tests that appending two non-empty arrays results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAppendStandard() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
    }

    /**
     * Tests that appending a non-empty array to itself results in
     * a new array containing the elements repeated twice.
     */
    @Test
    public void testAppendSelf() {
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(a2.append(a2), "wxyzwxyz");
    }

    /**
     * Tests that appending a non-empty array and an array of
     * length one results in a new array containing the elements
     * of both, in order.
     */
    @Test
    public void testAppendSingle() {
    compareToString(a1.append(s),"abcdefs");
    compareToString(s.append(a1),"sabcdef");
    compareToString(s.append(s),"ss");
    }

    /**
     * Tests that appending an empty array
     * results in a new array that matches the other array
     */
    @Test
    public void testAppendEmpty() {
        compareToString(a1.append(empty), "abcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(empty.append(empty), "");
    }

    // ~*~*~*~*~ Add Extract Tests Below ~*~*~*~*~

   /**
    * Tests that the standard function of extract works
    */
   @Test
   public void testExtractStandard() {
       compareToString(a1.extract(1,3), "bc");
       compareToString(a1, "abcdef");
   }

   /**
    * Tests that extracting the entire array works
    */
   @Test
   public void testExtractEntire() {
       compareToString(a1.extract(0,5), "abcdef");
   }

   /**
    * Tests that extracting no elements works
    */
   @Test
   public void testExtractZero() {
       compareToString(a1.extract(0,0), "");
   }

   /**
    * Tests that extracting an empty array works
    */
   @Test
   public void testExtractEmpty() {
       compareToString(empty.extract(0,0), "");
   }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBounds() {
        a1.extract(-1, 5);
    }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBoundsLowNegative() {
        a1.extract(-1, 5);
        // More bounds that you can check:
        // low index is negative => throws ArrayIndexOutOfBoundsException
    }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBoundsHighGreater() {
        // high index is greater than array length => throws ArrayIndexOutOfBoundsException
        a1.extract(0, 18);
    }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBoundsLowGreater() {
        // low index is greater than array length => throws ArrayIndexOutOfBoundsException
        a1.extract(18, 19);
    }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBoundsHighNegative() {
        // high index is negative => throws ArrayIndexOutOfBoundsException
        a1.extract(0, -18);

    }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testExtractBoundsHighLess() {
        // high index is less than low
        a1.extract(1, 0);
    }

    // ~*~*~*~*~ Write More Tests Below ~*~*~*~*~

    /**
    * Tests that the standard function of extract works
    */
   @Test
   public void testGetStandard() {
       Character actualChar = (Character) a1.get(1);
       assertEquals("b", String.valueOf(actualChar));
   }

   /**
    * Tests that get can get the highest value
    */
   @Test
   public void testGetHigh() {
       Character actualChar = (Character) a1.get(5);
       assertEquals("f", String.valueOf(actualChar));
   }

   /**
    * Tests that get can get the lowest value
    */
   @Test
   public void testGetLow() {
       Character actualChar = (Character) a1.get(0);
       assertEquals("a", String.valueOf(actualChar));
   }

   
   /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetNegativeIndex() {
        a1.get(-1); // should throw
    }

    /**
     * Tests that extract throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetIndexTooLarge() {
        a1.get(18); // should throw
    }

    /**
    * Tests that add works normally
    */
   @Test
   public void testAddStandard() {
        a1.add(1, 'g');
        compareToString(a1, "agbcdef");
   }

   /**
    * Tests that add works high
    */
   @Test
   public void testAddHigh() {
       a1.add(5, 'g');
       compareToString(a1, "abcdegf");
   }

   /**
    * Tests that add works low
    */
   @Test
   public void testAddLow() {
       a1.add(0, 'g');
       compareToString(a1, "gabcdef");
   }

   /**
     * Tests that add throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexTooLarge() {
        a1.add(18, 'g'); // should throw
    }

    /**
     * Tests that add throws the proper exception
     * when called on invalid indices
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddIndexTooLow() {
        a1.add(-1, 'g'); // should throw
    }

}





