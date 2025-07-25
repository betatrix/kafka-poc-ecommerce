package org.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Map;
import java.util.regex.Pattern;

public class LogService {
    public static void main(String[] args) {
        var logService = new LogService();
        try (var service = new KafkaService<>(
                Pattern.compile("ECOMMERCE.*"),
                logService::parse,
                LogService.class.getSimpleName(),
                String.class,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("-----------------------------------------");
        System.out.println("New logs found");
        System.out.println("Topic - " + record.topic());
        System.out.println("Key - " + record.key());
        System.out.println("Value - " + record.value());
        System.out.println("Partition - " + record.partition());
        System.out.println("Offset - " + record.offset());
    }
}
