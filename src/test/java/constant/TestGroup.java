package constant;

public final class TestGroup {

    public static final String SMOKE = "smoke";
    public static final String REGRESSION = "regression";

    private TestGroup() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
