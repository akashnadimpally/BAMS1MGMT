USE Bnk;
-- DROP Table Users;
CREATE TABLE IF NOT EXISTS Users(
    MemberID VARCHAR(255) NOT NULL,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    Nationality VARCHAR(255) NOT NULL,
    MobileNumber DOUBLE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    SSN VARCHAR(255) NOT NULL,
    DateOfBirth DATE NOT NULL,
    PRIMARY KEY (MemberID),
    UNIQUE (Email),
    UNIQUE (SSN),
    UNIQUE (MobileNumber)
);