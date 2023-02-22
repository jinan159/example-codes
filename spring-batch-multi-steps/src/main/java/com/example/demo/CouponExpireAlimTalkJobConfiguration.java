package com.example.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouponExpireAlimTalkJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final AtomicInteger readerCounter = new AtomicInteger(0);

    public CouponExpireAlimTalkJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean(name = "CouponExpireAlimTalkJob")
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step())
                .build();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<String, String>chunk(1)
                .reader(reader(null, null))
                .processor(processor(null))
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<String> reader(@Value("#{jobParameters[executionDateTime]}") String executionDateTime,
                                     @Value("#{jobParameters[couponRemainDays]}") String couponRemainDays) {
        var executionDate = LocalDateTime.parse(executionDateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toLocalDate();

        return () -> {
            if (readerCounter.getAndIncrement() == 0) {
                return "#{executionDate} #{couponRemainDays}일"
                    .replace("#{executionDate}", executionDate.toString())
                    .replace("#{couponRemainDays}", couponRemainDays);
            }

            return null;
        };
    }

    @Bean
    @StepScope
    public ItemProcessor<String, String> processor(@Value("#{jobParameters[couponRemainDays]}") String couponRemainDays) {
        return str -> str + ", processor #{couponRemainDays}일".replace("#{couponRemainDays}", couponRemainDays);
    }

    public ItemWriter<String> writer() {
        return strings -> {
            System.out.println("=======================================================");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -\n");
            for (String str : strings) {
                System.out.println(str);
            }
            System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("=======================================================");
        };
    }
}
