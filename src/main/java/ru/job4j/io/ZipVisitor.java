package ru.job4j.io;

import ru.job4j.io.duplicates.FileProperty;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ZipVisitor extends SimpleFileVisitor<Path> {
    private final List<File> filesList = new ArrayList<>();
    private String exclude;

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!file.toString().endsWith(exclude)) {
            filesList.add(file.toFile());
        }
        return super.visitFile(file, attrs);
    }

    public List<File> getFilesList() {
        return filesList;
    }
}