package org.apache.struts.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Themes {

    private static Map<String, ThemeDescriptor> themes;

    static {
        themes = new HashMap<>();
        themes.put("simple", new ThemeDescriptor("simple", "Simple Theme", "themes/simple"));
        themes.put("extended", new ThemeDescriptor("extended", "Extended Theme", "themes/extended"));
    }

    public static ThemeDescriptor get(String id) {
        return themes.get(id);
    }

    public static boolean contains(String id) {
        return themes.containsKey(id);
    }

    public static Map<String, ThemeDescriptor> list() {
        return Collections.unmodifiableMap(themes);
    }
}
