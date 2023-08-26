package example.application;

import example.data.PantryItem;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.conversions.ToStringConversion;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PantryItemTest {

    LocalDate currentDate = LocalDate.of(23,01,12);
    PantryItem item1;

    @BeforeEach
    public void setUp(){
        item1 = new PantryItem("Apple", "Red Gala", 1, 2, PantryItem.MassUnit.KILOGRAM,
                currentDate, PantryItem.ItemType.FRUIT, true);
    }

    @Test
    public void testGetItemName() {
        assertEquals("Apple", item1.getItemName());
    }

    @Test
    public void testGetItemDescription() {
        assertEquals("Red Gala", item1.getDescription());
    }

    @Test
    public void testGetItemWeight() {
        assertEquals(2, item1.getWeight());
    }

    @Test
    public void testGetExpiry() {
        assertEquals(LocalDate.of(23,01,12), currentDate);
    }

    @Test
    public void testGetQuantity() {
        assertEquals(1, item1.getQuantity());
    }

    @Test
    public void testGetItemType() {
        assertEquals("FRUIT", item1.getItemType().toString());
    }

    @Test
    public void testModifyItemName() {
        item1.modifyItemName("Oranges");
        assertEquals("Oranges", item1.getItemName());
    }

    @Test
    public void testModifyDescription() {
        item1.modifyDescription("Sweet oranges");
        assertEquals("Sweet oranges", item1.getDescription());
    }

    @Test
    public void testModifyWeight() {
        item1.modifyWeight(1200);
        assertEquals(1200, item1.getWeight());
    }

    @Test
    public void testModifyExpiry() {
        LocalDate newExpiry = LocalDate.now().plusDays(1); // Add one day to the current date
        item1.modifyExpiry(newExpiry);
        assertEquals(newExpiry, item1.getExpiry());
    }

    @Test
    public void testModifyShelfLife() {
        assertEquals(1, item1.getQuantity());
        item1.modifyQuantity(7);
        assertEquals(7, item1.getQuantity());
    }

    @Test
    public void testModifyItemType() {
        item1.modifyItemType(PantryItem.ItemType.VEGETABLE, false);
        assertEquals("VEGETABLE", item1.getItemType().toString());
    }
}
