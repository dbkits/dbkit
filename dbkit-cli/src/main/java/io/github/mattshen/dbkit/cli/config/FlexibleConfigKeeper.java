package io.github.mattshen.dbkit.cli.config;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class FlexibleConfigKeeper {

    private Map<String, Object> config = Collections.unmodifiableMap(Collections.emptyMap());

    private FlexibleConfigKeeper() {
    }

    private static class SingletonHelper {
        private static final FlexibleConfigKeeper INSTANCE = new FlexibleConfigKeeper();
    }

    public static FlexibleConfigKeeper getInstance() {
        return FlexibleConfigKeeper.SingletonHelper.INSTANCE;
    }

    public Map<String, Object> getConfig() {
        return config;
    }

    public void setConfig(Map<String, Object> config) {
        this.config = config;
    }

    /*
     * Get value from a nested Map using dot-notation-like syntax,
     * e.g. ["profile", "mysql", "datasource.username"]
     * @param path dot notation path
     * @return one of the three: null, simple value or partial config
     */
    public Object getValue(String... path) {

        Object unwrapped = config;
        return Stream.of((Object[]) path).reduce(unwrapped, (Object value, Object key) -> {
            if (value instanceof Map) {
                return ((Map) value).get(key);
            } else {
                return null;
            }
        });

    }

}
