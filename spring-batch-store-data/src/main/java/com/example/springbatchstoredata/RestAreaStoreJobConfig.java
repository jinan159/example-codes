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
public class RestAreaStoreJobConfig {

    private static final int PARSE_AND_VALIDATION_STEP_CHUNK_SIZE = 1000;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;
    private final BatchCsvResourceHolder<RestAreaStore> resourceHolder;
    private final ResourceLoader resourceLoader;

    private int wroteCount;


    @Bean
    public Job restAreaStoreJob() {
        return jobBuilderFactory.get("restAreaStoreJob")
            .incrementer(new RunIdIncrementer())
            .start(storeInsertStep())
            .build();
    }

    @Bean
    public Step storeInsertStep() {
        return stepBuilderFactory.get("storeInsertStep")
            .<RestAreaStore, RestAreaStore>chunk(PARSE_AND_VALIDATION_STEP_CHUNK_SIZE)
            .reader(itemReader())
            .processor(filterInvalidItemProcessor())
            .writer(itemWriter())
            .listener(itemReadListener())
            .listener(itemWriteListener())
            .build();
    }

    private ItemReader<RestAreaStore> itemReader() {
        return new CsvItemReader<>(
            resourceLoader.getResource(resourceHolder.getResourceLocation()),
            resourceHolder.getEncoding(),
            resourceHolder.getLineMapper()
        );
    }

    private ItemProcessor<RestAreaStore, RestAreaStore> filterInvalidItemProcessor() {
        return item -> {
            if (item.getId() == null) {
                return null;
            }
            return item;
        };
    }

    private ItemWriter<RestAreaStore> itemWriter() {
        return new JdbcBatchItemWriter<>() {{
            setDataSource(dataSource);
            setSql("INSERT INTO rest_area_store (id, store_name, location) values (:id, :storeName, POINT(:x, :y))");
            setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
            afterPropertiesSet();
        }};
    }

    private ItemReadListener<RestAreaStore> itemReadListener() {
        return new ItemReadListener<>() {

            @Override
            public void beforeRead() {

            }

            @Override
            public void afterRead(RestAreaStore item) {
                if (log.isDebugEnabled()) log.debug("ItemReadListener : {}", item);
            }

            @Override
            public void onReadError(Exception ex) {
                log.error("ItemReadListener : {}", ex.getMessage());
            }
        };
    }

    private ItemWriteListener<RestAreaStore> itemWriteListener() {
        return new ItemWriteListener<>() {
            @Override
            public void beforeWrite(List<? extends RestAreaStore> items) {
                int size = items.size();
                wroteCount += size;
                log.info("itemWriteListener : {}, {}", size, wroteCount);
            }

            @Override
            public void afterWrite(List<? extends RestAreaStore> items) {

            }

            @Override
            public void onWriteError(Exception exception, List<? extends RestAreaStore> items) {
                log.error("ItemWriteListener : {}, {}", items, exception.getMessage());
            }
        };
    }
}
