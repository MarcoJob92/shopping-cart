package com.cart;

import com.cart.model.Item;
import com.cart.model.ShoppingCart;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ShoppingCartTest {

    @Test
    void should_addAnItem() {
        ShoppingCart cart = new ShoppingCart();

        cart.add(item());

        assertEquals(1, cart.getItems().size());
        assertEquals(item(), cart.getItems().get(item().getName()));
    }

    @Test
    void should_addMultipleItems() {
        ShoppingCart cart = new ShoppingCart();
        items().forEach(cart::add);

        var cartItems = cart.getItems();

        assertEquals(items().get(0), cartItems.get(items().get(0).getName()));
        assertEquals(items().get(1), cartItems.get(items().get(1).getName()));
        assertEquals(items().get(2), cartItems.get(items().get(2).getName()));
    }

    @Test
    void should_updateItemQuantity() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(item());
        assertEquals(1, cart.getItems().get(item().getName()).getQuantity());

        var item = item();
        item.setQuantity(2);
        cart.add(item);

        assertEquals(3, cart.getItems().get(item().getName()).getQuantity());
    }

    @Test
    void should_removeAnItem() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(item());
        assertEquals(1, cart.getItems().size());

        cart.remove(item());

        assertEquals(0, cart.getItems().size());
    }

    @Test
    void should_removeAnItem_when_fewItemsInShoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        items().forEach(cart::add);
        assertEquals(3, cart.getItems().size());

        var item2 = items().get(1);
        cart.remove(item2);

        assertEquals(2, cart.getItems().size());
        assertFalse(cart.getItems().containsKey(item2.getName()));
    }

    @Test
    void should_throwItemNotFoundException_when_removingAnItemThatIsNotPresent() {
        ShoppingCart cart = new ShoppingCart();
        cart.add(item());
        assertEquals(1, cart.getItems().size());

        cart.remove(item());

        assertEquals(0, cart.getItems().size());
    }

    @Test
    void should_getReceipt_when_noOffer () {
        var cart = new ShoppingCart();
        items().stream()
               .peek(item -> item.setOnOffer(false))
               .forEach(cart::add);

        var receipt = cart.getReceipt();

        assertEquals("item1\t£2.00\nitem2 X2\t£9.80\nitem3 X4\t£28.00", receipt);
    }

    @Test
    void should_getReceipt_when_someItemsAreOnOffer () {
        var cart = new ShoppingCart();
        items().forEach(cart::add);
        cart.add(new Item("item4", 3.55, 5, true));

        var receipt = cart.getReceipt();

        assertEquals("item1\t£2.00\nitem2 X2\t£9.80\nitem3 X4\t£14.00\nitem4 X5\t£10.65", receipt);
    }

    private Item item() {
        return new Item("item1",2,1,false);
    }

    private ArrayList<Item> items() {
        return new ArrayList<>() {{
            add(item());
            add(new Item("item2",4.9,2,false));
            add(new Item("item3",7,4,true));
        }};
    }
}
