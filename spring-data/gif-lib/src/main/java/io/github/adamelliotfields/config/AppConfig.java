// package io.github.adamelliotfields.config;
//
// import com.mongodb.MongoClient;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.mongodb.MongoDbFactory;
// import org.springframework.data.mongodb.core.MongoOperations;
// import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
// import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
// import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
//
// @Configuration
// public class AppConfig {
//   @Bean
//   public MongoOperations mongoOperations() {
//     MongoClient mongoClient = new MongoClient("localhost", 27017);
//     MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongoClient, "gif_lib");
//
//     return new MongoTemplate(mongoDbFactory);
//   }
//
//   @Bean
//   public ValidatingMongoEventListener validatingMongoEventListener() {
//     LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
//
//     return new ValidatingMongoEventListener(localValidatorFactoryBean);
//   }
// }
