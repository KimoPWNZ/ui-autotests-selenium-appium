package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties PROPS = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) throw new IllegalStateException("config.properties not found in classpath");
            PROPS.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    private Config() {}

    public static String get(String key) {
        String v = PROPS.getProperty(key);
        if (v == null) throw new IllegalArgumentException("Missing config key: " + key);
        return v.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }
}