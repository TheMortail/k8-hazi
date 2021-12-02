package eu.pontsystems.hazi.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ad{
    @PrimaryKey
    private UUID uid;
    private String name;
    private String description;
}
