package eu.pontsystems.hazi.persistence.repository;

import eu.pontsystems.hazi.persistence.entity.Ad;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface AdsRepository extends CassandraRepository<Ad, UUID> {

}
