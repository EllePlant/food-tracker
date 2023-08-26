package example.application;

import static org.junit.jupiter.api.Assertions.*;

import example.data.PantryItem;
import example.data.User;
import org.junit.jupiter.api.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
public class PantryCollectionTest {

    User testUser;

    PantryItem item1;
    PantryItem item2;
    PantryItem item3;

    @BeforeEach
    public void setUp(){
        // Create a new user with an empty pantry
        testUser = new User("John", "testPass", "testEmail");

        // Add test items
        item1 = new PantryItem("testName1", "testDesc1", 1, 1, PantryItem.MassUnit.KILOGRAM,
                LocalDate.of(2023, 02, 02), PantryItem.ItemType.PORK, true);
        item2 = new PantryItem("testName2", "testDesc2", 2, 2, PantryItem.MassUnit.GRAM,
                LocalDate.of(2023, 02, 02), PantryItem.ItemType.FISH, true);
        item3 = new PantryItem("testName3", "testDesc3", 3, 3, PantryItem.MassUnit.KILOGRAM,
                LocalDate.of(2023, 03, 03), PantryItem.ItemType.RED_MEAT, true);

        // Add 2 items to the pantry
        testUser.getPantryCollection().addItem(item1);
        testUser.getPantryCollection().addItem(item2);
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Remove all users from the database.
        ArrayList<PantryItem> pantry = testUser.getPantryCollection().getPantry();
        testUser.getPantryCollection().getPantry().clear();
    }

    /* Test 1: Add an item to the user Pantry
     */
    @Test
    public void addItemToPantry() throws Exception {
        assertEquals(item1, testUser.getPantryCollection().getItem("testName1"),
                "Could not add item to pantry");
    }

    /* Test 2: Get a list of Item Names in the pantry
     */
    @Test
    public void getItemList() throws Exception {
        testUser.getPantryCollection().addItem(item3);
        assertEquals("testName1\n"+"testName2\n"+"testName3\n",
                testUser.getPantryCollection().getItemList(), "The pantry is empty");
    }

    /* Test 3: Get empty Pantry Collection List
     */
    @Test
    public void getItemListEmpty() throws Exception {
        tearDown();
        assertThrows(Exception.class, () -> {testUser.getPantryCollection().getItemList();});
    }

    /* Test 4: Remove an existing item from the pantry
     */
    @Test
    public void removeItem() throws Exception{
        // Remove and assert
        testUser.getPantryCollection().removeItem(item2);
        assertEquals(1, testUser.getPantryCollection().getPantry().size());
    }

    /* Test 5: Remove non-existing item from the pantry
     */
    @Test
    public void removeItemNoExist() throws Exception {
        // Create Test User, Pantry, and Item to try remove
        PantryItem fakeItem = new PantryItem("name", "desc", 1, 4, PantryItem.MassUnit.MILLILITRE,
                LocalDate.of(2023, 01, 01), PantryItem.ItemType.DAIRY, true);

        // Remove fail and assert exception
        assertThrows(Exception.class, () -> {testUser.getPantryCollection().removeItem(fakeItem);});
    }

}
