package nachkin.order.exception;

public class OrderDAOException extends Exception {

    public OrderDAOException() {}

    public OrderDAOException(String message) {
        super(message);
    }

    public OrderDAOException(Throwable cause) {
        super(cause);
    }

    public OrderDAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
