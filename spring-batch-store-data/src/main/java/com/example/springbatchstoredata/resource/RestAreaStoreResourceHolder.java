package com.example.springbatchstoredata.resource;

import com.example.springbatchstoredata.RestAreaStore;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestAreaStoreResourceHolder implements BatchCsvResourceHolder<RestAreaStore> {

    public static final RestAreaStore INVALID_STORE = new RestAreaStore();

    @Value("${batch.resource.location}")
    private String location;

    @Value("${batch.resource.encoding}")
    private String encoding;

    @Value("${batch.resource.headers}")
    private String[] headers;

    @Override
    public String getResourceLocation() {
        return location;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    @Override
    public LineMapper<RestAreaStore> getLineMapper() {
        return new DefaultLineMapper<>() {{
            setFieldSetMapper(fieldSet -> {
                try {
                    return new RestAreaStore(
                        fieldSet.readLong("번호"),
                        fieldSet.readString("사업장명"),
                        fieldSet.readDouble("좌표정보(x)"),
                        fieldSet.readDouble("좌표정보(y)")
                    );
                } catch (Exception e) {
                    return INVALID_STORE;
                }
            });

            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(DELIMITER_COMMA);
                setNames(headers);
            }});
        }};
    }
}
