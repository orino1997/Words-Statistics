package com.javarush;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;


public class Main {

    public static void main(String[] args) throws Throwable{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filePath = reader.readLine();
        if(!filePath.matches("^(?:[\\w]\\:|\\\\)(\\\\[a-z_\\-\\s0-9\\.]+)+\\.(txt|gif|pdf|doc|docx|xls|xlsx)$")
                || !filePath.matches("/\\.\\.?\\/[^\\n\"?:*<>|]+\\.[A-z0-9]+/g")) {
        } else{
            throw new PathVerificationException("Check if you path is correct. You can use both: absolute path or relative path");
        }
        File file = new File(filePath);
        reader.close();

        FileReader fileReader = new FileReader(file);
        int fileSize = (int) file.length();
        if (fileSize == 0) {
            throw new FileEmptyException("Your file is empty");
        }
        char[] buffer = new char[fileSize];
        fileReader.read(buffer);
        reader.close();

        String wholeFileLine1 = new String(buffer);
        String lowerCaseLine = wholeFileLine1.toLowerCase().trim();

        if(lowerCaseLine.matches("[0-9]+")) {
            throw new IncorrectDataException("You have figures, not words");
        }

        List<String> arrayOfWords = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(lowerCaseLine, " \t\n\r,.?!:;()'\"");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            arrayOfWords.add(token);
        }

        Map<String, Integer> statistics = new TreeMap<>();
        statistics.put(arrayOfWords.get(0), 1);
        for (int i = 1; i < arrayOfWords.size(); i++) {
            if (statistics.containsKey(arrayOfWords.get(i))) {
                int value = statistics.get(arrayOfWords.get(i));
                value += 1;
                statistics.put(arrayOfWords.get(i), value);
            } else {
                statistics.put(arrayOfWords.get(i), 1);
            }
        }
        for (Map.Entry map : statistics.entrySet()) {
            System.out.println(map.getKey() + ": " + map.getValue());
        }
    }
}
