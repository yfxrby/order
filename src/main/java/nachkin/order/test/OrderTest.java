package nachkin.order.test;

import nachkin.order.config.GlobalConfig;
import nachkin.order.gui.GUIResource;
import nachkin.order.gui.OrderFrame;

import java.io.IOException;

public class OrderTest {

    public static void main(String[] args) {

        try {
            GlobalConfig.initGlobalConfig();
            GUIResource.initComponentResources();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        OrderFrame orderFrame = new OrderFrame();
        orderFrame.setVisible(true);

    }

}
