package nachkin.order.dao;

import nachkin.order.entity.Order;
import nachkin.order.exception.OrderDAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDbDAO implements OrderDAO {

    private static final String SELECT = "SELECT order_id, pizza_name, customer_name, phone, email FROM jc_contact ORDER BY customer_name, pizza_name";
    private static final String SELECT_ONE = "SELECT order_id, pizza_name, customer_name, phone, email FROM jc_contact WHERE order_id=?";
    private static final String INSERT = "INSERT INTO jc_contact (pizza_name, customer_name, phone, email) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE jc_contact SET pizza_name=?, customer_name=?, phone=?, email=? WHERE order_id=?";
    private static final String DELETE = "DELETE FROM jc_contact WHERE order_id=?";

    private ConnectionBuilder builder = ConnectionBuilderFactory.getConnectionBuilder();

    private Connection getConnection() throws SQLException {
        return builder.getConnection();
    }

    @Override
    public Long addOrder(Order order) throws OrderDAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT, new String[]{"order_id"})) {
            Long orderId = -1L;
            statement.setString(1, order.getNamePizza());
            statement.setString(2, order.getNameCustomer());
            statement.setString(3, order.getPhone());
            statement.setString(4, order.getEmail());
            statement.executeUpdate();
            ResultSet set = statement.getGeneratedKeys();
            if (set.next()) {
                orderId = set.getLong("order_id");
            }
            set.close();
            return orderId;
        } catch (SQLException e) {
            throw new OrderDAOException(e);
        }
    }

    private Order fillOrder(ResultSet set) throws SQLException {
        Order order = new Order();
        order.setPhone(set.getString("phone"));
        order.setNamePizza(set.getString("pizza_name"));
        order.setNameCustomer(set.getString("customer_name"));
        order.setEmail(set.getString("email"));
        order.setOrderId(set.getLong("order_id"));
        return order;
    }

    @Override
    public Order getOrder(Long orderId) throws OrderDAOException {
        Order order = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ONE)) {
            statement.setLong(1, orderId);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                order = fillOrder(set);
            }
            set.close();
        } catch (SQLException e) {
            throw new OrderDAOException(e);
        }
        return order;
    }

    @Override
    public List<Order> findOrders() throws OrderDAOException {
        List<Order> list = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT);
            ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                list.add(fillOrder(set));
            }
        } catch (SQLException e) {
            throw new OrderDAOException(e);
        }
        return list;
    }

    @Override
    public void deleteOrder(Long orderId) throws OrderDAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new OrderDAOException(e);
        }
    }

    @Override
    public void updateOrder(Order order) throws OrderDAOException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, order.getNamePizza());
            statement.setString(2, order.getNameCustomer());
            statement.setString(3, order.getPhone());
            statement.setString(4, order.getEmail());
            statement.setLong(5, order.getOrderId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new OrderDAOException(e);
        }
    }


}
