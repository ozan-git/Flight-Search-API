CREATE TABLE flights
(
    flight_id         INT AUTO_INCREMENT,
    departure_airport VARCHAR(100),
    arrival_airport   VARCHAR(100),
    departure_time    DATETIME,
    arrival_time      DATETIME,
    price             DECIMAL(10, 2),
    PRIMARY KEY (flight_id)
);
