package nachkin.order.business;

import nachkin.order.dao.OrderDAO;
import nachkin.order.dao.OrderDAOFactory;
import nachkin.order.entity.Order;
import nachkin.order.exception.OrderBusinessException;
import nachkin.order.exception.OrderDAOException;

import java.util.List;

public class OrderManager {

    private OrderDAO dao;

    public OrderManager() {
        dao = OrderDAOFactory.getOrderDAO();
    }

    public Long addOrder(Order order) throws OrderBusinessException {
        try {
            return dao.addOrder(order);
        } catch (OrderDAOException e) {
            throw new OrderBusinessException(e);
        }
    }

    public void updateOrder(Order order) throws OrderBusinessException {
        try {
            dao.updateOrder(order);
        } catch (OrderDAOException e) {
            throw new OrderBusinessException(e);
        }
    }

    public void deleteOrder(Long orderId) throws OrderBusinessException {
        try {
            dao.deleteOrder(orderId);
        } catch (OrderDAOException e) {
            throw new OrderBusinessException(e);
        }
    }

    public Order getOrder(Long orderId) throws OrderBusinessException {
        try {
            return dao.getOrder(orderId);
        } catch (OrderDAOException e) {
            throw new OrderBusinessException(e);
        }
    }

    public List<Order> findOrders() throws OrderBusinessException {
        try {
            return dao.findOrders();
        } catch (OrderDAOException e) {
            throw new OrderBusinessException(e);
        }
    }

}
