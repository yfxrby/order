package nachkin.order.gui;

import nachkin.order.entity.Order;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class OrderModel extends AbstractTableModel {

    private static final String[] headers = {"id", "phone", "email", "namePizza", "nameCustomer"};
    private static final String MODEL = "model";

    private final List<Order> orders;

    public OrderModel(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int getRowCount() {
        return orders.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int column) {
        return GUIResource.getLabel(MODEL, headers[column]);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = orders.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return order.getOrderId().toString();
            case 1:
                return order.getPhone();
            case 2:
                return order.getEmail();
            case 3:
                return order.getNamePizza();
            default:
                  return order.getNameCustomer();
        }
    }
}
