package io.github.adamelliotfields.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class DataConfig {
  @Bean
  public MongoClient mongoClient() {
    return new MongoClient("localhost", 27017);
  }

  @Bean
  public MongoDbFactory mongoDbFactory() {
    return new SimpleMongoDbFactory(mongoClient(), "treehouse_spring_data");
  }

  @Bean
  public MongoOperations mongoOperations() {
    return new MongoTemplate(mongoDbFactory());
  }

  @Bean
  public LocalValidatorFactoryBean localValidatorFactoryBean() {
    return new LocalValidatorFactoryBean();
  }

  @Bean
  public ValidatingMongoEventListener mongoEventListener() {
    return new ValidatingMongoEventListener(localValidatorFactoryBean());
  }
}
