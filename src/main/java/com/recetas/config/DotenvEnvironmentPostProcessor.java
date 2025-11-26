package com.recetas.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

public class DotenvEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
    private static final String PROPERTY_SOURCE_NAME = "dotenvProperties";
    private int order = Ordered.HIGHEST_PRECEDENCE + 10;

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        File dotenv = new File(".env");
        if (!dotenv.exists() || !dotenv.isFile()) {
            return;
        }

        Map<String, Object> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dotenv))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int idx = line.indexOf('=');
                if (idx <= 0) continue;
                String key = line.substring(0, idx).trim();
                String value = line.substring(idx + 1).trim();
                // remove surrounding quotes
                if ((value.startsWith("\"") && value.endsWith("\"")) || (value.startsWith("'") && value.endsWith("'"))) {
                    value = value.substring(1, value.length() - 1);
                }
                if (key.isEmpty()) continue;
                map.put(key, value);
                // Also add relaxed property name (e.g. JWT_SECRET -> jwt.secret)
                String relaxed = key.toLowerCase().replace('_', '.');
                if (!map.containsKey(relaxed)) {
                    map.put(relaxed, value);
                }
            }
        } catch (IOException e) {
            // ignore - do not fail startup here; StartupValidator will check required props later
        }

        if (!map.isEmpty()) {
            MapPropertySource ps = new MapPropertySource(PROPERTY_SOURCE_NAME, map);
            environment.getPropertySources().addFirst(ps);
        }
    }

    @Override
    public int getOrder() {
        return order;
    }

}
