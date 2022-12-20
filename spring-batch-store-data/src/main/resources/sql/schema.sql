DROP TABLE IF EXISTS rest_area_store;

CREATE TABLE rest_area_store
(
    id          BIGINT          NOT NULL,
    store_name  VARCHAR(255)    NOT NULL,
    location    POINT           NOT NULL,
    PRIMARY KEY (id)
);

CREATE SPATIAL INDEX IDX_rest_area_store_location ON rest_area_store (location);
