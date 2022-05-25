CREATE TABLE Referee (
    ID int NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL,
    PhoneNumber varchar(15),
    birthday DATE
);

CREATE TABLE Users (
    username varchar(16) NOT NULL PRIMARY KEY,
    password varchar(16) NOT NULL,
	UserID int NOT NULL,
	FOREIGN KEY(UserID) REFERENCES Referee(ID)
);

CREATE TABLE Team (
    team_id int NOT NULL PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE Game (
    ID int NOT NULL PRIMARY KEY,
    home_team NOT NULL REFERENCES Team(name),
    guest_team NOT NULL REFERENCES Team(name),
    game_date DATE NOT NULL
);


INSERT INTO referee(ID, name, PhoneNumber, birthday)
VALUES ('12321', 'Doug Judy', '1-800-222-0451', '26/06/1994');