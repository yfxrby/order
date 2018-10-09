package nachkin.order.gui;

import nachkin.order.business.OrderManager;
import nachkin.order.entity.Order;
import nachkin.order.exception.OrderBusinessException;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class OrderFrame extends JFrame implements ActionListener {

    private static final String FRAME = "frame";
    private static final String BUTTON_UPDATE = "update";
    private static final String BUTTON_REFRESH = "refresh";
    private static final String BUTTON_DELETE = "delete";
    private static final String BUTTON_ADD = "add";

    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String EDIT = "EDIT";
    private static final String DELETE = "DELETE";

    private final OrderManager orderManager = new OrderManager();
    private final JTable orderTable = new JTable();

    public OrderFrame() {

        orderTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(5, 5, 0, 5);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(gridBagLayout);

        //GUIResource.initComponentResources();

        btnPanel.add(createButton(gridBagLayout, gridBagConstraints, GUIResource.getLabel(FRAME, BUTTON_REFRESH), LOAD));
        btnPanel.add(createButton(gridBagLayout, gridBagConstraints, GUIResource.getLabel(FRAME, BUTTON_ADD), ADD));
        btnPanel.add(createButton(gridBagLayout, gridBagConstraints, GUIResource.getLabel(FRAME, BUTTON_UPDATE), EDIT));
        btnPanel.add(createButton(gridBagLayout, gridBagConstraints, GUIResource.getLabel(FRAME, BUTTON_DELETE), DELETE));

        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        left.add(btnPanel, BorderLayout.NORTH);
        add(left, BorderLayout.WEST);

        add(new JScrollPane(orderTable), BorderLayout.CENTER);
        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            loadOrder();
        } catch (OrderBusinessException e) {
            e.printStackTrace();
        }

    }

    private JButton createButton(GridBagLayout gridBagLayout, GridBagConstraints gridBagConstraints, String title, String action) {
        JButton button = new JButton(title);
        button.setActionCommand(action);
        button.addActionListener(this);
        gridBagLayout.setConstraints(button, gridBagConstraints);
        return button;
    }

    private void loadOrder() throws OrderBusinessException {
        List<Order> orders = orderManager.findOrders();
        OrderModel orderModel = new OrderModel(orders);
        orderTable.setModel(orderModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        try {
            switch (action) {
                case LOAD:
                    loadOrder();
                    break;
                case ADD:
                    addOrder();
                    break;
                case EDIT:
                    editOrder();
                    break;
                case DELETE:
                    deleteOrder();
                    break;
            }
        } catch (OrderBusinessException ex) {
            ex.printStackTrace();
        }
    }

    private void saveOrder(EditOrderDialog editOrderDialog) throws OrderBusinessException {
        if (editOrderDialog.isSave()) {
            Order order = editOrderDialog.getOrder();
            if (order.getOrderId() != null) {
                orderManager.updateOrder(order);
            } else {
                orderManager.addOrder(order);
            }
        }
        loadOrder();
    }

    private void addOrder() throws OrderBusinessException {
        EditOrderDialog editOrderDialog = new EditOrderDialog();
        saveOrder(editOrderDialog);
    }

    private void editOrder() throws OrderBusinessException {
        int selectRow = orderTable.getSelectedRow();
        if (selectRow != -1) {
            Long id = Long.parseLong(orderTable.getModel().getValueAt(selectRow, 0).toString());
            Order order = orderManager.getOrder(id);
            EditOrderDialog editOrderDialog = new EditOrderDialog(order);
            saveOrder(editOrderDialog);
        } else {
            JOptionPane.showMessageDialog(this, "You should select a row for editing");
        }
    }

    private void deleteOrder() throws OrderBusinessException {
        int selectRow = orderTable.getSelectedRow();
        if (selectRow != -1) {
            Long id = Long.parseLong(orderTable.getModel().getValueAt(selectRow, 0).toString());
            orderManager.deleteOrder(id);
            loadOrder();
        } else {
            JOptionPane.showMessageDialog(this, "You should select a row for deleting");
        }
    }

}
