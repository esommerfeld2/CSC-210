import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Binary Search Tree (BST) class.
 *
 * @author YOUR_NAME_HERE
 * @version Fall 2025
 */
public class BSTTests {
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

    // Sample tests...
    @Test
    public void testBSTInsertions() {
        Integer[][] gt1 = {{5}};
        Integer[][] gt2 = {{5},{null,7}};

        BST<Integer> tree = new BST<>(5);
        verifyBT(tree, gt1);

        tree.insert(7);
        verifyBT(tree, gt2);

        //It's already there and should move on + not change tree
        tree.insert(7);
        verifyBT(tree, gt2);
    }

    @Test
    public void testBSTLookup() {
        Integer[][] gt2 = {{5},{null,7}};
        Integer[][] gt3 = {{7}};
        Integer[][] gt4 = {{1}};

        //lookup root
        BST<Integer> tree = new BST<>(5);
        tree.insert(7);
        verifyBT(tree.lookup(5), gt2);

        //look up leaf right
        verifyBT(tree.lookup(7), gt3);

        //lookup leaf left
        tree.insert(2);
        tree.insert(1);
        verifyBT(tree.lookup(1), gt4);

        //null because not found
        assertNull(tree.lookup(12));
    }

        @Test
    public void testBSTDelete() {
        Integer[][] gt1 = {{8},{5,10}, {2,7,9,12}};
        Integer[][] gt2 = {{8},{5,10}, {null,7,9,12}};
        Integer[][] gt3 = {{8},{7,10}, {null,null,9,12}};
        Integer[][] gt4 = {{8},{5,10}, {2,null,9,12}};
        Integer[][] gt5 = {{8},{2,10}, {null,null,9,12}};
        Integer[][] gt6 = {{7}};
        Integer[][] gt7 = {{7},{5,10}, {2,null,9,12}};
        Integer[][] gt8 = {{5},{2,10}, {null,null,9,12}};
        Integer[][] gt9 = {{8},{2,10}, {null,7,9,12}};

        //Making Trees
        BST<Integer> tree = new BST<>(8);
        tree.insert(5);
        tree.insert(10);
        tree.insert(2);
        tree.insert(7);
        tree.insert(9);
        tree.insert(12);
        verifyBT(tree, gt1);
        BST<Integer> tree1 = new BST<>(8);
        tree1.insert(5);
        tree1.insert(10);
        tree1.insert(2);
        tree1.insert(7);
        tree1.insert(9);
        tree1.insert(12);
        verifyBT(tree1, gt1);
        BST<Integer> tree2 = new BST<>(7);
        BST<Integer> tree3 = new BST<>(5);
        tree3.insert(7);
        BST<Integer> tree4 = new BST<>(8);
        tree4.insert(5);
        tree4.insert(10);
        tree4.insert(2);
        tree4.insert(7);
        tree4.insert(9);
        tree4.insert(12);
        verifyBT(tree4, gt1);
        BST<Integer> tree5 = new BST<>(8);
        tree5.insert(5);
        tree5.insert(10);
        tree5.insert(2);
        tree5.insert(7);
        tree5.insert(9);
        tree5.insert(12);
        verifyBT(tree5, gt1);

        //remove 2
        verifyBT(tree.deleteWithCopyLeft(2), gt2);

        //given 2 removed remove 5
        verifyBT(tree.deleteWithCopyLeft(5), gt3);

        //remove 7
        verifyBT(tree1.deleteWithCopyLeft(7), gt4);

        //given 7 removed remove 5
        verifyBT(tree1.deleteWithCopyLeft(5), gt5);

        //one node removing root
        assertNull(tree2.deleteWithCopyLeft(7));
        
        //degree one removing root
        verifyBT(tree3.deleteWithCopyLeft(5), gt6);

        //remove 5 with two children
        verifyBT(tree5.deleteWithCopyLeft(5), gt9);

        //remove root, degree 2
        verifyBT(tree4.deleteWithCopyLeft(8), gt7);
        //remove root again
        verifyBT(tree4.deleteWithCopyLeft(7), gt8);

        //can't remove something that doesn't exist
        verifyBT(tree, gt3);
        verifyBT(tree.deleteWithCopyLeft(16), gt3);
    }

        @Test
    public void testBSTrotateLeft() {
        Integer[][] gt1 = {{18},{12,24}, {6,16,null,32}, {3,9,15,17,null,null, null, null}};
        Integer[][] gt2 = {{18},{12,null}, {6,null,null,null}};
        Integer[][] gt3 = {{18},{12,null}};

        //Larger Tree Building
        BST<Integer> tree = new BST<>(12);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(9);
        tree.insert(16);
        tree.insert(24);
        tree.insert(15);
        tree.insert(17);
        tree.insert(32);
        //Smaller tree Building
        BST<Integer> tree2 = new BST<>(12);
        tree2.insert(6);
        tree2.insert(18);

        //Smallest tree Building
        BST<Integer> tree3 = new BST<>(18);
        tree3.insert(12);
        
        

        //Largre tree
        verifyBT(tree.rotateLeft(), gt1);

        //Smaller tree
        verifyBT(tree2.rotateLeft(), gt2);

        //No rotate
        verifyBT(tree3.rotateLeft(), gt3);

    }

            @Test
    public void testBSTrotateRight() {
        Integer[][] gt1 = {{12},{6,18}, {3,9,16,24}};
        Integer[][] gt2 = {{6},{null,12}, {null,null,null,18}};
        Integer[][] gt3 = {{18},{null,20}};

        //Larger Tree Building
        BST<Integer> tree = new BST<>(18);
        tree.insert(12);
        tree.insert(24);
        tree.insert(6);
        tree.insert(16);
        tree.insert(3);
        tree.insert(9);
        //Smaller tree Building
        BST<Integer> tree2 = new BST<>(12);
        tree2.insert(6);
        tree2.insert(18);

        //Smallest tree Building
        BST<Integer> tree3 = new BST<>(18);
        tree3.insert(20);
        
        

        //Largre tree
        verifyBT(tree.rotateRight(), gt1);

        //Smaller tree
        verifyBT(tree2.rotateRight(), gt2);

        //No rotate
        verifyBT(tree3.rotateRight(), gt3);

    }
}