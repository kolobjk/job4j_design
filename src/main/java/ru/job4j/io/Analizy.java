package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            String thisLine;
            String firstTime = null;
            while ((thisLine = read.readLine()) != null) {
                String[] splitLine = thisLine.split(" ", 2);
                if (Integer.parseInt(splitLine[0]) != 200) {
                    if (firstTime == null) {
                        firstTime = splitLine[1];
                    }
                } else {
                    if (firstTime != null) {
                        result.add(firstTime + ";" + splitLine[1]);
                        firstTime = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target, true))) {
            for (String in : result) {
                out.println(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/un.txt", "./data/out.txt");
    }
}