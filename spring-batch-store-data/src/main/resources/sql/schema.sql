DROP TABLE IF EXISTS store;

CREATE TABLE store
(
    id              BIGINT          NOT NULL,
    store_name      VARCHAR(255)    NOT NULL,
    address         VARCHAR(255)    NOT NULL,
    road_address    VARCHAR(255)    NOT NULL,
    location        POINT           NOT NULL SRID 2079,
    PRIMARY KEY (id)
);

# DROP INDEX IDX_store_location on store;
CREATE SPATIAL INDEX IDX_store_location ON store (location);
