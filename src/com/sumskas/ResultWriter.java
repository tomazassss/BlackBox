package com.sumskas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by IrateWarlock on 2017-12-24.
 */
public class ResultWriter {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "#timestamp,char,count,rand";
    private static final String FILE_NAME = "black_box_results.csv";
    private static final int RANDOM_UPPER_BOUND = 100;

    private final String outputDir;
    private final Random random;

    public ResultWriter(final String outputDir) {
        this.outputDir = outputDir;
        this.random = new Random();
    }

    public void outputToCsv(HashMap<String, Integer> charCounts) {

        try(FileWriter fileWriter = new FileWriter(outputDir + File.separator +  FILE_NAME))
        {
            fileWriter.append(FILE_HEADER.toString());
            fileWriter.append(NEW_LINE_SEPARATOR);

            for (Map.Entry<String, Integer> entry: charCounts.entrySet())
            {
                fileWriter.append(String.valueOf(System.currentTimeMillis()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(convertToAsciiChar(entry.getKey()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(entry.getValue()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(random.nextInt(RANDOM_UPPER_BOUND)));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private String convertToAsciiChar(String key)
    {
        int asciiChar = key.charAt(0);
        return String.valueOf(asciiChar);
    }
}
