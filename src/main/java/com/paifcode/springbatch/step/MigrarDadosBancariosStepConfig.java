package com.paifcode.springbatch.step;

import com.paifcode.springbatch.domain.DadosBancarios;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MigrarDadosBancariosStepConfig {

    private StepBuilder stepBuilder;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Bean
    public Step migrarDadosBancariosStep(
            ItemReader<DadosBancarios> arquivoDadosBancariosReader,
            ItemWriter<DadosBancarios> bancoDadosBancariosWriter) {

        return stepBuilder
                .<DadosBancarios, DadosBancarios>chunk(1, transactionManager)
                .reader(arquivoDadosBancariosReader)
                .writer(bancoDadosBancariosWriter)
                .build();
    }
}
