import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Jimmy Yuan
 * @author Jackson Jiang
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /*
     * Test cases for constructors.
     */
    @Test
    public final void testNoArgumentConstructor() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.constructorTest();
        Set<String> qExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for Add.
     */
    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef("a");
        /*
         * Call methods
         */
        q.add("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddOneRight() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1");
        Set<String> qExpected = this.createFromArgsRef("1", "2");
        /*
         * Call methods
         */
        q.add("2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddOneLeft() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2");
        Set<String> qExpected = this.createFromArgsRef("1", "2");
        /*
         * Call methods
         */
        q.add("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddMoreThanOneRight() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("a", "b");
        Set<String> qExpected = this.createFromArgsRef("a", "b", "c");
        /*
         * Call methods
         */
        q.add("c");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testAddMoreThanOneLeft() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2", "3");
        Set<String> qExpected = this.createFromArgsRef("1", "2", "3");
        /*
         * Call methods
         */
        q.add("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for Remove.
     */
    @Test
    public final void testRemoveEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2");
        Set<String> qExpected = this.createFromArgsRef("2");
        /*
         * Call methods
         */
        String x = q.remove("2");
        String xExpected = qExpected.remove("2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(xExpected, x);
    }

    @Test
    public final void testRemoveOneRoot() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2", "3");
        Set<String> qExpected = this.createFromArgsRef("2", "3");
        /*
         * Call methods
         */
        String x = q.remove("2");
        String xExpected = qExpected.remove("2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(xExpected, x);
    }

    @Test
    public final void testRemoveOneRight() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2", "3");
        Set<String> qExpected = this.createFromArgsRef("2", "3");
        /*
         * Call methods
         */
        String x = q.remove("3");
        String xExpected = qExpected.remove("3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(xExpected, x);
    }

    @Test
    public final void testRemoveOneLeft() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2", "1");
        Set<String> qExpected = this.createFromArgsRef("2", "1");
        /*
         * Call methods
         */
        String x = q.remove("1");
        String xExpected = qExpected.remove("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(xExpected, x);
    }

    @Test
    public final void testMultipleRemoveLeft() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2", "3", "1");
        Set<String> qExpected = this.createFromArgsRef("2", "3", "1");
        /*
         * Call methods
         */
        String x = q.remove("1");
        String xExpected = qExpected.remove("1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(xExpected, x);
    }

    @Test
    public final void testMultipleRemoveRight() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2", "3", "1");
        Set<String> qExpected = this.createFromArgsRef("2", "3", "1");
        /*
         * Call methods
         */
        String x = q.remove("3");
        String xExpected = qExpected.remove("3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(xExpected, x);
    }

    @Test
    public final void testMultipleRemoveRoot() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2", "3", "1");
        Set<String> qExpected = this.createFromArgsRef("2", "3", "1");
        /*
         * Call methods
         */
        String x = q.remove("2");
        String xExpected = qExpected.remove("2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(xExpected, x);
    }

    /*
     * Test cases for RemoveAny.
     */
    @Test
    public final void testRemoveAnyLeavingEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2");
        Set<String> qExpected = this.createFromArgsRef("2");
        /*
         * Call methods
         */
        String x = q.removeAny();
        boolean in = qExpected.contains(x);
        assertEquals(true, in);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testRemoveAnyLeavingOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2", "3.3");
        Set<String> qExpected = this.createFromArgsRef("2", "3.3");
        /*
         * Call methods
         */
        String x = q.removeAny();
        boolean in = qExpected.contains(x);
        assertEquals(true, in);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    @Test
    public final void testRemoveAnyLeavingMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("2", "3.3", "1");
        Set<String> qExpected = this.createFromArgsRef("2", "3.3", "1");
        /*
         * Call methods
         */
        String x = q.removeAny();
        boolean in = qExpected.contains(x);
        assertEquals(true, in);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /*
     * Test cases for Size.
     */
    @Test
    public final void testSizeZero() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Call methods
         */
        int size = q.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(0, size);
    }

    @Test
    public final void testSizeOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1.1");
        Set<String> qExpected = this.createFromArgsRef("1.1");
        /*
         * Call methods
         */
        int size = q.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(1, size);
    }

    @Test
    public final void testSizeMoreThanOne() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("1.1", "2.2", "3.3");
        Set<String> qExpected = this.createFromArgsRef("1.1", "2.2", "3.3");
        /*
         * Call methods
         */
        int size = q.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(3, size);
    }

    /*
     * Test cases for contains.
     */
    @Test
    public final void testContainsEmpty() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest();
        Set<String> qExpected = this.createFromArgsRef();
        /*
         * Call methods
         */
        boolean result = q.contains("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(false, result);
    }

    @Test
    public final void testContainsOneTrue() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("a");
        Set<String> qExpected = this.createFromArgsRef("a");
        /*
         * Call methods
         */
        boolean result = q.contains("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(true, result);
    }

    @Test
    public final void testContainsOneFalse() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("b");
        Set<String> qExpected = this.createFromArgsRef("b");
        /*
         * Call methods
         */
        boolean result = q.contains("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(false, result);
    }

    @Test
    public final void testContainsMoreThanOneFalse() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("a", "b", "c");
        Set<String> qExpected = this.createFromArgsRef("a", "b", "c");
        /*
         * Call methods
         */
        boolean result = q.contains("d");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(false, result);
    }

    @Test
    public final void testContainsMoreThanOneRootTrue() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("a", "b", "c");
        Set<String> qExpected = this.createFromArgsRef("a", "b", "c");
        /*
         * Call methods
         */
        boolean result = q.contains("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(true, result);
    }

    @Test
    public final void testContainsMoreThanOneRightTrue() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("a", "b", "c");
        Set<String> qExpected = this.createFromArgsRef("a", "b", "c");
        /*
         * Call methods
         */
        boolean result = q.contains("b");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(true, result);
    }

    @Test
    public final void testContainsMoreThanOneLeftTrue() {
        /*
         * Set up variables and call method under test
         */
        Set<String> q = this.createFromArgsTest("b", "a", "c");
        Set<String> qExpected = this.createFromArgsRef("b", "a", "c");
        /*
         * Call methods
         */
        boolean result = q.contains("a");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(true, result);
    }
}
