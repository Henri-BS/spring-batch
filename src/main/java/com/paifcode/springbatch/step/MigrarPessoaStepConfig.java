package com.paifcode.springbatch.step;

import com.paifcode.springbatch.domain.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MigrarPessoaStepConfig {

    @Autowired
    private StepBuilder stepBuilder;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Bean
    public Step migrarPessoaStep(
            ItemReader<Pessoa> arquivoPessoaReader,
            ItemWriter<Pessoa> bancoPessoaWriter) {

        return stepBuilder
                .<Pessoa, Pessoa>chunk(1, transactionManager)
                .reader(arquivoPessoaReader)
                .writer(bancoPessoaWriter)
                .build();

    }
}
