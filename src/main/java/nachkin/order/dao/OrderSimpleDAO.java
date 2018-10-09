package nachkin.order.dao;

import nachkin.order.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderSimpleDAO implements OrderDAO {

    private List<Order> orders = new ArrayList<>();

    OrderSimpleDAO() {
        addOrder(new Order("+7-911-890-7766", "sokolov@yandex.ru", "name1", "Andrew"));
        addOrder(new Order("+7-911-890-7645", "ivanov@google.com", "name2", "Ivan"));
        addOrder(new Order("+7-911-850-3156", "semenova@mail.ru", "name3", "Ann"));
    }

    @Override
    public List<Order> findOrders() {
        return orders;
    }

    @Override
    public Order getOrder(Long orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }

    private Long generateOrderId() {
        Long orderId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        while (getOrder(orderId) != null) {
            orderId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        }
        return orderId;
    }

    @Override
    public Long addOrder(Order order) {
        Long id = generateOrderId();
        order.setOrderId(id);
        orders.add(order);
        return id;
    }

    @Override
    public void updateOrder(Order order) {
        Order oldOrder = getOrder(order.getOrderId());
        if (oldOrder != null) {
            oldOrder.setEmail(order.getEmail());
            oldOrder.setNameCustomer(order.getNameCustomer());
            oldOrder.setNamePizza(order.getNamePizza());
            oldOrder.setPhone(order.getPhone());
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        orders.removeIf((Order order) -> order.getOrderId().equals(orderId));
        /*
        for (Iterator<Order> iterator = orders.iterator(); iterator.hasNext();) {
            Order order = iterator.next();
            if (order.getOrderId().equals(orderId)) {
                iterator.remove();
            }
        }
        */
    }

}
