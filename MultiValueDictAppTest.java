import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class MultiValueDictAppTest {
    private static MultiValueDictApp testApp;

    @BeforeEach
    void setUp() {
        // Initialize an empty MultiValueDictApp before each test
        testApp = new MultiValueDictApp();
    }

    @Test
    void testAdd() {
        String[] addCommand = {"add", "foo", "bar"};
        testApp.add(addCommand);

        assertTrue(testApp.map.containsKey("foo"));
        assertTrue(testApp.map.get("foo").contains("bar"));
    }

    @Test
    void testAdd_duplicatedMember_failed() {
        String[] addCommand = {"add", "foo", "bar"};
        testApp.add(addCommand);
        testApp.add(addCommand);

        assertTrue(testApp.map.containsKey("foo"));
        assertEquals(1, testApp.map.get("foo").size());
    }

    @Test
    void testAdd_differentMembers_added() {
        testApp.add(new String[] {"add", "foo", "bar"});
        testApp.add(new String[] {"add", "foo", "2 bar"});

        assertTrue(testApp.map.containsKey("foo"));
        assertEquals(2, testApp.map.get("foo").size());
    }

    @Test
    void testAdd_multipleKeys_added() {
        testApp.add(new String[] {"add", "foo", "bar"});
        testApp.add(new String[] {"add", "bar", "foo"});

        assertEquals(2, testApp.map.size());
    }

    @Test
    void testRemoval_rightMember_removed() {
        testApp.add(new String[] {"add", "foo", "bar"});
        testApp.remove(new String[] {"remove", "foo", "bar"});

        assertEquals(0, testApp.map.size());
    }

    @Test
    void testRemoval_incorrectMember_failed() {
        testApp.add(new String[] {"add", "foo", "bar"});
        testApp.remove(new String[] {"remove", "foo", "fakeValue"});

        assertTrue(testApp.map.containsKey("foo"));
    }

    @Test
    void testRemoval_incorrectKey_failed() {
        testApp.add(new String[] {"add", "foo", "bar"});
        testApp.remove(new String[] {"remove", "fakeKey", "bar"});

        assertTrue(testApp.map.containsKey("foo"));
    }

    @Test
    void testRemoveAll_rightKey_removed() {
        testApp.add(new String[] {"add", "foo", "bar"});
        testApp.add(new String[] {"add", "foo", "baz"});

        assertTrue(testApp.map.containsKey("foo"));
        assertEquals(2, testApp.map.get("foo").size());

        testApp.removeall(new String[] {"remove", "foo"});

        assertFalse(testApp.map.containsKey("foo"));
    }

    @Test
    void testRemoveAll_incorrectKey_failed() {
        testApp.add(new String[] {"add", "foo", "bar"});
        testApp.add(new String[] {"add", "foo", "baz"});

        assertTrue(testApp.map.containsKey("foo"));
        assertEquals(2, testApp.map.get("foo").size());

        testApp.removeall(new String[] {"remove", "fakeKey"});

        assertTrue(testApp.map.containsKey("foo"));
    }

    @Test
    void testClear_clearAllKeyValues() {
        testApp.add(new String[] {"add", "foo", "bar"});
        testApp.add(new String[] {"add", "foo", "baz"});
        testApp.add(new String[] {"add", "xxxx", "bar"});
        testApp.add(new String[] {"add", "xxxx", "baz"});

        testApp.clear();

        assertEquals(0, testApp.map.size());
    }
}
