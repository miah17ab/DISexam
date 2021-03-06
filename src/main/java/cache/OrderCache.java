package cache;

//TODO: Build this cache and use it. (FIXED)

import controllers.OrderController;
import java.util.ArrayList;
import model.Order;
import utils.Config;

public class OrderCache {

    // List of orders
    private ArrayList<Order> orders;

    // Time cache should live
    private long ttl;

    // Sets when the cache has been created
    private long created;

    // Get OrderTtl from the file config in a constructor
    public OrderCache() {
        this.ttl = Config.getOrderTtl();
    }

    // Takes orders from a arraylist, where it will get orders if boolean is false
    public ArrayList<Order> getOrders(Boolean forceUpdate) {

        // If we whis to clear cache, we can set force update.
        // Otherwise we look at the age of the cache and figure out if we should update.
        // If the list is empty we also check for new orders
        if (forceUpdate
                || ((this.created + this.ttl) <= (System.currentTimeMillis() / 1000L))
                || this.orders.isEmpty()) {

            // Get orders from controller, since we wish to update.
            ArrayList<Order> orders = OrderController.getOrders();

            // Set orders for the instance and set created timestamp
            this.orders = orders;
            this.created = System.currentTimeMillis() / 1000L;

        }

        // Return the orders from the arraylist
        return this.orders;
    }
}