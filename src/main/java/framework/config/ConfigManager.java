package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import framework.driver.BrowserType;

public class ConfigManager {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found on classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private ConfigManager() {
        // prevent instantiation — utility class
    }

    public static String getBaseUrl() {
        return System.getProperty("baseUrl", properties.getProperty("baseUrl"));
    }

    public static int getTimeout() {
        String override = System.getProperty("timeout");
        return override != null ? Integer.parseInt(override) : Integer.parseInt(properties.getProperty("timeout"));
    }

    public static int getMaxAttempt() {
        String override = System.getProperty("maxAttempt");
        return override != null ? Integer.parseInt(override) : Integer.parseInt(properties.getProperty("maxAttempt"));
    }

    public static BrowserType getBrowser() {
        String value = System.getProperty("browser", properties.getProperty("browser"));
        return BrowserType.valueOf(value.toUpperCase());
    }

    public static String getValidEmail() {
        String value = System.getenv("TN_VALID_EMAIL");
        if (value == null) {
            throw new RuntimeException("TN_VALID_EMAIL environment variable not set");
        }
        return value;
    }

    public static String getValidPassword() {
        String value = System.getenv("TN_VALID_PASSWORD");
        if (value == null) {
            throw new RuntimeException("TN_VALID_PASSWORD environment variable not set");
        }
        return value;
    }
}