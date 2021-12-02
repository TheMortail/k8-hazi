package eu.pontsystems.hazi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableCassandraRepositories
public class HaziApplication {

    public static void main(String[] args) {
        SpringApplication.run(HaziApplication.class, args);
    }
}
