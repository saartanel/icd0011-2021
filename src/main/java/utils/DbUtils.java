package utils;

import java.util.Properties;

public class DbUtils {

    public static ConnectionInfo readConnectionInfo() {
        Properties properties = PropertyLoader.loadApplicationProperties();

        return new ConnectionInfo(
                properties.getProperty("dbUrl"),
                properties.getProperty("dbUser"),
                properties.getProperty("dbPassword"));
    }

}
