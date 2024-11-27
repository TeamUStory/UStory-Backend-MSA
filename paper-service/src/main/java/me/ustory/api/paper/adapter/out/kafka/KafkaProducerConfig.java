package me.ustory.api.paper.adapter.out.kafka;

import me.ustory.api.common.kafka.CreatePaperNotificationKafkaDTO;
import me.ustory.api.common.kafka.UnlockPaperNotificationKafkaDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, CreatePaperNotificationKafkaDTO> createPaperProducerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, CreatePaperNotificationKafkaDTO> createPaperKafkaTemplate() {
        return new KafkaTemplate<>(createPaperProducerFactory());
    }

    @Bean
    public ProducerFactory<String, UnlockPaperNotificationKafkaDTO> unlockPaperProducerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, UnlockPaperNotificationKafkaDTO> unlockPaperKafkaTemplate() {
        return new KafkaTemplate<>(unlockPaperProducerFactory());
    }
}
