package io.github.adamelliotfields.config;

import com.mongodb.MongoClient;
import java.util.Collection;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class DataConfig extends AbstractMongoConfiguration {
  @Override
  protected String getDatabaseName() {
    return "treehouse_spring_data";
  }

  @Override
  protected Collection<String> getMappingBasePackages() {
    return Collections.singleton("io.github.adamelliotfields.entity");
  }

  @Override
  @Bean
  public MongoClient mongo() {
    final String host = "localhost";
    final int port = 27017;
    return new MongoClient(host, port);
  }

  @Override
  @Bean
  public MongoDbFactory mongoDbFactory() throws Exception {
    return new SimpleMongoDbFactory(mongo(), getDatabaseName());
  }

  @Override
  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    return new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
  }

  @Bean
  public GridFsTemplate gridFsTemplate() throws Exception {
    return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
  }

  @Bean
  public LocalValidatorFactoryBean localValidator() {
    return new LocalValidatorFactoryBean();
  }

  @Bean
  public ValidatingMongoEventListener mongoEventListener() {
    return new ValidatingMongoEventListener(localValidator());
  }
}
