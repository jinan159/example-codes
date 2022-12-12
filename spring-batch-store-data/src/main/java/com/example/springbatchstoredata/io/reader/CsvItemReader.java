package com.example.springbatchstoredata.io.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.separator.RecordSeparatorPolicy;
import org.springframework.core.io.Resource;

public class CsvItemReader<T> implements ItemReader<T> {

    private final ItemReader<T> reader;

    public CsvItemReader(Resource resource, String encoding, LineMapper<T> lineMapper) {
        this.reader = initializeItemReader(resource, encoding ,lineMapper);
    }

    private ItemReader<T> initializeItemReader(Resource resource, String encoding, LineMapper<T> lineMapper) {
        return new FlatFileItemReader<>() {{
            setLinesToSkip(1);
            setResource(resource);
            setEncoding(encoding);
            setRecordSeparatorPolicy(blankOrEmptySeparatorPolicy());
            setLineMapper(lineMapper);
            try {
                doOpen();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }};
    }

    @Override
    public T read() throws Exception {
        return reader.read();
    }

    private RecordSeparatorPolicy blankOrEmptySeparatorPolicy() {
        return new RecordSeparatorPolicy() {

            @Override
            public boolean isEndOfRecord(String record) {
                return record.trim().length() != 0;
            }

            @Override
            public String postProcess(String record) {
                if (record.trim().length() == 0) {
                    return null;
                }
                return record;
            }

            @Override
            public String preProcess(String record) {
                return record;
            }
        };
    }
}
