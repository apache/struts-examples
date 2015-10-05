package org.apache.examples.struts.services;

import org.apache.examples.struts.models.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrdersService {

    private static Map<String,Order> orders = new HashMap<>();
    private static int nextId = 6;
    static {
        orders.put("3", new Order("3", "Bob", 33));
        orders.put("4", new Order("4", "Sarah", 44));
        orders.put("5", new Order("5", "Jim", 66));
    }

    public Order get(String id) {
        return orders.get(id);
    }

    public List<Order> getAll() {
        return new ArrayList<>(orders.values());
    }

    public void save(Order order) {
        if (order.getId() == null) {
            order.setId(String.valueOf(nextId++));
        }

        orders.put(order.getId(), order);
    }

    public void remove(String id) {
        orders.remove(id);
        //throw new RuntimeException("Order remove currently not supported!");
    }

}
