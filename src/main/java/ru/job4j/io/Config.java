package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values.putAll(read.lines()
                    .map(v -> {
                        int sub = v.indexOf('#');
                        v = v.substring(0,  sub == -1 ? v.length() : sub);
                        return v;
                    })
                    .filter(v -> !v.equals(""))
                    .map(s -> s.split("=", 2))
                    .collect(Collectors.toMap(a -> a[0].trim(), a -> a.length > 1 ? a[1].trim() : null)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkMap();
    }

    public void checkMap() throws IllegalArgumentException {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null || entry.getKey().equals("") || entry.getValue().equals("")) {
                throw new IllegalArgumentException("Key or Value is null");
            }
        }
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public String value(String key) {
        return values.get(key);
    }

    public static void main(String[] args) {
        Config config = new Config("config.txt");
        config.load();
        System.out.println(config.values);
    }
}