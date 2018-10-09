package nachkin.order.entity;

public class Order {

    private Long orderId;
    private String phone;
    private String email;
    private String namePizza;
    private String nameCustomer;

    public Long getOrderId() {
        return orderId;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getNamePizza() {
        return namePizza;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNamePizza(String namePizza) {
        this.namePizza = namePizza;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public Order() {}

    public Order(Long orderId, String phone, String email, String namePizza, String nameCustomer) {
        this.email = email;
        this.nameCustomer = nameCustomer;
        this.namePizza = namePizza;
        this.orderId = orderId;
        this.phone = phone;
    }

    public Order(String phone, String email, String namePizza, String nameCustomer) {
        this.email = email;
        this.nameCustomer = nameCustomer;
        this.namePizza = namePizza;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "nachkin.order.entity.Order : " + "orderId = " + orderId + "; namePizza = " + namePizza + "; nameCustomer = " + nameCustomer + "; phone = " + phone + "; email = " + email;
    }

}
