CREATE TABLE rate (
    rateId int NOT NULL,
    rateDescription varchar(20) NOT NULL,
    rateEffectiveDate  DATE,
    rateExpirationDate DATE,
    amount int,
    PRIMARY KEY (rateId)
);