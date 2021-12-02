CREATE KEYSPACE hazi WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

CREATE TABLE hazi.ad(
    uid uuid PRIMARY KEY,
    name text,
    description text
);
