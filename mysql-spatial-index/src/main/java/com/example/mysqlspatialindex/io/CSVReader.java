package com.example.mysqlspatialindex.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader implements Closeable {

    private final BufferedReader reader;
    private final String[] keys;

    public CSVReader(String path) {
        this.reader = createReader(path);
        this.keys = readCsvRow();
    }

    private BufferedReader createReader(String path) {
        try {
            return new BufferedReader(new FileReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> read() {

        String[] line = readCsvRow();

        Map<String, String> row = new HashMap<>();

        for (int i = 0; i < line.length; i++) {
            row.put(keys[i], line[i]);
        }

        return row;
    }

    private String[] readCsvRow() {

        String line;
        try {
            line = reader.readLine();

            if (line == null) {
                throw new IllegalStateException("line is empty or null.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return line.split(",");
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
