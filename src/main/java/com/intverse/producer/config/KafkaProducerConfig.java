package com.intverse.producer.config;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

//    @Value("${spring.kafka.bootstrap-servers}")
//    private String bootstrapServers;
//
//    @Value("${spring.kafka.producer.acks}")
//    private String acks;
//
//    @Value("${spring.kafka.producer.retries}")
//    private int retries;
//
//    @Value("${spring.kafka.producer.batch-size}")
//    private int batchSize;
//
//    @Value("${spring.kafka.producer.linger-ms}")
//    private int lingerMs;
//
//    @Value("${spring.kafka.producer.buffer-memory}")
//    private int bufferMemory;
//
//    @Value("${spring.kafka.ssl.truststore.location}")
//    private String trustStoreLocation;
//
//    @Value("${spring.kafka.ssl.truststore.password}")
//    private String trustStorePassword;
//
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ProducerConfig.ACKS_CONFIG, acks);
//        props.put(ProducerConfig.RETRIES_CONFIG, retries);
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
//        props.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreLocation);
//        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, trustStorePassword);
//        props.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
//        props.put("security.protocol", "SASL_PLAINTEXT");
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @PostConstruct
//    public void configureSystemSecurity(){
//        System.setProperty("java.security.auth.login.config","src/main/resources/jaas.config");
//    }
}