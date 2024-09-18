package com.paifcode.springbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class MigracaoDadosJobConfig {

    @Autowired
    private JobBuilder jobBuilder;

    @Bean
    public Job migracaoDadosJob(
          @Qualifier("migrarPessoasStep") Step migracaoPessoaStep,
          @Qualifier("migrarDadosBancariosStep") Step migracaoDadosBancariosStep) {
        return jobBuilder
                .start(migracaoPessoaStep)
                .next(migracaoDadosBancariosStep)
                .incrementer(new RunIdIncrementer())
                .build();

    }
}
