package com.example.LoanApplicationUsingSpringBatch.config;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.LoanApplicationUsingSpringBatch.entity.Loan;
import com.example.LoanApplicationUsingSpringBatch.entity.User;
import com.example.LoanApplicationUsingSpringBatch.service.LoanService;


@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	@Autowired
	public EntityManagerFactory entityManagerFactory;

	@Autowired
	public LoanService lser;

	@Bean
	public FlatFileItemReader<User> reader() {
		FlatFileItemReader<User> reader = new FlatFileItemReader<User>();
		reader.setResource(new ClassPathResource("users.csv"));
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
		//reader.setLinesToSkip(1);

		return reader;
	}

	@Bean
	public JdbcBatchItemWriter<User> writer() {
		JdbcBatchItemWriter<User> writer = new JdbcBatchItemWriter<User>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
		writer.setSql(
				"INSERT INTO User(uname,salary,gender,age,pan,aadhar) VALUES (:uname,:salary,:gender,:age,:pan,:aadhar)");
		writer.setDataSource(dataSource);

		return writer;
	}

	@Bean
	public JpaItemWriter<User> jpaWriter() {
		JpaItemWriter<User> jpaWriter = new JpaItemWriter<User>();
		jpaWriter.setEntityManagerFactory(entityManagerFactory);
		return jpaWriter;
	}
	
	@Bean
	public ItemReader<? extends User> readLoanData() {
		Loan loan = lser.fetchLoanDetails(10);
		if (loan!=null) {
				Integer curLoanAmount	=   loan.getCurrentLoanAmount();
				Integer currentInstCount =	loan.getInstallmentCount();
				Double emi =loan.getEmiAmt();		
			}
		return (ItemReader<? extends User>) loan;
	}
	
	/*
	 * @Bean public JpaItemWriter<User> LoanWriter() {
	 * lser.updateLoanData(curLoanAmount,currentInstCount,emi); return jpaWriter; }
	 */
	
	
	
	@Bean
	public Job readUserCSVFile() {
		return jobBuilderFactory.get("Job1").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<User, User>chunk(1).reader(reader()).writer(writer()).build();
	}
	
	@Bean
	public Job updateEMI() {
		return jobBuilderFactory.get("Job2").incrementer(new RunIdIncrementer()).flow(step2()).end().build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").<User, User>chunk(1).reader(readLoanData()).writer(writer()).build();
	}
}
