package com.intverse.producer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Properties;

import static org.apache.kafka.common.config.SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG;

@Service
@Configuration
public class KafkaProducerConfig2 {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.topic}")
    private String intverseTopic;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.batch-size}")
    private String batchSize;

    @Value("${spring.kafka.producer.retries}")
    private String retries;

    @Value("${spring.kafka.producer.linger-ms}")
    private String lingerMs;

    @Value("${spring.kafka.properties.sasl.mechanism}")
    private String mechanism;

    @Value("${spring.kafka.properties.sasl.username}")
    private String username;

    @Value("${spring.kafka.properties.sasl.password}")
    private String password;

    @Value("${spring.kafka.properties.security.protocol}")
    private String securityProtocol;


    @Value("${spring.kafka.ssl.type}")
    private String sslType;

    @Value("${spring.kafka.ssl.algorithm}")
    private String algorithm;

    @Value("${spring.kafka.ssl.client-auth}")
    private String clientAuth;

    @Value("${spring.kafka.ssl.truststore-location}")
    private String trustStoreLocation;

    @Value("${spring.kafka.ssl.truststore-password}")
    private String trustStorePassword;

    public void sendMessage() {


        Properties props = new Properties();

        // Encryption Properties
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Connection properties
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Producer properties
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);

        // sasl properties
        props.put("security.protocol", securityProtocol);
        props.put("sasl.mechanism", mechanism);
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required " +
                "username=\"" + username + "\" password=\"" + password + "\";");

        // ssl properties
        props.put(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, algorithm);
        props.put("ssl.client.auth",clientAuth);
        props.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, sslType);
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreLocation);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, trustStorePassword);

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 10; i++) {
            String key = "key-" + i;
            String value = "value-" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>(intverseTopic, key, value);
            producer.send(record);
        }

        producer.close();

    }
}
