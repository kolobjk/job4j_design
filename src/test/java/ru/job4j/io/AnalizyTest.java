package ru.job4j.io;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailableTest() throws IOException {
        Analizy analizy = new Analizy();
        String expected1 = "10:57:01;10:59:01";
        String expected2 = "11:01:02;11:02:02";
        int expectedLength = 2;
        List<String> inputList = new ArrayList<>();
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("200 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            String thisLine;
            while ((thisLine = in.readLine()) != null) {
                inputList.add(thisLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expected1, inputList.get(0));
        Assert.assertEquals(expected2, inputList.get(1));
        Assert.assertEquals(expectedLength, inputList.size());
    }
}