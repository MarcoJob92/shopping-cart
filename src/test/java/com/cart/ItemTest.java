package com.cart;

import com.cart.model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    void should_getTotalPrice_when_notOnOffer() {
        var item = new Item("item",2,1,false);
        assertEquals("£2.00", item.getTotalPrice());

        item = new Item("item",2.49,2,false);
        assertEquals("£4.98", item.getTotalPrice());
    }

    @Test
    void should_getTotalPrice_when_onOffer() {
        var item = new Item("item",2,1,true);
        assertEquals("£2.00", item.getTotalPrice());

        item = new Item("item",2,2,true);
        assertEquals("£2.00", item.getTotalPrice());

        item = new Item("item",2,9,true);
        assertEquals("£10.00", item.getTotalPrice());
    }

    @Test
    void should_getQuantityString() {
        var item = new Item("item",2,1,false);
        assertEquals("", item.getQuantityString());

        item = new Item("item",2,2,false);
        assertEquals("X2", item.getQuantityString().trim());

        item = new Item("item",2,10,false);
        assertEquals("X10", item.getQuantityString().trim());
    }
}
