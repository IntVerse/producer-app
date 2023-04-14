package com.intverse.producer.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
@Configuration
public class KafkaProducerConfig2 {

    @Value("${spring.kafka.ssl.truststore-location}")
    private String trustStoreLocation;

    @Value("${spring.kafka.ssl.truststore-password}")
    private String trustStorePassword;

    @Value("${spring.kafka.ssl.keystore-location}")
    private String keyStoreLocation;

    @Value("${spring.kafka.ssl.keystore-password}")
    private String keyStorePassword;

    public void sendMessage() {

        String username = "intverseuser";
        String password = "intverse123";

        Properties props = new Properties();

        // Encryption Properties
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Connection properties
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "redpanda-0.redpanda.aws.intverse.io:31092,redpanda-1.redpanda.aws.intverse.io:31092,redpanda-2.redpanda.aws.intverse.io:31092");

        // Producer properties
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, 10);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);

        // sasl properties
        props.put("security.protocol", "SASL_SSL");
        props.put("sasl.mechanism", "SCRAM-SHA-512");
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"" + username + "\" password=\"" + password + "\";");

        // ssl properties
        props.put("ssl.endpoint.identification.algorithm", "https");

        props.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, "JKS");
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustStoreLocation);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, trustStorePassword);

        props.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, "JKS");
        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keyStoreLocation);
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, keyStorePassword);

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 10; i++) {
            String key = "key-" + i;
            String value = "value-" + i;
            ProducerRecord<String, String> record = new ProducerRecord<>("intverse_test_topic", key, value);
            producer.send(record);
        }

        producer.close();

    }
}
