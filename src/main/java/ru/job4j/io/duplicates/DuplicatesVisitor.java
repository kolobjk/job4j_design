package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final HashMap<FileProperty, List<String>> filesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().length(), file.getFileName().toString());
        if (filesMap.get(fileProperty) == null) {
            List<String> paths = new ArrayList<>();
            paths.add(file.toString());
            filesMap.put(fileProperty, paths);
        } else {
            filesMap.get(fileProperty).add(file.toString());
        }
        return super.visitFile(file, attrs);
    }

    public HashMap<FileProperty, List<String>> getFilesMap() {
        return filesMap;
    }
}