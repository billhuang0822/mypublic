package com.tsb.logging;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class MaskingRegistry {

    public interface FormatChecker extends Predicate<String> {}
    public interface MaskingStrategy extends Function<String, String> {}

    private static final Map<String, FormatChecker> formatCheckers = new HashMap<>();
    private static final Map<String, MaskingStrategy> maskingStrategies = new HashMap<>();

    static {
        formatCheckers.put("mobile", val -> val != null && val.matches("^\\d{10}$"));
        maskingStrategies.put("mobile", val -> val.substring(0, 3) + "****" + val.substring(7));

        formatCheckers.put("email", val -> val != null && val.matches("^.+@.+\\..+$"));
        maskingStrategies.put("email", val -> {
            int at = val.indexOf('@');
            if (at <= 1) return "****" + val.substring(at);
            return val.charAt(0) + "****" + val.substring(at);
        });

        formatCheckers.put("default", val -> true);
        maskingStrategies.put("default", val -> {
            if (val == null || val.length() <= 4) return "****";
            return val.substring(0, 2) + "****" + val.substring(val.length() - 2);
        });
    }

    public static String maskByFormat(String value, String format) {
        FormatChecker checker = formatCheckers.getOrDefault(format, formatCheckers.get("default"));
        MaskingStrategy masker = maskingStrategies.getOrDefault(format, maskingStrategies.get("default"));
        if (checker.test(value)) {
            return masker.apply(value);
        }
        return value;
    }

    public static Map<String, FormatChecker> getFormatCheckers() {
        return formatCheckers;
    }

    public static Map<String, MaskingStrategy> getMaskingStrategies() {
        return maskingStrategies;
    }
}