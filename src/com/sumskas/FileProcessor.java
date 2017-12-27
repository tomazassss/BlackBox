package com.sumskas;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by IrateWarlock on 2017-12-24.
 */
public class FileProcessor {

    private final String inputFilePath;

    public FileProcessor(final String inputFile)
    {
        this.inputFilePath = inputFile;
    }

    public Map<String, Integer> countFileChars() {
        HashMap<String, Integer> result = new HashMap<>();

        System.out.println("Start processing file");

        try (Stream<String> stream = Files.lines(Paths.get(inputFilePath), Charset.forName("Cp1252"))) {

            stream.forEach(new LineProcessor(result));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Processing done. Number of results: " + result.size());

        return result;
    }

    private class LineProcessor implements Consumer<String>
    {
        private HashMap<String, Integer> charCounts;

        public LineProcessor(HashMap<String, Integer> charCounts)
        {
            this.charCounts = charCounts;
        }

        @Override
        public void accept(String line)
        {
            line.chars()
                   .mapToObj(i -> (char)i)
                   .forEach(c -> {
                       String charRep = c.toString();

                       if(charCounts.containsKey(charRep)){
                           charCounts.put(charRep, charCounts.get(charRep)+ 1);
                       }else{
                           charCounts.put(charRep, 1);
                       }
                   });
        }


    }
}
