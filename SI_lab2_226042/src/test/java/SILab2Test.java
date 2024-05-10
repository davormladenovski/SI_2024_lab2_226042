import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;


import java.util.Arrays;
import java.util.List;

public class SILab2Test {

    @Test
    public void Every_Statement() {

        //testNullItems
        List<Item> allItems = null;
        RuntimeException ex;
        ex = Assertions.assertThrows(RuntimeException.class, () -> SILab2.checkCart(allItems, 100));
        Assertions.assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        //testItemsWithoutName
        List<Item> itemsWithNameNull = Arrays.asList(
                new Item(null, "12345", 50, 0.1F),
                new Item("", "67890", 100, 0.0F)
        );
        Assertions.assertDoesNotThrow(() -> SILab2.checkCart(itemsWithNameNull, 100));
        Assertions.assertFalse(() -> SILab2.checkCart(itemsWithNameNull, 100));

        //testNullBarcode
        List<Item> itemsWithBarcodeNull = Arrays.asList(
                new Item("Item1", null, 50, 0.1F),
                new Item(null, "12345", 50, 0.1F)

        );
        ex = Assertions.assertThrows(RuntimeException.class, () -> SILab2.checkCart(itemsWithBarcodeNull, 100));
        Assertions.assertTrue(ex.getMessage().contains("No barcode!"));

        //testInvalidBarcode
        List<Item> itemsWithInvalidBarcode = Arrays.asList(
                new Item("Item1", "123A45", 50, 0.1F),
                new Item("Item2", "67890", 100, 0.0F)
        );
        ex = Assertions.assertThrows(RuntimeException.class, () -> SILab2.checkCart(itemsWithInvalidBarcode, 100));
        Assertions.assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        //testSpecialDiscount
        List<Item> itemsForSpecialDiscount = Arrays.asList(
                new Item("Item1", "012345", 400, 0.1F),
                new Item("Item2", "12345", 200, 0.1F)
        );
        Assertions.assertTrue(SILab2.checkCart(itemsForSpecialDiscount, 1000));

    }

    @Test
    public void testSpecialDiscountCondition(){
        List<Item> allItems = Arrays.asList(
        new Item("Product A", "012345", 400, 0.2f),
        new Item("Product B", "123456", 200, 0.1f),
        new Item("Product C", "034567", 500, 0.25f)
        );

        float expectedSum = (float) ((400*0.2F)-30 + (200*0.1F) + (500*0.25)-30);
        
        Assertions.assertTrue(SILab2.checkCart(allItems,(int)expectedSum));

    }

}
