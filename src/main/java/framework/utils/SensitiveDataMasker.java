package framework.utils;

import java.lang.reflect.Parameter;
import java.util.Set;

public class SensitiveDataMasker {

    private static final Set<String> SENSITIVE_KEYWORDS = Set.of("password", "pwd", "token", "secret");
    private static final String MASK = "****";

    private SensitiveDataMasker() {
        // prevent instantiation, utility class
    }

    public static String describe(Parameter[] declared, Object[] values) {
        StringBuilder sb = new StringBuilder();
        int count = Math.min(declared.length, values.length);
        for (int i = 0; i < count; i++) {
            if (i > 0) sb.append(", ");
            sb.append(declared[i].isNamePresent() ? declared[i].getName() : "arg" + i)
                    .append("=")
                    .append(render(declared[i], values[i]));
        }
        return sb.toString();
    }

    private static boolean isSensitive(Parameter parameter) {
        if (!parameter.isNamePresent()) {
            return true; // fail closed
        }
        String name = parameter.getName().toLowerCase();
        return SENSITIVE_KEYWORDS.stream().anyMatch(name::contains);
    }

    private static String render(Parameter parameter, Object value) {
        if (value instanceof String s && s.isEmpty()) return "[empty]"; // not secret, and HTML-safe
        return isSensitive(parameter) ? MASK : String.valueOf(value);
    }
}