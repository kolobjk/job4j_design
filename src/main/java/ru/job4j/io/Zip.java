package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sources) {
                zip.putNextEntry(new ZipEntry(source.getCanonicalPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        /*new Zip().packSingleFile(
                new File("./chapter_005/pom.xml"),
                new File("./chapter_005/pom.zip")
        );*/
        ArgsName argsName =  ArgsName.of(args);
        if (argsName.get("d") == null) {
            throw new IllegalArgumentException("The input argument directory is null");
        }
        if (argsName.get("e") == null) {
            throw new IllegalArgumentException("The input argument exclude is null");
        }
        if (argsName.get("o") == null) {
            throw new IllegalArgumentException("The input argument output  is null");
        }
        File target = new File(argsName.get("d"));
        if (!target.exists()) {
            throw new IllegalArgumentException("The input argument directory is not exist");
        }
        if (!target.isDirectory()) {
            throw new IllegalArgumentException("The input argument directory is not directory");
        }
        ZipVisitor zipVisitor = new ZipVisitor();
        zipVisitor.setExclude(argsName.get("e"));
        Files.walkFileTree(Path.of(argsName.get("d")), zipVisitor);
        new Zip().packFiles(zipVisitor.getFilesList(), new File(argsName.get("o")));
    }
}