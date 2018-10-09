package nachkin.order.gui;

import nachkin.order.entity.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditOrderDialog extends JDialog implements ActionListener {

    private static final String SAVE = "SAVE";
    private static final String CANCEL = "CANCEL";
    private static final String DIALOG = "dialog";
    private static final String NAME_PIZZA = "namePizza";
    private static final String NAME_CUSTOMER = "nameCustomer";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";

    private static final int PAD = 10;
    private static final int W_L = 100;
    private static final int W_T = 300;
    private static final int W_B = 120;
    private static final int H_B = 25;

    private final JTextPane txtPizzaName = new JTextPane();
    private final JTextPane txtCustomerName = new JTextPane();
    private final JTextPane txtPhone = new JTextPane();
    private final JTextPane txtEmail = new JTextPane();

    private Long contactId = null;
    private boolean save = false;

    public EditOrderDialog() {
        this(null);
    }

    public EditOrderDialog(Order order) {
        setLayout(null);
        buildFields();
        initFields(order);
        buildButtons();

        setModal(true);
        setResizable(false);
        setBounds(300, 300, 450, 200);
        setVisible(true);
    }

    private void buildFields() {
        /*
        buildField(txtPizzaName, "Name of pizza", 0);
        buildField(txtCustomerName, "Customer's name", 1);
        buildField(txtPhone, "phone", 2);
        buildField(txtEmail, "Email", 3);
        */
        buildField(txtPizzaName, GUIResource.getLabel(DIALOG, NAME_PIZZA), 0);
        buildField(txtCustomerName, GUIResource.getLabel(DIALOG, NAME_CUSTOMER), 1);
        buildField(txtPhone, GUIResource.getLabel(DIALOG, PHONE), 2);
        buildField(txtEmail, GUIResource.getLabel(DIALOG, EMAIL), 3);
    }

    private void buildField(JTextPane jTextPane, String title, int height) {
        JLabel label = new JLabel(title + ":");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setBounds(new Rectangle(PAD, height * H_B + PAD, W_L, H_B));
        add(label);
        jTextPane.setBounds(new Rectangle(W_L + 2 * PAD, height * H_B + PAD, W_T, H_B));
        jTextPane.setBorder(BorderFactory.createEtchedBorder());
        add(jTextPane);
    }

    private void initFields(Order order) {
        if (order != null) {
            contactId = order.getOrderId();
            txtEmail.setText(order.getEmail());
            txtCustomerName.setText(order.getNameCustomer());
            txtPizzaName.setText(order.getNamePizza());
            txtPhone.setText(order.getPhone());
        }
    }

    private void buildButtons() {
        buildButton(SAVE, 0);
        buildButton(CANCEL, 1);
    }

    private void buildButton(String title, int tab) {
        JButton button = new JButton(title);
        button.setActionCommand(title);
        button.addActionListener(this);
        button.setBounds(new Rectangle(tab * W_B + (tab + 1) * PAD, 5 * H_B + PAD, W_B, H_B));
        add(button);
    }

    public boolean isSave() {
        return save;
    }

    public Order getOrder() {
        return new Order(contactId, txtPhone.getText(), txtEmail.getText(), txtPizzaName.getText(), txtCustomerName.getText());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        save = SAVE.equals(action);
        setVisible(false);
    }
}
