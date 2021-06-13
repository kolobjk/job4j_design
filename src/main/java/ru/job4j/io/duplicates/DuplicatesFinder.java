package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("C:\\Users\\aleksandr.khvostov\\IdeaProjects\\job4j_design\\src\\main\\java\\ru\\job4j"), duplicatesVisitor);
        for (Map.Entry<FileProperty, List<String>> entry : duplicatesVisitor.getFilesMap().entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("The file name " + entry.getKey().getName()
                        + " and the size is " + entry.getKey().getSize()
                        + " paths is " + entry.getValue().toString());
            }
        }
    }
}