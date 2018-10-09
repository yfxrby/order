package nachkin.order.dao;

import nachkin.order.entity.Order;
import nachkin.order.exception.OrderDAOException;

import java.util.List;

public interface OrderDAO {

    public Long addOrder(Order order) throws OrderDAOException;

    public void updateOrder(Order order) throws OrderDAOException;

    public void deleteOrder(Long orderId) throws OrderDAOException;

    public Order getOrder(Long orderId) throws OrderDAOException;

    public List<Order> findOrders() throws OrderDAOException;

}
