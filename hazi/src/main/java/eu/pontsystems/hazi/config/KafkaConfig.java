package eu.pontsystems.hazi.config;

import eu.pontsystems.hazi.dto.AdDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    String kafkaServer;

    @Bean
    public ConsumerFactory<String, AdDto> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, AdDto.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    KafkaListenerContainerFactory <ConcurrentMessageListenerContainer<String, AdDto>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AdDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ProducerFactory<String, AdDto> producerFactory(){
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
        configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configurations);
    }

    @Bean
    public KafkaTemplate<String, AdDto> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }





//    @Bean
//    public ConsumerFactory<String, AdDto> consumerFactory() {
//        System.out.println("Kafka Server :" + kafkaServer);
//        JsonDeserializer<AdDto> deserializer = new JsonDeserializer<>(AdDto.class);
//        deserializer.setRemoveTypeHeaders(false);
//        deserializer.addTrustedPackages("*");
//        deserializer.setUseTypeMapperForKey(true);
//
//        Map<String, Object> configurations = new HashMap<>();
//        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
//        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
//        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
//        configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
//
//        return new DefaultKafkaConsumerFactory<>(configurations, new StringDeserializer(), deserializer);
//    }

//    @Bean
//    public ConsumerFactory<String, Object> consumerFactory(@Value("${spring.kafka.bootstrap-servers}") String kafkaBootstrapServers){
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
//        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
//        typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
//        typeMapper.addTrustedPackages("eu.pontsystems.*");
//
//        Map<String, Class<?>> mappings = new HashMap<>();
//        mappings.put("eu.pontsystems.hazi.Dto.AdDto;", AdDto.class);
//
//        typeMapper.setIdClassMapping(mappings);
//
//        JsonDeserializer deserializer = new JsonDeserializer<>();
//        deserializer.typeMapper(typeMapper);
//
//
//        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), deserializer);
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory(ConsumerFactory<String, Object> consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        return factory;
//    }
//
//}
//
//    @Autowired
//    private KafkaProperties kafkaProperties;
//
//    @Value("${tpd.topic-name}")
//    private String topicName;
//
//    // Producer configuration
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props =
//                new HashMap<>(kafkaProperties.buildProducerProperties());
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
//                StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
//                JsonSerializer.class);
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<String, Object> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public NewTopic adviceTopic() {
//        return new NewTopic(topicName, 3, (short) 1);
//    }
//
//

}
