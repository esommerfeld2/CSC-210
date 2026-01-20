import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for array/BST/DLL conversion functions.
 *
 * @author YOUR_NAME_HERE
 * @version Fall 2025
 */
public class ConversionTests {
    /** Helper method: verify that two arrays contain the same sequence. */
    private static <T> void verifyArray(T[] expected, T[] actual) {
        assertEquals("Array lengths differ", expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals("Mismatch at position " + i, expected[i], actual[i]);
        }
    }

    /** Helper method: verify that DLL nodes and data match expected array. */
    private static <T> void verifyList(DLL<T> list, T[] arr) {
        if (arr.length == 0) {
            assertNull("Head should be null for empty list", list.getHead());
            assertNull("Tail should be null for empty list", list.getTail());
        } else {
            assertNull("Head's previous should be null", list.getHead().getLeft());
            assertNull("Tail's next should be null", list.getTail().getRight());

            for (int i = 0; i < arr.length; i++) {
                BinaryTree<T> pos = list.getHead();
                for (int j = 0; j < i; j++) pos = pos.getRight();

                BinaryTree<T> pos2 = list.getTail();
                for (int j = 0; j < arr.length - 1 - i; j++) pos2 = pos2.getLeft();

                assertSame("Node mismatch at position " + i, pos, pos2);
                assertEquals("Value mismatch at position " + i, arr[i], pos.getData());
            }
        }
    }

    /** Helper method: verifies that a BinaryTree has the expected structure and contents. */
    private static <T> void verifyBT(BinaryTree<? extends T> t, T[][] contents) {
        for (int i = 0; i <= contents.length; i++) {
            int nj = (int) Math.pow(2, i);
            for (int j = 0; j < nj; j++) {
                int h = (int) Math.pow(2, i - 1);
                int k = j;
                BinaryTree<?> node = t;

                while (h > 0 && node != null) {
                    if (k >= h) node = node.getRight();
                    else node = node.getLeft();
                    k = k % h;
                    h /= 2;
                }

                // Compare expected and actual structure
                if ((i == contents.length || contents[i][j] == null) && node != null) {
                    fail("Row " + i + " position " + j +
                         " should be null but found data: " + node.getData());
                } else if (i < contents.length && contents[i][j] != null) {
                    if (node == null) {
                        fail("Row " + i + " position " + j +
                             " should be " + contents[i][j] + " but found null");
                    } else {
                        assertEquals("Row " + i + " position " + j + 
                                     " expected " + contents[i][j] + 
                                     " but got " + node.getData(),
                                     contents[i][j], node.getData());
                    }
                }
            }
        }
    }

    @Test
    public void testArrayToBST() {
        Integer[][] gt1 = {{3}, {1,5}, {0,2,4,6}};
        Integer[] test = {0, 1, 2, 3, 4, 5, 6};
        Integer[][] gt2 = {{2}, {1,4}, {0,null,3,null}};
        Integer[] test2 = {0, 1, 2, 3, 4};

        //Array equal
        verifyBT(Conversion.arrayToBST(test), gt1);

        //Array unbalanced
        verifyBT(Conversion.arrayToBST(test2), gt2);
        
    }


    @Test
    public void testbinaryTreetoDLL() {
        Integer[] gt1 = {0,1,2};
        Integer[] gt2 = {3,6,9,12,15,18,24};

        //tree small
        BST<Integer> tree = new BST<>(1);
        tree.insert(0);
        tree.insert(2);

        //tree large
        BST<Integer> tree2 = new BST<>(12);
        tree2.insert(6);
        tree2.insert(3);
        tree2.insert(9);
        tree2.insert(18);
        tree2.insert(15);
        tree2.insert(24);

        //Small
        verifyList(Conversion.binaryTreeToDLL(tree), gt1);

        //Larger
        verifyList(Conversion.binaryTreeToDLL(tree2), gt2);
        
    }
}
