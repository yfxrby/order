package nachkin.order.dao;

public class ConnectionBuilderFactory {

    public static ConnectionBuilder getConnectionBuilder() {
        //return new ComboConnectionBuilder();
        return new SimpleConnectionBuilder();
    }

}
