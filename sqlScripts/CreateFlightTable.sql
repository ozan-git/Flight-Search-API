CREATE TABLE Flights
(
    FlightID         INT AUTO_INCREMENT,
    DepartureAirport VARCHAR(100),
    ArrivalAirport   VARCHAR(100),
    DepartureTime    DATETIME,
    ArrivalTime      DATETIME,
    Price            DECIMAL(10, 2),
    PRIMARY KEY (FlightID)
);
