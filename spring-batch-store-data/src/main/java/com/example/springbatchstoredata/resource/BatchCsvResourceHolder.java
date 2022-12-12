package com.example.springbatchstoredata.resource;

import org.springframework.batch.item.file.LineMapper;

public interface BatchCsvResourceHolder<T> extends BatchResourceHolder {

    LineMapper<T> getLineMapper();
}
