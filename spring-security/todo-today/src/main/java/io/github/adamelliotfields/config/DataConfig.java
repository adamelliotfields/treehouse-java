package io.github.adamelliotfields.config;

import com.mongodb.MongoClient;
import io.github.adamelliotfields.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class DataConfig {
  @Bean
  public MongoClient mongoClient() {
    return new MongoClient("localhost", 27017);
  }

  @Bean
  public MongoDbFactory mongoDbFactory() {
    return new SimpleMongoDbFactory(mongoClient(), "treehouse_spring_security");
  }

  @Bean
  public MongoOperations mongoOperations() {
    MongoOperations mongoOperations = new MongoTemplate(mongoDbFactory());

    mongoOperations.dropCollection(User.class);

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    String adamPassword = passwordEncoder.encode("password");
    String joshPassword = passwordEncoder.encode("password");

    User adam = new User("adam", adamPassword, "ROLE_USER");
    User josh = new User("josh", joshPassword, "ROLE_USER");

    mongoOperations.save(adam);
    mongoOperations.save(josh);

    return mongoOperations;
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
