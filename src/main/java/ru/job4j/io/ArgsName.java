package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The input argument is null");
        }
        values.putAll(Arrays.stream(args)
                .filter(v -> !v.equals(""))
                .map(s -> s.split("=", 2))
                .filter(v -> {
                    if (v[0] == null || v[0].trim().substring(1).equals("") || v[0] == null || v[1].trim().equals("")) {
                        throw new IllegalArgumentException("Key or Value is null");
                    }
                    return true;
                })
                .collect(Collectors.toMap(a -> a[0].trim().substring(1), a -> a.length > 1 ? a[1].trim() : null)));
    }

    public void checkMap() throws IllegalArgumentException {
        for (Map.Entry<String, String> entry : values.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null || entry.getKey().equals("") || entry.getValue().equals("")) {
                throw new IllegalArgumentException("Key or Value is null");
            }
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}