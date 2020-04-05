package com.training.springbatch.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.training.springbatch.entity.User;
import com.training.springbatch.processor.UserProcessor;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

	@Autowired
	public DataSource dataSource;

	@Autowired
	public EntityManagerFactory entityManagerFactory;

	@Value("classpath:/user.xlsx")
	private Resource[] userInput;

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public FlatFileItemReader<User> reader() {
		FlatFileItemReader<User> reader = new FlatFileItemReader<User>();
		reader.setResource(new ClassPathResource("user.csv"));
		reader.setLineMapper(new DefaultLineMapper<User>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "uname", "salary", "gender", "age", "pan","aadhar"});
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
					{
						setTargetType(User.class);
					}
				});

			}
		});

		return reader;
	}

	@Bean
	public JdbcBatchItemWriter<User> writerJdfc() {
		JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<User>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
		writer.setSql(
				"INSERT INTO User(uname,salary,gender,age,pan,aadhar) VALUES (:uname,:salary,:gender,:age,:pan,:aadhar)");
		writer.setDataSource(dataSource);

		return writer;
	}

	@Bean
	public UserProcessor processor() {
		return new UserProcessor();
	}

	@Bean
	public JpaItemWriter<User> jpaWriter() {
		JpaItemWriter<User> jpaWriter = new JpaItemWriter<User>();
		jpaWriter.setEntityManagerFactory(entityManagerFactory);
		return jpaWriter;
	}

	@Bean
	public Job readUserCSV() {
		return jobBuilderFactory.get("Job").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<User, User>chunk(1).reader(reader()).processor(processor())
				.writer(writerJdfc()).build();
	}
}
