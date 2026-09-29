package framework.utils;

public class TestDataUtils {

    private TestDataUtils() {
        // prevent instantiation — utility class
    }

    public static String generateRandomEmail() {
        return "invalid_" + java.util.UUID.randomUUID().toString().substring(0, 8) + "@test.com";
    }
    public static String generateRandomPassword() {
        return "P@s_" + System.currentTimeMillis();
    }
}
