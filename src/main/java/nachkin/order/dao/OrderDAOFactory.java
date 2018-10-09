package nachkin.order.dao;

import nachkin.order.config.GlobalConfig;
import nachkin.order.entity.Order;

public class OrderDAOFactory {

    public static OrderDAO getOrderDAO() {
        try {
            Class dao = Class.forName(GlobalConfig.getProperty("nachkin.order.dao.class"));
            return (OrderDAO)dao.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
