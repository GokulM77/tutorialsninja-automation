package framework.utils;

public class TestDataUtils {

    private TestDataUtils() {
        // prevent instantiation — utility class
    }

    public static String generateRandomEmail() {
        return "invalid_" + System.currentTimeMillis() + "@test.com";
    }
}
