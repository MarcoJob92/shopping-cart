package com.cart.model;

import com.cart.exception.ItemNotFoundException;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@Data
public class ShoppingCart {

    private final Map<String, Item> items = new LinkedHashMap<>();

    public void add (Item item) {
        if(items.containsKey(item.getName())) {
            var currentItem = items.get(item.getName());
            currentItem.setQuantity(currentItem.getQuantity() + item.getQuantity());
        } else {
            items.put(item.getName(), item);
        }
    }

    public void remove (Item item) {
        if (!items.containsKey(item.getName()))
            throw new ItemNotFoundException("Item " + item.getName() + " not found, so it cannot be removed.");
        items.remove(item.getName());
    }

    public String getReceipt() {
        return items.values()
                .stream()
                .map(item -> item.getName() + item.getQuantityString() + "\t" + item.getTotalPrice())
                .collect(joining("\n"));
    }
}
