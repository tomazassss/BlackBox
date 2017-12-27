package com.sumskas;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        String inputFile = args[0];
        String outputDir = args[1];

        if(inputFile.isEmpty())
        {
            System.out.println("Input file argument is empty");
            return;
        }

        if(outputDir.isEmpty())
        {
            System.out.println("Output dir argument is empty");
            return;
        }

        FileProcessor processor = new FileProcessor(inputFile);
        HashMap<String, Integer> charCounts = (HashMap<String, Integer>) processor.countFileChars();

        ResultWriter outputWriter = new ResultWriter(outputDir);
        outputWriter.outputToCsv(charCounts);
    }
}
