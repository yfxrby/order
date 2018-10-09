package nachkin.order.gui;

import nachkin.order.config.GlobalConfig;

import java.util.Locale;
import java.util.PropertyResourceBundle;

public class GUIResource {

    private static final String RESOURCES = "nachkin.order.gui.OrderResources";
    private static final String LANGUAGE = "language";

    private static PropertyResourceBundle components = null;

    public static void initComponentResources() {
        String lang = GlobalConfig.getProperty(LANGUAGE);
        if (lang != null && !lang.trim().isEmpty()) {
            components = (PropertyResourceBundle) PropertyResourceBundle.getBundle(RESOURCES, new Locale(lang));
        } else {
            components = (PropertyResourceBundle) PropertyResourceBundle.getBundle(RESOURCES);
        }
    }

    public static String getLabel(String formId, String componentId) {
        return components.getString(formId + "." + componentId);
    }

}
