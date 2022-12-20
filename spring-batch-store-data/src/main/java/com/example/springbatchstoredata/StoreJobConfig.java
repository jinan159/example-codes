package com.example.springbatchstoredata;

import com.example.springbatchstoredata.io.reader.CsvItemReader;
import com.example.springbatchstoredata.resource.BatchCsvResourceHolder;
import java.util.List;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class StoreJobConfig {

    private static final int PARSE_AND_VALIDATION_STEP_CHUNK_SIZE = 1000;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final BatchCsvResourceHolder<Store> resourceHolder;
    private final ResourceLoader resourceLoader;

    private int wroteCount;


    @Bean
    public Job storeJob() {
        return jobBuilderFactory.get("storeJob")
            .incrementer(new RunIdIncrementer())
            .start(storeInsertStep())
            .build();
    }

    @Bean
    public Step storeInsertStep() {
        return stepBuilderFactory.get("storeInsertStep")
            .<Store, Store>chunk(PARSE_AND_VALIDATION_STEP_CHUNK_SIZE)
            .reader(itemReader())
            .processor(filterInvalidItemProcessor())
            .writer(itemWriter())
            .listener(itemReadLoggingListener())
            .listener(itemWriteLoggingListener())
            .build();
    }

    private ItemReadListener<? super Store> itemReadLoggingListener() {
        return new ItemLoggingListener<>();
    }

    private ItemWriteListener<? super Store> itemWriteLoggingListener() {
        return new ItemLoggingListener<>();
    }

    private ItemReader<Store> itemReader() {
        return new CsvItemReader<>(
            resourceLoader.getResource(resourceHolder.getResourceLocation()),
            resourceHolder.getEncoding(),
            resourceHolder.getLineMapper()
        );
    }

    private ItemProcessor<Store, Store> filterInvalidItemProcessor() {
        return item -> {
            if (item.getId() == null) {
                return null;
            }
            return item;
        };
    }

    private ItemWriter<Store> itemWriter() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("INSERT INTO store (")
            .append("id")
            .append(", store_name")
            .append(", address")
            .append(", road_address")
            .append(", location")
            .append(") VALUES (")
            .append(":id")
            .append(", :storeName")
            .append(", :address")
            .append(", :roadAddress")
            .append(", ST_PointFromText('POINT(:x :y)', 2079)")
            .append(");");

        return new JdbcBatchItemWriter<>() {{
            setDataSource(dataSource);
            setSql(sqlBuilder.toString());
            setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
            afterPropertiesSet();
        }};
    }

    private class ItemLoggingListener<I, O> extends ItemListenerSupport<I, O> implements ItemReadListener<I>, ItemWriteListener<O> {

        @Override
        public void afterRead(I item) {
            if (log.isDebugEnabled()) log.debug("afterRead : {}", item);
        }

        @Override
        public void onReadError(Exception ex) {
            log.error("onReadError : {}", ex.getMessage());
        }

        @Override
        public void afterWrite(List<? extends O> items) {
            int size = items.size();
            wroteCount += size;
            log.info("afterWrite : {}, {}", size, wroteCount);
        }

        @Override
        public void onWriteError(Exception exception, List<? extends O> items) {
            String itemsString = items.stream()
                .map(Object::toString)
                .reduce((item, nested) -> nested + ", " + item)
                .orElse("");
            log.error("onWriteError : [{}], {}", itemsString, exception.getMessage());
        }
    }
}
