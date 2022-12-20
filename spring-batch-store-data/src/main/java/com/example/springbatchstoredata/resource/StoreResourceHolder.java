package com.example.springbatchstoredata.resource;

import com.example.springbatchstoredata.Store;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StoreResourceHolder implements BatchCsvResourceHolder<Store> {

    public static final Store INVALID_STORE = new Store();

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
    public LineMapper<Store> getLineMapper() {
        return new DefaultLineMapper<>() {{
            setFieldSetMapper(fieldSet -> {
                try {
                    return new Store(
                        fieldSet.readLong("번호"),
                        fieldSet.readString("사업장명"),
                        fieldSet.readString("소재지전체주소"),
                        fieldSet.readString("도로명전체주소"),
                        fieldSet.readDouble("좌표정보(x)"),
                        fieldSet.readDouble("좌표정보(y)"));
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
