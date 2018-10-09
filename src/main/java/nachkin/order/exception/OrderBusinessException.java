package nachkin.order.exception;

public class OrderBusinessException extends Exception {

    public OrderBusinessException() {}

    public OrderBusinessException(String message) {
        super(message);
    }

    public OrderBusinessException(Throwable cause) {
        super(cause);
    }

    public OrderBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

}
